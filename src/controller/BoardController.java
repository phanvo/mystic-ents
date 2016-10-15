package controller;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.board.BoardState;
import model.board.Square;
import model.piece.Team;
import utils.CFacade;
import utils.GameConfig;
import view.BoardFrame;

/**
 * Controller for board state Generates the Board Model Generates the Board View
 * Observes Board Model changes and calls View update
 * 
 * @author Mark
 *
 */

public class BoardController implements Observer {

	// PIECE CONTROLLER
	private ActionController actionController;

	// VIEW
	private BoardFrame boardFrame;

	// MODEL
	private BoardState boardState;
	private BoardData boardData;

	private CFacade systemUtils = CFacade.getInstance();

	public BoardController() {
		boardState = new BoardState();
		boardFrame = new BoardFrame(this);
		boardData = BoardData.getInstance();
		observe(boardData);
	}

	public void init() {
		boardState.init();
	}

	public void buildBoard() {
		boardFrame.pack();
		boardFrame.getBoardPanel().setLayout(new GridLayout(GameConfig.getROW_COL(), GameConfig.getROW_COL()));
		CFacade.getInstance().buildFullBoard(boardFrame.getBoardPanel(), boardData.getBoardArray());
	}

	public void getRangeCells(Square origin) {
		clearRangeCells();
		boardData.setBoardArray(systemUtils.getRangeCells(origin.getID()[0], origin.getID()[1], boardData.getBoardArray()));
		updateBoard();
	}

	public void restoreValuesFromSave(BoardData data) {
		getBoardData().setCurrentTeam(data.getCurrentTeam());
		getBoardData().setBoardArray(data.getBoardArray());
	}
	
	public void restoreUndoStateFromSave(BoardData data) {		
		for (Team t : data.getTeamUndo().keySet()) {
			Boolean isUndo = data.getTeamUndo().get(t);
			getBoardData().setTeamUndo(t, isUndo);
		}
	}
	
	public void clearRangeCells() {
		systemUtils.clearRangeCells();
		updateBoard();
	}

	public void updateBoard() {
		boardData.doCellsUpdate();
	}
	
	public void disableBoard() {
		CFacade.getInstance().disableBoard(getBoardFrame().getBoardPanel());	
	}
	public void enableBoard() {
		CFacade.getInstance().enableBoard(getBoardFrame().getBoardPanel());	
	}
	

	@Override
	public void update(Observable o, Object arg) {
		Square[][] data = ((BoardData) o).getBoardArray();
		if (data == null)
			return;
		boardFrame.getBoardPanel().refreshBoard(data);
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}
	
	/** UNDO functionality **/
	public boolean undo(int i) {
		return CFacade.getInstance().undoMove(i, this);
	}

	public BoardData getBoardData() {
		return boardData;
	}

	public BoardFrame getBoardFrame() {
		return boardFrame;
	}

	public void setPieceActionController(ActionController a) {
		this.actionController = a;
	}

	public ActionController getPieceActionController() {
		return actionController;
	}


}