package edu.wm.cs.cs301.guimemorygame.model;

import java.util.Random;

import edu.wm.cs.cs301.guimemorygame.view.MemoryGameButton;

public class MemoryGameModel {

	private char[] alphabet;
	
	private final MemoryGameButton[][] board;
	
	private int turnCount=1;

	public MemoryGameModel() {
		setAlphabet();
		int numSymbols = getRows() * getCols() / 2;
		this.board = new MemoryGameButton[getRows()][getCols()];
		for (int i = 0; i < numSymbols; i++) {
			addPiece(alphabet[i]);
			addPiece(alphabet[i]);
		}
	}
	
	public void addPiece(char c) {
		Random rand = new Random();
		int m = rand.nextInt(0, this.board.length);
		int n = rand.nextInt(0, this.board[0].length);
		if (this.board[m][n] == null) {
			this.board[m][n] = new MemoryGameButton(c);
			return;
		}
		addPiece(c);
	}
	
	public MemoryGameButton[][] getBoard(){
		return this.board;
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
