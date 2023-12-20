package edu.wm.cs.cs301.guimemorygame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
import edu.wm.cs.cs301.guimemorygame.view.InstructionsDialog;
import edu.wm.cs.cs301.guimemorygame.view.MemoryGameFrame;

public class GUIMemoryGame implements Runnable {

	public static		 void main(String[] args) {
		SwingUtilities.invokeLater(new GUIMemoryGame());

		// use cross-platform look and feel so button backgrounds work on Mac
		if (!System.getProperty("os.name").contains("Windows")) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run() {
		new InstructionsDialog(new MemoryGameFrame(new MemoryGameModel()));
	}

}