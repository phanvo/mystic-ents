package model.piece;
import model.board.Square;
import model.skills.SkillSet;
import model.traits.TraitSet;
/**
 * Interface forms part of Piece Decorator Pattern, declaring all regular piece behaviour
 * @author Daniel
 *
 */
public interface Piece {


	public Square getParentSquare();
	
	public void setParentSquare(Square parentSquare);
	
	public String getIcon();

	public void setIcon(String icon);

	public SkillSet getSkillSet();

	public void setSkillSet(SkillSet skillSet);

	public TraitSet getTraitSet();

	public void setTraitSet(TraitSet traitSet);

	public Enum<Team> getTeam();

	public void setTeam(Enum<Team> team);

	public void attackOut(Piece piece);

	public Boolean getInPlay();

	public void setInPlay(Boolean inPlay);
	
	public void setInMove(Boolean inMove);
	
	public Boolean getInMove();

	public Boolean getIsUsurper();

	public void setIsUsurper(Boolean isUsurper);

}
