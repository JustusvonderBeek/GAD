package Blatt10;


import java.util.ArrayList;

/**
 * Diese Klasse implementiert einen (a,b)-Baum.
 */
public class ABTree {
	/**
	 * Diese Variable speichert die untere Schranke des Knotengrades.
	 */
	private int a;

	/**
	 * Diese Variable speichert die obere Schranke des Knotengrades.
	 */
	private int b;

	/**
	 * Diese Klasse repräsentiert einen Knoten des Baumes. Es kann sich
	 * dabei um einen inneren Knoten oder ein Blatt handeln.
	 */
	public abstract class ABTreeNode {

		protected ABTreeInnerNode parent;

		/**
		 * Diese Methode fügt einen Schlüssel in den Baum ein.
		 *
		 * @param key
		 *            der Schlüssel, der eingefügt wird
		 * @return
		 */
		public abstract void insert(int key);

		/**
		 * Diese Methode ermittelt, ob aus dem entsprechenden Knoten gestohlen werden
		 * kann oder nicht.
		 *
		 * @return 'true' falls gestohlen werden kann, 'false' sonst
		 */
		public abstract boolean canSteal();

		/**
		 * Diese Methode sucht den Schlüssel 'key' im Teilbaum.
		 *
		 * @param key
		 *            der Schlüssel, der gesucht wird
		 * @return 'true' falls der Schlüssel vorhanden ist, 'false' sonst
		 */
		public abstract boolean find(int key);

		/**
		 * Diese Methode entfernt den Schlüssel 'key' im Teilbaum, falls es ihn gibt.
		 *
		 * @param key
		 *            der Schlüssel, der entfernt werden soll
		 * @return 'true' falls der Schlüssel gefunden und entfernt wurde, 'false' sonst
		 */
		public abstract boolean remove(int key);

		/**
		 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
		 *
		 * @return die ermittelte Höhe
		 */
		public abstract int height();

		/**
		 * Diese Methode ermittelt das Minimum im jeweiligen Teilbaum.
		 * 
		 * @return das Minimum
		 */
		public abstract Integer min();

		/**
		 * Diese Methode ermittelt das Maximum im jeweiligen Teilbaum.
		 * 
		 * @return das Maximum
		 */
		public abstract Integer max();

		/**
		 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich um einen validen
		 * (a,b)-Baum handelt.
		 *
		 * @return 'true' falls der Baum ein valider (a,b)-Baum ist, 'false' sonst
		 */
		public abstract boolean validAB(boolean root);

		/**
		 * Diese Methode wandelt den Baum in das Graphviz-Format um.
		 *
		 * @return der nächste freie Index für das Graphviz-Format
		 */
		public abstract int dot(StringBuilder sb, int from);
	}

	/**
	 * Diese Klasse repräsentiert einen inneren Knoten des Baumes.
	 */
	private class ABTreeInnerNode extends ABTreeNode {

		private ArrayList<Integer> keys;
		private ArrayList<ABTreeNode> children;

		public ABTreeInnerNode(ArrayList<Integer> keys, ArrayList<ABTreeNode> children) {
			this.keys = keys;
			this.children = children;
		}

		public ABTreeInnerNode(int key, ABTreeNode left, ABTreeNode right) {
			keys = new ArrayList<Integer>();
			children = new ArrayList<ABTree.ABTreeNode>();
			keys.add(key);
			children.add(left);
			children.add(right);
		}

		public ABTreeInnerNode(int key) {
			this(key, new ABTreeLeaf(), new ABTreeLeaf());
		}

		@Override
		public void insert(int key) {
			if (children.get(0) instanceof ABTreeLeaf) {
				int i = 0;
				while (i < keys.size() && keys.get(i) <= key) {
					i++;
				}
				if (i == keys.size()) {
					keys.add(key);
					ABTreeLeaf insert = new ABTreeLeaf();
					insert.parent = this;
					children.add(insert);
//					System.out.println("Inserted " + key);
				} else {
					keys.add(i, key);
					children.add(i, new ABTreeLeaf());
//					System.out.println("Inserted " + key);
				}
				parent = correctInsertion();
			} else {
				int i = 0;
				while (i < keys.size() && keys.get(i) < key) {
					i++;
				}
				children.get(i).insert(key);
				parent = correctInsertion();
			}
		}

