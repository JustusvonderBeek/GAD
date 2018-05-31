
public class Mergesort implements SortingBase {
	public void sort(int[] numbers) {
		mergeSort(numbers, 0, numbers.length-1);
	}

	private void mergeSort(int [] numbers, int l, int r) {
		if (l == r) {				// Rekursionsabbruch
			return;
		}
		int middle = (l + r) / 2;			// Mittelwertsbrechnung
		mergeSort(numbers, l, middle);		// linke Teilhälfte
		mergeSort(numbers, middle+1, r);	// rechte Teilhälfte
		int lIndex = l;						// Index der linken Hälfte
		int rIndex = middle+1;				// Index der rechten Hälfte
		int[] b = new int[r-l+1];
		for (int i = 0; i <= (r-l); i++) {
			if(lIndex > middle) {
				b[i] = numbers[rIndex];		// wenn die kleinere Hälfte keine Elemente mehr enthält, schreiben wir die Elemente der größeren Hälfte in das Feld
				rIndex++;
			} else if (rIndex > r) {
				b[i] = numbers[lIndex];		// wenn wir über das Feld hinausgelaufen sind, schreiben wir nur noch den kleineren Teil in das Feld
				lIndex++;
			} else if (numbers[lIndex] <= numbers[rIndex]) {
				b[i] = numbers[lIndex];
				lIndex++;
			} else {
				b[i] = numbers[rIndex];
				rIndex++;
			}
		}
		for (int i = 0; i <= (r-l); i++) {
			numbers[l+i] = b[i];
		}
		
	}
	
	public String getName() {
		return "Mergesort";
	}
}
