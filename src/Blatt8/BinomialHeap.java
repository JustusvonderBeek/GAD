package Blatt8;

import java.util.ArrayList;

public class BinomialHeap {
	/**
	 * Dieser Konstruktor baut einen leeren Haufen.
	 */
	
	private ArrayList<BinomialTreeNode> trees;
	private BinomialTreeNode min;
	
	public BinomialHeap() {
		trees = new ArrayList<>();
		min = new BinomialTreeNode(Integer.MAX_VALUE);
	}

	/**
	 * Diese Methode ermittelt das minimale Element im binomialen Haufen.
	 * Wenn es dieses nicht gibt (bei leerem Haufen), soll eine RuntimeException geworfen werden.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		if (trees.size() <= 0) {
			throw new RuntimeException("Heap is empty");
		}
		return this.min.min();
	}

	/**
	 * Diese Methode fügt einen Schlüssel in den Haufen ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		ArrayList<BinomialTreeNode> tmpA = new ArrayList<>();
		BinomialTreeNode tmp = new BinomialTreeNode(key);
		tmpA.add(tmp);
		merge(tmpA);
		if (key < this.min.min()) {
			this.min = tmp;
		}
	}
	
	private void merge (ArrayList<BinomialTreeNode> a) {
		for (BinomialTreeNode element : a) {
			int i = element.rank();
			if (trees.size() < i) {
				trees.add(element);
			} else {
				int l = 0;
				while (trees.get(l).rank() < i && trees.size() > l) {
					l++;
				}
				if (l < trees.size()) {
					BinomialTreeNode tmp = trees.get(l);
					BinomialTreeNode tmpHead = tmp.merge(tmp, element);
					if (trees.size() <= l+1) {
						trees.add(tmpHead);
					} else {
						ArrayList<BinomialTreeNode> tmpList = new ArrayList<>();
						tmpList.add(tmpHead);
						merge(tmpList);
					}
				} else {
					trees.add(element);
				}
			}
		}
	}

	/**
	 * Diese Methode entfernt das minimale Element aus dem binomialen
	 * Haufen und gibt es zurück.
	 *
	 * @return das minimale Element
	 */
	public int deleteMin() {
		if (trees.isEmpty()) {
			throw new RuntimeException("Heap is empty");
		}
		BinomialTreeNode tmp = trees.get(0);
		int min = tmp.min();
		BinomialTreeNode[] tmpArray = tmp.deleteMin();
		ArrayList<BinomialTreeNode> tmpList = new ArrayList<>();
		for (int j = 0; j < tmpArray.length; j++) {
			tmpList.add(tmpArray[j]);
		}
		merge(tmpList);
		return min;
	}
}
