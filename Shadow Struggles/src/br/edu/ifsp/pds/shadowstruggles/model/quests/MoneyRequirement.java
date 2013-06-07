package br.edu.ifsp.pds.shadowstruggles.model.quests;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class MoneyRequirement extends Requirement {
	public int money;
	
	public MoneyRequirement() {
		super();
		
		this.money = 0;
	}
	
	public MoneyRequirement(String name, String description, int money) {
		super(name, description);
		
		this.money = money;
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.money = json.readValue("money", Integer.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("money", this.money);
	}
}
