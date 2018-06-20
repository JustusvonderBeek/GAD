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

	private void updateBalance() {								// Updated die Balance für alle Teilbäume unterhalb dieses Knotens
		if (this.left == null && this.right == null) {
			setBalance(0);
			return;
		}
		if (this.left == null) {
			setBalance(this.right.height() + 1);
			this.right.updateBalance();
			return;
		}
		if (this.right == null) {
			setBalance(-(this.left.height() + 1));
			this.left.updateBalance();
			return;
		}
		int l = this.left.height() + 1;
		int r = this.right.height() + 1;
		setBalance(r - l);
		this.left.updateBalance();
		this.right.updateBalance();
	}
	
	
	public boolean validAVL() {										// prüft auf die Korrektness im Baum
		if (getBalance() >= 2 || getBalance() <= -2) {
			System.out.println("Wrong Balance");
			return false;
		}
		if (this.left == null && this.right == null) {
			return true;
		}
		if (this.left == null) {
			if (this.right.getKey() < getKey()) {
				System.out.println("Key rechts " + this.right.getKey() + " ist kleiner als " + getKey());
				return false;
			}
			return this.right.validAVL();
		}
		if (this.right == null) {
			if (this.left.getKey() > getKey()) {
				System.out.println("Key links " + this.left.getKey() + " ist größer als " + getKey());
				return false;
			}
			return this.left.validAVL();
		}
		if (this.left.getKey() > getKey()) {
			System.out.println("Key links " + this.left.getKey() + " ist größer als " + getKey());
			return false;
		}
		if (this.right.getKey() < getKey()) {
			System.out.println("Key rechts " + this.right.getKey() + " ist kleiner als " + getKey());
			return false;
		}
		if (this.left.validAVL()) {
			return this.right.validAVL();
		}
		System.out.println("Not left right");
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
	 			updateBalance();
	 			rotate();
	 		} else {
	 			AVLTreeNode tmp = this.left.insert(key);
	 			if (tmp != this.left) {
	 				this.left = tmp;
	 			}
	 			updateBalance();
	 			AVLTreeNode thisTmp = rotate();
	 			if (thisTmp != this) {
	 				thisTmp.updateBalance();
	 				return thisTmp;
	 			} else {
	 				return this;
	 			}
	 		}
	 	}
	 	if (key > getKey()) {
	 		if (this.right == null) {
	 			this.right = new AVLTreeNode(key);
	 			updateBalance();
	 			rotate();
	 		} else {
	 			AVLTreeNode tmp = this.right.insert(key);
	 			if (tmp != this.right) {
	 				this.right = tmp;
	 			}
	 			updateBalance();
	 			AVLTreeNode thisTmp = rotate();
	 			if (thisTmp != this) {
	 				thisTmp.updateBalance();
	 				return thisTmp;
	 			} else {
	 				return this;
	 			}
	 		}
	 	}
	 	return this;
	 }
	
	 
	 private AVLTreeNode rotate() {							// Entscheidet ob rotiert werden soll oder nicht und in welche Richtung
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
		 AVLTreeNode tmp = this.right.left;
		 AVLTreeNode result = this.right;
		 this.right.left = this;
		 this.right = tmp;
		 return result;
	 }
	 
	 private AVLTreeNode rotateRight() {
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
			next = left.dotLink(sb, idx, next, "l");
		if (right != null)
			next = right.dotLink(sb, idx, next, "r");
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next, String label) {
		sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
		return dotNode(sb, next);
	};
}
