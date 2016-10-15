package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import model.board.BoardData;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;

import utils.CFacade;

import view.mediator.DialogView;
import view.mediator.EndTurnPanel;
import view.mediator.MoveInfoPanel;
import view.mediator.PieceInfoPanel;
import view.mediator.SaveGamePanel;
import view.mediator.TeamColorPanel;
import view.mediator.TimePanel;
import view.mediator.UndoPanel;

public class UIMediator {
	
	private BoardController boardController;
	private GameController gameController;
	private DialogView dialogView;
	
	private TimePanel pnTime;
	private TeamColorPanel pnTeamColor;
	private PieceInfoPanel pnPieceInfo;
	private EndTurnPanel pnEndTurn;
	private UndoPanel pnUndo;
	private SaveGamePanel pnSaveGame;
	private MoveInfoPanel pnMoveInfo;	
	
	private static UIMediator instance;

	private UIMediator() { }

	public static synchronized UIMediator getInstance()
	{
		if(instance == null)
			instance = new UIMediator();      
		return instance;
	}	   
	
	public void registerColleagues
	(
			TimePanel pnTime,
			TeamColorPanel pnTeamColor,
			PieceInfoPanel pnPieceInfo,
			UndoPanel pnUndo,
			SaveGamePanel pnSaveGame,
			EndTurnPanel pnEndTurn,
			MoveInfoPanel pnMoveInfo			
	)
	{
		
		/**
		 * Just a question:
		 * Wouldn't it be better if we used getters and setters for this kind of thing?
		 * The example was done this way, but it feels really unwieldy.
		 * 
		 */
		this.pnTime = pnTime;
		this.pnTeamColor = pnTeamColor;
		this.pnPieceInfo = pnPieceInfo;
		this.pnUndo = pnUndo;
		this.pnSaveGame = pnSaveGame;
		this.pnEndTurn = pnEndTurn;
		this.pnMoveInfo = pnMoveInfo;		
		this.dialogView  = DialogView.getInstance();
		
		//this.pnEndTurn.setGameTurn(gameController.getGameTimer());

	}
	
	public void doUndo(Object o) {
		if (!boardController.undo(Integer.parseInt(o.toString()))) {
			DialogView.getInstance().showInformation("Undo move number invalid.");
			return;
		} else {
			checkUndoButton();			
			// set undo value in save game for the team who used undo feature
			BoardData data = boardController.getBoardData();
			Team t = data.getCurrentTeam();
			data.setTeamUndo(t, Boolean.valueOf(true));
		}
		CFacade.getInstance().buildFullBoard(boardController.getBoardFrame().getBoardPanel(), boardController.getBoardData().getBoardArray());
		boardController.clearRangeCells();
		boardController.updateBoard();
	}	
	
	public void doUIEndTurn(Team t) {
		//reset the Piece info panel on switch team.
		pnPieceInfo.resetPieceInformation();
		// auto end the current player's turn
		pnEndTurn.executeEndTurn();
		//reset AI status
		pnTeamColor.setAIButtonToggle(t.getAI());
		checkUndoButton();
	}
	
	public void doUIUpdate(GameTurn gameTurn) {
		// update time on ControlPanel view		
		pnTime.setTime(gameTurn.getGameTime());
		// set end turn conditions
		pnEndTurn.setGameTurn(gameTurn);
	}
	
	public void disableBoard() {
		boardController.disableBoard();
	}
	
	public void enableBoard() {
		boardController.enableBoard();
	}
	
	public void handleEndGameUI(){
		// disable buttons in control panel
		disableAllButtons();
		// disable board game interactions
		boardController.disableBoard();
		// disable timer
		gameController.getGameTimer().setIsRunning(false);
		gameController.getGameTimer().stop();
		//Show victory banners...
		gameController.setMessage("Team " + gameController.getCurrentTeam() + " wins!");
		//showDialog("Team " + gameController.getCurrentTeam() + " wins!");
	}
	
	// each team has only one chance to undo
	public void checkUndoButton(){
		Team t = boardController.getBoardData().getCurrentTeam();
		Boolean isUndo = boardController.getBoardData().getTeamUndo().get(t);
		// enable/disable undo button
		if (isUndo != null) {
			// if this is a continue game
			pnUndo.getUndoButton().setEnabled(!isUndo.booleanValue());
		} else {
			// else, check the allowed undo number
			pnUndo.getUndoButton().setEnabled((t.getUndoNum() == 0) ? false : true);
		}		
	}
	
	public void setCurrentTeamAI() {
		Team t = boardController.getBoardData().getCurrentTeam();
		t.setAI(!t.getAI());
	}

	public void pauseGame() {	
		gameController.getGameTimer().setIsRunning(false);
		gameController.getGameTimer().stop();
	}
	
	public Boolean gameIsRunning() {
		return gameController.getGameTimer().getIsRunning();
	}
	
	public void unpauseGame() {
		gameController.getGameTimer().setIsRunning(true);
		gameController.getGameTimer().startTimer();
	}	
	
	public void disableAllButtons() {
		pnSaveGame.getSaveButton().setEnabled(false);
		pnUndo.getUndoButton().setEnabled(false);
		pnEndTurn.getEndTurnButton().setEnabled(false);
	}
	
	public void setMoveInfoMessage(String msg) {
		pnMoveInfo.setMessage(msg);
	}	
	
	public void updatePieceInformation(Piece pce) {
		pnPieceInfo.updatePieceInformation(pce);
	}
	
	public void setPieceCount(int count) {
		// update available pieces for the current team 
		pnTeamColor.setAvailablePieces(count);
	}	

	public void setCurrentTeam(Team team) {
		// update team color on ControlPanel view based on current team enum
		pnTeamColor.setTeamColor(new Color(team.getRed(),team.getGreen(),team.getBlue()));
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public void setBoardController(BoardController boardController) {
		this.boardController = boardController;
	}

	public GameController getGameController() {
		return gameController;
	}
	
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void showDialog(MouseEvent arg0, String msg) {
		dialogView.showInformation(msg, arg0.getXOnScreen(), arg0.getYOnScreen());
	}	
	public void showDialog(String msg) {
		dialogView.showInformation(msg);
	}	

}
