package model.board;

import java.awt.Color;
import java.io.Serializable;

import model.piece.Piece;
import model.piece.RegularPiece;
import model.piece.Team;
import model.piece.UsurperPiece;

/**
 * Contained inside board, can contain piece or become wall
 * 
 * @author skh
 *
 */
public class Square implements Serializable {

	private Piece occupyingPiece = null;
	private int[] ID = new int[2];

	private Boolean inrange = false;
	private Boolean accessible = true;
	private Boolean teamPiece = false;
	private Team teamTower = null;
	private Color bgColor = Color.WHITE;

	public Square() {
	}

	/**
	 * Copy constructor used for saving game state in UNDO functionality
	 * 
	 * @param other
	 *            Square being copied
	 */
	public Square(Square other) {
		super();
		if (other.getOccupant() != null) {
			this.occupyingPiece = other.occupyingPiece instanceof RegularPiece ? new RegularPiece(other.occupyingPiece)
					: new UsurperPiece(new RegularPiece(other.occupyingPiece));
		}
		ID = other.ID;
		this.inrange = other.inrange;
		this.accessible = other.accessible;
		this.teamPiece = other.teamPiece;
		this.teamTower = other.teamTower;

	}

	public int[] getID() {
		return ID;
	}

	public void setID(int[] iD) {
		ID = iD;
	}

	public void setAccessible(Boolean pm) {
		accessible = pm;
	}

	public Boolean getAccessible() {
		return accessible;
	}

	public void setOccupant(Piece pm) {
		if (pm != null) { pm.setParentSquare(this); }
		occupyingPiece = pm;
	}

	public Piece getOccupant() {
		return occupyingPiece;
	}

	public Boolean getTeamPiece() {
		return teamPiece;
	}

	public Team getTeamTower() {
		// TODO Auto-generated method stub
		return this.teamTower;
	}

	public void setTeamTower(Team team) {
		this.teamTower = team;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Boolean getInRange() {
		return inrange;
	}

	public void setInRange(Boolean inrange) {
		this.inrange = inrange;
	}

	public String toString() {
		if (occupyingPiece != null) {
			return "p";
		} else if (!this.accessible) {
			return "w";
		} else {
			return "x";
		}
	}


}
