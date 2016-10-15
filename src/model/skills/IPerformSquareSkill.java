package model.skills;

import model.board.Square;
/**
 * Interface for all skills that perform action on squares
 * @author Daniel
 *
 */
public interface IPerformSquareSkill {

	public boolean performSkill(Square aSqr, Square tSqr);
	
}