package edu.wm.cs.cs301.guimemorygame.view;

import edu.wm.cs.cs301.guimemorygame.model.Difficulty;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.guimemorygame.model.MemoryGameModel;
public class MemoryGameFrame {

	private final JFrame frame;

	private final MemoryGameModel model;

	private final MemoryGamePanel memoryGamePanel;
	
	private final JLabel turnLabel;

	public MemoryGameFrame(MemoryGameModel model) {
		this.model = model;
		this.memoryGamePanel = new MemoryGamePanel(this, model, 300);
		this.turnLabel = createTurnLabel();
		this.frame = createAndShowGUI();
	}

	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Memory Game");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});

		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(memoryGamePanel, BorderLayout.CENTER);
		frame.add(turnLabel, BorderLayout.SOUTH);

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		System.out.println("Frame size: " + frame.getSize());

		return frame;
	}
	
	private JLabel createTurnLabel() {
		JLabel label = new JLabel("Turns: " + model.getTurnCount());
		label.setFont(new Font("Helvetica", Font.BOLD, 20));
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}
	
	public void updateTurnLabel() {
		turnLabel.setText("Turns: " + model.getTurnCount());
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu difficultyMenu = new JMenu("Difficulty");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(difficultyMenu);
		menuBar.add(helpMenu);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new CancelAction());
		helpMenu.add(exitItem);

		JMenuItem easyItem = new JMenuItem(new DifficultyAction("Easy", Difficulty.EASY));
		JMenuItem mediumItem = new JMenuItem(new DifficultyAction("Medium", Difficulty.MEDIUM));
		JMenuItem hardItem = new JMenuItem(new DifficultyAction("Hard", Difficulty.HARD));

		difficultyMenu.add(easyItem);
		difficultyMenu.add(mediumItem);
		difficultyMenu.add(hardItem);

		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		instructionsItem.addActionListener(event -> new InstructionsDialog(this));
		helpMenu.add(instructionsItem);

		return menuBar;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());

		JLabel title = new JLabel("Memory Game");
		title.setFont(AppFonts.getTitleFont());
		panel.add(title);

		return panel;
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public void repaintMemoryGamePanel() {
		memoryGamePanel.repaint();
	}

	public void shutdown() {
		// model.getStatistics().writeStatistics();
		frame.dispose();
		System.exit(0);
	}
	
	public boolean isWon() {
		return memoryGamePanel.isWon();
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}

	}

	public class DifficultyAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		private final Difficulty difficulty;

		public DifficultyAction(String difficultyName, Difficulty difficulty) {
			this.difficulty = difficulty;
			putValue(NAME, difficultyName);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			startNewGame(difficulty);
		}

		private void startNewGame(Difficulty difficulty) {
			// model.getStatistics().writeStatistics();
			frame.dispose();
			// model.reset(difficulty);
			new MemoryGameFrame(model);
		}
	}

}
