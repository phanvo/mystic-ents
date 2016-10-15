package model.skills;


import java.io.Serializable;

import model.board.Square;
import model.piece.Piece;
import utils.GameConfig;
import view.mediator.DialogView;

/**
 * Class contains logic to increase the value of health trait of another piece of the same team by constant
 * @author Daniel
 *
 */

public class HealSkill extends Skill implements IPerformSquareSkill, Serializable {

	public HealSkill() {
		super.setName("Heal");
		super.setIcon("hospital-cross.png");
	}
	/**
	 * Method is passed target square and skill owner piece to check if there is occupant
	 * in the target square and whether occupant is same team as skill owner.
	 * If so, target occupant's healthtrait value is increase by constant.
	 * If not, exception is thrown.
	 * @param Square
	 * @param Piece
	 * return Boolean
	 */
	@Override
	public boolean performSkill(Square aSqr, Square tSqr) {

		/*Test if square empty, then if square occupant in other team. If either, throw exception. 
		 * If neither increment occupants HealthTrait Value and set result to true*/
		
		Piece tPiece = tSqr.getOccupant();		
		try{
			if(tSqr.getOccupant() == null){
				super.setSkillMessage("No piece in square.");
				throw new IncorrectSquareException("No piece in square.");
			}
			else{
				if (tPiece.getTeam() != aSqr.getOccupant().getTeam()){
					super.setSkillMessage("Wrong team.");
					throw new IncorrectSquareException("Wrong team.");
				}
				else{					
					tPiece.getTraitSet().getHealthTrait().modifyValue(GameConfig.getHealamount());
					super.setSkillMessage("Piece healed!");					
				}
			}
		}
		catch(IncorrectSquareException e){
			System.out.println(e.getMessage());
		}
		return true;
	}
}


class IncorrectSquareException extends RuntimeException { 
	
	public IncorrectSquareException() { 
		super(); 
	} 
	
	public IncorrectSquareException(String message) {
		super(message);
	}
}


