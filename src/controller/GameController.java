package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;

import utils.CFacade;
import view.MainMenuFrame;
import view.mediator.DialogView;

/**
 * Responsible for turn handling
 * Via Timer and end turn handling
 * And managing UI view
 * 
 * Generate game data, pieces that will be passed to the Board
 * Creates the Board, then manages game flow of control.
 *
 * @author skh, mark, pv
 *
 */

public class GameController implements Observer {

	//GAME CONTROL
	private GameTurn gameTimer;

	private BoardController boardController;
	private ActionController actionController;
	private Boolean isAITurn = false;

	//UI
	private UIMediator uiMediator;

	//BOARD OBJECTS
	private Team currentTeam;
	
	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();

	public GameController() {
		uiMediator = UIMediator.getInstance();
		actionController = ActionController.getInstance();
		buildTimer();
		new MainMenuFrame(this);
	}

	public void newGame() {
		boardController.init();		
		startGame();
	}
	
	public void continueGame() {
		CFacade.getInstance().setUpGameFromLoad(boardController.getBoardData());
		startGame();
	}
	
	/**
	 * START THE GAME.
	 * The Board Data will have been loaded or constructed at this point.
	 * In which case, this is the point where the two start states converge.
	 * We can now build the board, assign the controllers, build the AI.
	 * As we have established the game assets and populated the gamePiecesList.
	 * Mediator and Facade patterns come into play here, e.g. through the assigning controllers to the Mediator.
	 * When all is set up, we start the game timer.
	 * 
	 * @author Mark
	 * 
	 */		
	
	public void startGame() {
		gamePiecesList = CFacade.getInstance().getGamePieces();
		currentTeam = loadCurrentTeam();
		boardController.getBoardData().setCurrentTeam(currentTeam);
		boardController.buildBoard();
		boardController.getBoardFrame().setVisible(true);
		uiMediator.setBoardController(boardController);
		uiMediator.setGameController(this);
		CFacade.getInstance().populateAIObjects();
		//Start Game running
		gameTimer.startTimer();
	}	

	/**
	 * The update method of the Observer pattern
	 * Watches the GameTurn timer to handle end turns, team change,
	 * And to update the UI
	 */	
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTimer = (GameTurn) o;
		//Post condition exception if GameTurn is null, return (exit?)
		if (gameTimer == null) return;		
		uiMediator.setPieceCount(getAvailablePieceCount());
		uiMediator.doUIUpdate(gameTimer);
		
		//AI Turn
		try {
			if (CFacade.getInstance().checkAIStatus(currentTeam)) {
				CFacade.getInstance().doAIGameTurn(actionController, currentTeam);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// when time is up
		if (gameTimer.getGameTime() == 0) {
			handleEndTurn();
		}
	}

	/**
	 * EndTurn handler
	 * Called via update method when GameTurn.getGameTimer = 0
	 * A turn is ended via the timer value rather than calling a set function
	 * Gives better adherence to MVC pattern.
	 * 
	 */		
	private void handleEndTurn() {
		//Change teams
		currentTeam = changeTeams();
		//Update UI
		updateUI();
		//Clear Board
		boardController.clearRangeCells();
		//Reset TraitValues of all pieces on board to base value
		CFacade.getInstance().resetPieceTraitValueToBase(gamePiecesList);
		//reset ActionController
		resetActionController();
		// set game turn count;
		gameTimer.setCount(gameTimer.getCount()+1);
		//Check AI Status
		isAITurn = currentTeam.getAI();
	}
	
	private void updateUI() {
		uiMediator.setCurrentTeam(currentTeam);
		uiMediator.setPieceCount(getAvailablePieceCount());
		uiMediator.doUIEndTurn(currentTeam);		
	}
	
	private void resetActionController() {
		actionController.resetActionCount();
		actionController.setActivePiece(null);	
	}
	
	public Boolean loadGame(){
		BoardData data = CFacade.getInstance().loadGame();
		if (CFacade.getInstance().loadGame() != null) {
			boardController.restoreValuesFromSave(data);			
			for (Team t : data.getTeamUndo().keySet()) {
				Boolean isUndo = data.getTeamUndo().get(t);
				boardController.getBoardData().setTeamUndo(t, isUndo);
			}
			if(uiMediator != null) uiMediator.checkUndoButton();
			return true;
		} else {
			DialogView.getInstance().showInformation("Save game not found!");
			return false;
		}
	}	
	
	private Team changeTeams() {
		Team t = CFacade.getInstance().getNextTeam(gamePiecesList, currentTeam);
		boardController.getBoardData().setCurrentTeam(t);
		return t;
	}
	
	private Team loadCurrentTeam() {
		Team team = boardController.getBoardData().getCurrentTeam();
		team = team == null ? Team.values()[0] : team;
		uiMediator.setCurrentTeam(team);
		return team;
	}	

	private int getAvailablePieceCount() {
		return CFacade.getInstance().getActivePieces(gamePiecesList, currentTeam).size();
	}
		
	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		uiMediator.updatePieceInformation(pce);
	}

	public void setMessage(String msg) {
		uiMediator.setMoveInfoMessage(currentTeam + " : " + msg);
	}
	
	private void buildTimer() {
		gameTimer = new GameTurn();
		gameTimer.buildTimer();
		observe(gameTimer);
	}	

	public UIMediator getUiMediator() {
		return uiMediator;
	}
	
	public void setUiMediator(UIMediator uiMediator) {
		this.uiMediator = uiMediator;
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}

	public ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	

	public void setBoardController(BoardController gameBoard) {
		this.boardController = gameBoard;
	}
	
	public void setActionController(ActionController actionController) {
		this.actionController = actionController;
	}
	
	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public GameTurn getGameTimer() {
		return gameTimer;
	}

	public BoardController getBoardController() {
		return boardController;
	}	
	
	public Boolean getAiTurn() {
		return isAITurn;
	}

	public void setAiTurn(Boolean aiTurn) {
		this.isAITurn = aiTurn;
	}
}