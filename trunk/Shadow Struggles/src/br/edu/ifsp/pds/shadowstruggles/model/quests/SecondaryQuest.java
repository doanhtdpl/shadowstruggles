package br.edu.ifsp.pds.shadowstruggles.model.quests;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SecondaryQuest extends Requirement {
	public Quest quest;
	
	public SecondaryQuest() {
		super();
		
		this.quest = new Quest();
	}
	
	public SecondaryQuest(String name, String description, Quest quest) {
		super(name, description);
		this.quest = quest;
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
