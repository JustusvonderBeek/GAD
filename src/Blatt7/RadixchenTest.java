package Blatt7;

import java.util.ArrayList;
import java.util.Scanner;

public class RadixchenTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> eingabe = new ArrayList<>();
		RadixSort sort = new RadixSort();

		while (scanner.hasNextInt()) {
//			System.out.println(scanner.next());
			eingabe.add(scanner.nextInt());
		}

		int i = 0;
		Integer[] sortingArray = new Integer[eingabe.size()];
		
		for (Integer integer : eingabe) {
			sortingArray[i++] = integer;
		}
		
		sort.sort(sortingArray);
		
		for (int j = 0; j < sortingArray.length; j++) {
			System.out.println(sortingArray[j]);
		}
	}
}
