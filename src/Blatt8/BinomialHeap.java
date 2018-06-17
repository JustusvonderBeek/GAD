package Blatt8;

import java.util.ArrayList;

public class BinomialHeap {
	/**
	 * Dieser Konstruktor baut einen leeren Haufen.
	 */
	
	private ArrayList<BinomialTreeNode> trees;
	private BinomialTreeNode min;				// sortiert gehalten
	
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
		ArrayList<BinomialTreeNode> tmpList = new ArrayList<>();
		BinomialTreeNode tmp = new BinomialTreeNode(key);
		tmpList.add(tmp);
		merge(tmpList);
		if (key < min()) {
			this.min = tmp;
		}
	}
	
	private void merge (ArrayList<BinomialTreeNode> a) {
		for (BinomialTreeNode element : a) {
			int rg = element.rank();
			if (rg > trees.size() - 1) {
				while (trees.size() < rg) {
					trees.add(null);
				}
				trees.add(element);
			} else if (rg == trees.size() - 1) {
				if (trees.get(trees.size() - 1) == null) {
					trees.set(trees.size() - 1, element);
				} else {
					BinomialTreeNode tmp = trees.get(trees.size() - 1);
					BinomialTreeNode tmpHead = BinomialTreeNode.merge(tmp, element);
					trees.set(trees.size() - 1, null);
					trees.add(tmpHead);
				}
			} else {		// rg < trees.size() - 1
				if (trees.get(rg) == null) {
					trees.set(rg, element);
				} else {
					BinomialTreeNode tmp = trees.get(rg);
					BinomialTreeNode tmpHead = BinomialTreeNode.merge(tmp, element);
					trees.set(rg, null);
					if (trees.size() - 1 < rg + 1) {
						trees.add(tmpHead);
					} else if (trees.get(rg + 1) == null) {
						trees.set(rg + 1, tmpHead);
					} else {
						ArrayList<BinomialTreeNode> tmpList = new ArrayList<>();
						tmpList.add(tmpHead);
						merge(tmpList);
					}
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
		int i = 0;
		while (i < trees.size() - 1 && trees.get(i) != this.min) {
			i++;
		}
		BinomialTreeNode tmp = trees.get(i);
		int min = min();
		BinomialTreeNode[] tmpArray = tmp.deleteMin();
		trees.set(i, null);
		ArrayList<BinomialTreeNode> tmpList = new ArrayList<>();
		BinomialTreeNode newMin = null;
		if (tmpArray.length > 0) {
			for (int j = 0; j < tmpArray.length; j++) {
				tmpList.add(tmpArray[j]);
			}
			merge(tmpList);
		}
		i = 0;
		while (i < trees.size() && trees.get(i) == null) {
			i++;
		}
		if (i < trees.size()) {
			newMin = trees.get(i);
			for (int j = i; j < trees.size(); j++) {
				if (trees.get(j) == null) {
					continue;
				}
				if (newMin.min() > trees.get(j).min()) {
					newMin = trees.get(j);	
				}
			}
		}
		if (trees.get(trees.size() - 1) == null) {
			trees.remove(trees.size() - 1);
		}
		this.min = newMin;
		return min;
	}
}
