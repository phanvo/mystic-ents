package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;
import utils.CFacade;

public class StateSelect implements IGameState {
	
	private StateSelect() { }

	private static StateSelect instance;

	public static StateSelect getInstance(ActionController a) {
		if (instance == null) {
			instance = new StateSelect();
		}
		return instance;
	}	
	
	@Override
	public void startAction(ActionController a, Square s) {
		System.out.println("Start move.");
		a.setActiveSquare(s);
		a.setActivePiece(s.getOccupant());
		a.getBoardController().getRangeCells(s);
		a.getGameController().updatePieceInformation(s.getOccupant());
	}

	@Override
	public void endAction(ActionController a, Square s) {
		if (CFacade.getInstance().checkMoveRules(a, s)) {
			if (!s.getInRange()) return;
			System.out.println("End move");
			a.saveToMemento(new BoardMemento(a.getActiveSquare(), s));
			s.setOccupant(a.getActivePiece());
			a.updateAction(a);
		} else {
			a.startAction(a, s);
		}		
	}
	
	@Override
	public void updateAction(ActionController a) {
		// TODO Auto-generated method stub
		a.getActiveSquare().setOccupant(null);
		a.setActivePiece(null);
		a.getBoardController().clearRangeCells();
	}
	
	

}
