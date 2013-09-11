package br.edu.ifsp.pds.shadowstruggles.model.profiles.conditions;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Ending;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * A condition related to the player's progression in the storyline.
 */
public class StoryCondition extends ProfileCondition {
	public static enum EvaluationType {
		STORY_POINTS, PATH, HAS_ENDINGS
	};

	private int storyPoints;
	private String path;
	private Array<Ending> endings;
	private NumericComparator pointsComparator;
	private EvaluationType type;

	public StoryCondition() {
		this.storyPoints = 0;
		this.path = "";
		this.endings = new Array<Ending>();
		this.pointsComparator = null;
		this.type = null;
	}

	@Override
	public boolean evaluate() {
		Profile profile = ShadowStruggles.getInstance().getProfile();

		if (type == EvaluationType.PATH) {
			return profile.getPath().equals(path);
		} else if (type == EvaluationType.HAS_ENDINGS) {
			for (Ending ending : endings) {
				if (!profile.getEndings().contains(ending, false))
					return false;
			}
			return true;
		} else if (type == EvaluationType.STORY_POINTS) {
			if (pointsComparator == NumericComparator.EQUAL_TO)
				return storyPoints == profile.getStoryPoints();
			if (pointsComparator == NumericComparator.GREATER_THAN)
				return storyPoints > profile.getStoryPoints();
			if (pointsComparator == NumericComparator.GREATER_THAN_OR_EQUAL_TO)
				return storyPoints >= profile.getStoryPoints();
			if (pointsComparator == NumericComparator.LESS_THAN)
				return storyPoints < profile.getStoryPoints();
			if (pointsComparator == NumericComparator.LESS_THAN_OR_EQUAL_TO)
				return storyPoints <= profile.getStoryPoints();
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.storyPoints = json.readValue("storyPoints", Integer.class,
				jsonData);
		this.path = json.readValue("path", String.class, jsonData);
		this.endings = json.readValue("endings", Array.class, jsonData);
		this.pointsComparator = json.readValue("pointsComparator",
				NumericComparator.class, jsonData);
		this.type = json.readValue("type", EvaluationType.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("storyPoints", this.storyPoints);
		json.writeValue("path", this.path);
		json.writeValue("endings", this.endings);
		json.writeValue("pointsComparator", this.pointsComparator);
		json.writeValue("type", this.type);
	}
}