		private ABTreeInnerNode correctInsertion() {
			if (this.children.size() > b) {
				int tmp = keys.size() / 2;
				if (parent == null) {
					parent = new ABTreeInnerNode(keys.get(tmp));
					((ABTreeInnerNode) parent).children = new ArrayList<ABTreeNode>();
					((ABTreeInnerNode) parent).children.add(this);
					((ABTreeInnerNode) parent).keys.remove(0);
				}
				int counter = 0;
				while (counter < parent.keys.size() && parent.keys.get(counter) < keys.get(tmp)) {
					counter++;
				}
				if (counter == parent.keys.size()) {
					parent.keys.add(keys.get(tmp));
				} else {
					parent.keys.add(counter, keys.get(tmp));
				}
				ArrayList<Integer> newKeysLeft = new ArrayList<>();
				for (int i = 0; i < tmp; i++) {
					newKeysLeft.add(keys.get(i));
				}
				ArrayList<Integer> newKeysRight = new ArrayList<>();
				for (int i = tmp + 1; i < keys.size(); i++) {
					newKeysRight.add(keys.get(i));
				}
				ArrayList<ABTreeNode> newChildrenLeft = new ArrayList<>();
				for (int i = 0; i <= tmp; i++) {
					newChildrenLeft.add(children.get(i));
				}
				ArrayList<ABTreeNode> newChildrenRight = new ArrayList<>();
				for (int i = tmp + 1; i < children.size(); i++) {
					newChildrenRight.add(children.get(i));
				}
				ABTreeInnerNode newLeftNode = new ABTreeInnerNode(newKeysLeft, newChildrenLeft);
				newLeftNode.parent = parent;
				ABTreeInnerNode newRightNode = new ABTreeInnerNode(newKeysRight, newChildrenRight);
				newRightNode.parent = parent;
				for (int i = 0; i < newChildrenLeft.size(); i++) {
					newChildrenLeft.get(i).parent = newLeftNode;
				}
				for (int i = 0; i < newChildrenRight.size(); i++) {
					newChildrenRight.get(i).parent = newRightNode;
				}
				int i = parent.children.indexOf(this);
				if (i >= parent.children.size()) {
					parent.children.add(newLeftNode);
					parent.children.add(newRightNode);
				} else {
					parent.children.set(i, newLeftNode);
					parent.children.add(i + 1, newRightNode);
				}
			}
			return parent;
		}

