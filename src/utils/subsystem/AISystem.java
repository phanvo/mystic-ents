package utils.subsystem;

import java.util.ArrayList;
import java.util.Random;

import controller.ActionController;
import model.board.Square;
import model.piece.Piece;
import model.piece.Team;
import utils.CFacade;
import utils.GameConfig;

/**
 * This is the main class which handles AI Team movement
 * It is coupled only with the ActionController and GameUtils Singletons.
 * Allows ActionController take orders from this class and send to State Machine.
 * Besides the methods required to inform decision making, no extra game functionality has been added 
 * 
 * @author Mark
 *
 */

public class AISystem {
	
	//private ArrayList <Team> teamList;
	private ArrayList <Piece> piecesList;
	private ArrayList <Square> towersList;

	private Random rN = new Random();
	
	public void doGameTurn(ActionController a, ArrayList<Square> rangeList, Piece p) {
		Square ts;
		ArrayList<Square> sqrs = new ArrayList<Square>();
		
		if (p.getIsUsurper()) {			
			sqrs = getOpponentTowers(p);
		} else {
			sqrs = getOpponentPieces(p);
		}
		if (sqrs.size() == 0) {
			a.endAction(a, p.getParentSquare());
			return;
		}
		ts = getNextSquare(sqrs, rangeList);
		a.endAction(a, ts);
	}	
	
	private ArrayList<Square> getOpponentTowers(Piece p) {
		ArrayList<Square> sqrs = new ArrayList<Square>();
		for (Square s : towersList) {
			if (p.getTeam() != s.getTeamTower()) {
				sqrs.add(s);
			}
		}
		return sqrs;
	}
	/** 
	 * Pieces need to repopulate existing adversaries.
	 * The method returns a list of options, NOT the target,
	 * as it may be out of range.
	 * The list is then compared to their distance from the squares in range.
	 * A suitable target square is then selected using getNextSquare();
	 * 
	 * @author Mark
	 * 
	 * @param p
	 * The game piece searching
	 * 
	 * @return sqrs
	 * An ArrayList potential long term targets. 
	 * 
	 */	
	private ArrayList<Square> getOpponentPieces(Piece p) {
		ArrayList<Square> sqrs = new ArrayList<Square>();
		for (Piece s : piecesList) {
			if (p.getTeam() != s.getTeam() && s.getInPlay()) {
				sqrs.add(s.getParentSquare());
			}
		}
		return sqrs;
	}	
	
	public int SelectNextAction(Piece p) {
		float chance = rN.nextFloat();
		int i = 1;
		if (chance > 0.80f) {
			i = 3;
		}
		return i;
	}	
	
	/** 
	 * Select a square from those currently in range 
	 * Piece will prioritize according to type
	 * 
	 * @author Mark
	 * 
	 * @param sqrs
	 * The assigned target squares
	 * based on the Piece's preference - e.g. Usurper should head for enemy Tower.
	 * 
	 * @param rangeList
	 * Is re-populated on every startAction State.
	 * Only squares which are in range may be selected as targets.
	 * 
	 * @return
	 * Returns move square based on selection criteria
	 * 
	 */
	private Square getNextSquare(ArrayList<Square> sqrs, ArrayList<Square> rangeList) {
		Square s = getClosestInRange(sqrs, rangeList);
		return s;
	}

	private Square getClosestInRange(ArrayList<Square> sqrs, ArrayList<Square> rangeList) {
		Square cSquare = null;
		int shortestDistance = GameConfig.getROW_COL()*2;
		for (Square o : sqrs) {
			//System.out.println(o.getID()[0] + " : " + o.getID()[1]);
			for (Square r : rangeList) {
				int d = getDistance(r, o);
				if (d < shortestDistance) {
					cSquare = r;
					shortestDistance = d;
				}
			}
		}
		return cSquare;
	}

	/** 
	 * Standard Euclidian Distance method
	 * Returns the (integer) distance between two points
	 * 
	 * @author Mark
	 * 
	 * @return Piece
	 */
	private int getDistance(Square a, Square t) {
		int dx = Math.abs(t.getID()[0] - a.getID()[0]);
	    int dy = Math.abs(t.getID()[1] - a.getID()[1]);
	    int min = Math.min(dx, dy);
	    int max = Math.max(dx, dy);
	    int diagonalSteps = min;
	    int straightSteps = max - min;	    
	    Double r = Math.sqrt(2) * diagonalSteps + straightSteps;
	    return (int) Math.abs(r);		
	}
	
	/**
	 * 
	 * Randomly select next piece from list
	 * Could be refined by some logic
	 * 
	 * @author Mark
	 * 
	 * @return Piece
	 */
	
	public Piece getNextPiece(Team t) {
		ArrayList<Piece> aP = new ArrayList<Piece>();
		aP = CFacade.getInstance().getActivePieces(piecesList, t);
		if (aP.size() > 0) {
			int rI = rN.nextInt(aP.size());
			Piece rP = aP.get(rI);
			return rP;		
		}
		return null;
	}
	
	public Boolean checkAIStatus(Team ct) {
		return ct.getAI();
	}

	/*public ArrayList<Team> getTeamList() {
		return teamList;
	}
	
	public void setTeamList(ArrayList<Team> teamList) {
		this.teamList = teamList;
	}*/
	
	public ArrayList<Piece> getPiecesList() {
		return piecesList;
	}
	
	public void setPiecesList(ArrayList<Piece> piecesList) {
		this.piecesList = piecesList;
	}
	
	public ArrayList<Square> getTowersList() {
		return towersList;
	}
	
	public void setTowersList(ArrayList<Square> towersList) {
		this.towersList = towersList;
	}

}

