package Blatt10;

public class TestingAB {

	public static void main(String[] args) {
		ABTree tree = new ABTree(3, 5);
		tree.insert(1);
		System.out.println(tree.dot());
		tree.insert(2);
		System.out.println(tree.dot());
		tree.insert(3);
		System.out.println(tree.dot());
		tree.insert(4);
		System.out.println(tree.dot());
		tree.insert(5);
		System.out.println(tree.dot());
		tree.insert(6);
		System.out.println(tree.dot());
		tree.insert(7);
		System.out.println(tree.dot());
		tree.insert(8);
		System.out.println(tree.dot());
		tree.insert(9);
		System.out.println(tree.dot());
		tree.insert(10);
		System.out.println(tree.dot());
		tree.insert(80);
		System.out.println(tree.dot());
		tree.insert(0);
		System.out.println(tree.dot());
		tree.insert(-14);
		System.out.println(tree.dot());
		tree.insert(63);
		System.out.println(tree.dot());
		tree.insert(34);
		System.out.println(tree.dot());
		tree.insert(22);
		System.out.println(tree.dot());
		tree.insert(17);
		System.out.println(tree.dot());
		tree.insert(-5);
		System.out.println(tree.dot());
		tree.insert(-2);
		System.out.println(tree.dot());
		tree.insert(97);
		System.out.println(tree.dot());
		tree.insert(13);
		System.out.println(tree.dot());
		tree.insert(-22);
		System.out.println(tree.dot());
		tree.insert(-15);
		System.out.println(tree.dot());
		tree.insert(100);
		System.out.println(tree.dot());
		System.err.println(tree.validAB());
		tree.remove(7);
		System.out.println(tree.dot());
		tree.remove(17);
		System.out.println(tree.dot());
		tree.remove(13);
		System.out.println(tree.dot());
		tree.remove(6);
		System.out.println(tree.dot());
	}
	
}
