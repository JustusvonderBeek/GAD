package Blatt8;

import java.util.ArrayList;

public class BinomialTreeNode {
	
	private int key;
	private ArrayList<BinomialTreeNode> children;
	
	public BinomialTreeNode(int key) {
		this.key = key;
		children = new ArrayList<>();
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */
	
	public int min() {
		return this.key;
	}

	/**
	 * Gibt den Rang des Teilbaumes zurück.
	 *
	 * @return der Rang des Teilbaumes
	 */
	public int rank() {
		return this.children.size();
	}

	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * Teilbäumen zurück, in die der aktuelle Baum zerfällt, wenn man
	 * den Knoten entfernt.
	 *
	 * @return die Menge von Teilbäumen
	 */
	public BinomialTreeNode[] deleteMin() {
		BinomialTreeNode[] tmp = new BinomialTreeNode[children.size()];
		return this.children.toArray(tmp);
	}

	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a der erste Baum
	 * @param b der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */
	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		if (a.rank() != b.rank()) {
			throw new RuntimeException("Wrong rank!");
		}
		if (a.min() > b.min()) {
			b.children.add(a);
			return b;
		} else {
			a.children.add(b);
			return a;
		}
	}
}
