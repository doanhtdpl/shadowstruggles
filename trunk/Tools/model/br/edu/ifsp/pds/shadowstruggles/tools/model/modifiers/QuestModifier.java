package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest.QuestStatus;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Requirement;

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

	public Quest quest;
	public QuestStatus questStatus;
	public Requirement requirement;
	public boolean requirementFulfilled;
	public OperationType operation;
}
