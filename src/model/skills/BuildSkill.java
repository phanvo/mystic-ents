package model.skills;

import java.io.Serializable;

import model.board.Square;
import view.mediator.DialogView;

/**
 * Toggle square accessibility
 * @author skh
 *
 */

public class BuildSkill extends Skill implements IPerformSquareSkill, Serializable {

	public BuildSkill() {
		super.setName("Build");
		super.setIcon("sword-spade.png");
	}
	
	@Override
	public boolean performSkill(Square aSqr, Square tSqr) {

		// If the square is empty and is accessible then build
		if (tSqr.getOccupant()==null && tSqr.getAccessible()) {
			tSqr.setAccessible(false);
			super.setSkillMessage("Wall built!");
		} else if (!tSqr.getAccessible()) {
			tSqr.setAccessible(true);
			super.setSkillMessage("Wall removed!");
		} else {
			super.setSkillMessage("Build failed!");
		}
		return !tSqr.getAccessible();
		
	}

}
