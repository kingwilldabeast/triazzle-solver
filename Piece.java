package triazzlePuzzleSolver;

public class Piece {
	
	/** 
     * Represents a three-sided puzzle piece. Each side has one value, 
     * which allows it to be match to a corresponding value. 
     * 
     *
     * @param side1     the side that holds the first value 
     * @param side2     the side that holds the second value    
     * @param side3     the side that holds the third value
     * @param bottomEdge  the side which is considered to be on the bottom. 
     * 					This allows the orientation of the piece to be set and remembered.
     * @param coords    the location of the piece on the puzzle, or null if it is not placed on the puzzle.
     */
	
	private bugColor side1;
	private bugColor side2;
	private bugColor side3;
	private int bottomEdge;
	private int[] coords;	//null means not on board yet
	
	
	Piece (bugColor side1, bugColor side2, bugColor side3, int bottomEdge) {
		this.setSide1(side1);
		this.setSide2(side2);
		this.setSide3(side3);
		this.setBottomEdge(bottomEdge);
		this.setCoords(null);
		
	}
	
	Piece (bugColor side1, bugColor side2, bugColor side3) {
		this( side1,  side2, side3, 1);
		
	}
		
	public int getBottomEdge() {
		return bottomEdge;
	}

	public void setBottomEdge(int bottomEdge) {
		this.bottomEdge = bottomEdge;
	}

	public bugColor getSide1() {
		return side1;
	}

	public void setSide1(bugColor side1) {
		this.side1 = side1;
	}

	public bugColor getSide2() {
		return side2;
	}

	public void setSide2(bugColor side2) {
		this.side2 = side2;
	}

	public bugColor getSide3() {
		return side3;
	}

	public void setSide3(bugColor side3) {
		this.side3 = side3;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

	enum bugColor {
		RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE,  
	}
	
	enum bugType {
		
		FLYHEAD, FLYTAIL, ANTHEAD, ANTTAIL, WASPHEAD, WASPTAIL, 
		BEEHEAD, BEETAIL, BUGHEAD, BUGTAIL, TICHEAD, TICTAIL, //$~ @& () [] {} <> 
	}
	
	public boolean matchHalves(bugType first, bugType second) {
		if (first == bugType.FLYHEAD && second == bugType.FLYTAIL) {
			return true;
		} else if (first == bugType.FLYTAIL && second == bugType.FLYHEAD) {
			return true;
		} else if (first == bugType.ANTHEAD && second == bugType.ANTTAIL) {
			return true;
		} else if (first == bugType.ANTTAIL && second == bugType.ANTHEAD) {
			return true;
		} else if (first == bugType.WASPHEAD && second == bugType.WASPTAIL) {
			return true;
		} else if (first == bugType.WASPTAIL && second == bugType.WASPHEAD) {
			return true;
		} else if (first == bugType.BEEHEAD && second == bugType.BEETAIL) {
			return true;
		} else if (first == bugType.BEETAIL && second == bugType.BEEHEAD) {
			return true;
		} else if (first == bugType.BUGHEAD && second == bugType.BUGTAIL) {
			return true;
		} else if (first == bugType.BUGTAIL && second == bugType.BUGHEAD) {
			return true;
		} else if (first == bugType.TICHEAD && second == bugType.TICTAIL) {
			return true;
		} else if (first == bugType.TICTAIL && second == bugType.TICHEAD) {
			return true;
		} 
		return false;
	}
	
	public void showInsect(bugType input) {
		switch (input) {
		case FLYHEAD: System.out.println("$");
					break;
		case FLYTAIL: System.out.println("~");
					break; 
		case ANTHEAD: System.out.println("@");
					break; 
		case ANTTAIL: System.out.println("=");
					break; 
		case WASPHEAD: System.out.println("[");
					break; 
		case WASPTAIL: System.out.println("]");
					break; 
		case BEEHEAD: System.out.println("(");
					break;
		case BEETAIL: System.out.println(")");
					break; 
		case BUGHEAD: System.out.println("{");
					break; 
		case BUGTAIL: System.out.println("}");
					break; 
		case TICHEAD: System.out.println("<");
					break; 
		case TICTAIL: System.out.println(">");
					break;
		}
		
	}

}
