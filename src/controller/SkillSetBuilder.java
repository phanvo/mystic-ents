package controller;
import java.util.ArrayList;

import model.skills.Skill;
import model.skills.SkillRandomizer;
import model.skills.SkillSet;

/**
 * Class instantiates Skills, coordinates their construction by
 * passing them to SkillRandomizer and then aggregating into set
 * @author Daniel
 * @modified, Mark - moved SkillSet to model
 * 
 */
public class SkillSetBuilder {

 	private SkillSet skillSet;
 	
 	public SkillSetBuilder() {
 
 		generateSkillSetFromSkills();
 	}
 
 	/**
 	 * Method requests a Skill factory from factoryProducers, which creates appropriate
 	 * Skill on request, they are returned at Skill parent type.
 	 * Added to ArrayList for randomised selection and placement into set
 	 */
 	private void generateSkillSetFromSkills() {
 		
 		AbstractFactory skillFactory = FactoryProducer.getFactory("SKILL");
 		
 		Skill healSkill = skillFactory.makeSkill("HEAL");
 		Skill buildSkill = skillFactory.makeSkill("BUILD");
 		Skill rangeSkill = skillFactory.makeSkill("RANGE");
 		Skill attackSkill = skillFactory.makeSkill("ATTACK");
 		
		ArrayList<Skill> listOfSkills = new ArrayList<Skill>();
 		
		listOfSkills.add(healSkill);
		listOfSkills.add(buildSkill);
		listOfSkills.add(rangeSkill);
		listOfSkills.add(attackSkill);
		
		SkillRandomizer skillRandomizer = new SkillRandomizer();
		Skill chosenSkill = skillRandomizer.randomAllocationToSet(listOfSkills);
		this.skillSet = new SkillSet(chosenSkill);
 	}
	
	public SkillSet getSkillSet() {
		return skillSet;
	}
 }
