/**
 * Die Klasse {@link HashPair} speichert zwei Indices
 */

public class HashPair {
  public int master_index = 0;
  public int store_index = 0;

  public HashPair(int master_index, int store_index) {
    this.master_index = master_index;
    this.store_index = store_index;
  }

  @Override
  public String toString() {
    return "[" + Integer.toString(master_index) + ", " + Integer.toString(store_index) + "]";
  }
}
