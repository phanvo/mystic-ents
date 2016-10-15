package model.skills;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
/**
 * Class is an aggregate wrapper for whatever skill(s) are randomly assigned to piece
 * @author Daniel
 * @modified Mark
 *
 */
public class SkillSet implements Serializable {
	
	private Skill currentSkill;
	
	public SkillSet(Skill newSkill){
		
		this.currentSkill = newSkill;
		
	}
	
	public Skill getCurrentSkill() {
		return currentSkill;
	}
	
	public void setCurrentSkill(Skill newSkill) {
		this.currentSkill = newSkill;
	}

}
