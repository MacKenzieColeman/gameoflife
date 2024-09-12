import java.util.Scanner;

public class gameoflifetest {
	public static void main(String[] args) {

		//========= GET INITIAL SIZE
		Scanner scan = new Scanner(System.in);
		System.out.print("Please input an integer for the size of the grid (n x n): ");
		int size = scan.nextInt();

		//========= GET GENERATION COUNT
		System.out.print("Please input the first generation displayed: ");
		int generation = scan.nextInt();

		int[][] matrix = new int[size][size];
		int[][] firstmatrix = new int[size][size]; // === This is the state of the first generation, saved for the PrintGeneration script.

		boolean stillRunning = true;

		//========= ASSIGN INITIAL VALUES (set everything to 0)
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				matrix[y][x] = 0;
			}
		}
    	System.out.print("Would you like to chose the Glider formation, the Blinker formation, or a Custom formation? \n1. Glider \n2. Blinker \n3. Custom \n");
    	int formation = scan.nextInt();
    	if(formation == 1) {
			//========= GLIDER TEST
			matrix[2][1] = 1;
			matrix[3][2] = 1;
			matrix[3][3] = 1;
			matrix[1][3] = 1;
			matrix[2][3] = 1;
			matrix[3][3] = 1;
    	} else if(formation == 2) {
    		//========= BLINKER TEST
      		matrix[2][2] = 1;
      		matrix[2][3] = 1;
      		matrix[2][4] = 1;
    	} else if(formation == 3) {
    		//========= CUSTOM TEST
      		System.out.println("Please enter 6 pairs of coordinates");
			int matrix11 = scan.nextInt();
			int matrix12 = scan.nextInt();
			int matrix21 = scan.nextInt();
			int matrix22 = scan.nextInt();
			int matrix31 = scan.nextInt();
			int matrix32 = scan.nextInt();
			int matrix41 = scan.nextInt();
			int matrix42 = scan.nextInt();
			int matrix51 = scan.nextInt();
			int matrix52 = scan.nextInt();
			int matrix61 = scan.nextInt();
			int matrix62 = scan.nextInt();
			matrix[matrix11][matrix12] = 1;
			matrix[matrix21][matrix22] = 1;
			matrix[matrix31][matrix32] = 1;
			matrix[matrix41][matrix42] = 1;
			matrix[matrix51][matrix52] = 1;
			matrix[matrix61][matrix62] = 1;
    	}

		for (int y = 0; y < 10; y++) { //====== Sets the first state
			for (int x = 0; x < 10; x++) {
			  firstmatrix[y][x] = matrix[y][x];
			}
		}

		String next = "";
		while(stillRunning){
			PrintGeneration(generation, size, firstmatrix);

			System.out.println("n for next, b for previous, q to quit.");
			next = scan.nextLine();

			if(next.equals("n")) {
				generation++;
			}
			if(next.equals("b") && generation>0) {
				generation--;
			}
			if(next.equals("q")) {
				stillRunning = false;
			}
		}
    scan.close();
	}

	//========= Checks nearby squares. First sees if they exist, then checks if they're alive.
	//========= Returns 1 or 0 depending on if the spot should be alive or dead
	public static int CheckLife(int[][] matrix, int y, int x) {
		int total = 0;

		if(y-1 != -1 && x-1 != -1 && matrix[y-1][x-1]==1) { //top left
			total = total+1;
		}

		if(y-1 != -1 && matrix[y-1][x]==1) { //top
			total = total+1;
		}

		if(y-1 != -1 && x+1 != 10 && matrix[y-1][x+1]==1) { //top right
			total = total+1;
		}

		if(x-1 != -1 && matrix[y][x-1]==1) { //left
			total = total+1;
		}

		if(x+1 != 10 && matrix[y][x+1]==1) { //right
			total = total+1;
		}

		if(y+1 != 10 && x-1 != -1 && matrix[y+1][x-1]==1) { //bottom left
			total = total+1;
		}

		if(y+1 != 10 && matrix[y+1][x]==1) { //bottom
			total = total+1;
		}

		if(y+1 != 10 && x+1 != 10 && matrix[y+1][x+1]==1) { //bottom right
			total = total+1;
		}


		//========= To die or not to die :)
		if(total>3) { //dies of overcrowding
			return 0;
		}
		else if(total<2) { //dies of loneliness
			return 0;
		}
		else if(total==3) { //birth!
			return 1;
		}
		else { //if its 2, then it stays the same state
			return matrix[y][x];
		}
	}

	public static void PrintMatrix(int[][] matrix) {
		for (int i = 0; i<matrix.length; i++) { // print matrix
		  for (int j = 0; j<matrix[0].length; j++) {
				if(matrix[i][j] == 1){
					System.out.print("\u001B[32m" +matrix[i][j] + " ");
				}
				else{
					System.out.print("\u001B[37m" + matrix[i][j] + " ");
				}
		  }
		  System.out.println();
		}
   }

   public static void PrintGeneration(int generation, int size, int[][] matrix)
   {
		int[][] newmatrix = new int[size][size];//makes a copy of the matrix
		int[][] tempMatrix = new int[size][size];//makes a copy of the matrix

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
			newmatrix[y][x] = matrix[y][x];
			}
		}

		for(int g = 0; g <= generation; g++){ //runs through game of life until it gets to desired generation
			for(int y = 0; y<size; y++) { //looking at original matrix (saved as newmatrix), but printing live/dead states to tempmatrix
				for(int x = 0; x < size; x++) {
					tempMatrix[y][x] = CheckLife(newmatrix, y, x);
				}
			}
			for (int y = 0; y < 10; y++) { //sets the old matrix to the new one
				for (int x = 0; x < 10; x++) {
				newmatrix[y][x] = tempMatrix[y][x];
				}
			}
		}
		PrintMatrix(newmatrix); //prints new matrix
   }
}
