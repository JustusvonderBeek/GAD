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
	  int hash = key.hashCode(); // erstellen einer int Darstellung des Keys
	  String bitstring = Integer.toBinaryString(hash);
	  int hexValue = Integer.toHexString(hash).length() * 4;
	  if (bitstring.length() != hexValue) {
		  int difference = hexValue - bitstring.length();
		  String add = "";
		  for (int i = 0; i < difference; i++) {
			add += "0";
		  }
		  add += bitstring;
		  bitstring = add;
	  }
	  int w = (int) (Math.log(m)/Math.log(2));
	  int k = bitstring.length()/w;
	  int[] parts = new int[k];
	  int count = 0;
	  for (int i = 0; i < bitstring.length()-1; i+=4) {
		 String substring = bitstring.substring(i, i+4);
		 parts[count] = toInteger(substring);
		 count++;
	  }
	  System.out.println("String: " + key + "\nHash: " + hash + "\nByteValue: " + bitstring + "\nW: " + w + "\nK: " + k + "\nArray: " + printingArray(parts));
	  int[] a = new int[k];
	  for (int i = 0; i < a.length; i++) {
		a[i] = i*2+5;
	  }
	  int result = 0;
	  for (int i = 0; i < parts.length; i++) {
		result += parts[i] * a[i];
	  }
	  result %= m;
	  System.out.println("Ergebnis: " + result);
	  return result;
  }
  
  private int toInteger(String string) {
	  int result = Integer.parseInt(string.substring(0, 1))*8 + Integer.parseInt(string.substring(1, 2))*4 + Integer.parseInt(string.substring(2, 3))*2 + Integer.parseInt(string.substring(3, 4));
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
