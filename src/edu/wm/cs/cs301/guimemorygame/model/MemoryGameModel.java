package edu.wm.cs.cs301.guimemorygame.model;

public class MemoryGameModel {

	private char[] alphabet;
	
	private int turnCount=1;

	public MemoryGameModel() {
		setAlphabet();
	}

	public int getRows() {
		return 4;
	}

	public int getCols() {
		return 7;
	}
	
	public int getTurnCount() {
		return this.turnCount;
	}
	
	public void incrementTurnCount() {
		this.turnCount+=1;
	}

	public char[] getAlphabet() {
		return this.alphabet;
	}

	public void setAlphabet() {
		Alphabet a = new GermanAlphabet();
		this.alphabet = a.toCharArray();
	}

}
