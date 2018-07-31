package triazzlePuzzleSolver;

import triazzlePuzzleSolver.Piece.bugColor;

public class Board {
	
	private bugColor[] side1;
	private bugColor[] side2;
	private bugColor[] side3;
	private int numFill;
	private int size;
	private Space[][] triAzzle;
	
	public Board (bugColor[] side1, bugColor[] side2, bugColor[] side3) {
		this.setSide1(side1);
		this.setSide2(side2);
		this.setSide3(side3);
		this.numFill = 0;
		this.setSize(side1.length*side1.length);
		this.triAzzle = createTriArray();
	}
	
public  Space[][] createTriArray(){
		
		int length = this.getSide1().length;
		Space[][] triArray = new Space[length][];
		for (int i = 0; i < length; i++) {
			triArray[i] = new Space[i*2+1];
			for (int j =0;j<triArray[i].length;j++) {
				triArray[i][j] = new Space(this,new int[]{i,j});
				if (j == 0) {
					triArray[i][j].setMatch1(this.getSide1()[i]);
				}
				if (j == i*2) {
					triArray[i][j].setMatch2(this.getSide2()[length-1-i]);
				}
				if (i == length-1 && j%2 ==0) {
					triArray[i][j].setMatch3(this.getSide3()[j/2]);
				}
			}
		}
		return triArray;
	}
	

	public int getNumFill() {
		return numFill;
	}


	public void setNumFill(int numFill) {
		this.numFill = numFill;
	}





	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public bugColor[] getSide1() {
		return side1;
	}

	public void setSide1(bugColor[] side1) {
		this.side1 = side1;
	}

	public bugColor[] getSide2() {
		return side2;
	}

	public void setSide2(bugColor[] side2) {
		this.side2 = side2;
	}

	public bugColor[] getSide3() {
		return side3;
	}

	public void setSide3(bugColor[] side3) {
		this.side3 = side3;
	}


	public Space[][] getTriAzzle() {
		return triAzzle;
	}


	public void setTriAzzle(Space[][] triAzzle) {
		this.triAzzle = triAzzle;
	}
	
	

}
