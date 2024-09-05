//A new cell is born on an empty square if it is surrounded by exactly three occupied neighbor cells
//A cell dies of overcrowding if it is surrounded by four or more neighbors, and it dies of loneliness if it is surrounded by zero or one neighbor
//when n is pressed, next generation will be shown
//blinker and glider formations are preset options, also allows user to input a custom formation
//occupied cells are shown with x, unoccupied cells are shown with o
// glider            blinker
//o o o o o        o o o o o
//o o x o o        o o x o o
//o o o x o        o o x o o
//o x x x o        o o x o o
//o o o o o        o o o o o

public class lifegame {
  public static void main(String[] args) {
    int[][] glider = new int[10][10];

    for(int i = 0; i < glider.length; i++) {
      for(int j = 0; j < glider.length; j++) {
        if(j == 5) {
          if(i == 4) {
            glider[i][j] = 1;
          } else if(i == 6) {
            glider[i][j] = 1;
          }
        }
        if(j == 4) {
          if(i == 6) {
            glider[i][j] = 1;
          }
        }
        if(j == 6) {
          if(i == 5) {
            glider[i][j] = 1;
          } else if(i == 6) {
            glider[i][j] = 1;
          }
        }
        if(glider[i][j] != 1) {
          glider[i][j] = 0;
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