		@Override
		public boolean canSteal() {
			if (this.keys.size() > a) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean find(int key) {
			int i = 0;
			while (i < keys.size() && keys.get(i) < key) {
				i++;
			}
			if (i < keys.size() && keys.get(i) == key) {
				return true;
			}
			return children.get(i).find(key);
		}

		public boolean remove(int key) {
			if (children.get(0) instanceof ABTreeLeaf) {
				int i = 0;
				while (i < keys.size() && keys.get(i) < key) {
					i++;
				}
				if (i >= keys.size()) {															// Wenn das Kind durch einen Key in einem oberen Knoten bestimmt wird
					children.remove(i);
					ABTreeInnerNode tmp = searchNode(key);
					int index = 0;
					while (index < tmp.keys.size() && tmp.keys.get(index) < key) {
						index++;
					}
					tmp.keys.set(index, keys.get(keys.size() - 1));
					correctRemove();
					return true;
				} else if (keys.get(i) == key) {
//					System.out.println("Removed " + key);
					keys.remove(i);
					children.remove(i);
					correctRemove();
					return true;
				} else {
					return false;
				}
			} else {
				int i = 0;
				while (i < keys.size() && keys.get(i) < key) {
					i++;
				}
				if (children.get(i).remove(key)) {
					correctRemove();
					return true;
				}
				return false;
			}
		}

		private void correctRemove() {
			if (parent != null && children.size() < a) {
				int index = parent.children.indexOf(this);
				if (index == 0) {																// Kein linker Nachbar
					if (parent.children.get(index + 1).canSteal()) {							// Kann vom rechten Nachbarn klauen
						stealLeftNeighbor();
					} else {																	// Kann nicht klauen
						mergeNodes();
					}
				} else if (index == parent.children.size() - 1) {								// Kein rechter Nachar
					if (parent.children.get(index - 1).canSteal()) {							// Kann vom linken Nacharn klauen
						stealRightNeighbor();
					} else {																	// Kann nicht klauen
						mergeNodes();
					}
				} else {
					if (parent.children.get(index - 1).canSteal()) {							// Kann vom linken Nachbarn klauen
						stealLeftNeighbor();
					} else if (parent.children.get(index + 1).canSteal()) {						// Kann vom rechten Nachbarn klauen
						stealRightNeighbor();
					} else {																	// Kann nicht klauen und muss mergen
						mergeNodes();
					}
				}
			} else if (parent == null && children.size() < 2) {									// Wurzel
				if (children.get(0) instanceof ABTreeLeaf) {									// Wenn wir die Wurzel sind ist die Invariante immer gegeben also auch, wenn wir weniger als a Kinder haben (die Blätter sind)
					// Intentionally left Blank
				} else {																		// Wenn die Kinder keine Blätter sind und wir durch die Bedingung oben nur noch ein Kind haben, dann mergen wir
					mergeNodes();
				}
			}
		}
		
		private void stealLeftNeighbor() {
			int index = parent.children.indexOf(this);											// Der Index, an dem der aktuelle Knoten im Elternknoten ist
			ABTreeInnerNode tmpChild = (ABTreeInnerNode) parent.children.get(index - 1);		// Das linke Kind vom aktuellen Knoten aus gesehen
			ABTreeNode newLeftChild = tmpChild.children.get(tmpChild.children.size()-1);		// Das größte Kind vom rechten Nachbarn
			children.add(0, newLeftChild);														// Wird das aktuell kleinste Kind
			children.get(0).parent = this;														// Setzten die Referenz auf das Elternobjekt auf den aktuellen Knoten
			keys.add(0, parent.keys.get(index - 1));											// Hinzufügen des Keys für das jetzt neue kleinste Kind
			tmpChild.children.remove(tmpChild.children.size() - 1);								// Entfernen der Referenz auf das ehemalige größte Kind im linken Nachbarn
			parent.keys.add(index - 1, tmpChild.keys.get(tmpChild.keys.size() - 1));			// Hinzufügen der neuen Refernz auf das jetzt größte Element im linken Nachbarn
			tmpChild.keys.remove(tmpChild.keys.size() - 1);										// Entfernen des Schlüssels aus den Keys des linken Nachbarn
		}
		
		private void stealRightNeighbor() {
			int index = parent.children.indexOf(this);
			ABTreeInnerNode tmpChild = (ABTreeInnerNode) parent.children.get(index + 1);		// Der Teilknoten rechts vom aktuellen Knoten
			ABTreeNode newRightChild = tmpChild.children.get(0);								// Das kleinste Kind im rechten Teilbaum
			children.add(children.size() - 1, newRightChild);									// Ersetzt das aktuell größte Kind
			children.get(children.size()-1).parent = this;										// Setzen der Referenz auf den aktuellen Knoten
			keys.add(keys.size(), parent.keys.get(index));										// Schreibt das Element das bisher auf das größte Element im Baum verweist ans Ende der Liste
			tmpChild.children.remove(0);														// Entfernt das Kind aus dem rechten Teilbaum, das wir klauen
			parent.keys.set(index, tmpChild.keys.get(0));										// Schreibt das kleinste Element aus der rechten Liste in die Keys vom Vaterknoten
			tmpChild.keys.remove(0);															// Und entfernt es aus dem rechten Knoten
		}
		
		private void mergeNodes() {
			if (parent != null) {
				int index = parent.children.indexOf(this);
				if (index == 0) {
					mergeRight();
				} else {
					mergeLeft();
				}
			} else {
				if (children.get(0) instanceof ABTreeLeaf) {
					
				} else {
					this.parent = (ABTreeInnerNode) this.children.get(0);
				}
			}
		}
		
		private void mergeRight() {
			int index = parent.children.indexOf(this);
			ABTreeInnerNode tmpChild = (ABTreeInnerNode) parent.children.get(index + 1);	// Rechtes Kind
			for (int i = 0; i < keys.size(); i++) {
				children.get(i).parent = tmpChild;
				tmpChild.keys.add(i, keys.get(i));
				tmpChild.children.add(i, children.get(i));
			}
			parent.children.remove(index);
			parent.keys.remove(index);
		}
		
		private void mergeLeft() {
			int index = parent.children.indexOf(this);
			ABTreeInnerNode tmpChild = (ABTreeInnerNode) parent.children.get(index - 1);	// Links Kind
			for (int i = 0; i < keys.size(); i++) {
				children.get(i).parent = tmpChild;
				tmpChild.keys.add(keys.get(i));
				tmpChild.children.add(children.get(i));
			}
			parent.children.remove(index);
			parent.keys.remove(index);
		}
		
		private ABTreeInnerNode searchNode (int key) {
			int index = 0;
			while (index < keys.size() && keys.get(index) < key) {
				index++;
			}
			if (index == keys.size()) {
				return this.parent.searchNode(key);
			} else if (keys.get(index) == key) {
				return this;
			}
			return null;
		}

		@Override
		public int height() {
			if (children.get(0) instanceof ABTreeLeaf) {
				return 1;
			} else {
				return children.get(0).height() + 1;
			}
		}

		@Override
		public Integer min() {
			if (children.get(0) instanceof ABTreeLeaf) {
				return keys.get(0);
			} else {
				return this.children.get(0).min();
			}
		}

		@Override
		public Integer max() {
			if (children.get(0) instanceof ABTreeLeaf) {
				return keys.get(keys.size() - 1);
			} else {
				return this.children.get(children.size() - 1).max();
			}
		}

		@Override
		public boolean validAB(boolean root) {
			if (root) {
				if ((parent == null && this.children.size() <= b)
						|| (parent != null && this.children.size() <= b && this.children.size() >= a)) {
					int tmpKey = keys.get(0);
					for (int i = 1; i < keys.size(); i++) { // Testet ob die Schlüssel sortiert sind
						if (keys.get(i) >= tmpKey) {
							tmpKey = keys.get(i);
						} else {
//							System.out.println("Falsche Keyreihenfolge");
							return false;
						}
					}
					int tmpHeight = children.get(0).height();
					for (int i = 1; i < children.size(); i++) { // Testet ob die Teilbäume alle die gleiche Höhe haben
						if (children.get(i).height() != tmpHeight) {
//							System.out.println("Unteschiedliche Höhe");
							return false;
						}
					}
					if (!(children.get(0) instanceof ABTreeLeaf)) {
						for (int i = 0; i < keys.size(); i++) {
							if (keys.get(i) >= children.get(i).max() && keys.get(i) < children.get(i + 1).min()) {
								// Intentionally left blank
							} else {
//								System.out.println("Teilbäume enhalten falsche Elemente");
								return false;
							}
						}
					}
					for (int i = 0; i < children.size(); i++) { // Prüft für alle Unterbäume dasselbe
						if (!children.get(i).validAB(true)) {
//							System.out.println("Teilbäume sind nicht korrekt");
							return false;
						}
					}
					return true;
				}
//				System.out.println("Falsche Children Size " + children.size() + " " + a);
				return false;
			}
			return false;
		}

		@Override
		public int dot(StringBuilder sb, int from) {
			int mine = from++;
			sb.append(String.format("\tstruct%s [label=\"", mine));
			for (int i = 0; i < 2 * keys.size() + 1; i++) {
				if (i > 0)
					sb.append("|");
				sb.append(String.format("<f%d> ", i));
				if (i % 2 == 1)
					sb.append(keys.get(i / 2));
			}
			sb.append("\"];\n");
			for (int i = 0; i < children.size(); i++) {
				int field = 2 * i;
				sb.append(String.format("\tstruct%d:<f%d> -> struct%d;\n", mine, field, from));
				from = children.get(i).dot(sb, from);
			}
			return from;
		}
	}

