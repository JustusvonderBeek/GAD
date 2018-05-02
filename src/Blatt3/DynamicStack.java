package Blatt3;

/**
 * Die Klasse DynamicStack soll einen Stapel auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class DynamicStack {
  private DynamicArray dynArr;
  
  /**
   * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
   */
  private int length;
  
  public int getLength() {
    return length;
  }
  
  public DynamicStack (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    length = 0;
  }
  
  /**
   * Diese Methode legt ein Element auf den Stapel.
   * 
   * @param value das Element, das auf den Stapel gelegt werden soll
   */
  public void pushBack (int value) {
	  NonEmptyInterval usage = new NonEmptyInterval(0, getLength()-1);
	  dynArr.reportUsage(usage, getLength());
	  dynArr.set(getLength(), value);
  }

  /**
   * Diese Methode nimmt ein Element vom Stapel.
   * @return das entfernte Element
   */
  public int popBack () {
    int value = dynArr.get(getLength()-1);
    NonEmptyInterval usage = new NonEmptyInterval(0, getLength()-1);
    dynArr.reportUsage(usage, getLength()-2);
    return value;
  }
  
  public String toString() {
	  String result = dynArr + " Länge: " + getLength();
	  return result;
  }
}
