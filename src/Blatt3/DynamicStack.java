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
	  length++;			// Enthält nach dem Push ein weiteres Element
	  NonEmptyInterval usage = new NonEmptyInterval(0, getLength()-1); // Gibt die benutzen Indices an
	  dynArr.reportUsage(usage, getLength());
	  dynArr.set(usage.getTo(), value);
  }

  /**
   * Diese Methode nimmt ein Element vom Stapel.
   * @return das entfernte Element
   */
  public int popBack () {
    int value = dynArr.get(getLength()-1);							  		// Entnimmt den gesuchten Wert aus dem Array
    length--;																// Verringert die Element die auf dem Stack liegen
    NonEmptyInterval usage;
    if (getLength() > 0) {
    	usage = new NonEmptyInterval(0, getLength()-1);				 		// Erzeugt ein Interval mit den Element, die in Benutzung sind
    } else {
    	usage = new NonEmptyInterval(0, getLength());
    }
    dynArr.reportUsage(usage, getLength());
    dynArr.set(getLength(), 0);
    return value;
  }
  
  public String toString() {
	  String result = dynArr + " ; Länge: " + getLength();
	  return result;
  }
}
