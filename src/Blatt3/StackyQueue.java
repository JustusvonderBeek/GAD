package Blatt3;

/**
 * Die Klasse StackyQueue soll eine Warteschlange auf
 * Basis der Klasse {@link DynamicStack} implementieren. Es
 * soll ausschließlich die Klasse {@link DynamicStack} zur
 * Datenspeicherung verwendet werden.
 */
public class StackyQueue {
  private DynamicArray waiting;
  private DynamicArray working;

  private NonEmptyInterval waitingUsage;
  private NonEmtpyInterval workingUsage;
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
	waiting = new DynamicArray(growthFactor, maxOverhead);
	working = new DynamicArray(growthFactor, maxOverhead);
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
	waitingUsage = new NonEmptyInterval(waitingUsage.getFrom(), waitingUsage.getTo()+1);
	usage = waiting.reportUsage(waitingUsage, getLength());
	waiting.set(usage.getTo(), value);
	System.out.println(this);
  }
  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue () {
     length--;
	 if (workingUsage.getSize(working.getInnerLength()) == 0) {
		for (int i = 0; i < waitingUsage.getSize(waiting.getInnerLength()); i++) {
			if (waitingUsage.getSize(waiting.getInnerLength) > 0) {
				waitingUsage = new NonEmptyInterval(waitingUsage.getFrom(), waitingUsage.getTo()-1);
			} else {
				break;
			}
			int value = waiting.get(waitingUsage.getTo());
			waitingUsage = new NonEmptyInterval(waitingUsage.getFrom(), waitingUsage.getTo()-1);
			waiting.reportUsage(waitingUsage, waitingUsage.getSize(waiting.getInnerLength());
			workingUsage = new NonEmptyInterval(workingUsage.getFrom(), workingUsagegetTo());
			working.reportUsage(workingUsage, workingUsage.getSize(working.getInnterLength));
		}
	 }
	 workingUsage = working.reportUsage(workingUsage, usage.getSize(working.getInnerLength());
	 int value = working.get(usage.getTo());
	 return value;
  }
  
  public String toString() {
	  String result = "Noch nicht implementiert";
	  return result;
  }
  
}
