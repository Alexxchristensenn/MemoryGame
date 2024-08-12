package edu.wm.cs.cs301.guimemorygame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Timer;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameButton;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameFrame;

public class ButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private final MemoryGameFrame view;
	private final MemoryGameModel model;

	private MemoryGameButton button1;
	private MemoryGameButton button2;

	private boolean secondFlip;
	private boolean processingClick;

	public ButtonAction(MemoryGameFrame v, MemoryGameModel m) {
		this.view = v;
		this.model = m;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (processingClick || secondFlip && (MemoryGameButton) event.getSource() == button1) {
			return;
		}
		MemoryGameButton button = (MemoryGameButton) event.getSource();
		button.setFaceUp();
		checkTurn(button);
	}

	public void checkTurn(MemoryGameButton button) {
		while (true) {
			if (this.secondFlip) {
				processingClick = true;
				this.button2 = button;
				if (this.button1.equals(this.button2)) {
					button1.setEnabled(false);
					button2.setEnabled(false);
					if (view.isWon()) {
						
					}
					secondFlip = false;
					processingClick = false;
					break;
				} else {
					Timer timer = new Timer(2000, new ActionListener() {

					    @Override
					    public void actionPerformed(ActionEvent arg0) {            
					        button1.setFaceDown();
					        button2.setFaceDown();
					        button1.setEnabled(true);
					        button2.setEnabled(true);
					        model.incrementTurnCount();
					        view.updateTurnLabel();
					        processingClick = false;
					    }
					});
					timer.setRepeats(false);
					timer.start();
					secondFlip = false;
					break;
				}
			} else {
				this.button1 = button;
				secondFlip = true;
				break;
			}
		}
	}

}
