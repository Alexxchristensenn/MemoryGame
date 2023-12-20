package edu.wm.cs.cs301.guimemorygame.view;

import javax.swing.*;
import java.awt.*;

import edu.wm.cs.cs301.guimemorygame.controller.ButtonAction;
import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;

public class MemoryGamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final MemoryGameModel model;

	private MemoryGameButton[][] buttons;

	private ButtonAction action;
	

	public MemoryGamePanel(MemoryGameFrame memoryGameFrame, MemoryGameModel model, int width) {
		this.model = model;
		this.action = new ButtonAction(memoryGameFrame, model);
		setLayout(new GridLayout(model.getRows(), model.getCols(), 10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buttons = model.getBoard();

		for (int i = 0; i < model.getRows(); i++) {
            for (int j = 0; j < model.getCols(); j++) {
                buttons[i][j].setPreferredSize(new Dimension(80, 80));
                buttons[i][j].setMargin(new Insets(10, 10, 10, 10));
                buttons[i][j].addActionListener(this.action);
                buttons[i][j].setBackground(Color.lightGray);
                buttons[i][j].setForeground(Color.darkGray);

                add(buttons[i][j]);
            }
		}
		
	}
	
	public boolean isWon() {
		for (int i=0; i<model.getRows(); i++) {
			for (int j = 0; j<model.getCols(); j++) {
				if (!buttons[i][j].isFaceUp()) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	public void flipPiece(int x, int y) {
		buttons[x][y].flip();
	}

}
