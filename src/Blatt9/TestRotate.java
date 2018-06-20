package Blatt9;

public class TestRotate {

	public static void main(String[] args) {

		AVLTree tree = new AVLTree();
		tree.insert(33);
		tree.insert(10);
		tree.insert(42);
		tree.insert(42);
		tree.insert(22);
		tree.insert(22);
		tree.insert(49);
		tree.insert(49);
		tree.insert(49);
		tree.insert(49);
		System.out.println(tree);
	}

}
