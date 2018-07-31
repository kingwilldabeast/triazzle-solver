package triazzlePuzzleSolver;

import triazzlePuzzleSolver.Piece.bugColor;

public class Space {
	private bugColor match1;
	private bugColor match2;
	private bugColor match3;
	private Board ofBoard;
	private Piece contains;
	private int[] coords; //two number represent coords in 2D triangle array on board
	//private boolean isFull;
	
	
	Space(bugColor match1, bugColor match2, bugColor match3, Board ofBoard, int[] coords) {
		this.setMatch1(match1);
		this.setMatch2(match2);
		this.setMatch3(match3);
		this.setOfBoard(ofBoard);
		this.contains = null;
		this.setCoords(coords);
	}
	
	Space(Board ofBoard, int[] coords) {
		this.setMatch1(null);
		this.setMatch2(null);
		this.setMatch3(null);
		this.setOfBoard(ofBoard);
		this.contains = null;
		this.setCoords(coords);
	}
	
	Space() {
		this(null, null);
	}

	public String showCoords() {
		String answer = "["+this.coords[0]+","+this.coords[1]+"]";
		return answer;
	}

	public bugColor getMatch1() {
		return match1;
	}


	public void setMatch1(bugColor match1) {
		this.match1 = match1;
	}


	public bugColor getMatch2() {
		return match2;
	}


	public void setMatch2(bugColor match2) {
		this.match2 = match2;
	}


	public bugColor getMatch3() {
		return match3;
	}


	public void setMatch3(bugColor match3) {
		this.match3 = match3;
	}


	public Board getOfBoard() {
		return ofBoard;
	}


	public void setOfBoard(Board ofBoard) {
		this.ofBoard = ofBoard;
	}


	public Piece getContains() {
		return contains;
	}


	public void setContains(Piece hold) {
		this.contains = hold;
	}
	

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
	}

}
