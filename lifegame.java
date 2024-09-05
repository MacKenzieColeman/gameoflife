public class lifegame {
  public static void main(String[] args) {
    String[][] glider = new String[10][10];

    for(int i = 0; i < glider.length; i++) {
      for(int j = 0; j < glider.length; j++) {
        if(j == 5) {
          if(i == 4) {
            glider[i][j] = "x";
          } else if(i == 6) {
            glider[i][j] = "x";
          }
        }
        if(j == 4) {
          if(i == 6) {
            glider[i][j] = "x";
          }
        }
        if(j == 6) {
          if(i == 5) {
            glider[i][j] = "x";
          } else if(i == 6) {
            glider[i][j] = "x";
          }
        }
        if(glider[i][j] != "x") {
          glider[i][j] = "o";
        }
      }
    }
    for(int i = 0; i < glider.length; i++) {
      for(int j = 0; j < glider.length; j++) {
      System.out.print(glider[i][j] + " ");
      }
      System.out.println();
    }
  }
}
