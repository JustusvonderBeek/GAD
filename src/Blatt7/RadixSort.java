package Blatt7;

import java.util.ArrayList;

public class RadixSort {
	public int key(Integer element, int digit) throws IllegalArgumentException {
		if (element < 0) {
			throw new IllegalArgumentException();				// Negative Elemente werden nicht verarbeitet
		}
		String stringVal = element.toString();
		if (digit > stringVal.length() - 1) {
			return 0;
		}
		String stringDig = "" + stringVal.charAt(stringVal.length() - digit - 1);
		int result = Integer.parseInt(stringDig);
		return result;
	}

	public void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
		int j = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] == null) {							// sollte kein Element mit einer Ziffer i vorkommen
				continue;										// ist das Bucket leer und wir müssen nichts anfügen
			}
			ArrayList<Integer> tmpList = buckets[i];
			for (Integer integer : tmpList) {
				elements[j++] = integer;
			}
		}
	}

	/*
	 * Es fehlt noch, dass Zahlen kleiner als eine bestimme Anzahl an Ziffern nicht weiter sortiert werden und
	 * dass die Concatenate Methode nur immer alle Elemente neu einfügen kann, was allerdings auch nicht gewollt ist.
	 */
	
	private void kSort(Integer[] elements, int digit) {
		ArrayList<Integer>[] buckets = new ArrayList[10];		// erstellt 10 Buckets für jede Ziffer von 0-9
		for (int i = 0; i < elements.length; i++) {
			if (buckets[key(elements[i], digit)] == null) {		// initialisiert neue Buckets
				buckets[key(elements[i], digit)] = new ArrayList<>();
			}
			buckets[key(elements[i], digit)].add(elements[i]);	// hängt Element hinten an
		}
		concatenate(buckets, elements);							// schreibt die Elemente zurück in das Feld
	}

	public void sort(Integer[] elements) {
		for (int i = 0; i < 10; i++) {
			kSort(elements, i);
		}
	}
}
