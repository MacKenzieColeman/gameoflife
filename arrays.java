public class arrays {
  private String[][] glider = new String[10][10];
  private String[][] blinker = new String[10][10];

  // Constructor to initialize the arrays
  public arrays() {
    for (int i = 0; i < glider.length; i++) {
      for (int j = 0; j < glider[i].length; j++) {
        glider[i][j] = "o";
      }
    }

    for (int i = 0; i < blinker.length; i++) {
      for (int j = 0; j < blinker[i].length; j++) {
        blinker[i][j] = "o";
      }
    }

    // Setting the patterns for the arrays
    glider[4][5] = "\u001B[32mx\u001B[37m";
    glider[6][5] = "\u001B[32mx\u001B[37m";
    glider[6][4] = "\u001B[32mx\u001B[37m";
    glider[5][6] = "\u001B[32mx\u001B[37m";
    glider[6][6] = "\u001B[32mx\u001B[37m";

    blinker[4][5] = "\u001B[32mx\u001B[37m";
    blinker[5][5] = "\u001B[32mx\u001B[37m";
    blinker[6][5] = "\u001B[32mx\u001B[37m";
  }

  public String[][] getGlider() {
    return glider;
  }

  public String[][] getBlinker() {
    return blinker;
  }
}
