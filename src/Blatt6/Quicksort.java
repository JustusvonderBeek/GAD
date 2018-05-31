package Blatt6;

public class Quicksort implements SortingBase {
	public void sort(int[] numbers) {
		quickSort(numbers, 0, numbers.length-1);
	}

	private void quickSort(int[] numbers, int l, int r) {
		if (l < r) {
			int p = numbers[r];				// eine Implementierung mit dem immer rechtesten Element führt für große Zahlen immer zu einem Stackoverflow
			int lIndex = l - 1;
			int rIndex = r;
			do {
				do {lIndex++;} while (numbers[lIndex] < p);
				do {rIndex--;} while (rIndex >= l && numbers[rIndex] > p);
				if (lIndex < rIndex) swap(numbers, lIndex, rIndex);
			} while (lIndex < rIndex);
			swap(numbers, lIndex, r);
			quickSort(numbers, l, lIndex-1);
			quickSort(numbers, lIndex+1, r);
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
