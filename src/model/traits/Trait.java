package model.traits;

import java.io.Serializable;

/**
 * Abstract class inherited by all traits, containing traitValue and modification method
 * @author Daniel
 *
 */
public abstract class Trait implements ITraitValueModifiable, Serializable {

	private int traitValue;
	private int baseTraitValue;
	
	public Trait(int startingValue){
		setTraitValue(startingValue);
		setBaseValue(startingValue);
	}
	
	public Trait(Trait other) {
		this.traitValue = other.traitValue;
		this.baseTraitValue = other.baseTraitValue;
	}

	public void modifyValue(int changeBy) {
		setTraitValue(getTraitValue() + changeBy);
	}

	public int getTraitValue() {
		return traitValue;
	}

	public void setTraitValue(int traitValue) {
		this.traitValue = traitValue;
	}
	
	public int getBaseTraitValue(){
		return baseTraitValue;
	}
	
	public void setTraitValueToBase(){
		setTraitValue(baseTraitValue);
	}
	
	public void setBaseValue(int baseValue){
		this.baseTraitValue = baseValue;
	}

	public int getBaseValue(){
		return baseTraitValue;
	}
}
