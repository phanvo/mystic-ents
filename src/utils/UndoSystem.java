package utils;

import controller.BoardController;
import model.board.BoardCareTaker;
import model.board.BoardMemento;
import model.board.Square;

public class UndoSystem {

	public Boolean undoMove(int undos, BoardController b) {
		if (BoardCareTaker.getInstance().getMementosSize() >= undos * 4) {
			BoardMemento boardMemento = null;
			for (int i = 0; i < undos * 4; i++) {
				boardMemento = BoardCareTaker.getInstance().getMemento();
				b.getBoardData().setBoardArray(undoFromMemento(boardMemento, b.getBoardData().getBoardArray()));
			}
			b.getBoardData().getCurrentTeam().decreaseUndoNum();
			return true;
		}
		return false;
	}
	
	public Square[][] undoFromMemento(BoardMemento boardMemento, Square[][] data) {
		data[boardMemento.getToSquare().getID()[0]][boardMemento.getToSquare().getID()[1]] = boardMemento
				.getToSquare();
		data[boardMemento.getFromSquare().getID()[0]][boardMemento.getFromSquare().getID()[1]] = boardMemento
				.getFromSquare();
		return data;
	}
	
}
