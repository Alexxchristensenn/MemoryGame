package edu.wm.cs.cs301.guimemorygame.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class MemoryGameButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	private boolean isFaceUp;
	private char letter;
	
	public MemoryGameButton(char c) {
		super();
		this.letter = c;
		updateAppearance();
	}
	
	public void flip() {
		this.isFaceUp = !this.isFaceUp;
		updateAppearance();
	}
	
	public void setFaceUp() {
		this.isFaceUp = true;
		updateAppearance();
	}
	
	public void setFaceDown() {
		this.isFaceUp = false;
		updateAppearance();
	}
	
	public boolean isFaceUp() {
		return isFaceUp;
	}
	
	private void updateAppearance() {
		if (isFaceUp) {
			setText(Character.toString(letter));
			setBackground(Color.white);
			setForeground(Color.darkGray);
			setFont(new Font("Helvetica", Font.BOLD, 16));
		} else {
			setText("");
			setBackground(Color.lightGray);
			setForeground(Color.darkGray);
		}
		
	}
	
	private char getLetter() {
		return this.letter;
	}
	
	public boolean equals(MemoryGameButton x) {
		if (this.letter == x.getLetter()){
			return true;
		} return false;
	}

}
