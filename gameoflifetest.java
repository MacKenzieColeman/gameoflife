package gameoflifetest;

public class gameoflifetest {
	public static void main(String[] args) {
		int[][] matrix = new int[10][10];
		int[][] newmatrix = new int[10][10];

		//========= ASSIGN INITIAL VALUES (set everything to 0)
		for(int y = 0; y < matrix.length; y++) {
			for(int x = 0; x < matrix[0].length; x++) {
				matrix[y][x] = 0;
			}
		}
		//========= GLIDER TEST
		matrix[2][1] = 1;
		matrix[3][2] = 1;
		matrix[3][3] = 1;
		matrix[1][3] = 1;
		matrix[2][3] = 1;
		matrix[3][3] = 1;

		//========= PRINT INITIAL STATE
		PrintMatrix(matrix);
		System.out.println();

		//========= Loops 5 times. Goes through each spot, and runs CheckLife() on it
		for(int times = 0; times <5; times++) {

			for(int y = 0; y<10; y++) {
				for(int x = 0; x < 10; x++) {
					newmatrix[y][x] = CheckLife(matrix, y, x);
				}
			}
			//========= Print matrix, and then set up the initial matrix for the next generation
			PrintMatrix(newmatrix);
			System.out.println();

			//========= Copy newmatrix to matrix
      for (int y = 0; y < 10; y++) {
        for (int x = 0; x < 10; x++) {
          matrix[y][x] = newmatrix[y][x];
        }
      }
		}
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
	      System.out.print(matrix[i][j] + " ");
		  }
		  System.out.println();
		}
	}
}
