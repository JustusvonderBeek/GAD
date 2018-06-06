package Blatt7;

import java.util.ArrayList;

public class RadixSort {
	public int key(Integer element, int digit) throws IllegalArgumentException {
		if (element < 0) {
			throw new IllegalArgumentException();
		}
		String stringVal = element.toString();
		if (digit > stringVal.length() - 1) {
			throw new RuntimeException("Indize von Digit in Key stimmt nicht.");
		}
		String stringDig = "" + stringVal.charAt(stringVal.length() - digit - 1);
		int result = Integer.parseInt(stringDig);
		return result;
	}

	public void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
		int j = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] == null) {
				continue;
			}
			ArrayList<Integer> tmpList = buckets[i];
			for (Integer integer : tmpList) {
				elements[j++] = integer;
				System.out.println(elements[j-1]);
			}
		}
	}

	private void kSort(Integer[] elements, int digit) {
		ArrayList<Integer>[] buckets = new ArrayList[10];		// erstellt 10 Buckets für jede Ziffer von 0-9
		for (int i = 0; i < elements.length; i++) {
			if (buckets[key(elements[i], digit)] == null) {
				buckets[key(elements[i], digit)] = new ArrayList<>();
			}
			buckets[key(elements[i], digit)].add(elements[i]);
		}
		concatenate(buckets, elements);
	}

	public void sort(Integer[] elements) {
		for (int i = 0; i < 9; i++) {
			kSort(elements, i);
		}
	}
}
