package Blatt8;

public class BinomialTreeNode {
	
	private int key;
	private BinomialTreeNode rightChild;
	private BinomialTreeNode leftChild;
	private BinomialTreeNode parent;
	
	public BinomialTreeNode(int key) {
		this.key = key;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */
	
	public int min() {
		BinomialTreeNode tmp = this;
		while (tmp.parent != null) {
			tmp = tmp.parent;
		}
		return tmp.key;
	}

	/**
	 * Gibt den Rang des Teilbaumes zurück.
	 *
	 * @return der Rang des Teilbaumes
	 */
	public int rank() {
		int counter = 0;
		BinomialTreeNode tmp = this;
		while (tmp.parent != null) {
			tmp = tmp.parent;
			counter++;
		}
		return counter;
	}

	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * Teilbäumen zurück, in die der aktuelle Baum zerfällt, wenn man
	 * den Knoten entfernt.
	 *
	 * @return die Menge von Teilbäumen
	 */
	public BinomialTreeNode[] deleteMin() {
		BinomialTreeNode tmp = this;
		while (tmp.parent != null) {
			tmp = tmp.parent;
		}
		BinomialTreeNode[] result = new BinomialTreeNode[2];
		result[0] = tmp.leftChild;
		result[1] = tmp.rightChild;
		return result;
	}

	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a der erste Baum
	 * @param b der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */
	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		return null;
	}
}
