package utils.subsystem;

import controller.ActionController;
import model.board.Square;
import model.state.StateAttack;
import model.state.StatePerformSkill;
import view.mediator.DialogView;

public class GameRulesSystem {

	public Boolean checkMoveRules(ActionController a, Square s) {
		//Perform skill. Change State, restart;
		if (a.getActionButton() == (Integer) 3) {
			System.out.println("SKILL");
			a.changeState(StatePerformSkill.getInstance(a));
			return false;
		}
		if (s.getOccupant() == null) {
			
			System.out.println("MOVING: " + s.getInRange());
			if (s.getInRange()) {
				return true;		
			}
		} else {
			//Swap piece so restart this State
			if (a.getActivePiece().getTeam() == s.getOccupant().getTeam() ) {
				
				System.out.println("SWAPPING");
				
				a.setActivePiece(s.getOccupant());
				return false;
			}		
			//Attack piece so change State
			if (a.getActivePiece().getTeam() != s.getOccupant().getTeam() && s.getInRange()) {
				System.out.println("ATTACKING");
				a.changeState(StateAttack.getInstance(a));
				return false;
			}			
		}		
		return true;
	}
	
	public Boolean checkTowerWin(ActionController a, Square s){
		// if the player's own Usurper piece lands on the opponent tower
		if (s.getTeamTower() != null && 
			a.getActivePiece().getIsUsurper() &&
			a.getActivePiece().getTeam() != s.getTeamTower()) {
			return true;
		}
		return false;
	}
	
	public Boolean checkSurvivorWin(ActionController a, int i){
		// if the player's own Usurper piece lands on the opponent tower
		if (i == 1) {
			// it is a win
			return true;
		}
		return false;
	}
	
}