	/**
	 * Diese Klasse repräsentiert ein Blatt des Baumes.
	 */
	private class ABTreeLeaf extends ABTreeNode {

		@Override
		public void insert(int key) {
			// Intentionally left blank
		}

		@Override
		public boolean canSteal() {
			return false;
		}

		@Override
		public boolean find(int key) {
			return true;
		}

		@Override
		public boolean remove(int key) {
			return true;
		}

		@Override
		public int height() {
			return 0;
		}

		@Override
		public Integer min() {
			return null;
		}

		@Override
		public Integer max() {
			return null;
		}

		@Override
		public boolean validAB(boolean root) {
			if (root) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int dot(StringBuilder sb, int from) {
			sb.append(String.format("\tstruct%d [label=leaf, shape=ellipse];\n", from));
			return from + 1;
		}
	}

	public ABTree(int a, int b) {
		this.a = a;
		this.b = b;
	}

	/**
	 * Diese Objektvariable speichert die Wurzel des Baumes.
	 */
	private ABTreeInnerNode root = null;

	public boolean validAB() {
		if (this.root == null) {
			return true;
		} else {
			return this.root.validAB(true);
		}
	}

	/**
	 * Diese Methode ermittelt die Höhe des Baumes.
	 *
	 * @return die ermittelte Höhe
	 */
	public int height() {
		if (this.root == null) {
			return 0;
		} else {
			return this.root.height();
		}
	}

	/**
	 * Diese Methode sucht einen Schlüssel im (a,b)-Baum.
	 *
	 * @param key
	 *            der Schlüssel, der gesucht werden soll
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		if (this.root == null) {
			return false;
		} else {
			return this.root.find(key);
		}
	}

	/**
	 * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
	 *
	 * @param key
	 *            der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if (this.root == null) {
			this.root = new ABTreeInnerNode(key);
		} else {
			this.root.insert(key);
			if (this.root.parent != null) {
				this.root = (ABTreeInnerNode) this.root.parent;
			}
		}
	}

	/**
	 * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
	 *
	 * @param key
	 *            der zu löschende Schlüssel
	 * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
	 */
	public boolean remove(int key) {
		if (this.root == null) {
			return false;
		} else {
			boolean returnBool = this.root.remove(key);
			if (this.root.parent != null) {
				this.root = (ABTreeInnerNode) this.root.parent;
			}
			return returnBool;
		}
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	String dot() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		sb.append("\tnode [shape=record];\n");
		if (root != null)
			root.dot(sb, 0);
		sb.append("}");
		return sb.toString();
	}
}
