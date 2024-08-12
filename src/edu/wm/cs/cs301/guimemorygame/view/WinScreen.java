package edu.wm.cs.cs301.guimemorygame.view;

import javax.swing.JDialog;

public class WinScreen extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private MemoryGameFrame parentFrame;
	
	public WinScreen(MemoryGameFrame parentFrame, int turns) {
        super(parentFrame.getFrame(), "Congratulations!", true);
        this.parentFrame = parentFrame;

        add(createMainPanel(turns), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parentFrame.getFrame());
        setVisible(true);
	}

}
