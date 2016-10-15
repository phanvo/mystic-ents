package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;
import view.mediator.DialogView;

public class StateAttack implements IGameState {

	private StateAttack() { }

	private static StateAttack instance;

	public static StateAttack getInstance(ActionController a) {
		if (instance == null) {
			instance = new StateAttack();
		}
		return instance;
	}	
	
	@Override
	public void startAction(ActionController a, Square s) {
		if (!s.getInRange()) return;
		System.out.println("Start attack");		
		//Save state before making action
		a.saveToMemento(new BoardMemento(a.getActiveSquare(), s));
		a.getActivePiece().attackOut(s.getOccupant());
		a.resolveAttack(a.getActivePiece(), s.getOccupant());
		a.endAction(a, s);
	}

	@Override
	public void endAction(ActionController a, Square s) {
		a.updateAction(a);
	}

	@Override
	public void updateAction(ActionController a) {
		System.out.println("End attack");
		a.setActivePiece(null);
		a.changeState(StateSelect.getInstance(a));
		a.getBoardController().clearRangeCells();
	}


}