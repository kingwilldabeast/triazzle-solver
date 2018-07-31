package triazzlePuzzleSolver;

import triazzlePuzzleSolver.Piece.bugColor;

public class Main {
	
	public static void main(String [ ] args) {
		//print5();
		
		//int[][] triArray = createTriArray(4);
//		int[][] triArray1 = {{1} ,{2,3,4}, {5,6,7,8,9}};
//		System.out.println(triArray[2][4]);
//		System.out.println(triArray[3][6]);
//		System.out.println(triArray1[1][2]);
		
		Piece[] testArray = new Piece[4];
		testArray[1] = new Piece (bugColor.BLUE, bugColor.PURPLE, bugColor.RED);
		testArray[0] = new Piece (bugColor.YELLOW, bugColor.BLUE, bugColor.PURPLE );
		testArray[2] = new Piece (bugColor.BLUE, bugColor.RED, bugColor.GREEN);
		//testArray[2] = new Piece (bugColor.RED, bugColor.GREEN, bugColor.ORANGE);
		//testArray[3] = new Piece (bugColor.GREEN, bugColor.YELLOW, bugColor.BLUE);
		testArray[3] = new Piece (bugColor.BLUE, bugColor.GREEN,bugColor.YELLOW);
		//testArray[3] = new Piece (bugColor.BLUE, bugColor.GREEN,bugColor.RED);
		bugColor[] sideA = {bugColor.BLUE, bugColor.YELLOW}; 
		bugColor[] sideB = {bugColor.YELLOW, bugColor.PURPLE};
		bugColor[] sideC = {bugColor.PURPLE, bugColor.BLUE};
		Board testBoard = new Board(sideA, sideB, sideC);
		int[] origin = {0,0};
		
		//testBoard.setTriAzzle(testBoard.createTriArray());
		solver(testArray, origin, testBoard);
//		System.out.println(testBoard.getTriAzzle()[1][2].showCoords());
//		System.out.println(testBoard.getSize());
//		System.out.println(testBoard.getNumFill());
		//matching2(testBoard.getTriAzzle()[1][2], bob);
		//matchTask(testBoard.getTriAzzle()[0][0], testArray[0]);
		//unMatchTask(testBoard.getTriAzzle()[0][0], testArray[0]);
		System.out.println("end");
		
/*		bugColor[] sideA = new bugColor[1] ;
		sideA[0] = bugColor.GREEN;
		bugColor[] sideB = new bugColor[1] ;
		sideB[0] = bugColor.RED;
		bugColor[] sideC = new bugColor[1] ;
		sideC[0] = bugColor.PURPLE;
		Board myBoard = new Board (sideA,  sideB, sideC);
		Piece myPiece = new Piece (bugColor.PURPLE, bugColor.GREEN, bugColor.RED);
		Piece myPiece2 = new Piece (bugColor.RED, bugColor.PURPLE, bugColor.GREEN );
		Piece myPiece3 = new Piece (bugColor.PURPLE, bugColor.RED, bugColor.GREEN);
		Piece[] myArray = new Piece[1];
		Piece[] myArray2 = new Piece[1];
		Piece[] myArray3 = new Piece[1];
		myArray[0]= myPiece;
		myArray2[0]= myPiece2;
		myArray3[0]= myPiece3;
		myBoard.solver(myArray);
		myBoard.solver(myArray2);
		myBoard.solver(myArray3);*/
	}
	public static boolean solver(Piece[] pieceArray, int[] coords, Board puzzle) {
		//input:
		//position to check. coord ints?
		
		//board
		
		//list of pieces? noting which are currently on the board?
		
		Space hole = puzzle.getTriAzzle()[coords[0]][coords[1]];
		System.out.println("Trying to fill hole at " + hole.showCoords());
		
		if (hole.getCoords()[1] %2 == 0 && hole.getCoords()[1] != 0) {
			switch (puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getBottomEdge()) {
			case 1: hole.setMatch1(puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getSide3());
					break;
			case 2: hole.setMatch1(puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getSide1());
					break;
			case 3: hole.setMatch1(puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getSide2());
					break;
			default: System.out.println("something went horribly wrong. bottom must be 1, 2, or 3.");
					break;
			}
			 //could also use findPrev function here
		} else if (hole.getCoords()[1] %2 != 0) {
			switch (puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getBottomEdge()) {
			case 1: hole.setMatch1(puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getSide2());
					break;
			case 2: hole.setMatch1(puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getSide3());
					break;
			case 3: hole.setMatch1(puzzle.getTriAzzle()[coords[0]][coords[1]-1].getContains().getSide1());
					break;
			default: System.out.println("something went horribly wrong. bottom must be 1, 2, or 3.");
					break;
			}
			//could also use findPrev function here
			
			switch (puzzle.getTriAzzle()[coords[0]-1][coords[1]-1].getContains().getBottomEdge()) {
			case 1: hole.setMatch2(puzzle.getTriAzzle()[coords[0]-1][coords[1]-1].getContains().getSide3());
					break;
			case 2: hole.setMatch2(puzzle.getTriAzzle()[coords[0]-1][coords[1]-1].getContains().getSide1());
					break;
			case 3: hole.setMatch2(puzzle.getTriAzzle()[coords[0]-1][coords[1]-1].getContains().getSide2());
					break;
			default: System.out.println("something went horribly wrong. bottom must be 1, 2, or 3.");
					break;
			}
			 
		}
		
		for (Piece index: pieceArray) {
			if (index.getCoords() == null && matching1(hole, index)) {
				if (puzzle.getNumFill()==puzzle.getSize()) {
					System.out.println("Hooray, triazzle solved!");
					return true; //base case
				} else {
					if (solver(pieceArray, findNext(puzzle, index.getCoords()), puzzle)) {
						return true;
					} else {
						unMatchTask(hole, index);
						//if solver is false, undo various things, and keep going?						
					}
				}
			} else if (index.getCoords() == null && matching2(hole, index)) {
				if (puzzle.getNumFill()==puzzle.getSize()) {
					System.out.println("Hooray, triazzle solved!");
					return true; //base case
				} else {
					if (solver(pieceArray, findNext(puzzle, index.getCoords()), puzzle)) {
						return true;
					} else {
						unMatchTask(hole, index);
						//if solver is false, undo various things, and keep going?						
					}
				}
			} else if (index.getCoords() == null && matching3(hole, index)) {
				if (puzzle.getNumFill()==puzzle.getSize()) {
					System.out.println("Hooray, triazzle solved!");
					return true; //base case
				} else {
					if (solver(pieceArray, findNext(puzzle, index.getCoords()), puzzle)) {
						return true;
					} else {
						unMatchTask(hole, index);
						//if solver is false, undo various things, and keep going?						
					}
				}
			}
		}
		System.out.println("Unable to fill hole at " + hole.showCoords());
		//reset matches to null
		if (hole.getCoords()[1] %2 == 0 && hole.getCoords()[1] != 0) {
			hole.setMatch1(null);
		} else if (hole.getCoords()[1] %2 != 0) {
			hole.setMatch1(null);
			hole.setMatch2(null);
		}
		return false;
		
	}
	
