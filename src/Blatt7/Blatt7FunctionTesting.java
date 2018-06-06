package Blatt7;

import java.util.ArrayList;
import java.util.Random;

public class Blatt7FunctionTesting {

	public static void main(String[] args) {
		
		RadixSort sort = new RadixSort();
		
		Random r = new Random();
		Integer rnd = r.nextInt();
		String rndS = rnd.toString();
		System.out.println(Integer.MAX_VALUE);
		
//		System.out.println("Zahl: " + rnd);
		
//		for (int i = 0; i < rndS.length(); i++) {
//			int digit = sort.key(rnd, i);
//			System.out.println("Digit für " + i + ": " + digit);
//		}
		
//		int menge = 5;
//		int elemente = 5;
//		ArrayList<Integer>[] wrapper = new ArrayList[menge];
//		
//		for (int i = 0; i < menge; i++) {
//			ArrayList<Integer> list = new ArrayList<>();
//			for (int j = 0; j < elemente; j++) {
//				Integer tmp = r.nextInt();
//				while(tmp < 0) {
//					tmp = r.nextInt();
//				}
//				list.add(tmp);
//			}
//			wrapper[i] = list;
//		}
//		
//		Integer[] result = new Integer[menge*elemente];
////		sort.concatenate(wrapper, result);
		
		Integer[] result = new Integer[25];
		
//		for (int i = 0; i < result.length; i++) {
//			System.out.println(result[i]);
//		}
		
		for (int i = 0; i < result.length; i++) {
			Integer tmp = r.nextInt();
			while(tmp < 0) {
				tmp = r.nextInt();
			}
			result[i] = tmp;
//			System.out.println(result[i]);
		}
		
		sort.sort(result);
		
		System.out.println("-------------\nErgebnis:\n");
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		
	}

}
