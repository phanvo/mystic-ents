package model.traits;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

/**
 * Class that is passed a list of traits and randomly assigns a set of value points to them
 * @author Daniel
 *
 */
public class TraitRandomizer {
	
	final int VALUESDISTRIBUTED = 6;
	final int VALUEINCREMENT = 1;

	/**
	 * Method loops through number of points to be added, and randomly assigns points to trait values.
	 * @param listofTraits
	 * @param randomNumGen
	 */
	public void generateRandomTraitValues(ArrayList<Trait> listofTraits, Random randomNumGen){
		
		int randomNum;
		
		for (int i = 0; i < VALUESDISTRIBUTED; i++) {
			
			randomNum = randomNumGen.ints(0, listofTraits.size()).limit(1).findFirst().getAsInt();
			listofTraits.get(randomNum).modifyValue(VALUEINCREMENT);
		}
	}
	
}
