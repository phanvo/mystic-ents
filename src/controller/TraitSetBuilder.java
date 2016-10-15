package controller;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

public class TraitSetBuilder {

	private Random randomNumGen;
	private TraitSet traitSet;
	final int INITIALTRAITVALUE = 1;

	/**
	 * Trait set is created on instantiation of Trait Builder
	 * 
	 * @param randomNumGen
	 *            - Seeded Random generator
	 */

	public TraitSetBuilder(Random randomNumGen) {
		this.randomNumGen = randomNumGen;
		generateTraitSetfromTraits();
	}

	/**
	 * All traits instantiated here, references passed to allocateTraitValues
	 * then aggregated into a trait set
	 * Trait factory is requested from FactoryProducers and is used to create and return the appropriate traits
	 */

	private void generateTraitSetfromTraits() {
		
		AbstractFactory traitFactory = FactoryProducer.getFactory("TRAIT");

		Trait healthTrait = traitFactory.makeTrait("HEALTH");
		Trait rangeTrait = traitFactory.makeTrait("RANGE");
		Trait attackTrait = traitFactory.makeTrait("ATTACK");
		Trait damageTrait = traitFactory.makeTrait("DAMAGE");

		allocateTraitValues(healthTrait, damageTrait, rangeTrait);
		
		traitSet = new TraitSet(healthTrait, rangeTrait, attackTrait, damageTrait);
		
	}

	/**
	 * Trait references with randomizable values packaged into ArrayList, passed
	 * to traitRandomizer along with random number generate
	 * 
	 * @param healthTrait
	 * @param damageTrait
	 * @param rangeTrait
	 */
	private void allocateTraitValues(Trait healthTrait, Trait damageTrait, Trait rangeTrait) {

		ArrayList<Trait> listOfTraits = new ArrayList<Trait>();

		listOfTraits.add(damageTrait);
		listOfTraits.add(rangeTrait);
		listOfTraits.add(healthTrait);

		TraitRandomizer traitRandomizer = new TraitRandomizer();
		traitRandomizer.generateRandomTraitValues(listOfTraits, randomNumGen);
		
		for(Trait t: listOfTraits){
			t.setBaseValue(t.getTraitValue());
		}
		
	}

	public TraitSet getTraitSet() {
		return traitSet;
	}
}
