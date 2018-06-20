package Blatt9;

/**
 * Diese Klasse implementiert einen Knoten in einem AVL-Baum.
 */
public class AVLTreeNode {
	/**
	 * Diese Variable enthält den Schlüssel, den der Knoten speichert.
	 */
	private int key;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * Diese Variable speichert die Balancierung des Knotens - wie in der
	 * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
	 * um eins tiefer ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
	 * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
	 * rechte Teilbaum tiefer ist.
	 */
	private int balance = 0;

	private AVLTreeNode left = null;
	private AVLTreeNode right = null;

	public AVLTreeNode(int key) {
		this.key = key;
	}

	/**
	 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
	 *
	 * @return die ermittelte Höhe
	 */
	public int height() {
		if (this.left == null && this.right == null) {
			return 0;
		}
		if (this.left == null) {
			return this.right.height() + 1;
		}
		if (this.right == null) {
			return this.left.height() + 1;
		}
		int l = this.left.height() + 1;
		int r = this.right.height() + 1;
		if (l > r) {
			return l;
		} else {
			return r;
		}
	}
	
	private int getBalance() {
		return balance;
	}

	private void setBalance(int balance) {
		this.balance = balance;
	}

	private void calcBalance() {
		if (this.left == null && this.right == null) {
			setBalance(0);
			return;
		}
		if (this.left == null) {
			setBalance(this.right.height() + 1);
			return;
		}
		if (this.right == null) {
			setBalance((-1) * (this.left.height() + 1));
			return;
		}
		int l = this.left.height() + 1;
		int r = this.right.height() + 1;
		setBalance(r - l);
	}
	
	
	public boolean validAVL() {
		if (getBalance() >= 2 || getBalance() <= -2) {
			return false;
		}
		if (this.left == null && this.right == null) {
			return true;
		}
		if (this.left == null) {
			if (this.right.getKey() < getKey()) {
				return false;
			}
			return this.right.validAVL();
		}
		if (this.right == null) {
			if (this.left.getKey() > getKey()) {
				return false;
			}
			return this.left.validAVL();
		}
		if (this.left != null && this.right != null) {
			if (this.left.getKey() > getKey()) {
				return false;
			}
			if (this.right.getKey()< getKey()) {
				return false;
			}
			if (this.left.validAVL()) {
				return this.right.validAVL();
			}
			return false;
		}
		return false;
	}

	/**
	 * Diese Methode sucht einen Schlüssel im Baum.
	 *
	 * @param key der zu suchende Schlüssel
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		if (getKey() == key) {
			return true;
		}
		if (getKey() < key) {
			if (this.left == null) {
				return false;
			} else {
				return this.left.find(key);
			}
		}
		if (getKey() > key) {
			if (this.right == null) {
				return false;
			} else {
				return this.right.find(key);
			}
		}
		return false;
	}

	 public void insert(int key) {
	 	if(key <= getKey()) {
	 		if (this.left == null) {
	 			this.left = new AVLTreeNode(key);
	 			calcBalance();
	 			return;
	 		} else {
	 			this.left.insert(key);
	 			calcBalance();
	 		}
	 	}
	 	if (key > getKey()) {
	 		if (this.right == null) {
	 			this.right = new AVLTreeNode(key);
	 			calcBalance();
	 			return;
	 		} else {
	 			this.right.insert(key);
	 			calcBalance();
	 		}
	 	}
	 }

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @param sb
	 *          der StringBuilder für die Ausgabe
	 */
	public void dot(StringBuilder sb) {
		dotNode(sb, 0);
	}

	private int dotNode(StringBuilder sb, int idx) {
		sb.append(String.format("\t%d [label=\"%d, b=%d\"];\n", idx, key, balance));
		int next = idx + 1;
		if (left != null)
			next = left.dotLink(sb, idx, next, "links");
		if (right != null)
			next = right.dotLink(sb, idx, next, "rechts");
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next, String label) {
		sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
		return dotNode(sb, next);
	};
}
