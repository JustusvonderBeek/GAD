package Blatt9;

/**
 * Diese Klasse implementiert einen AVL-Baum.
 */
public class AVLTree {
	/**
	 * Diese Variable speichert die Wurzel des Baumes.
	 */
	AVLTreeNode root = null;

	public AVLTree() {
		
	}

	/**
	 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich um einen
	 * validen AVL-Baum handelt. Dabei werden die folgenden Eigenschaften geprüft:
	 *
	 * - Die Höhe des linken Teilbaumes eines Knotens unterscheidet sich von der
	 * Höhe des rechten Teilbaumes um höchstens eins. - Die Schlüssel im linken
	 * Teilbaum eines Knotens sind kleiner als der oder gleich dem Schlüssel des
	 * Knotens. - Die Schlüssel im rechten Teilbaum eines Knotens sind größer als
	 * der Schlüssel des Knotens. - Die Balancierung jedes Knoten entspricht der
	 * Höhendifferenz der Teilbäume entsprechend der Erklärung in der Vorlesung.
	 *
	 * @return 'true' falls der Baum ein valider AVL-Baum ist, 'false' sonst
	 */
	public boolean validAVL() {
		return this.root.validAVL();
	}

	/**
	 * Diese Methode fügt einen neuen Schlüssel in den AVL-Baum ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if (this.root == null) {
			this.root = new AVLTreeNode(key);
		}
		this.root.insert(key);
	}
	
	 private void Rotate() {
		 if (getBalance() == -2) {
			 if (this.left.getBalance() <= 0) {
				 rotateOneRight();
			 } else {
				 this.left.rotateOneLeft();
				 rotateOneRight();
			 }
		 } else if (getBalance() == 2) {
			 if (this.right.getBalance() < 0) {
				 this.right.rotateOneRight();
				 rotateOneLeft();
			 } else {
				 rotateOneLeft();
			 }
		 }
	 }
	 
	 private AVLTreeNode rotateOneLeft() {		// Rotiert mit diesem Knoten als Wurzel nach Links
		 System.out.println("RotateOneLeft auf " + getKey());
		 return null;
	 }
	 
	 private AVLTreeNode rotateOneRight() {
		 System.out.println("RotateOneRight auf " + getKey());
		 return null;
	 }

	/**
	 * Diese Methode sucht einen Schlüssel im AVL-Baum.
	 *
	 * @param key der Schlüssel, der gesucht werden soll
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		if (this.root == null) {
			return false;
		}
		return this.root.find(key);
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	public String dot() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		if (root != null)
			root.dot(sb);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	@Override
	public String toString() {
		return dot();
	}

}
