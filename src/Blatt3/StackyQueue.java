package Blatt3;

/**
 * Die Klasse StackyQueue soll eine Warteschlange auf
 * Basis der Klasse {@link DynamicStack} implementieren. Es
 * soll ausschließlich die Klasse {@link DynamicStack} zur
 * Datenspeicherung verwendet werden.
 */
public class StackyQueue {
  private DynamicStack waiting;
  private DynamicStack working;

  private NonEmptyInterval waitingUsage;
  private NonEmptyInterval workingUsage;
 
  private int length;
  /**
   * Diese Methode ermittelt die Länge der Warteschlange.
   * @return die Länge der Warteschlange
   */
  public int getLength() {
    return this.length;
  }
  
  /**
   * Dieser Kontruktor initialisiert eine neue Schlange.
   * 
   * @param growthFactor
   * @param maxOverhead
   */
  public StackyQueue (int growthFactor, int maxOverhead) {
	waiting = new DynamicStack(growthFactor, maxOverhead);
	working = new DynamicStack(growthFactor, maxOverhead);
	this.length = 0;
	waitingUsage = new NonEmptyInterval(0,0);
	workingUsage = new NonEmptyInterval(0,0);
  }
  
  /**
   * Diese Methode reiht ein Element in die Schlange ein.
   * 
   * @param value der einzufügende Wert
   */
  
  public void enqueue (int value) {
    length++;
    waiting.pushBack(value);
  }
  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue () {
     length--;
	 if (working.getLength() == 0) {
		 for (int i = 0; i <= getLength(); i++) {
			int value = waiting.popBack();
			working.pushBack(value);
		}
	 }
	 int value = working.popBack();
	 return value;
  }
  
  public String toString() {
	  String result = "\t" + waiting + "\n\t" + working;
	  return result;
  }
  
}
