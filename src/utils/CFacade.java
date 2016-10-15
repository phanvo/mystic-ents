package utils;

import java.util.ArrayList;

import controller.ActionController;
import controller.BoardController;
import controller.GameController;
import model.board.BoardData;
import model.board.Square;
import model.piece.Piece;
import model.piece.Team;

import view.BoardPanel;

import utils.subsystem.*;

public class CFacade {

	private static CFacade instance;

	//Subsystems
	private BoardSystem boardSystem = new BoardSystem();
	private RangeSystem rangeSystem = new RangeSystem();
	private GameRulesSystem gameRulesSystem = new GameRulesSystem();
	private FileSystem fileSystem = new FileSystem();
	private PiecesSystem piecesSystem = new PiecesSystem();
	private UndoSystem undoSystem = new UndoSystem();
	private AISystem aiSystem = new AISystem();
	
	/*
	 * Facade pattern to present a single interface from the client to the subsystems.
	 * CFacade is an instance and acts as a utility front of house.
	 * 
	 * @author mark
	 *    
	 */
	
	private CFacade() {}

	public static synchronized CFacade getInstance() {
		if (instance == null) {
			instance = new CFacade();
		}
		return instance;
	}

	public Square[][] getRangeCells(int x, int y, Square[][] data) {
		return rangeSystem.getRangeCells(x, y, data);
	}

	public void clearRangeCells() {
		rangeSystem.clearRangeCells();
	}

	public ArrayList <Square> getRangeList(Square[][] data) {
		return rangeSystem.populateRangeList(data);
	}

	public void updateBoard() {
		boardSystem.updateBoard(rangeSystem.getRangeList());
	}

	public void disableBoard(BoardPanel boardPanel) {
		boardSystem.disableBoard(boardPanel);
	}

	public void enableBoard(BoardPanel boardPanel) {
		boardSystem.enableBoard(boardPanel);
	}

	public void buildFullBoard(BoardPanel boardPanel, Square[][] data) {
		rangeSystem.setRangeList(boardSystem.buildFullBoard(boardPanel, data));
	}

	public Boolean checkMoveRules(ActionController a, Square s) {
		return gameRulesSystem.checkMoveRules(a, s);
	}

	public Boolean checkTowerWin(ActionController a, Square s){
		return gameRulesSystem.checkTowerWin(a, s);
	}

	public Boolean checkSurvivorWin(ActionController a){
		int c = piecesSystem.getAvailableTeamList(piecesSystem.getPiecesList()).size();
		return gameRulesSystem.checkSurvivorWin(a, c);
	}

	public ArrayList<String> getAllGameMaps() {
		return fileSystem.getAllGameMaps();
	}

	public BoardData loadGame() {
		return fileSystem.loadGame();
	}	

	public void setUpGameFromLoad(BoardData boardData) {
		piecesSystem.setUpGameFromLoad(boardData.getBoardArray());
	}

	public Boolean saveGameData(BoardData boardData) {
		return fileSystem.saveGameData(boardData);
	}

	public void addGamePiece(Piece p) {
		piecesSystem.addGamePiece(p);
	}
	public void addGameTower(Square s) {
		piecesSystem.addGameTower(s);
	}

	public ArrayList <Piece> getGamePieces() {
		return piecesSystem.getPiecesList();
	}

	public ArrayList <Square> getGameTowers() {
		return piecesSystem.getTowersList();
	}

	public ArrayList<Piece> getActivePieces(ArrayList<Piece> pieces, Team team) {
		return piecesSystem.getActivePieces(pieces, team);
	}

	public Boolean checkAIStatus(Team team) {
		return aiSystem.checkAIStatus(team);
	}

	public void populateAIObjects() {
		aiSystem.setTowersList(piecesSystem.getTowersList());
		aiSystem.setPiecesList(piecesSystem.getPiecesList());
	}
	
	/*
	 * AI game turn controller
	 * ActionController is passed in and some preparatory operations happen here
	 * to not break the Facade pattern by remaining unknown to subsystems
	 * Try, catch used to allow for intervention into AI turn
	 * which may fail if intervention occurs at mid move.
	 * 
	 * @author mark
	 * 
	 * @Param a
	 * ActionController to manage State Machine
	 * 
	 * @Param team
	 * The current team from which a target, adversity can be derived
	 *    
	 */

	public void doAIGameTurn (ActionController a, Team team) {
		Piece p = aiSystem.getNextPiece(team);
		try {
			a.startAction(a, p.getParentSquare());
			a.setActionButton(aiSystem.SelectNextAction(p));
			aiSystem.doGameTurn(a, getRangeList(), p);
		} catch (Exception e) {
			System.out.println("AI failed " + e.getMessage());
		}
	}

	public Team getNextTeam (ArrayList<Piece> pieces, Team team) {
		return piecesSystem.getNextTeam (pieces, team);
	}

	public void resetPieceTraitValueToBase(ArrayList<Piece> pieces) {
		piecesSystem.resetPieceTraitValueToBase(pieces);
	}

	public ArrayList<Team> getTeamList(ArrayList<Piece> pieces) {
		return piecesSystem.getAvailableTeamList(pieces); 
	}

	public Boolean undoMove(int undos, BoardController b) {
		return undoSystem.undoMove(undos, b) ;
	}

	public ArrayList<Square> getRangeList() {
		return rangeSystem.getRangeList();
	}

}
