package model.piece;
import java.io.Serializable;

import model.board.Square;
import model.skills.SkillSet;
import model.traits.TraitSet;

/**
 * Concrete decorator class used as part of Decorator Pattern
 * Implements all piece interface methods be directing classes to the aggregated decorated regular piece
 * Usurpser functions have different implementation
 * @author Daniel
 *
 */
public class UsurperPiece extends PieceDecorator  implements Serializable{	
	
	public UsurperPiece(Piece decoratedPiece){
		super(decoratedPiece);
		decoratedPiece.setIsUsurper(true);
	}

	@Override
	public Square getParentSquare() {
		return decoratedPiece.getParentSquare();
	}

	@Override
	public void setParentSquare(Square parentSquare) {
		decoratedPiece.setParentSquare(parentSquare);
	}

	@Override
	public String getIcon() {
		
		return "grim-reaper.png";
	}

	@Override
	public void setIcon(String icon) {
		decoratedPiece.setIcon(icon);
		
	}

	@Override
	public SkillSet getSkillSet() {
		
		return decoratedPiece.getSkillSet();
	}

	@Override
	public void setSkillSet(SkillSet skillSet) {
		
		decoratedPiece.setSkillSet(skillSet);
	}

	@Override
	public TraitSet getTraitSet() {
		
		return decoratedPiece.getTraitSet();
	}

	@Override
	public void setTraitSet(TraitSet traitSet) {
		
		decoratedPiece.setTraitSet(traitSet);
	}

	@Override
	public Enum<Team> getTeam() {
		
		return decoratedPiece.getTeam();
	}

	@Override
	public void setTeam(Enum<Team> team) {
		
		decoratedPiece.setTeam(team);
	}

	@Override
	public void attackOut(Piece piece) {
		
		decoratedPiece.attackOut(piece);
	}

	@Override
	public Boolean getInPlay() {
		
		return decoratedPiece.getInPlay();
	}

	@Override
	public void setInPlay(Boolean inPlay) {
		
		decoratedPiece.setInPlay(inPlay);
	}

	@Override
	public Boolean getIsUsurper() {
		
		return decoratedPiece.getIsUsurper();
	}

	@Override
	public void setIsUsurper(Boolean isUsurper) {
		
		decoratedPiece.setIsUsurper(isUsurper);
	}

	@Override
	public void setInMove(Boolean inMove) {
		decoratedPiece.setInMove(inMove);
	}

	@Override
	public Boolean getInMove() {
		return decoratedPiece.getInMove();
	}

}
