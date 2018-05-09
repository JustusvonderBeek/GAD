package Blatt4;

public class DHTTesting {

	public static void main(String[] args) {
		DHT table = new DHT(7, 269); // initialisieren eine DHT mit 7 (Primzahl) Stores à 269 Slots (Primzahl)
		
		int itteration = 20;
		
		HashString hs = new HashString(20);
		hs.hash("testT");
		
//		String test = "";
//		
//		for (int i = 0; i < itteration; i++) {
//			test += "Test";
//			System.out.println("Key: " + table.hashKey(test));
//		}
		
	}

}
