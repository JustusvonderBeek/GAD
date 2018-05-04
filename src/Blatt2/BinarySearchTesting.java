package Blatt2;

public class BinarySearchTesting {

	public static void main(String[] args) {
		int[] sortedData = {-10, 33, 50, 99, 123, 4242};
		Interval testCase1 = BinSea.search(sortedData, new NonEmptyInterval(-12, -11));		// linker Rand
		System.out.println("TestCase1 [-12;-11]: " + testCase1);
		
		Interval testCase2 = BinSea.search(sortedData, new NonEmptyInterval(-50, 60));		// linker Grenze nicht enthalten
		System.out.println("TestCase2 [-50; 60]: " + testCase2);
		
		Interval testCase3 = BinSea.search(sortedData, new NonEmptyInterval(5, 60));		// Im Interval
		System.out.println("TestCase3 [5; 60]: " + testCase3);
		
		Interval testCase4 = BinSea.search(sortedData, new NonEmptyInterval(10, 5000));		// rechte Grenze nicht enthalten
		System.out.println("TestCase4 [10; 5000]: " + testCase4);
		
		Interval testCase5 = BinSea.search(sortedData, new NonEmptyInterval(5000, 6000));	// beide Grenzen nicht enthalten
		System.out.println("TestCase5 [5000; 6000]: " + testCase5);
		
		Interval testCase6 = BinSea.search(sortedData, new NonEmptyInterval(10, 5));		// Falsch herum
		System.out.println("TestCase6 [10; 5]: " + testCase6);
	}
}
