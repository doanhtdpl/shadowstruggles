package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Requirement;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

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
			@Override
			public boolean checkCompleted() {
				return false;
			}
		};
	}
	
	public QuestModifier(Quest quest, Requirement requirement,
			boolean completeQuest) {
		this.quest = quest;
		this.requirement = requirement;
		this.completeQuest = completeQuest;
	}

	@Override
	public void modify(Profile profile) {
		// TODO Auto-generated method stub
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub

	}
}
