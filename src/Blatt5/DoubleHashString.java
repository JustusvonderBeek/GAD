package Blatt5;


import java.util.ArrayList;

/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class DoubleHashString implements DoubleHashable<String> {
  
	private int m;
	private int w;
	private int k;
	private ArrayList<Integer> a;

  /**
   * Dieser Konstruktor initialisiert ein {@link DoubleHashString}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public DoubleHashString (int size) {
    this.m = size;
    this.w = (int)(Math.log(m)/Math.log(2));
	this.k = 32 / w;
	initilizeA();
  }
  
  /**
   * Diese Methode berechnet h(key) für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  public long hash (String key) {
	  String bitstring= processKey(key);
	  int k = bitstring.length()/w;					// Ausrechnen der Anzahl an Teilen
	  int[] parts = new int[k];						// Erstellen des k-Tupels
	  createParts(bitstring, parts, w);				// Überführen der einzelnen Bytes in ein k-Tupel
	  long result = 0;
	  for (int i = 0; i < parts.length; i++) {
		result += parts[i] * a.get(i);
	  }
	  result %= m;
	  return result;
  }
  
  private String processKey(String key) {
	  String hash = Integer.toBinaryString(key.hashCode()); 			// Erstellen eines Integerwertes aus dem String Schlüssel
	  int hexValue = Integer.toHexString(key.hashCode()).length() * 4; 	// Vergleichswert zum Auffüllen mit Nullen
	  if (hash.length() != hexValue) {
		  int difference = hexValue - hash.length();
		  String add = "";
		  for (int i = 0; i < difference; i++) {
			add += "0";													// Einfädeln von führenden Nullen solange, bis die Differenz ausgeglichen ist
		  }
		  add += hash;													// Anhängen des ursprünglichen Strings, sodass eine Korrekte Bitdarstellung gewährleistet ist
		  hash = add;													// Ändern der Referenz
	  }
	  return hash;
  }
  
  private void createParts(String string, int[] parts, int w) {
	  int lowerIndex = 0;
	  int higherIndex = w;
	  for (int i = 0; i < parts.length; i++) {
		 String substring = string.substring(lowerIndex, higherIndex);	// Erzeugen von Substrings
		 parts[i] = byteToInteger(substring);							// Einfügen der vorher berechneten w Bitwerte in das Array
		 lowerIndex = higherIndex;
		 higherIndex += w;
	  }
  }
  
  private int byteToInteger(String string) {
	  int result = 0;
	  int index = string.length();
	  for (int i = 0; i < string.length(); i++) {
		result += Integer.parseInt(string.substring(index-1, index))*Math.pow(2, i);	// Die Umsetzung des Binärsystems; zur Implementierung: da der String die Elemente "falsch" herum hält, beginne ich beim obersten Element und arbeite mich nach unten durch
		index--;
	  }
	  return result;
  }
  
  /**
   * Diese Methode berechnet h'(key) für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  public long hashTick (String key) {
	  long hash = (1 + key.hashCode()) % (m - 2);
	  if (hash < 0) {
		  hash *= -1;
	  }
	  return hash;
  }
  
  private void initilizeA() {
	  this.a = new ArrayList<>();
	  for (int i = 0; i < k; i++) {
		a.add(((int)(Math.random()*m) + 1) % m);
	  }
  }
}
