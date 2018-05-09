package Blatt4;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashString {
  private int m;

  /**
   * Dieser Konstruktor initialisiert ein {@link HashString}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public HashString (int size) {
    this.m = size;
  }

  /**
   * Diese Methode berechnet den Hashwert für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden sollen
   * @return der Hashwert des Schlüssels
   */
  
  public int hash (String key) {
	  String bitstring= processKey(key);
	  int w = (int) (Math.log(m)/Math.log(2));		// Ausrechnen, wie viele Bits in einen Teil gehören
	  int k = bitstring.length()/w;					// Ausrechnen der Anzahl an Teilen
	  int[] parts = new int[k];						// Erstellen des k-Tupels
	  
	  // TODO: muss den Bitstring noch umdrehen, damit ich ihn einfacher bearbeiten kann. vllt auch in byteToInteger
	  createParts(bitstring, parts, w);				// Überführen der einzelnen Bytes in ein k-Tupel
	  int[] a = new int[k];
	  for (int i = 0; i < a.length; i++) {
		a[i] = i*2+5;
	  }
	  int result = 0;
	  for (int i = 0; i < parts.length; i++) {
		result += parts[i] * a[i];
	  }
	  result %= m;
	  System.out.println("String: " + key + "\nByteValue: " + bitstring + "\nW: " + w + "\nK: " + k + "\nArray: " + printingArray(parts) + "\nErgebnis: " + result);
	  return result;
  }
  
  private String processKey(String key) {
	  String hash = Integer.toBinaryString(key.hashCode()); // Erstellen eines Integerwertes aus dem String Schlüssel
	  int hexValue = Integer.toHexString(key.hashCode()).length() * 4; // Vergleichswert zum Auffüllen mit Nullen
	  if (hash.length() != hexValue) {
		  int difference = hexValue - hash.length();
		  String add = "";
		  for (int i = 0; i < difference; i++) {
			add += "0";								// Einfädeln von führenden Nullen solange, bis die Differenz ausgeglichen ist
		  }
		  add += hash;								// Anhängen des ursprünglichen Strings, sodass eine Korrekte Bitdarstellung gewährleistet ist
		  hash = add;							// Ändern der Referenz
	  }
	  System.out.println("Result: " + hash);
	  return hash;
  }
  
  private void createParts(String string, int[] parts, int w) {
	  int lowerIndex = 0;
	  int higherIndex = w;
	  System.out.println("Parts " + parts.length);
	  for (int i = 0; i < parts.length; i++) {
		 String substring = string.substring(lowerIndex, higherIndex);
		 System.out.println("Substring: " + substring);
		 parts[i] = byteToInteger(substring);
		 lowerIndex = higherIndex;
		 higherIndex += w;
	  }
  }
  
  private int byteToInteger(String string) {
	  int result = 0;
	  int index = 0;
	  for (int i = 0; i < string.length(); i++) {
		result += Integer.parseInt(string.substring(index, ++index))*Math.pow(2, i);
	  }
	  return result;
  }
  
  private String printingArray(int[] array) {
	  String result = "";
	  for (int i = 0; i < array.length; i++) {
		result += array[i] + "; ";
	  }
	  return result;
  }
}
