package Blatt5;

import java.util.ArrayList;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden,
 * Integer zu hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {
 
	private int m;
	private int k;
	private int w;
	private ArrayList<Integer> a;

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
    String bitstring = checkLeadingZero(key.toUnsignedString(key, 2), key);			// Der Integer wird in Bitrepräsentation geändert und bearbeitet
    int[] tupel = splitIntoArray(bitstring);
    long result = 0;
    checkA(tupel.length);
    for (int i = 0; i < tupel.length; i++) {
		result += tupel[i] * a.get(i);												// Und mit dem erstellten k-Tupel multipliziert
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
	  int hash = (1 + key) % (m - 1);					// Die Hashfunktion h(x) = 1 + k mod m - 1
	  if (hash < 0) {									// für negative Werte wird sie hier positiv gemacht, damit der Index beim einfügen passt
		  hash *= -1;
	  }
	  return hash;
  }
  
  /*
   * Diese Methode überprüft die gegebene Zahl auf führende Nullen und fügt falls sie fehlen sollten genügend an, damit die Zahl auf Integergröße erweitert wird.
   */
  
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
  
  /*
   * Die Methode nimmt einen String mit Nullen und Einsen und teilt die Teile in ein k-Tupel auf.
   */
  
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
  
  /*
   * Die Methode nimmt ebenfalls einen String, der eine Binärzahl repäsentiert und macht daraus wieder einen Dezimalwert.
   */
  
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
	  for (int i = 0; i < a.size(); i++) {
		result += a.get(i) + " | ";
	  }
	  result += "\n";
	  return result;
	  
  }
  
  private void initilizeA() {
	  this.a = new ArrayList<>();
	  for (int i = 0; i < 32; i++) {
		a.add(((int) (Math.random()*m) + 1) % m);		// initialisiere ein k - Tupel aus zufälligen Zahlen, standartmäßig gehe ich davon aus, dass ich maximal 32 Stellen brauche.
	  }
  }
  
  private void checkA(int k) {
	  while (a.size() <= k) {
		  a.add(((int) (Math.random()*m) + 1) % m);		// Fügt neue Stellen hinzu, falls das Tupel zu klein sein sollte.
	  }
  }

}
