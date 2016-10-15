package controller;

import model.board.BoardCareTaker;
import model.board.BoardMemento;
import model.board.Square;
import model.piece.Piece;
import model.state.IGameState;
import model.state.StateSelect;
import utils.CFacade;
import utils.GameConfig;

/**
 * This is main handler for selecting pieces and controlling their moves
 * Class is assigned access to the GameController
 * 
 * @author mark
 *
 */
public class ActionController implements IGameState {

	private GameController gameController;
	private BoardController boardController;

	private Piece activePiece;
	private Square activeSquare;
	private Square targetSquare;

	private int actionCount;
	private int actionButton;

	private IGameState gameState;

	private static ActionController instance;

	private BoardCareTaker careTaker;

	private ActionController(){
		gameState = StateSelect.getInstance(this);
		careTaker = BoardCareTaker.getInstance();
	}

	public static synchronized ActionController getInstance() {
		if (instance == null) {
			instance = new ActionController();
		}
		return instance;
	}

	/**
	 * Method controls number of actions permitted per turn
	 * Ends turn if at least 2 actions performed
	 * If less than 2 actions performed, increments the action counter
	 * 
	 * @author DS
	 */
	public void changeState(IGameState s) {
		gameState = s;
	}

	public void startAction(ActionController a, Square sqr)	{
		gameState.startAction(this, sqr);
	}

	public void endAction(ActionController a, Square sqr) {
		setTargetSquare(sqr);
		gameState.endAction(this, sqr);
		boardController.getBoardData().doCellsUpdate();
	}

	public void updateAction(ActionController a) {
		//check the game win condition before updating
		checkWinConditions(this);
		gameState.updateAction(this);
		checkActionCount();
	}

	public void checkWinConditions(ActionController a) {
		if(CFacade.getInstance().checkTowerWin(this, getTargetSquare()) ||
				CFacade.getInstance().checkSurvivorWin(this)) {
			UIMediator.getInstance().handleEndGameUI();
		};
	}

	public void resolveAttack(Piece a, Piece t) {
		int targetHealthValue = t.getTraitSet().getHealthTrait().getTraitValue();
		if ( targetHealthValue < 1) {
			t.setInPlay(false);
			t.getParentSquare().setOccupant(null);
			getGameController().setMessage("  KILLED  " + t.getTeam() + "!");
			boardController.getBoardData().doCellsUpdate();
		}	
	}

	/**
	 * Method controls number of actions permitted per turn
	 * Ends turn if at least 2 actions performed
	 * If less than 2 actions performed, increments the action counter
	 * 
	 * @author DS
	 */
	public void checkActionCount(){
		if (actionCount >= (GameConfig.getMovesPerTurn()-1)){
			endTurn();
			activePiece = null;
		}else{
			actionCount++;
		}
	}

	private void endTurn() {		
		// automatically switch player when finishing a move
		gameController.getGameTimer().setGameTime(0);
		//reset actionCount;
		actionCount = 0;
	}

	public void resetTraitValuesToBase(){
		activePiece.getTraitSet().getDamageTrait().setTraitValueToBase();
		activePiece.getTraitSet().getRangeTrait().setTraitValueToBase();
	}
	public void setActionButton(int i) {
		this.actionButton = i;
	}

	public int getActionButton() {
		return this.actionButton;
	}

	public IGameState getGameState() {
		return gameState;
	}

	public void setGameState(IGameState gameState) {
		this.gameState = gameState;
	}
	public GameController getGameController() {
		return gameController;
	}	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
	public BoardController getBoardController() {
		return boardController;
	}	
	public void setBoardController(BoardController bd) {
		this.boardController = bd;
	}	
	public void resetActionCount(){
		actionCount = 0;
	}
	public Piece getActivePiece() {
		return activePiece;
	}	
	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;
	}
	public Square getActiveSquare() {
		return activeSquare;
	}	
	public void setActiveSquare(Square activeSquare) {
		this.activeSquare = activeSquare;
	}
	public Square getTargetSquare() {
		return targetSquare;
	}	
	public void setTargetSquare(Square targetSquare) {
		this.targetSquare = targetSquare;
	}	
	public void saveToMemento(BoardMemento memento){
		careTaker.addMemento(memento);
	}

}
