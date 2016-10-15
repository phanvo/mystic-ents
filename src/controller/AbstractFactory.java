package controller;
import model.skills.Skill;
import model.traits.Trait;

/**
 * Abstract class contains declaration for factory methods
 * @author Daniel
 *
 */
public abstract class AbstractFactory {
	
	abstract Trait makeTrait(String traitType);
	abstract Skill makeSkill(String skillType);

}
