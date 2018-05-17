package Blatt5;

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
		 hTick = (int) intHash.hashTick((Integer) key);
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
  
  public boolean insert(K k, V v) {
	  int i = 0;
	  int index = hash(k, i++);
	  while (i < storage.length && storage[index] != null) {
		 index = hash(k, i++);
	  }
	  if (this.valueRehash < i) {
		  this.valueRehash = i;
	  }
	  if (i > 1) {
		  valueCollision++;
	  }
	  if (storage[index] == null) {
		  storage[index] = new Pair<K, V>(k, v);
		  return true;
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
  public Optional<V> find(K k) {
	  int i = 0;
	  int valueHash = findNext(k, i);
	  System.out.println("First ValueHash: " + storage[valueHash]);
	  while (true) {
		  Pair<K, V> value = storage[valueHash];
		  if (value._1.equals(k)) {
			  System.out.println("Find returns: " + value._2);
			  return (Optional<V>) Optional.of(value._2);
		  }
		  i = findNext(k, i);
		  if (i < 0) {
			  return Optional.empty();
		  }
	  }
	  
  }
  
  private int findNext(K k, int i) {
	  int valueHash = hash(k, i);
	  while (i < storage.length && storage[valueHash] == null) {
		  System.out.println("Inner Loop");
		  valueHash = hash(k, i++);
	  }
	  if (i >= storage.length) {
		  return -1;
	  }
	  System.out.println("Returns: " + (i-1));
	  return i;
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
