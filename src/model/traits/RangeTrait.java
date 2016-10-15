package model.traits;

import java.io.Serializable;

/**
 * Class that extends Trait that represents the range in which a piece can perform actions
 * @author Daniel
 *
 */
public class RangeTrait extends Trait implements Serializable {

	public RangeTrait(int startingValue) {
		super(startingValue);
	}
	
}