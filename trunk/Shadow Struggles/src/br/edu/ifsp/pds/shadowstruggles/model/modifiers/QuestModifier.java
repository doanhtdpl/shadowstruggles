package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Requirement;

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

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.quest = json.readValue("quest", Quest.class, jsonData);
		this.requirement = json.readValue("requirement", Requirement.class,
				jsonData);
		this.completeQuest = json.readValue("completeQuest", Boolean.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("quest", this.quest);
		json.writeValue("requirement", this.requirement);
		json.writeValue("completeQuest", this.completeQuest);
	}
}
