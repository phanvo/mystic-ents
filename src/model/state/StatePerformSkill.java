package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;
import model.skills.IPerformSquareSkill;
import model.skills.IPerformTraitSkill;
import model.skills.Skill;

public class StatePerformSkill implements IGameState {

	private StatePerformSkill() { }

	private static StatePerformSkill instance;

	public static StatePerformSkill getInstance(ActionController a) {
		if (instance == null) {
			instance = new StatePerformSkill();
		}
		return instance;
	}	

	@Override
	public void startAction(ActionController a, Square s) {
		System.out.println("Start Perform Skill.");
		a.saveToMemento(new BoardMemento(a.getActiveSquare(), s));
		Skill currentSkill = a.getActivePiece().getSkillSet().getCurrentSkill();
		if (currentSkill instanceof IPerformTraitSkill){
			((IPerformTraitSkill) currentSkill).performSkill(a.getActivePiece());		
		}
		else if (currentSkill instanceof IPerformSquareSkill){
			((IPerformSquareSkill) currentSkill).performSkill(a.getActiveSquare(), s);
		}
		a.getGameController().setMessage(currentSkill.getSkillMessage());
		a.endAction(a, s);
	}

	@Override
	public void endAction(ActionController a, Square s) {
		System.out.println("End Perform Skill.");
		a.getBoardController().getRangeCells(a.getActiveSquare());
		a.getBoardController().getBoardData().doCellsUpdate();
		a.getGameController().updatePieceInformation(a.getActivePiece());
		a.updateAction(a);
	}

	@Override
	public void updateAction(ActionController a) {
		// TODO Auto-generated method stub
		a.changeState(StateSelect.getInstance(a));
	}

}