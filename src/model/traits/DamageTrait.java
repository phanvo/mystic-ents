package model.traits;

import java.io.Serializable;

/**
 * Class that extends Trait and represents the damage a piece can do by attacking
 * @author Daniel
 *
 */
public class DamageTrait extends Trait implements Serializable {

	public DamageTrait(int startingValue) {
		super(startingValue);
	}


}