	public static void unMatchTask(Space hole, Piece fit) { //things to do when a match is ruled a failure
		hole.setContains(null);
		fit.setCoords(null);
		hole.getOfBoard().setNumFill(hole.getOfBoard().getNumFill()-1);
		fit.setBottomEdge(6);//to ensure errors if not updated later?
		System.out.println("Removed piece with sides " + fit.getSide1() + ","+ fit.getSide2()+ ","+ fit.getSide3());
		
		System.out.println("Board now has " +hole.getOfBoard().getNumFill()+ " fitting pieces.");
	}
	public static void matchTask(Space hole, Piece fit) { //list of general things to do when match is made 
		hole.setContains(fit);
		fit.setCoords(hole.getCoords());
		hole.getOfBoard().setNumFill(hole.getOfBoard().getNumFill()+1);
		System.out.println("Piece with sides: " + fit.getSide1() + ","+ fit.getSide2()+ ","+ fit.getSide3());
		System.out.println("Hole needs sides: " + hole.getMatch1() + ","+ hole.getMatch2()+ ","+ hole.getMatch3());
		System.out.println("Hole now contains: " + hole.getContains().getSide1()+hole.getContains().getSide2()+hole.getContains().getSide3()); 
		System.out.println("Piece fits in " +hole.showCoords()+ " with side "+ fit.getBottomEdge() + " on bottom!");
		System.out.println("Board now has " +hole.getOfBoard().getNumFill()+ " fitting pieces.");

	}
	
