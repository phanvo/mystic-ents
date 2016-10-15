package utils.subsystem;

import java.util.ArrayList;

import model.board.Square;
import model.piece.Piece;

public class RangeSystem {
	
	private ArrayList<Square> rangeList = new ArrayList<Square>();

	public Square[][] getRangeCells(int x, int y, Square[][] boardData) {
		try {
			Piece pce = boardData[x][y].getOccupant();
			int range = pce.getTraitSet().getRangeTrait().getTraitValue();
			int bSize = boardData.length;		
			rangeList.clear();
			rangeList.add(boardData[x][y]);
			for (int i = (x - range > -1 ? x - range : 0); i < (x + (range + 1) < bSize ? x + (range + 1) : bSize); i++) {
				for (int j = (y - range > -1 ? y - range : 0); j < (y + (range + 1) < bSize ? y + (range + 1)
						: bSize); j++) {
					boardData[i][j].setInRange(checkRangeCriteria(boardData[i][j]));
					rangeList.add(boardData[i][j]);
				}
			}
		} catch (Exception e) {
			System.out.println("Turn interrupted: " + e.getMessage());
		}
		return boardData;
	}

	private Boolean checkRangeCriteria(Square check) {
		Boolean setRange = true;
		if (!check.getAccessible()) {
			setRange = false;
		}
		return setRange;
	}

	public void clearRangeCells() {
		for (int i = 0; i < rangeList.size(); i++) {
			rangeList.get(i).setInRange(false);
		}
	}

	public ArrayList <Square> populateRangeList(Square[][] data) {
		ArrayList <Square> s = new ArrayList <Square>();
		for (int i=0; i<data.length; i++) {
			for (int j=0; j<data[i].length; j++) {
				if (data[i][j].getInRange()) {
					s.add(data[i][j]);
				}				
			}
		}
		return s;
	}

	public ArrayList<Square> getRangeList() {
		return rangeList;
	}
	
	public void setRangeList(ArrayList<Square> rangeList) {
		this.rangeList = rangeList;
	}
}
