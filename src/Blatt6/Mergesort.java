package Blatt6;

public class Mergesort implements SortingBase {
	public void sort(int[] numbers) {
		mergeSort(numbers, 0, numbers.length);
	}

	private void mergeSort(int [] numbers, int l, int r) {
		if (l >= r) {
			return;
		}
		int m = (l + r) / 2;
		mergeSort(numbers, l, m);
		mergeSort(numbers, m+1, r);
		int k = l;					// linke Teilhälfte
		int i = m+1;				// rechte Teilhälfte
		int[] b = new int[numbers.length];
		for (int j = 0; j <= r-l; j++) {
			if(k > m) {
				b[j] = numbers[i];		// wenn die kleinere Hälfte keine Elemente mehr enthält, schreiben wir die Elemente der größeren Hälfte in das Feld
				i++;
			} else if (i > r) {
				b[j] = numbers[k];		// wenn wir über das Feld hinausgelaufen sind, schreiben wir nur noch den kleineren Teil in das Feld
				k++;
			} else if (numbers[k] <= numbers[i]) {
				b[j] = numbers[k];
				k++;
			} else {
				b[j] = numbers[i];
				i++;
			}
		}
		for (int j = 0; j < r - l; j++) {
			numbers[l+j] = b[j];
		}
		
	}
	
	public String getName() {
		return "Mergesort";
	}
}
