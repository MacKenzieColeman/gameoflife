import java.util.Scanner;

public class lifegame {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    arrays Arrays = new arrays();

    String[][] glider = Arrays.getGlider();
    String[][] blinker = Arrays.getBlinker();

    System.out.println("Welcome to the Game of Life");
    System.out.println("Choose between these presets: ");
    System.out.println("1. Glider \n2. Blinker \n");
    String selection = scan.nextLine();

    if(selection.equals("1")) {
      printGlider(glider);
    } else if(selection.equals("2")) {
      printBlinker(blinker);
    }

  }

  public static void printGlider(String[][] glider) {
    for(int i = 0; i < glider.length; i++) {
      for(int j = 0; j < glider[i].length; j++) {
        System.out.print(glider[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void printBlinker(String[][] blinker) {
    for(int i = 0; i < blinker.length; i++) {
      for(int j = 0; j < blinker[i].length; j++) {
        System.out.print(blinker[i][j] + " ");
      }
      System.out.println();
    }
  }
}
