package Blatt2;

/**
 * Die Klasse BinSea stellt Methoden bereit, in sortierten Feldern binär
 * nach Wertebereichen zu suchen.
 */
class BinSea {
  /**
   * Diese Methode sucht nach einem Wert in einem einem sortierten Feld. Sie
   * soll dabei dazu verwendet werden können, den ersten bzw. letzten Index in
   * einem Intervall zu finden. Wird kein passender Index gefunden, wird
   * -1 zurückgegeben.
   * 
   * Beispiel:
   * 
   *             0 <-----------------------> 5
   * sortedData: [-10, 33, 50, 99, 123, 4242 ]
   * value: 80             ^   ^
   *                       |   |
   *                       |   `- Ergebnis (3) für ersten Index im Intervall (lower == false),
   *                       |      da 99 als erster Wert im Feld größer oder gleich 80 ist
   *                       |
   *                       `- Ergebnis (2) für letzten Index im Intervall (lower == true),
   *                          da 50 als letzter Wert kleiner oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param value der Wert der Intervallbegrenzung, nach dem gesucht wird
   * @param lower true für untere Intervallbegrenzung, false für obere Intervallbegrenzung
   * @return der passende Index, -1 wenn dieser nicht gefunden werden kann
   */
  public static int search (int[] sortedData, int value, boolean lower) {
	  int upperBorder = sortedData.length-1;
	  int lowerBorder = 0;
	  if (lower) {
		  while (upperBorder >= lowerBorder) {
			  int tmp = (upperBorder + lowerBorder) / 2;
			  if (sortedData[tmp] == value) {
				  return tmp;
			  } else if (sortedData[tmp] < value) {
				  lowerBorder = tmp + 1;
			  } else {
				  if (tmp - 1 >= 0) {
					  if (sortedData[tmp-1] < value) {
						  return tmp;
					  }
				  } else {
					  return tmp;
				  }
				  upperBorder = tmp - 1;
			  }
		  }
	  } else {
		  while (upperBorder >= lowerBorder) {
			  int tmp = (upperBorder + lowerBorder) / 2;
			  if (sortedData[tmp] == value) {
				  return tmp;
			  } else if (sortedData[tmp] > value) {
				  upperBorder = tmp - 1;
			  } else {
				  if (tmp + 1 < sortedData.length) {
					  if (sortedData[tmp+1] > value) {
						  return tmp;
					  }
				  } else {
					  return tmp;
				  }
				  lowerBorder = tmp + 1;
			  }
		  }
	  }
	  return -1;
  }

  /**
   * Diese Methode sucht ein Intervall von Indices eines sortierten Feldes, deren Werte
   * in einem Wertebereich liegen. Es soll dabei binäre Suche verwendet werden.
   * 
   * Beispiel:
   *                     0 <-----------------------> 5
   * sortedData:         [-10, 33, 50, 99, 123, 4242 ]
   * valueRange: [80;700]              ^   ^
   *                                   |   |
   *                                   |   `- Ergebnis (4) für obere Intervallbegrenzung,
   *                                   |      da 123 als letzter Wert kleiner oder gleich 700 ist
   *                                   |
   *                                   `- Ergebnis (3) für untere Intervallbegrenzung,
   *                                      da 99 als erster Wert größer oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param valueRange der Wertebereich, zu dem Indices gesucht werden
   */
  public static Interval search (int[] sortedData, Interval valueRange) {
	  int lowerBorder = search(sortedData, valueRange.getFrom(), true);
	  int upperBorder = search(sortedData, valueRange.getTo(), false);
	  if (lowerBorder == -1 || upperBorder == -1) {
		 return new EmptyInterval();
	  }
	  if (lowerBorder > upperBorder) {
		  return new EmptyInterval();
	  }
	  return new NonEmptyInterval(lowerBorder, upperBorder);
  }

}
