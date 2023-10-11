package refined_checkers.util;

import java.util.Collections;
import java.util.HashMap;

public class BoardCoordinatesTool {
  private static HashMap<String, Integer[]> boardCoordinates = initBoardCoordinates();

  public static Integer[] convert(String coordinates) throws NullPointerException {
    return boardCoordinates.get(coordinates.toLowerCase());
  }

  public static HashMap<String, Integer[]> getHashMap() {
    return (HashMap<String, Integer[]>) Collections.unmodifiableMap(boardCoordinates);
  }

  private static HashMap<String, Integer[]> initBoardCoordinates() {
    HashMap<String, Integer[]> temp = new HashMap<>();
    int row = 8;

    for (int r = 0; r < 8; r++) {
      char alphabet = 'a';

      for (int c = 0; c < 8; c++) {
        String spName = Integer.toString(row) + alphabet;
        Integer[] coordinates = { r, c };

        temp.put(spName, coordinates);
        alphabet++;
      }

      row--;
    }

    return temp;
  }
}
