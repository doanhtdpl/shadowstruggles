package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Ending;

/**
 * A condition related to the player's progression in the storyline.
 */
public class StoryCondition extends ProfileCondition {
	public static enum EvaluationType {
		STORY_POINTS, PATH, HAS_ENDINGS
	};

	public int storyPoints;
	public String path;
	public ArrayList<Ending> endings;
	public NumericComparator pointsComparator;
	public EvaluationType type;

	public StoryCondition() {
		this.storyPoints = 0;
		this.path = "";
		this.endings = new ArrayList<Ending>();
		this.pointsComparator = null;
		this.type = null;
	}
}
