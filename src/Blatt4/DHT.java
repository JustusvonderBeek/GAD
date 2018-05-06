/**
 * Die Klasse DHT (distributed hash table) stellt eine vereinfachte
 * verteilte Hash Tabelle dar. Sie wird (um die Aufgabe zu simplifizieren)
 * nicht für die Speicherung der Daten verwendet sondern kümmert sich
 * ausschließlich um die Indexberechnung (hashKey).
 */

public class DHT {

  private HashString master;
  private HashString store;

  private int store_count;
  private int size_store;

  /**
   * Dieser Konstruktor initialisiert eine {@link DHT}
   * mit jeweils size_master Stores mit size_store Speicherzellen
   *
   * @param store_count die Anzahl der Stores
   * @param size_store die Größe eines Stores
   */
  public DHT(int store_count, int size_store) {
    this.master = new HashString(store_count);
    this.store = new HashString(size_store);

    this.store_count = store_count;
    this.size_store = size_store;
  }

  /**
   * Diese Methode berechnet den Hashwert für einen String.
   * dabei wird zunächst ein Store selektiert und anschließend 
   * eine die jeweilige Zelle im Store.
   *
   * @param key der Schlüssel, der gehasht werden sollen
   * @return HashPair mit dem Index im Store (store_index) und der Storezuweisung (master_index)
   */
  public HashPair hashKey(String key) {
    return new HashPair(master.hash(key), store.hash(key));
  }

}
