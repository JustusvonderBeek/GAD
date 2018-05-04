package Blatt3;

public class TestingArrays {

	private static RingQueue ring;
	private static DynamicArray dynamic;
	private static StackyQueue stack;
	private static DynamicStack dstack;
	
	public static void main(String[] args) {
		ring = new RingQueue(2, 3);
		dynamic = new DynamicArray(2, 3);
		stack = new StackyQueue(2, 3);
		dstack = new DynamicStack(2, 3);
		
		// Testing DynamicArray
		
		NonEmptyInterval interval = new NonEmptyInterval(0, 0);
//		dynamic.reportUsage(interval, 2);
//		dynamic.set(0, 5);
//		dynamic.set(1, 3);
//		System.out.println("Dynamic: " + dynamic);
//		interval = new NonEmptyInterval(0, 1);
//		dynamic.reportUsage(interval, 4);
//		System.out.println("Dynamic: " + dynamic);
//		dynamic.set(2, -15);
//		System.out.println("Dynamic: " + dynamic);
//		interval = new NonEmptyInterval(0, 1);
//		dynamic.reportUsage(interval, 1);
//		System.out.println("Dynamic: " + dynamic);
//		dynamic.reportUsage(interval, 5);
//		System.out.println("Dynamic: " + dynamic);
		
		// Insertion
		
		adding(7);
		deleting(7);
		adding(10);
		deleting(5);
		adding(2);
		deleting(7);
		
		System.out.println("Alle Tests erfolgreich!");
	}
	
	public static void adding(int itterations) {
		for (int i = 0; i < itterations; i++) {
			int value = 1 + (int)(Math.random()*30);
			System.out.println("Einzufügender Wert: " + value);
			ring.enqueue(value);
			stack.enqueue(value);
			dstack.pushBack(value);
			ArraysToString("Hinzufügen");
		}
	}
	
	public static void deleting (int itterations) {
		for (int i = 0; i < itterations; i++) {
			ring.dequeue();
			stack.dequeue();
			dstack.popBack();
			ArraysToString("Entfernen");
		}
	}
	
	
	public static void ArraysToString(String s) {
		System.out.println("Datenstrukturen: " + s);
		System.out.println("Ring: " + ring);
		System.out.println("Stack: " + stack);
//		System.out.println("Dynamic: " + dynamic);
		System.out.println("Dynamic Stack: " + dstack + "\n");
	}

}
