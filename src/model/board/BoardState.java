package model.board;

import utils.BoardGenerator;

/**
 * BoardState manages the creation of BoardData
 * Contains the generator, squares, pieces, 
 * 
 * @author skh, mark
 *
 */

public class BoardState {

	private BoardData boardData;
	private BoardGenerator boardGenerator;
	
	public BoardState() {
		boardData = BoardData.getInstance();
	}

	public void init() {
		boardGenerator = new BoardGenerator();
		boardGenerator.loadMapData();
		boardData.setBoardArray(boardGenerator.processMapData());
		boardData.doCellsUpdate();
	}
	
}
