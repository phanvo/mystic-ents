package model.traits;

import java.io.Serializable;

/**
 * Class that extends Trait that represents a piece's health
 * @author Daniel
 *
 */
public class HealthTrait extends Trait implements Serializable {

	public HealthTrait(int startingValue) {
		super(startingValue);
	}

}
