package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import controller.PieceCreationController;
import model.board.Square;
import model.piece.Piece;
import model.piece.Team;

/**
 * Factory method to Generate board data for view to draw
 * First, uses BufferedReader in MapLoader class to load map data.
 * When Board model is ready, uses Iterator to generateStartBoard
 * from map data.
 * A corresponding Square Object is assigned to the ArrayList Model
 * Its properties are defined according to model value
 *  
 * @author Mark
 *
 */

public class BoardGenerator  {
	
	private ArrayList<ArrayList<String>> map;	
	private PieceCreationController pieceCreator;
	
	public BoardGenerator() {
		pieceCreator = new PieceCreationController();
	}
	
	public void loadMapData() {		
		MapLoader mapData = new MapLoader();
		try {
			map = mapData.getMapData();
			GameConfig.setROW_COL(map.size());
			System.out.println("Loading Map Size: " + map.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	
	/**
	 * Iterates over the loaded map data and assigns a Square Object
	 * corresponding to the value of the data
	 * 
	 * @author Mark
	 *
	 * @param piecesList
	 * sent from the model after piece creation
	 * 
	 * @return
	 * Square[][] - the 2D game board objects
	 * 
	 */	
	
	public Square[][] processMapData() {
	    int col = 0;
	    int row = 1;
	    int size = map.size();
	    Square[][] boardData = new Square[map.size()][map.size()];
		Iterator<ArrayList<String>> mapIterator = map.iterator();
	    while (mapIterator.hasNext()) {
	        ArrayList<String> line = mapIterator.next();
	        if (row > line.size()) {
	            break;
	        }
	        Iterator<String> val = line.iterator();
	        int index = 0;
	        String cell = "";
	        while (index != row) {
	        	cell = val.next();
	        	index++;
	        }
	        boardData[col][row-1] = null;
	        Square sqr = processMapCell(col, row-1, cell);
			int[] ids = {col, row-1};
			sqr.setID(ids);	
	        boardData[col][row-1] = sqr;
	        index = 0;
	        col++;	        
	        if (col == size) {
	        	mapIterator = map.iterator();  
	            row++;
	            col = 0;
	        }	        
	    }	    
	    return boardData;
	}

	
	/**
	 * Processes map data based on game rules
	 * Returns the Square object
	 * 
	 * @author Mark
	 *
	 * @param col
	 * the x value of the cell
	 * @param row
	 * the y value of the cell
	 * @param cell
	 * the map data value of the cell
	 * 
	 * @return
	 * Square assigned properties based on map data rules
	 */		
	private Square processMapCell(int col, int row, String cell) {
		
		Square gsqr = new Square();
		int it = Character.getNumericValue(cell.charAt(1));	

		//TEAMS
		if (it < (Team.values().length+1) && it != -1) {
			char ct = cell.charAt(0);	
			if (ct == 'R') {			
				Piece rNew = pieceCreator.createRegPiece(Team.values()[it-1]);
				CFacade.getInstance().addGamePiece(rNew);
				gsqr.setOccupant(rNew);
			}
			if (ct == 'U') {
				Piece uNew = pieceCreator.createUsurpPiece(Team.values()[it-1]);
				CFacade.getInstance().addGamePiece(uNew);
				gsqr.setOccupant(uNew);
			}
			if (ct == 'T') {
				CFacade.getInstance().addGameTower(gsqr);
				gsqr.setTeamTower(Team.values()[it-1]);
			}			
		}
		
		//WALLS
		if (cell.equals("WL")) {
			gsqr.setAccessible(false);
		}
			
		return gsqr;		
	}
}
