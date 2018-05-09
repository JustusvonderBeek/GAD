package Blatt4;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashString {
  private int m;
  private int w;
  private int k;
  private int[] a;
  
  /**
   * Dieser Konstruktor initialisiert ein {@link HashString}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public HashString (int size) {
    this.m = size;
    this.w = (int) (Math.log(size)/Math.log(2));		// Ausrechnen, wie viele Bits in einen Teil gehören
    this.k = 32;										// Da k abhängig von der eingehenden Bitlänge ist, können wir k hier noch garnicht wissen und ich nehme deshalb einfach das Maximum an
    initilizeA();
  }

  /**
   * Diese Methode berechnet den Hashwert für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden sollen
   * @return der Hashwert des Schlüssels
   */
  
  public int hash (String key) {
	  String bitstring= processKey(key);
	  int k = bitstring.length()/w;					// Ausrechnen der Anzahl an Teilen
	  int[] parts = new int[k];						// Erstellen des k-Tupels
	  createParts(bitstring, parts, w);				// Überführen der einzelnen Bytes in ein k-Tupel
	  int result = 0;
	  for (int i = 0; i < parts.length; i++) {
		result += parts[i] * a[i];
	  }
	  result %= m;
//	  System.out.println("String: " + key + "\nByteValue: " + bitstring + "\nW: " + w + "\nK: " + k + "\nArray: " + printingArray(parts) + "\nErgebnis: " + result + "\n-----------------------------------");
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
//	  System.out.println("Result: " + hash);
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
  
  private int[] initilizeA() {
	  a = new int[k];
	  for (int i = 0; i < a.length; i++) {
		a[i] = (int) (Math.random()*m) + 1;
	  }
	  return a;
  }
  
  // Zu Debugzwecken
  private String printingArray(int[] array) { 
	  String result = "";
	  for (int i = 0; i < array.length; i++) {
		result += array[i] + "; ";
	  }
	  return result;
  }
}
