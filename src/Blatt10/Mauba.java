package Blatt10;

import java.util.Scanner;

public class Mauba {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ABTree abTree = new ABTree(3, 5);
		while (scanner.hasNextInt()) {
			int n = scanner.nextInt();
			abTree.insert(n);
			if (!abTree.validAB()) {
				System.err.println(String.format("Baum ungueltig bei insert(%d)", n));
				System.out.println(abTree.dot());
				return;
			}
		}
		scanner.close();

		System.out.println(abTree.dot());
	}
}