	public static int[] findNext(Board puzzle, int[] coords) {
		System.out.println("locating coords of next spot...");
		if (coords[0] == puzzle.getSide1().length -1 && coords[1]== coords[0]*2) {
			System.out.println("end of puzzle. Can't go further. Error.");
			return null;
		} else if (coords[1]== coords[0]*2) {
			return new int[] {coords[0]+1, 0};
		} else {
			return new int[] {coords[0], coords[1]+1};
		}
		
		
	}
	
	public int[] findPrev(Board puzzle, int[] coords) {
		if (coords[0] == 0 && coords[1] == 0) { 
			System.out.println("beginning of puzzle");
			return null;
		} else if (coords[1] == 0) {
			return new int[] {coords[0]-1, (coords[0]-1)*2};
		} else {
			return new int[] {coords[0], coords[1]-1};
		}
		
		
	}
 //not sure if i use findPrev??
	
	public Piece[] checkLength(Piece[] pieceArray) { //do i need this? as outer shell function?
		

/*		
		if (pieceArray.length != side1.length*side1.length) {
			System.out.println("Wrong number of pieces.");
			return null;
		}
		if (side1.length != side2.length || side3.length != side2.length || side1.length != side3.length) {
			System.out.println("Sides do not match in length");
			return null;
		}*/
		
		
		return null;
		
	}

	public static boolean matching1(Space hole, Piece fit) {
		if ((fit.getSide1() == hole.getMatch1() || hole.getMatch1() == null) &&
				(fit.getSide2()==hole.getMatch2() || hole.getMatch2() == null) && 
				(fit.getSide3()==hole.getMatch3() || hole.getMatch3() == null)) {
			fit.setBottomEdge(1);
			System.out.println("we have a match!");
			matchTask(hole, fit);
			return true;
		} else {
			System.out.println("no match!");
			return false;
			}
		}

	public static boolean matching2(Space hole, Piece fit) {
		if ((fit.getSide2() == hole.getMatch1() || hole.getMatch1() == null) &&
				(fit.getSide3()==hole.getMatch2() || hole.getMatch2() == null) && 
				(fit.getSide1()==hole.getMatch3() || hole.getMatch3() == null)) {
			fit.setBottomEdge(2);
			System.out.println("we have a match!");
			matchTask(hole, fit);
			return true;
		} else {
			System.out.println("no match!");
			return false;
			}
		}
	public static boolean matching3(Space hole, Piece fit) {
		if ((fit.getSide3() == hole.getMatch1() || hole.getMatch1() == null) &&
				(fit.getSide1()==hole.getMatch2() || hole.getMatch2() == null) && 
				(fit.getSide2()==hole.getMatch3() || hole.getMatch3() == null)) {
			fit.setBottomEdge(3);
			System.out.println("we have a match!");
			matchTask(hole, fit);
			return true;
		} else {
			System.out.println("no match!");
			return false;
			}
		}
	

	
/*	public static int[][] createTriArray(Board myBoard){
		
		int[][] triArray = new int[myBoard.getSide1().length][];
		for (int i = 0; i < triArray.length; i++) {
			triArray[i] = new int[i*2+1];
			for (int j =0;j<triArray[i].length;j++) {
				triArray[i][j] = 0;
			}
		}
		return triArray;
	}*/

	
	
