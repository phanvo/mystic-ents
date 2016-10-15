package controller;
import java.util.HashMap;

import model.skills.AttackSkill;
import model.skills.BuildSkill;
import model.skills.HealSkill;
import model.skills.RangeSkill;
import model.skills.Skill;
import model.traits.Trait;

/**
 * Class uses Abstract Factory and Flyweight Pattern to either create an new Skill upon request
 * or return existing flyweight skill.
 * @author Daniel, Sam
 *
 */
public class SkillFactory extends AbstractFactory {

	private static HashMap<String, Skill> skills = new HashMap<>();


	@Override
	/**
	 * Method received request for particular skill subtype, if it exists already in HashMap then it is returned
	 * If not, it is created, saved in hashmap for future use and return.
	 */
	public Skill makeSkill(String skillType) {

		Skill sk = skills.get(skillType);

		if (skillType == null) {
			return null;
		}

		if (sk == null) {

			switch (skillType) {
			case "ATTACK":
				sk = new AttackSkill();
				break;
			case "RANGE":
				sk = new RangeSkill();
				break;
			case "BUILD":
				sk = new BuildSkill();
				break;
			case "HEAL":
				sk = new HealSkill();
				break;
			}
			skills.put(skillType, sk);
		}

		return sk;
	}

	
	@Override
	public Trait makeTrait(String traitType) {
		return null;
	}
}
