package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Requirement;

public class QuestModifier extends Modifier {

	public Quest quest;
	public Requirement requirement;
	/**
	 * If true, marks the quest as completed.
	 */
	public boolean completeQuest;

	public QuestModifier() {
		this.quest = new Quest();
		this.requirement = new Requirement() {
		};
	}

	public QuestModifier(Quest quest, Requirement requirement,
			boolean completeQuest) {
		this.quest = quest;
		this.requirement = requirement;
		this.completeQuest = completeQuest;
	}
}
