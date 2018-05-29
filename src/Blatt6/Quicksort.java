package Blatt6;

public class Quicksort implements SortingBase {
	public void sort(int[] numbers) {
		quickSort(numbers, 0, numbers.length);
	}

	private void quickSort(int[] numbers, int l, int r) {
		if (l < r) {
			int p = numbers[(int) (Math.random()*numbers.length)];
			int i = l - 1;
			int j = r;
			do {
				do {i++;} while (numbers[i] < p);
				do {j++;} while (j >= l && numbers[j] > p);
				if ( i < j) swap(numbers, i, j);
			} while (i < j);
			swap(numbers, i, r);
			quickSort(numbers, l, i-1);
			quickSort(numbers, i+1, r);
		}
	}
	
	private void swap(int[] array, int l, int r) {
		int tmp = array[l];
		array[l] = array[r];
		array[r] = tmp;
	}
	
	public String getName() {
		return "Quicksort";
	}
}
