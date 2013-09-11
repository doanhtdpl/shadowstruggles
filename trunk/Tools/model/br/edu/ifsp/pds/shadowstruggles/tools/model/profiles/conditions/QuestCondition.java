package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest.QuestStatus;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Requirement;

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

	public Quest quest;
	public QuestStatus questStatus;
	public Requirement requirement;
	public boolean requirementFulfilled;
	public EvaluationType type;
}
