package model.skills;

import java.io.Serializable;

/**
 * Abstract class from which all Skill inherit
 * @author 
 *
 */
public abstract class Skill implements Serializable {
	private String name;
	private String icon;
	private String msg;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	public String getIcon(){
		return this.icon;
	}
	
	public String getSkillMessage() {
		return msg;
	}
	public void setSkillMessage(String msg) {
		this.msg = msg;
	}
}
