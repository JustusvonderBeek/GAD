package Blatt3;

/**
 * Die Klasse RingQueue soll eine zirkuläre Warteschlange auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class RingQueue {
  private DynamicArray dynArr;
  
  private int size;
  
  private int from;
  
  private int to;
  
  public int getSize() {
    return size;
  }
  
  public boolean isEmpty() {
    return from == to;
  }
  
  /**
   * Dieser Konstruktor erzeugt eine neue Ringschlange. Ein leere
   * Ringschlange habe stets eine Größe von 0, sowie auf 0
   * gesetzte Objektvariablen to und from. 
   * 
   * @param growthFactor der Wachstumsfaktor des zugrundeliegenden
   * dynamischen Feldes
   * @param maxOverhead der maximale Overhead des zugrundeliegenden
   * dynamischen Feldes
   */
  public RingQueue (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    size = 0;
    from = 0;
    to = 0;
  }
  
  /**
   * Diese Methode reiht ein Element in die Schlange ein.
   * 
   * @param value der einzufügende Wert
   */
  public void enqueue(int value) {
	  NonEmptyInterval usage = new NonEmptyInterval(from, to);
	  usage = (NonEmptyInterval) dynArr.reportUsage(usage, ++size);
	  if (usage.getFrom() != from) {
	    	from = usage.getFrom();
	    }
	    if (usage.getTo() != to) {
	        to = usage.getTo();	
	    }
	  if (size <= 1) {
		  dynArr.set(to, value);
	  } else {
		  dynArr.set(++to, value);
	  }
  }
  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue() {
	int value = dynArr.get(from);
	dynArr.set(from, 0);
	NonEmptyInterval usage;
	if (size == 1) {
		usage = new NonEmptyInterval(from, to);
	} else {
		usage = new NonEmptyInterval(++from, to);
	}
    usage = (NonEmptyInterval) dynArr.reportUsage(usage, --size);
    if (usage.getFrom() != from) {
    	from = usage.getFrom();
    }
    if (usage.getTo() != to) {
        to = usage.getTo();	
    }
    return value;
  }
  
  public String toString() {
	  String result = dynArr + " ; From: " + from + " ; To: " + to + " ; Size: " + size;
	  return result;
  }
}
