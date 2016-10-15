package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.board.Square;
import utils.CFacade;
import utils.GameConfig;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh, mark
 *
 */
public class BoardPanel extends JPanel {
	
	public BoardPanel() {
		super();
		this.setPreferredSize(new Dimension(GameConfig.getDefaultWidth(), GameConfig.getDefaultHeight()));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Draw the squares
	 */
	private void updateBoard(Square[][] board) {
//		ArrayList <Square> rangeList = new ArrayList <Square>();
//		rangeList = CFacade.getInstance().getRangeList();
		CFacade.getInstance().updateBoard();
	}
	
	public void buildFullBoard(Square[][] board) {
		this.removeAll();
		CFacade.getInstance().buildFullBoard(this, board);
	}
	
	public void refreshBoard(Square[][] boardData) {
		updateBoard(boardData);
		this.revalidate();
	}
	

}
