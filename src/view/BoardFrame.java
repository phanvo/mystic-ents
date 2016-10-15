package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.BoardController;
import utils.GameConfig;
import view.mediator.MediatorView;

/**
 * Outer container of the board.
 * 
 * @author skh
 *
 */
public class BoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/** Draws the shape. */
	private BoardPanel gamePanel;
	private MediatorView controlView;

	public BoardFrame(BoardController boardController) {
		super(GameConfig.GAME_TITLE);
		buildFrame();
		buildUI(boardController);
	}

	/**
	 * Adding and configuring properties of the frame
	 */
	private void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		c.setBackground(Color.YELLOW);

		// adjust size using Dimension.
		c.setPreferredSize(new Dimension(GameConfig.getDefaultWidth() + GameConfig.getControlsWidth(), GameConfig.getDefaultHeight()));
		
		// standard JFrame startup...
		this.setResizable(false);
		this.setVisible(false);
	}

	/**
	 * Creating the board panel and control panel
	 */
	private void buildUI(BoardController boardController) {
		gamePanel = new BoardPanel();
		this.add(gamePanel, BorderLayout.CENTER);

		controlView = new MediatorView();
		this.add(controlView, BorderLayout.EAST);
	}

	public BoardPanel getBoardPanel() {
		return gamePanel;
	}

	public MediatorView getControlView() {
		return controlView;
	}

}
