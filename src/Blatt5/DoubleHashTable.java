package Blatt5;


import java.util.ArrayList;
import java.util.Optional;

/**
 * Die Klasse DoubleHashTable implementiert eine Hashtabelle, die doppeltes
 * Hashing verwendet.
 *
 * @param <K> der Typ der Schlüssel, die in der Hashtabelle gespeichert werden
 * @param <V> der Typ der Werte, die in der Hashtabelle gespeichert werden
 */
public class DoubleHashTable<K, V> {
  
	private Pair<K, V>[] storage;
	
	private DoubleHashInt intHash;
	private DoubleHashString stringHash;
	
	private int localI;
	
	
  /**
   * Diese Methode implementiert h(x, i).
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @param i der Index, der angibt, der wievielte Hash für den gegebenen Schlüssel
   * berechnet werden soll
   * @return der generierte Hash
   */
  private int hash(K key, int i) {
	 int hFlat = 0;
	 int hTick = 0;
	 if (key instanceof Integer) {
		 hFlat = (int) intHash.hash((Integer) key);
		 hTick = (int) intHash.hashTick((Integer) key);				// Berechnet h und h' für die eingegebenen Werte und unterscheidet dabei zwischen Integern und Strings.
	 } else if (key instanceof String) {
		 hFlat = (int) stringHash.hash((String) key);
		 hTick = (int) stringHash.hashTick((String) key);
	 }
//	 System.out.println(" HFlat: " + hFlat + " HTick: " + hTick + " Result: " + ((hFlat + (i * hTick)) % storage.length));
	 return (hFlat + (i * hTick)) % storage.length;
  }

  /**
   * Dieser Konstruktor initialisiert die Hashtabelle.
   * 
   * @param primeSize die Größe 'm' der Hashtabelle; es kann davon ausgegangen
   * werden, dass es sich um eine Primzahl handelt.
   * @param hashableFactory Fabrik, die aus einer Größe ein DoubleHashable<K>-Objekt erzeugt.
   */
  public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
	  intHash = new DoubleHashInt(primeSize);
	  stringHash = new DoubleHashString(primeSize);
	  storage = new Pair[primeSize];
	  this.valueCollision = 0;
	  this.valueRehash = 0;
	  this.zaehler = 0;					// Nur für Debugzwecke benutzt.
	  this.localI = 0;					// Zählt die lokalen I mit für die Methoden insert und find
  }

  /**
   * Diese Methode fügt entsprechend des doppelten Hashens ein Element
   * in die Hashtabelle ein.
   * 
   * @param k der Schlüssel des Elements, das eingefügt wird
   * @param v der Wert des Elements, das eingefügt wird
   * @return 'true' falls das einfügen erfolgreich war, 'false' falls die
   * Hashtabelle voll ist.
   */
  
  /*
   * Da die Methode alte Werte überschreiben können soll, muss sie zunächst das ganze Feld nach möglichen alten Werten durchsuchen um diese zu ersetzen.
   * Wird ein alter Wert gefunden, dann wird dieser Überschrieben, sollte keiner gefunden werden, dann wird an der ersten freien stelle der Wert eingefügt.
   */
  
  public boolean insert(K k, V v) {
	  int i = 0;
	  int index;
	  if (find(k) != Optional.empty()) {
		  index = hash(k, this.localI);
//		  System.out.println("Altes Feld: " + storage[index] + " wird zu neuem : " + new Pair<>(k, v));
		  storage[index] = new Pair<>(k, v);
		  if (valueRehash < this.localI) {
			  valueRehash = this.localI;
		  }
		  if (localI > 1) {
			  valueCollision++;
		  }
		  return true;
	  } else {
		  while (i < storage.length) {
				 index = hash(k, i++);
				 if (storage[index] == null) {
					 storage[index] = new Pair<K, V>(k, v);
					 if (this.valueRehash < i) {
						  this.valueRehash = i;
					  }
					  if (i > 1) {
						  valueCollision++;
					  }
					  return true;
				 }
			  }
	  }
	  return false;
  }

  /**
   * Diese Methode sucht ein Element anhand seines Schlüssels in der Hashtabelle
   * und gibt den zugehörigen Wert zurück, falls der Schlüssel gefunden wurde.
   * 
   * @param k der Schlüssel des Elements, nach dem gesucht wird
   * @return der Wert des zugehörigen Elements, sonfern es gefunden wurde
   */
  
  private int zaehler;	// Zu Debugzwecken
  
  /*
   * Die Methode geht alle Werte von i für einen Schlüssel k durch, so lange bis entweder der Wert gefunden wurde, oder das Feld einmal komplett durchlaufen wurde
   * Sollte dann kein Wert gefunden worden sein, wird Optional.empty zurückgegeben, sonst der Value an der Stelle.
   */
  
  public Optional<V> find(K k) {
	  int i = 0;
	  int valueHash;
	  Pair<K, V> value;
	  while (i < storage.length) {
		  valueHash = hash(k, i++);
//		  System.out.println("Iteration: " + zaehler + " Hash Index: " + valueHash + " mit " + storage[valueHash]);
		  if (storage[valueHash] != null) {
			  value = storage[valueHash];
			  if (k.getClass() == String.class) {
				  if (storage[valueHash]._1.equals(k)) {
					  this.localI = i-1;
					  return (Optional<V>) Optional.of(value._2);
				  }
			  } else if (k.getClass() == Integer.class) {
				  if (Integer.compare((Integer) storage[valueHash]._1, (Integer) k) == 0) {
					  this.localI = i-1;
					  return (Optional<V>) Optional.of(value._2);
				  }
			  }
		  }
//		  zaehler++;
	  }
	  return Optional.empty();
	  
  }
  
  
  /**
   * Diese Methode ermittelt die Anzahl der Kollisionen, also die Anzahl
   * der Elemente, die nicht an der 'optimalen' Position in die Hashtabelle eingefügt
   * werden konnten. Die optimale Position ist diejenige Position, die der
   * erste Aufruf der Hashfunktion (i = 0) bestimmt.
   * 
   * @return die Anzahl der Kollisionen
   */
  
  private int valueCollision;
  
  public int collisions() {
	  return this.valueCollision;
  }
 
  /**
   * Diese Methode berechnet die maximale Anzahl von Aufrufen der Hashfunktion,
   * die nötig waren, um ein einziges Element in die Hashtabelle einzufügen.
   * 
   * @return die berechnete Maximalzahl von Aufrufen
   */
  
  private int valueRehash;
  
  public int maxRehashes() {
	  return this.valueRehash;
  }
  
  
  /*
   * Debugmethode
   */
  
  public String toString() {
	  String result = "";
	  for (int i = 0; i < storage.length; i++) {
		if (storage[i] != null) {
			result += i + ": " + storage[i] + "\n";
		}
	  }
	  return result;
  }
}
