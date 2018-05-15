package Blatt5;

import java.util.Random;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden,
 * Integer zu hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {
 
	int m;
	int k;
	int w;
	int[] a;

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
	  this.k = 32;
	  initilizeA();
  }

  /**
   * Diese Methode berechnet h(key) für einen Integer.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  @Override public long hash (Integer key) {
    int hashCode = key.hashCode();
    
  }

  /**
   * Diese Methode berechnet h'(key) für einen Integer.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  @Override public long hashTick (Integer key) {
    /*
     * Todo
     */
  }
  
  private String printingDebug() {
	  String result = "Size: " + this.m + "\n";
	  result += "K: " + this.k + "\n";
	  result += "W: " + this.w + "\n";
	  result += "A: ";
	  for (int i = 0; i < a.length; i++) {
		result += i + ": " + a[i] + "; ";
	  }
	  result += "\n";
	  return result;
	  
  }
  
  private void initilizeA() {
	  this.a = new int[k];
	  for (int i = 0; i < a.length; i++) {
		this.a[i] = (int) (Math.random()*m) + 1;
	  }
  }

}
