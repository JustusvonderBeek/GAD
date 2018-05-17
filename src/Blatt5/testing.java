package Blatt5;

public class testing {

	public static void main(String[] args) {
		
		DoubleHashInt tst = new DoubleHashInt(157);
		System.out.println(tst.printingDebug());
		
		System.out.println("555: " + tst.hash(555));
		System.out.println("555 Tick: " + tst.hashTick(555));
		
		System.out.println("555: " + tst.hash(555));
		System.out.println("555 Tick: " + tst.hashTick(555));
		
		DoubleHashString tst2 = new DoubleHashString(37);
		
		System.out.println("24684: " + tst2.hash("24684"));
		System.out.println("24684: " + tst2.hashTick("24684"));
		
		System.out.println("24684: " + tst2.hash("24684"));
		System.out.println("24684: " + tst2.hashTick("24684"));
		
		System.out.println("Test: " + tst2.hash("Test"));
		System.out.println("Test: " + tst2.hashTick("Test"));
		
		System.out.println("Test: " + tst2.hash("Test"));
		System.out.println("Test: " + tst2.hashTick("Test"));
		
		System.out.println("TesT: " + tst2.hash("TesT"));
		System.out.println("TesT: " + tst2.hashTick("TesT"));
		
		System.out.println("TesT: " + tst2.hash("TesT"));
		System.out.println("TesT: " + tst2.hashTick("TesT"));
		
		System.out.println("Test: " + tst2.hash("Test") + " " + ((2*tst2.hash("Test"))%37));
		
		
		DoubleHashTable<String, Integer> table = new DoubleHashTable<>(219, new StringHashableFactory());
		
		DoubleHashTable<Integer, Integer> table2 = new DoubleHashTable<>(133, new IntHashableFactory());
	
		table.insert("Test", 100);
		
		
		
	}

}
