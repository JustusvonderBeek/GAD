package Blatt5;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden,
 * Integer zu hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {
 
	private int m;
	private int k;
	private int w;
	private int[] a;

  /**
   * Dieser Konstruktor initialisiert ein {@link DoubleHashInt}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public DoubleHashInt (int size) {
	  this.m = size;
	  this.w = (int) (Math.log(size)/Math.log(2));
	  this.k = 32 / w;
	  initilizeA();
  }

  /**
   * Diese Methode berechnet h(key) für einen Integer.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  @Override public long hash (Integer key) {
    String bitstring = checkLeadingZero(key.toUnsignedString(key, 2), key);
    int[] tupel = splitIntoArray(bitstring);
    int result = 0;
    for (int i = 0; i < tupel.length; i++) {
		result += tupel[i] * a[i];
	}
    result %= m;
    return result;
  }

  /**
   * Diese Methode berechnet h'(key) für einen Integer.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  @Override public long hashTick (Integer key) {
	 return (1 + key) % m -1;
  }
  
  private String checkLeadingZero(String bitstring, Integer key) {
	  String adding = "";
	  if (bitstring.length() < 32) {
		  int difference = 32 - bitstring.length();
		  for (int i = 0; i < difference; i++) {
			adding += "0";
		  }
		  adding += bitstring;
	  } else {
		  return bitstring;
	  }
	  return adding;
  }
  
  private int[] splitIntoArray(String bitstring) {
	  int[] tupel = new int[k];
	  int lowerIndex = 0;
	  int higherIndex = w;
	  for (int i = 0; i < tupel.length; i++) {
		 String substring = bitstring.substring(lowerIndex, higherIndex);
		 tupel[i] = byteToInteger(substring);
		 lowerIndex = higherIndex;
		 higherIndex += w;
	  }
	  return tupel;
  }
  
  private int byteToInteger(String string) {
	  int result = 0;
	  int index = string.length();
	  for (int i = 0; i < string.length(); i++) {
		result += Integer.parseInt(string.substring(index-1, index))*Math.pow(2, i);
		index--;
	  }
	  return result;
  }
  
  public String printingDebug() {
	  String result = "Size: " + this.m + "\n";
	  result += "K: " + this.k + "\n";
	  result += "W: " + this.w + "\n";
	  result += "A: ";
	  for (int i = 0; i < a.length; i++) {
		result += a[i] + " | ";
	  }
	  result += "\n";
	  return result;
	  
  }
  
  private void initilizeA() {
	  this.a = new int[k];
	  for (int i = 0; i < a.length; i++) {
		this.a[i] =  ((int) (Math.random()*m) + 1) % m;
	  }
  }

}
