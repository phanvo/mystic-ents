package model.board;

/**
 * Memento class
 * 
 * @author skh
 *
 */

public class BoardMemento {
	private Square fromSquare;
	private Square toSquare;

	public BoardMemento() {
	}

	public BoardMemento(Square fs, Square ts) {
		this.fromSquare = new Square(fs);
		this.toSquare = new Square(ts);
	}

	public Square getFromSquare() {
		return this.fromSquare;
	}

	public Square getToSquare() {
		return this.toSquare;
	}

	// For debugging
	public void print() {

	}
}
