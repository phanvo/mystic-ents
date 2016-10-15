package controller;
import java.util.Random;

import model.piece.Piece;
import model.piece.Team;
import model.skills.SkillSet;
import model.traits.TraitSet;

/**
 * Class is passed instantiated but empty Piece and builds it with Traits, Skill and Team
 * @author Daniel
 *
 */
public class PieceBuilder {
	
	//Random is seeded by date long here and used throughout the piece gen process
	Random randomNumGen = new Random(System.currentTimeMillis());
	
	/**
	 * Method is passed empty Piece and team num,
	 * and coordinates the aggregation of skill and trait sets into the piece,
	 * and assigns team to the piece
	 * @param newPiece
	 * @param team
	 */
	public void buildPiece (Piece newPiece, Enum<Team> team){
		
		TraitSet getTraitSet = new TraitSetBuilder(randomNumGen).getTraitSet();
		SkillSet getSkillSet = new SkillSetBuilder().getSkillSet();

		newPiece.setTraitSet(getTraitSet);
		newPiece.setSkillSet(getSkillSet);
		newPiece.setTeam(team);
	}
}
