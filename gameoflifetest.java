import java.util.Scanner;

/*
 * Game of Life
 * Benjamin and MacKenzie
 * Finished: September 17th, 2024
 *
 */

public class gameoflifetest {
	public static void main(String[] args) {
		//========= GET INITIAL SIZE
		Scanner scan = new Scanner(System.in);
		int size = 0;
		while(true){
			System.out.print("Please input an integer for the size of the grid (n x n): ");

			if(scan.hasNextInt()){ //checks if theres an int
				int input = scan.nextInt();  //store input
				if(input > 0) {  //checks if pos
					size = input;  //sets it to size
					break;
				} else {
					System.out.println("The input must be a positive integer. Please try again. . .");
				}
			}
			else { //if there isnt a proper int
				System.out.println("That is not a valid input. Please try again. . .");
				scan.next();  // Consume invalid input
			}
		}

		//========= GET GENERATION COUNT
		int generation = 0;
		while(true){
			System.out.print("Please input the first generation displayed: ");

			if(scan.hasNextInt()){
				int input = scan.nextInt();  //store input
				if(input > 0) {  //checks if pos
					generation = input;  //sets it to size
					break;
				} else {
					System.out.println("The input must be a positive integer. Please try again. . .");
				}
			}
			else { //if not an integer
				System.out.println("That is not a valid input. Please try again...");
				scan.next();  // Consume invalid input
			}
		}

		//========= CREATING THE MATRIXS
		int[][] matrix = new int[size][size];
		int[][] firstmatrix = new int[size][size]; // === This is the SAVED state of the first/starting generation, saved for the PrintGeneration script.

		boolean stillRunning = true; //bool that keeps track of if we are still running the program

		//========= ASSIGN INITIAL VALUES (set everything in matrix to 0)
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				matrix[y][x] = 0;
			}
		}

		//========= FIRST FORMATION (start with a glider, blinker, or something custom)
    	int formation = 0;
		while(true){
			System.out.print("Would you like to chose the Glider formation, the Blinker formation, or a Custom formation? \n1. Glider \n2. Blinker \n3. Custom \n");

			if(scan.hasNextInt()){
				int input = scan.nextInt();  //Store input
				if(input == 1||input==2||input==3) {  //Checks if the value is acceptable
					formation = input;  //sets it to size
					break;
				} else {
					System.out.println("The input must be 1, 2, or 3. Please try again. . .");
				}
			}
			else {
				System.out.println("That is not a valid input. Please try again...");
				scan.next();  // Consume invalid input
			}
		}

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
			while(true)
			{
				System.out.print("Input coordinates, one number at a time (y [enter] x). Whenever you are done, input (-1) or any number outside the array's range: ");

				if(scan.hasNextInt()){
					int input = scan.nextInt();  //store input
					if(input < 0 || input >= size )break;

					if(scan.hasNextInt()){
						int input2 = scan.nextInt();
						if(input2 < 0 || input2 >= size )break;

						matrix[input][input2] = 1;
						System.out.println("Created alive space at: y= " + input +" | x= "+input2);
					}
				}
				else { //if not an integer
					System.out.println("That is not a valid input. Please try again...");
					scan.next();  // Consume invalid input
				}
			}

      		// System.out.println("Please enter 6 pairs of coordinates, inputed one by one (EX: 1 [enter] 2. This is the coordinate (1,2)");
			// int matrix11 = scan.nextInt();
			// int matrix12 = scan.nextInt();
			// int matrix21 = scan.nextInt();
			// int matrix22 = scan.nextInt();
			// int matrix31 = scan.nextInt();
			// int matrix32 = scan.nextInt();
			// int matrix41 = scan.nextInt();
			// int matrix42 = scan.nextInt();
			// int matrix51 = scan.nextInt();
			// int matrix52 = scan.nextInt();
			// int matrix61 = scan.nextInt();
			// int matrix62 = scan.nextInt();
			// matrix[matrix11][matrix12] = 1;
			// matrix[matrix21][matrix22] = 1;
			// matrix[matrix31][matrix32] = 1;
			// matrix[matrix41][matrix42] = 1;
			// matrix[matrix51][matrix52] = 1;
			// matrix[matrix61][matrix62] = 1;
    	}

		for (int y = 0; y < size; y++) { //====== Sets the first state
			for (int x = 0; x < size; x++) {
			  firstmatrix[y][x] = matrix[y][x];
			}
		}

		//========= LOOP FOR GOING THROUGH GENERATIONS
		String next = "";

		while(stillRunning){
			System.out.print("\n\n=== GENERATION ["+ (generation)+"] ===\n");
			PrintGeneration(generation, size, firstmatrix); //Prints the matrix at the corresponding generation
			next = "";

			while(true){
				System.out.println("\u001B[37mn for next, b for previous, q to quit.");

				if(scan.hasNextLine()){
					String strInput = scan.nextLine();  //Store input
					if(strInput.equals("n")||strInput.equals("b")||strInput.equals("q")) {  //Checks if an acceptable answer
						next = strInput;
						break;
					} else {
						System.out.println("The input must be 'n', 'b', or 'q'. Please try again. . .");
					}
				}
				else {
					System.out.println("That is not a valid input. Please try again...");
					scan.next();  // Consume invalid input
				}
			}

			//Handles what operation should be done next
			if(next.equals("n")) {
				generation++;
			}
			if(next.equals("b") && generation>1) {
				generation--;
			}
			if(next.equals("q")) {
				stillRunning = false;
			}
		}
    scan.close();
	}

	//========= Checks nearby squares. First sees if they exist, then checks if they're alive.
	//========= Method returns 1 or 0 depending on if the spot should be alive or dead
	public static int CheckLife(int[][] matrix, int y, int x) {
		int total = 0;

		if(y-1 != -1 && x-1 != -1 && matrix[y-1][x-1]==1) { //top left
			total = total+1;
		}

		if(y-1 != -1 && matrix[y-1][x]==1) { //top
			total = total+1;
		}

		if(y-1 != -1 && x+1 != matrix[0].length && matrix[y-1][x+1]==1) { //top right
			total = total+1;
		}

		if(x-1 != -1 && matrix[y][x-1]==1) { //left
			total = total+1;
		}

		if(x+1 != matrix[0].length && matrix[y][x+1]==1) { //right
			total = total+1;
		}

		if(y+1 != matrix[0].length && x-1 != -1 && matrix[y+1][x-1]==1) { //bottom left
			total = total+1;
		}

		if(y+1 != matrix[0].length && matrix[y+1][x]==1) { //bottom
			total = total+1;
		}

		if(y+1 != matrix[0].length && x+1 != matrix[0].length && matrix[y+1][x+1]==1) { //bottom right
			total = total+1;
		}

		//========= To die or not to die :) The total is the amount of live squares surrounding the coordinate
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

	// This method takes in a matrix, and prints it out properly- making all the 1s a different color.
	public static void PrintMatrix(int[][] matrix) {
		for (int i = 0; i<matrix.length; i++) { // print matrix
		  for (int j = 0; j<matrix[0].length; j++) {
				if(matrix[i][j] == 1){
					System.out.print("\u001B[32m" +matrix[i][j] + " \u001B[37m");
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

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
			newmatrix[y][x] = matrix[y][x];
			}
		}

		for(int g = 0; g <= generation; g++){ //runs through game of life until it gets to desired generation
			for(int y = 0; y<size; y++) { //looking at original matrix (saved as newmatrix), but printing live/dead states to tempmatrix
				for(int x = 0; x < size; x++) {
					tempMatrix[y][x] = CheckLife(newmatrix, y, x);
				}
			}
			for (int y = 0; y < size; y++) { //sets the old matrix to the new one
				for (int x = 0; x < size; x++) {
				  newmatrix[y][x] = tempMatrix[y][x];
				}
			}
		}
		PrintMatrix(newmatrix); //prints new matrix
  }
}
