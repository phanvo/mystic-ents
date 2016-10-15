package model.traits;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class that is an aggregate wrapper for all traits
 * @author Daniel
 *
 */
public class TraitSet implements Serializable{
		
	private Trait healthTrait;
	private Trait moveTrait;
	private Trait attackTrait;
	private Trait damageTrait;
	
	
	public Trait getHealthTrait() {
		return healthTrait;
	}
	
	

	public void setHealthTrait(Trait healthTrait) {
		this.healthTrait = healthTrait;
	}


	public Trait getRangeTrait() {
		return moveTrait;
	}


	public void setRangeTrait(Trait moveTrait) {
		this.moveTrait = moveTrait;
	}


	public Trait getAttackTrait() {
		return attackTrait;
	}


	public void setAttackTrait(Trait attackTrait) {
		this.attackTrait = attackTrait;
	}


	public Trait getDamageTrait() {
		return damageTrait;
	}


	public void setDamageTrait(Trait damageTrait) {
		this.damageTrait = damageTrait;
	}

	public TraitSet (Trait healthTrait, Trait moveTrait, Trait attackTrait, Trait damageTrait){
		
		this.healthTrait = healthTrait;
		this.moveTrait = moveTrait;
		this.attackTrait = attackTrait;
		this.damageTrait = damageTrait;
		
	}
}
