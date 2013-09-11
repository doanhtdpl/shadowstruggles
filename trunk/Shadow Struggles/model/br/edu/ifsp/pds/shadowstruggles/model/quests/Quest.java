package br.edu.ifsp.pds.shadowstruggles.model.quests;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Quest implements Serializable {
	public static enum QuestStatus {
		NOT_ACCEPTED, ONGOING, REQUIREMENTS_COMPLETED, QUEST_COMPLETED
	};

	private int id;
	private String name;
	private String description;
	private Array<Requirement> requirements;
	private Array<Modifier> rewards;
	private QuestStatus status;

	public Quest() {
		this.id = 1;
		this.name = "";
		this.description = "";
		this.requirements = new Array<Requirement>();
		this.rewards = new Array<Modifier>();
		this.status = QuestStatus.NOT_ACCEPTED;
	}

	public Quest(int id, String name, String description,
			Array<Requirement> requirements, Array<Modifier> rewards,
			QuestStatus status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.requirements = requirements;
		this.rewards = rewards;
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Quest)
			return ((Quest) obj).getId() == this.id;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.requirements = json.readValue("requirements", Array.class,
				jsonData);
		this.rewards = json.readValue("rewards", Array.class, jsonData);
		this.status = json.readValue("status", QuestStatus.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
		json.writeValue("description", this.description);
		json.writeValue("requirements", this.requirements);
		json.writeValue("rewards", this.rewards);
		json.writeValue("status", this.status);
	}

	public int getId() {
		return this.id;
	}

	public Array<Requirement> getRequirements() {
		return requirements;
	}

	public QuestStatus getStatus() {
		return this.status;
	}

	public void setStatus(QuestStatus status) {
		this.status = status;
	}
}
