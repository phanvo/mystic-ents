package model.board;

import java.util.LinkedList;

/**
 * Care taker for the board
 * 
 * @author skh
 *
 */

public class BoardCareTaker {

	final LinkedList<BoardMemento> mementos = new LinkedList<>();

	private static BoardCareTaker instance;

	private BoardCareTaker(){}

	public static synchronized BoardCareTaker getInstance() {
		if (instance == null) {
			instance = new BoardCareTaker();
		}
		return instance;
	}

	public int getMementosSize() {
		return mementos.size();
	}

	public BoardMemento getMemento() {
		return mementos.pollLast();
	}

	public void addMemento(BoardMemento boardMemento) {

		// Remove old states
		if (mementos.size() > 12) {
			mementos.poll();
		}
		System.out.println("Adding Memento:" + mementos.size());
		mementos.add(boardMemento);

	}
}
