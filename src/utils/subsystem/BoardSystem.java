package utils.subsystem;

import java.awt.Component;
import java.util.ArrayList;

import model.board.BoardData;
import model.board.Square;
import model.piece.Piece;
import utils.CFacade;
import view.BoardPanel;
import view.SquareView;

public class BoardSystem {
	
	private SquareView[][] squareViewArray;

	public BoardSystem () {}
	
	public void updateBoard(ArrayList<Square> rangeList) {
		try {
			for(int i = 0; i < rangeList.size(); i++) {
				squareViewArray[ rangeList.get(i).getID()[0] ][ rangeList.get(i).getID()[1] ].buildView(rangeList.get(i));
			}
		} catch (Exception e) {
			System.out.println("Update failed or interrupted: " + e.getMessage());
		}
	}
	
	public ArrayList<Square> buildFullBoard(BoardPanel boardPanel, Square[][] data) {
		ArrayList<Square> rangeList = new ArrayList<Square>();
		boardPanel.removeAll();
		squareViewArray = new SquareView[data.length][data.length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				SquareView sqr = new SquareView(data[i][j]);
				squareViewArray[i][j] = sqr;
				rangeList.add(sqr.getSqrObj());
				boardPanel.add(sqr);
			}
		}
		return rangeList;
	}
	
	public void disableBoard(BoardPanel boardPanel) {
		for (Component com : boardPanel.getComponents()) {
			if (com instanceof SquareView) {
				SquareView sv = (SquareView)com;
				sv.removeListener();
			}
		}
	}
	public void enableBoard(BoardPanel boardPanel) {
		for (Component com : boardPanel.getComponents()) {
			if (com instanceof SquareView) {
				SquareView sv = (SquareView)com;
				sv.setListener();
			}
		}
	}
	

}
