package Blatt4;

public class DHTTesting {

	public static void main(String[] args) {
		DHT table = new DHT(7, 269); // initialisieren eine DHT mit 7 (Primzahl) Stores à 269 Slots (Primzahl)
		
		System.out.println(table.hashKey("test"));
		System.out.println(table.hashKey("test"));
		System.out.println(table.hashKey("TEST"));
		System.out.println(table.hashKey("TEST"));
		System.out.println(table.hashKey("Test"));
		System.out.println(table.hashKey("Test"));
		System.out.println(table.hashKey("tesT"));
		System.out.println(table.hashKey("tesT"));
		System.out.println(table.hashKey(" "));
		System.out.println(table.hashKey(" "));
		System.out.println(table.hashKey("Das ist ein sehr sehr langer String der hoffentlich alles kaputt macht"));
		System.out.println(table.hashKey("Das ist ein sehr sehr langer String der hoffentlich alles kaputt macht"));
		System.out.println(table.hashKey("DasistimmernocheinsuperlangerTextMITGANZEplötzlichauftauchendenSachenWIEKLEinundGrtjkasgtbja,sbgkjashgjklashglkahsglkhalksghalkhglkahsljkgfhalkshglkashglkashlkghaslkghajklshvjlkashglkjahgaklshglkashglk"));
		System.out.println(table.hashKey("DasistimmernocheinsuperlangerTextMITGANZEplötzlichauftauchendenSachenWIEKLEinundGrtjkasgtbja,sbgkjashgjklashglkahsglkhalksghalkhglkahsljkgfhalkshglkashglkashlkghaslkghajklshvjlkashglkjahgaklshglkashglk"));
		System.out.println(table.hashKey("12345"));
		System.out.println(table.hashKey("12345"));
		
		System.out.println("---------------");
		
		DHT table2 = new DHT(55, 2692); // initialisieren eine zweite DHT mit anderen Werten
		
		System.out.println(table2.hashKey("test"));
		System.out.println(table2.hashKey("test"));
		System.out.println(table2.hashKey("TEST"));
		System.out.println(table2.hashKey("TEST"));
		System.out.println(table2.hashKey("Test"));
		System.out.println(table2.hashKey("Test"));
		System.out.println(table2.hashKey("tesT"));
		System.out.println(table2.hashKey("tesT"));
		System.out.println(table2.hashKey(" "));
		System.out.println(table2.hashKey(" "));
		System.out.println(table2.hashKey("Das ist ein sehr sehr langer String der hoffentlich alles kaputt macht"));
		System.out.println(table2.hashKey("Das ist ein sehr sehr langer String der hoffentlich alles kaputt macht"));
		System.out.println(table2.hashKey("DasistimmernocheinsuperlangerTextMITGANZEplötzlichauftauchendenSachenWIEKLEinundGrtjkasgtbja,sbgkjashgjklashglkahsglkhalksghalkhglkahsljkgfhalkshglkashglkashlkghaslkghajklshvjlkashglkjahgaklshglkashglk"));
		System.out.println(table2.hashKey("DasistimmernocheinsuperlangerTextMITGANZEplötzlichauftauchendenSachenWIEKLEinundGrtjkasgtbja,sbgkjashgjklashglkahsglkhalksghalkhglkahsljkgfhalkshglkashglkashlkghaslkghajklshvjlkashglkjahgaklshglkashglk"));
		System.out.println(table.hashKey("12345"));
		System.out.println(table.hashKey("12345"));
		
		System.out.println("---------------");
		
		DHT table3 = new DHT(2, 50); // initialisieren eine dritte DHT mit anderen Werten
		
		System.out.println(table3.hashKey("test"));
		System.out.println(table3.hashKey("test"));
		System.out.println(table3.hashKey("TEST"));
		System.out.println(table3.hashKey("TEST"));
		System.out.println(table3.hashKey("Test"));
		System.out.println(table3.hashKey("Test"));
		System.out.println(table3.hashKey("tesT"));
		System.out.println(table3.hashKey("tesT"));
		System.out.println(table3.hashKey(" "));
		System.out.println(table3.hashKey(" "));
		System.out.println(table3.hashKey("Das ist ein sehr sehr langer String der hoffentlich alles kaputt macht"));
		System.out.println(table3.hashKey("Das ist ein sehr sehr langer String der hoffentlich alles kaputt macht"));
		System.out.println(table3.hashKey("DasistimmernocheinsuperlangerTextMITGANZEplötzlichauftauchendenSachenWIEKLEinundGrtjkasgtbja,sbgkjashgjklashglkahsglkhalksghalkhglkahsljkgfhalkshglkashglkashlkghaslkghajklshvjlkashglkjahgaklshglkashglk"));
		System.out.println(table3.hashKey("DasistimmernocheinsuperlangerTextMITGANZEplötzlichauftauchendenSachenWIEKLEinundGrtjkasgtbja,sbgkjashgjklashglkahsglkhalksghalkhglkahsljkgfhalkshglkashglkashlkghaslkghajklshvjlkashglkjahgaklshglkashglk"));
		System.out.println(table.hashKey("12345"));
		System.out.println(table.hashKey("12345"));
		
	}

}
