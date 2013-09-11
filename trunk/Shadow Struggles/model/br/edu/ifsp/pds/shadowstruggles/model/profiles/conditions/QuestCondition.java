package br.edu.ifsp.pds.shadowstruggles.model.profiles.conditions;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest.QuestStatus;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Requirement;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Evaluates aspects of a quest according to the specified evaluation type.
 */
public class QuestCondition extends ProfileCondition {
	/**
	 * QUEST_STATUS - Checks a Quest against a given QuestStatus;
	 * REQUIREMENT_FULFILLED - Given a Quest and a Requirement, checks if the
	 * requirement has been fulfilled; REQUIREMENTS_FULFILLED - Given a quest,
	 * checks if all of its requirements have been fulfilled or not.
	 */
	public static enum EvaluationType {
		QUEST_STATUS, REQUIREMENT_FULFILLED, REQUIREMENTS_FULFILLED
	};

	private Quest quest;
	private QuestStatus questStatus;
	private Requirement requirement;
	private boolean requirementFulfilled;
	private EvaluationType type;

	@Override
	public boolean evaluate() {
		Profile profile = ShadowStruggles.getInstance().getProfile();
		Quest playerQuest = profile.getQuests().get(
				profile.getQuests().indexOf(quest, false));

		if (type == EvaluationType.QUEST_STATUS) {
			return playerQuest.getStatus().equals(questStatus);
		} else if (type == EvaluationType.REQUIREMENT_FULFILLED) {
			Requirement playerRequirement = playerQuest.getRequirements().get(
					playerQuest.getRequirements().indexOf(requirement, false));
			return playerRequirement.isFulfilled() == requirementFulfilled;
		} else if (type == EvaluationType.REQUIREMENTS_FULFILLED) {
			for (Requirement requirement : playerQuest.getRequirements()) {
				if (!requirement.isFulfilled())
					return false;
			}
			return true;
		}

		return false;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.quest = json.readValue("quest", Quest.class, jsonData);
		this.questStatus = json.readValue("questStatus", QuestStatus.class,
				jsonData);
		this.requirement = json.readValue("requirement", Requirement.class,
				jsonData);
		this.requirementFulfilled = json.readValue("requirementFulfilled",
				Boolean.class, jsonData);
		this.type = json.readValue("type", EvaluationType.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("quest", this.quest);
		json.writeValue("questStatus", this.questStatus);
		json.writeValue("requirement", this.requirement);
		json.writeValue("requirementFulfilled", this.requirementFulfilled);
		json.writeValue("type", this.type);
	}
}
