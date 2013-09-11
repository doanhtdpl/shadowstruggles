package br.edu.ifsp.pds.shadowstruggles.model.quests;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SecondaryQuest extends Requirement {
	private Quest quest;
	
	public SecondaryQuest() {
		super();
		
		this.quest = new Quest();
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.quest = json.readValue("quest", Quest.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("quest", this.quest);
	}
}
