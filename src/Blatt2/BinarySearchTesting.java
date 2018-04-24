package Blatt2;

public class BinarySearchTesting {

	public static void main(String[] args) {
		// TODO: implement testing here
		int[] sortedData = {-33, -5, -2, 0, 3, 7, 15, 33 ,122};
		int[] secondSortedData = {-10, 33, 50, 99, 123, 4242};
		boolean flag = true;
		int test = BinSea.search(sortedData, -4, flag);
		int test2 = BinSea.search(secondSortedData, -20, flag);
		if (flag) {
			if (test != -1) {
				System.out.println("Untere Intervallgrenze in sortedData: " + test + " Zahl: " + sortedData[test]);
			} 
			if (test2 != -1) {
				System.out.println("Untere Intervallgrenze in secondSortedData: " + test2 + " Zahl: " + secondSortedData[test2]);
			}
		} else {
			if (test != -1) {
				System.out.println("Obere Intervallgrenze in sortedData: " + test + " Zahl: " + sortedData[test]);
			}
			if (test2 != -1) {
				System.out.println("Obere Intervallgrenze in secondSortedData: " + test2 + " Zahl: " + secondSortedData[test2]);
			}
			
			
		}
		Interval interval = BinSea.search(sortedData, new NonEmptyInterval(10, 50));
		Interval interval2 = BinSea.search(secondSortedData, new NonEmptyInterval(10, 50));
		System.out.println("Gefundenes Interval:\nsortedData: " + interval.toString() + "\nsecondSortedData: " + interval2.toString());
		
	}
}
