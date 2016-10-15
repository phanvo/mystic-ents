package utils.subsystem;

import java.util.ArrayList;

import model.board.Square;
import model.piece.Piece;
import model.piece.Team;

public class PiecesSystem {
	
	private ArrayList<Piece> piecesList = new ArrayList<Piece>();
	private ArrayList<Square> towersList = new ArrayList<Square>();
	
	public PiecesSystem () {}
	
	public void addGamePiece(Piece p) {
		piecesList.add(p);
	}
	public void addGameTower(Square p) {
		towersList.add(p);
	}
	
	public ArrayList<Piece> getActivePieces(ArrayList<Piece> piecesList, Team team) {
		ArrayList<Piece> aP = new ArrayList<Piece>();
		for (Piece p : piecesList) {
			if (p.getInPlay() && p.getTeam() == team) {
				aP.add(p);
			}			
		}
		return aP;
	}

	public Team getNextTeam (ArrayList<Piece> pieceList, Team currentTeam) {
		ArrayList<Team> tList = new ArrayList<Team>(getAvailableTeamList(pieceList));
		int a = tList.indexOf(currentTeam);
		a = ++a == tList.size() ? 0 : a;
		return tList.get(a);
	}
	
	public ArrayList<Team> getAvailableTeamList(ArrayList<Piece> pieceList) {
		ArrayList<Team> tList = new ArrayList<Team>();
		for (Piece piece : pieceList) {
			if(piece.getInPlay() && !tList.contains(piece.getTeam())) {
				tList.add((Team) piece.getTeam());
			}
		}
		return tList;
	}	
	
	public void setUpGameFromLoad(Square[][] data) {
		ArrayList<Piece> p = new ArrayList<Piece>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j].getOccupant() != null) {
					addGamePiece(data[i][j].getOccupant());
				}
				if (data[i][j].getTeamTower() != null) {
					addGameTower(data[i][j]);
				}
			}
		}
	}	
	
	public void resetPieceTraitValueToBase(ArrayList<Piece> pieceList) {
		for (int i = 0; i < pieceList.size(); i++) {
			pieceList.get(i).getTraitSet().getDamageTrait().setTraitValueToBase();
			pieceList.get(i).getTraitSet().getRangeTrait().setTraitValueToBase();
		}
	}

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
