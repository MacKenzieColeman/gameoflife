import java.util.Scanner;

/*
 * The Game of Life
 * Benjamin and MacKenzie
 * Finished: September 17th, 2024
 *
 */

public class gameoflifeproject {
	public static void main(String[] args) {
		//========= GETS SIZE OF GRID
		Scanner scan = new Scanner(System.in);
		int size = 0;
		while(true){ //Escapes the while loop when the user inputs an acceptable input
			System.out.print("\u001B[33mPlease input an integer for the size of the grid n (Where the grid is n x n): \u001B[37m");

			if(scan.hasNextInt()){ //checks if theres an inputted int
				int input = scan.nextInt();  //store input
				if(input > 0) {  //checks if its a positive number
					size = input;  //sets size to the input
					break;
				} else {
					System.out.println("\u001B[31mThe input must be a positive integer. Please try again. . .");
				}
			}
			else { //if user inputted something other then an int
				System.out.println("\u001B[31mThat is not a valid input. Please try again. . .");
				scan.next();  //resets scanner
			}
		}

		//========= GET GENERATION COUNT
		int generation = 0;
		while(true){ //Escapes the while loop when the user inputs an acceptable input
			System.out.print("\u001B[33mPlease input the first generation displayed: \u001B[37m");

			if(scan.hasNextInt()){
				int input = scan.nextInt();  //stores input
				if(input > 0) {  //checks if its above 0, because the first possible generation count is 1
					generation = input;  //sets generation int to the input
					break;
				} else {
					System.out.println("\u001B[31mThe input must be a positive integer. Please try again. . .");
				}
			}
			else { //if input is not an integer
				System.out.println("\u001B[31mThat is not a valid input. Please try again...");
				scan.next();  // Consume invalid input
			}
		}

		//========= CREATING THE MATRIXS (matrixie? Matrixi? whatever the plural is for matrix)
		int[][] matrix = new int[size][size];
		int[][] firstmatrix = new int[size][size]; //=== This is the saved state of the starting generation, saved for the PrintGeneration method.

		boolean stillRunning = true; //bool that keeps track of if we are still running the program


		//========= FIRST FORMATION (start with a glider, blinker, or custom points)
    	int formation = 0;
		while(true){
			System.out.print("\u001B[33mChoose either to start with the Glider formation, the Blinker formation, or a Custom formation. \u001B[34m\n1 = Glider \n2 = Blinker \n3 = Custom \n \u001B[37m");

			if(scan.hasNextInt()){
				int input = scan.nextInt();  //Store input
				if(input == 1||input==2||input==3) {  //Checks if the value is one of the formations
					formation = input;  //sets it to size
					break;
				} else {
					System.out.println("\u001B[31mThe input must be 1, 2, or 3. Please try again. . .");
				}
			}
			else {
				System.out.println("\u001B[31mThat is not a valid input. Please try again...");
				scan.next();  //resets the scanner
			}
		}

    	if(formation == 1) { //========= GLIDER
			matrix[2][1] = 1;
			matrix[3][2] = 1;
			matrix[3][3] = 1;
			matrix[1][3] = 1;
			matrix[2][3] = 1;
			matrix[3][3] = 1;
    	} else if(formation == 2) { //========= BLINKER
      		matrix[2][2] = 1;
      		matrix[2][3] = 1;
      		matrix[2][4] = 1;
    	} else if(formation == 3) { //========= CUSTOM

			while(true)//Loops to allow as many custom points as possible
			{
				System.out.print("\u001B[33mInput coordinates, one number at a time (y [enter] x). Whenever you are done, input (-1) or any number outside the array's range: \u001B[37m");

				if(scan.hasNextInt()){
					int input = scan.nextInt();  //store input
					if(input < 0 || input >= size )break; //if its an exit input, exit the loop

					if(scan.hasNextInt()){
						int input2 = scan.nextInt();
						if(input2 < 0 || input2 >= size )break; //if its an exit input, exit the loop

						matrix[input][input2] = 1;
						System.out.println("\u001B[32mCreated alive space at: y= " + input +" | x= "+input2);
					}
				}
				else { //if not an integer
					System.out.println("\u001B[31mThat is not a valid input. Please try again...");
					scan.next();  //Reset the scanner
				}
			}
    	}

		//Makes firstmatrix a copy of the first generation. This is to be called on later in the PrintGeneration method
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
			  firstmatrix[y][x] = matrix[y][x];
			}
		}

		//========= LOOP FOR GOING THROUGH GENERATIONS
		String next = "";
		scan.nextLine();
		while(stillRunning){
			System.out.print("\n\n\u001B[34m=== GENERATION ["+ (generation)+"] ===\n"); //Title to show user what generation they're on
			PrintGeneration(generation, size, firstmatrix); //Prints the matrix at the corresponding generation
			next = "";

			while(true){
				System.out.println("\u001B[33mn for next | b for previous | q to quit.\u001B[37m");
				String strInput = scan.nextLine();  //Store input

				if(strInput.equals("n")||strInput.equals("b")||strInput.equals("q")) {  //Checks if its an acceptable answer (n, b, or q)
					next = strInput;
					break;
				} else {
					System.out.println("\u001B[31mThe input must be 'n', 'b', or 'q'. Please try again. . .");
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
				stillRunning = false; //Stops the while loop, and exits
			}
		}
    scan.close(); //Closes the scanner then finishes the program
	}

	//===== Check nearby Squares!
	// This is the method that handles if a space should live or die. Checks each spot, first to make sure it exists in the matrix, then if its alive or dead!
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

		//========= To die or not to die :) The total variable is the amount of live squares surrounding the coordinate
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

	// This method takes in a matrix, and prints it out properly- making all the 1s a different color to be easily read by the user.
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

   // This method takes a generation number, the size of a matrix, and the matrix itself. Then, it prints whatever generation the user asked for!
   public static void PrintGeneration(int generation, int size, int[][] matrix)
   {
		int[][] newmatrix = new int[size][size]; // this will be a copy of the current matrix
		int[][] tempMatrix = new int[size][size]; //this temp matrix is used to save the spot, so it can calculate the next generation in line.

		for (int y = 0; y < size; y++) { // This loop sets newmatrix as a copy of matrix, so we dont affect the original matrix' state
			for (int x = 0; x < size; x++) {
			newmatrix[y][x] = matrix[y][x];
			}
		}

		for(int g = 1; g < generation; g++){ //runs through game of life until it gets to desired generation
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
		PrintMatrix(newmatrix); //prints the new matrix at the desired generation
   }
}
