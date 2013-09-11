package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest.QuestStatus;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Requirement;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Modifies quests of the player according to the specified operation.
 */
public class QuestModifier extends Modifier {
	/**
	 * The operation type determines which kind of actions will be taken and
	 * which attributes are necessary or optional. ADD_QUEST - Marks a quest as
	 * ONGOING and adds it to the player's quests; CHANGE_QUEST_STATUS - Given a
	 * Quest and a QuestStatus object, change the quest's status;
	 * CHANGE_REQUIREMENT_STATUS - Given a quest and a requirement belonging to
	 * it, marks the requirement as fulfilled or not.
	 */
	public static enum OperationType {
		ADD_QUEST, CHANGE_QUEST_STATUS, CHANGE_REQUIREMENT_STATUS
	};

	private Quest quest;
	private QuestStatus questStatus;
	private Requirement requirement;
	private boolean requirementFulfilled;
	private OperationType operation;

	@Override
	public void modify() {
		Profile profile = ShadowStruggles.getInstance().getProfile();

		if (operation == OperationType.ADD_QUEST) {
			quest.setStatus(QuestStatus.ONGOING);
			profile.getQuests().add(quest);
		} else if (operation == OperationType.CHANGE_QUEST_STATUS) {
			Quest playerQuest = profile.getQuests().get(
					profile.getQuests().indexOf(quest, false));
			playerQuest.setStatus(questStatus);
		} else if (operation == OperationType.CHANGE_REQUIREMENT_STATUS) {
			Quest playerQuest = profile.getQuests().get(
					profile.getQuests().indexOf(quest, false));
			Requirement playerRequirement = playerQuest.getRequirements().get(
					playerQuest.getRequirements().indexOf(requirement, false));
			playerRequirement.setFulfilled(requirementFulfilled);
		}
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
		this.operation = json.readValue("operation", OperationType.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("quest", this.quest);
		json.writeValue("questStatus", this.questStatus);
		json.writeValue("requirement", this.requirement);
		json.writeValue("requirementFulfilled", this.requirementFulfilled);
		json.writeValue("operation", this.operation);
	}
}
