package utils.subsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.board.BoardData;
import utils.GameConfig;
import view.mediator.DialogView;

public class FileSystem {
	
	//public FileSystem () {}

	/**
	 * Retrieve all game maps for user to choose
	 * @return a String list of available maps
	 */
	public ArrayList<String> getAllGameMaps() {
		ArrayList<String> maps = new ArrayList<>();
		try {
			File f = new File("./src/model/maps/"); // maps directory
			File[] files = f.listFiles();
			for (File file : files) {
				String fullPath = file.getCanonicalPath();
				String currentFile = fullPath.substring(fullPath.lastIndexOf("\\") + 1);
				currentFile = currentFile.substring(0, currentFile.lastIndexOf("."));
				maps.add(currentFile);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("File map errors: " + e.getMessage());
		}
		return maps;
	}	
	
	
	public BoardData loadGame() {
		Object gameState = loadGameData();
		if (gameState != null) {
			BoardData data = (BoardData) gameState;
			GameConfig.setROW_COL(data.getBoardArray().length);
			return data;
		} else {
			DialogView.getInstance().showInformation("Save game not found!");
			return null;
		}
	}	
	
	/**
	 * Save the current game state to file
	 * @param boardData
	 */
	public Boolean saveGameData(BoardData boardData){
		try {
	    	FileOutputStream fileOut = new FileOutputStream(GameConfig.SAVE_GAME_FILE);
	        ObjectOutputStream oos = new ObjectOutputStream(fileOut);
	        oos.writeObject(boardData);
	        oos.close();
	        fileOut.close();
	        return true;
	    } catch(Exception e) {
	        System.out.println("Error saving game: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
		
	}
	
	/**
	 * Load the previous game state from file 
	 * @return gameState as an Object
	 */
	public Object loadGameData(){
		Object data = null;
		try {
			FileInputStream fileIn = new FileInputStream(GameConfig.SAVE_GAME_FILE);
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			data = (BoardData) ois.readObject();
			ois.close();
			fileIn.close();
		} catch (Exception e) {
			System.out.println("Error loading game: " + e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
	
}
