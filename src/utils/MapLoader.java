package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {
	
	ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
	
	/**
	* Loads the file specified in the GameConfig
	* Parses the text 'map' into a 2D ArrayList
	* @author Mark
	* 
	* @return the ArrayList<ArrayList<Integer>> - 'map'
	*/
	
	public ArrayList<ArrayList<String>> getMapData() throws IOException {		
		try {
			int i = 0;			
			// Create our bufferedreader to read the file
			BufferedReader reader = new BufferedReader(new FileReader(GameConfig.getMapTextfile()));			
			// Line to hold the line read from file
			String line = "";			
			// Loop through the file reading in lines and storing in "line". Do this until readLine returns null (end of file)
			while ((line = reader.readLine()) != null) {
				String[] numbers = line.split(" ");
				//Create the 2d ArrayList
				map.add(new ArrayList<String>());
		        ArrayList<String> inner = map.get(i);
				for(int j = 0; j < numbers.length; j++) {
					inner.add(numbers[j]);
				}
				i++;
			}
			reader.close();
		}
		catch (Exception ex) { 
			System.out.println("getMapData() Exception: " + ex.getMessage()); 
		}		
		return map;		
	}
	
}