	/*public static int[][] createTriArray(int length){
		
		int[][] triArray = new int[length][];
		for (int i = 0; i < length; i++) {
			triArray[i] = new int[i*2+1];
			for (int j =0;j<triArray[i].length;j++) {
				triArray[i][j] = 0;
			}
		}
		return triArray;
	}*/
		
 	public static void print() {
		int num = 4;
		
		for (int i = 0; i < num; i++) { //4 levels total
			for (int j = num-i; j>0 ; j--) { //number of spaces before each level
				System.out.print(" ");
			}
			
			for (int j = 0; j <= i; j++) {

				System.out.print("/_\\");
			}

			System.out.print("\n");
		}
		

		System.out.println("&");
		
	}
	public static void print2() {
		int num = 4;
		for (int i = 0; i < num; i++) { //4 levels total
			for (int j = 2*num-2*i-1; j>0 ; j--) { //number of spaces before first triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("  /\\"); // prints /
			}

			System.out.print("\n");
			for (int j = 2*num-2*i; j>0 ; j--) { //number of spaces before each level
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {
				

				
				System.out.print("/");
				for (int j = 0; j <= 1; j++) {

					System.out.print("_"); //prints two bottom lines
				}
				System.out.print("\\");
			}
			System.out.print("\n");
		}
		
		System.out.println("&");
		
	}
	public static void print3() {
		int num = 4;
		for (int i = 0; i < num; i++) { //4 levels total
			for (int j = 2*num-2*i-2; j>0 ; j--) { //number of spaces before first triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("    /\\"); // prints /
			}
			System.out.print("\n");
			
			for (int j = 2*num-2*i-1; j>0 ; j--) { //number of spaces before first triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("  /  \\"); // prints /
			}

			System.out.print("\n");
			for (int j = 2*num-2*i; j>0 ; j--) { //number of spaces before each level
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {
				

				
				System.out.print("/");
				for (int j = 0; j <= 3; j++) {

					System.out.print("_"); //prints three bottom lines
				}
				System.out.print("\\");
			}
			System.out.print("\n");
		}
	}
	public static void print4() {

		
		int num = 4;
				
		for (int i = 0; i < num; i++) { //4 levels total
			for (int j = 3*num-3*i; j>0 ; j--) { //number of spaces before first triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("/\\    "); // prints /
			}
			System.out.print("\n");
			
			for (int j = 3*num-3*i-1; j>0 ; j--) { //number of spaces before middle of triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("/  \\  "); // prints /
			}

			System.out.print("\n");
			for (int j = 3*num-3*i-2; j>0 ; j--) { //number of spaces before bottom of triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {
				
				System.out.print("/");
				for (int j = 0; j <= 3; j++) {

					System.out.print("_"); //prints three bottom lines
				}
				System.out.print("\\");
			}
			System.out.print("\n");
		}

	}
	
	public static void print5() {


		int num = 4;
				
		for (int i = 0; i < num; i++) { //4 levels total
			for (int j = 3*num-3*i; j>0 ; j--) { //number of spaces before first triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("/\\  c "); // prints /
			}
			System.out.print("\n");
			
			for (int j = 3*num-3*i-2; j>0 ; j--) { //number of spaces before middle of triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {//each triangle in a row

				System.out.print("a/ab\\b"); // prints /
			}

			System.out.print("\n");
			for (int j = 3*num-3*i-2; j>0 ; j--) { //number of spaces before bottom of triangle
				System.out.print(" ");
			}			
			for (int k = 0; k <= i; k++) {
				
				System.out.print("/");

				System.out.print("__c_"); //prints three bottom lines and picture
				
				System.out.print("\\");
			}
			System.out.print("\n");
		}

	
	}

	
}
