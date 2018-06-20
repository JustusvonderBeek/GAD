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

	 public AVLTreeNode insert(int key) {
	 	if(key <= getKey()) {
	 		if (this.left == null) {
	 			this.left = new AVLTreeNode(key);
	 			calcBalance();
//	 			rotate();
	 		} else {
	 			this.left.insert(key);
	 			calcBalance();
//	 			AVLTreeNode tmp = rotate();
//	 			if (tmp != this.left) {
//	 				this.left = tmp;
//	 			}
	 		}
	 	}
	 	if (key > getKey()) {
	 		if (this.right == null) {
	 			this.right = new AVLTreeNode(key);
	 			calcBalance();
//	 			rotate();
	 		} else {
	 			this.right.insert(key);
	 			calcBalance();
//	 			AVLTreeNode tmp = rotate();
//	 			if (tmp != this.right) {
//	 				this.right = tmp;
//	 			}
	 		}
	 	}
	 	return this;
	 }
	
	 
	 /*
	  *  Idee ist, bei Insert jeweils die Referenz des Nachfolgers zurückzugeben, bei dem das eingefügte Element vorbei gekommen ist
	  *  Mit dieser wird dann auf die Balance geprüft. Ist diese +/- 2 wird eine Rotation vollzogen und der neue Kopf nach der Rotation wird weiter gegeben
	  *  womit die Referenz im Elternobjekt geändert werden kann.
	  */
	 
	 private AVLTreeNode rotate() {
		 if (getBalance() == -2) {
			 if (this.left.getBalance() <= 0) {
				 return rotateRight();
			 } else {
				 this.left = this.left.rotateLeft();
				 return rotateRight();
			 }
		 } else if (getBalance() == 2) {
			 if (this.right.getBalance() < 0) {
				 this.right = this.right.rotateRight();
				 return rotateLeft();
			 } else {
				 return rotateLeft();
			 }
		 }
		 return this;
	 }
	 
	 private AVLTreeNode rotateLeft() {		// Rotiert mit diesem Knoten als Wurzel nach Links
		 System.out.println("RotateOneLeft auf " + getKey());
		 AVLTreeNode tmp = this.right.left;
		 AVLTreeNode result = this.right;
		 this.right.left = this;
		 this.right = tmp;
		 return result;
	 }
	 
	 private AVLTreeNode rotateRight() {
		 System.out.println("RotateOneRight auf " + getKey());
		 AVLTreeNode tmp = this.left.right;
		 AVLTreeNode result = this.left;
		 this.left.right = this;
		 this.left = tmp;
		 return result;
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
