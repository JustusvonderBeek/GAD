package Blatt10;

import java.util.ArrayList;
import java.util.List;

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
		/**
		 * Diese Methode fügt einen Schlüssel in den Baum ein.
		 *
		 * @param key der Schlüssel, der eingefügt wird
		 * @return
		 */
		public abstract void insert(int key);

		/**
		 * Diese Methode ermittelt, ob aus dem entsprechenden Knoten gestohlen
		 * werden kann oder nicht.
		 *
		 * @return 'true' falls gestohlen werden kann, 'false' sonst
		 */
		public abstract boolean canSteal();

		/**
		 * Diese Methode sucht den Schlüssel 'key' im Teilbaum.
		 *
		 * @param key der Schlüssel, der gesucht wird
		 * @return 'true' falls der Schlüssel vorhanden ist, 'false' sonst
		 */
		public abstract boolean find(int key);

		/**
		 * Diese Methode entfernt den Schlüssel 'key' im Teilbaum, falls es ihn gibt.
		 *
		 * @param key der Schlüssel, der entfernt werden soll
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
		 * @return das Minimum
		 */
		public abstract Integer min();

		/**
		 * Diese Methode ermittelt das Maximum im jeweiligen Teilbaum.
		 * @return das Maximum
		 */
		public abstract Integer max();

		/**
		 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich
		 * um einen validen (a,b)-Baum handelt.
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
		
		private ABTreeInnerNode parent;
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
		
		public ABTreeInnerNode getParent() {
			return parent;
		}

		public void setParent(ABTreeInnerNode parent) {
			this.parent = parent;
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
					children.add(new ABTreeLeaf());
					System.out.println("Inserted " + key);
				} else {
					System.out.println("Inserted " + key);
					keys.add(i, key);
				}
				parent = correctTree();
			} else {
				int i = 0;
				while (i < keys.size() && keys.get(i) < key) {
					i++;
				}
				children.get(i).insert(key);
				parent = correctTree();
			}
		}
		
		private ABTreeInnerNode correctTree() {
			if (this.keys.size() > b) {
				int tmp = keys.size() / 2;
				int newTop = keys.get(tmp);
				List<Integer> newKeysLeft = keys.subList(0, tmp);
				List<Integer> newKeysRight = keys.subList(tmp + 1, keys.size());
				List<ABTreeNode> newChildrenLeft = children.subList(0, tmp);
				List<ABTreeNode> newChildrenRight = children.subList(tmp + 1, children.size() - 1);
				ABTreeInnerNode newLeftNode = new ABTreeInnerNode((ArrayList<Integer>)newKeysLeft, (ArrayList<ABTreeNode>)newChildrenLeft);
				ABTreeInnerNode newRightNode = new ABTreeInnerNode((ArrayList<Integer>)newKeysRight, (ArrayList<ABTreeNode>)newChildrenRight);
				ABTreeInnerNode newTopNode = new ABTreeInnerNode(newTop, newLeftNode, newRightNode);
				return newTopNode;
			}
			if (this.keys.size() < a) {
				return null;
			}
			return null;
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
			return children.get(i).find(key);
		}

		public boolean remove(int key) {
			if (children.get(0) instanceof ABTreeLeaf) {
				int i = 0;
				while (i < keys.size() && keys.get(i) < key) {
					i++;
				}
				if (children.get(i).remove(key)) {
					children.remove(i);
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
					correctTree();
					return true;
				}
				return false;
			}
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
				return this.children.get(children.size()-1).max();
			}
		}

		@Override
		public boolean validAB(boolean root) {
			if (root) {
				if ( (parent == null && this.children.size() <= b) || (parent != null && this.children.size() <= b && this.children.size() >= a) ) {
					int tmpKey = keys.get(0);
					for (int i = 1; i < keys.size(); i++) {				// Testet ob die Schlüssel sortiert sind
						if (keys.get(i) >= tmpKey) {
							tmpKey = keys.get(i);
						} else {
							System.out.println("Falsche Keyreihenfolge");
							return false;
						}
					}
					int tmpHeight = children.get(0).height();
					for (int i = 1; i < children.size(); i++) {			// Testet ob die Teilbäume alle die gleiche Höhe haben
						if (children.get(i).height() != tmpHeight) {
							System.out.println("Unteschiedliche Höhe");
							return false;
						}
					}
					if (!(children.get(0) instanceof ABTreeLeaf)) {
						for (int i = 0; i < keys.size(); i++) {
							if (keys.get(i) >= children.get(i).max() && keys.get(i) < children.get(i + 1).min()) {
								// Intentionally left blank
							} else {
								System.out.println("Teilbäume enhalten falsche Elemente");
								return false;
							}
						}
					}
					for (int i = 0; i < children.size(); i++) {			// Prüft für alle Unterbäume dasselbe
						if(!children.get(i).validAB(true)) {
							System.out.println("Teilbäume sind nicht korrekt");
							return false;
						}
					}
					return true;
				}
				System.out.println("Falsche Children Size " + children.size() + " " + a);
			}
			System.out.println("Root Falsch");
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
	 * @param key der Schlüssel, der gesucht werden soll
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
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if (this.root == null) {
			this.root = new ABTreeInnerNode(key);
		} else {
			ABTreeNode tmpParent = this.root.getParent();
			this.root.insert(key);
			if (root.getParent() != tmpParent) {
				this.root = this.root.getParent();
			}
		}
	}

	/**
	 * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
	 *
	 * @param key der zu löschende Schlüssel
	 * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
	 */
	public boolean remove(int key) {
		if (this.root == null) {
			return false;
		} else {
			return this.root.remove(key);	
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
