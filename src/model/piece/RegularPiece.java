package model.piece;
import java.io.Serializable;

import model.board.Square;
import model.skills.SkillSet;
import model.traits.TraitSet;

/**
 * Concrete class implements all behaviours of regular piece
 * @author Daniel
 *
 */
public class RegularPiece implements Piece, Serializable {

	protected TraitSet traitSet;
	protected SkillSet skillSet;

	protected String pieceIcon;

	protected Boolean isUsurper = false;
	protected Boolean inPlay = true;
	protected Boolean inMove = false;

	// Team color
	protected Enum<Team> team;
	
	//Keep reference to parent Square for AI 
	protected Square parentSquare;

	public RegularPiece() {
	}

	/**
	 * Constructor used to clone piece for undo feature
	 * @param other
	 */
	public RegularPiece(Piece other) {
		this.traitSet = other.getTraitSet();
		this.skillSet = other.getSkillSet();
		this.pieceIcon = other.getIcon();
		this.isUsurper = other.getIsUsurper();
		this.inMove = other.getInMove();
		this.team = other.getTeam();
	}

	public Square getParentSquare() {
		return parentSquare;
	}
	public void setParentSquare(Square parentSquare) {
		this.parentSquare = parentSquare;
	}

	public void setIcon(String icon) {
		this.pieceIcon = icon;
	}

	public SkillSet getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}

	public TraitSet getTraitSet() {
		return traitSet;
	}

	public void setTraitSet(TraitSet traitSet) {
		this.traitSet = traitSet;
	}

	public Enum<Team> getTeam() {
		return team;
	}

	public void setTeam(Enum<Team> team) {
		this.team = team;
	}

	public void attackOut(Piece piece) {
		int damageValue = this.getTraitSet().getDamageTrait().getTraitValue();
		piece.getTraitSet().getHealthTrait().modifyValue(-damageValue);
	}

	public Boolean getInPlay() {
		return inPlay;
	}

	public void setInPlay(Boolean inPlay) {
		this.inPlay = inPlay;
	}
	
	public void setInMove(Boolean inMove){
		
		this.inMove = inMove;
	}
	
	public Boolean getInMove(){
		return inMove;
	}

	public Boolean getIsUsurper() {
		return isUsurper;
	}

	public void setIsUsurper(Boolean isUsurper) {
		this.isUsurper = isUsurper;
	}
	
	public String getIcon() {
		return this.getSkillSet().getCurrentSkill().getIcon();
	}

}
