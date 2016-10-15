package model.skills;

import model.board.Square;
import model.piece.Piece;
/**
 * Interface for all skills that perform actions on traits
 * @author Daniel
 *
 */
public interface IPerformTraitSkill {
	
	public void performSkill(Piece skillOwner);
	

}
