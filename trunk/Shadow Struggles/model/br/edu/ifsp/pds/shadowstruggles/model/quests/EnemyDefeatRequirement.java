package br.edu.ifsp.pds.shadowstruggles.model.quests;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.profiles.EnemyDefeat;

public class EnemyDefeatRequirement extends Requirement {
	public EnemyDefeat enemyDefeat;
	
	public EnemyDefeatRequirement() {
		super();
		
		this.enemyDefeat = new EnemyDefeat();
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.enemyDefeat = json.readValue("enemyDefeat", EnemyDefeat.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("enemyDefeat", this.enemyDefeat);
	}
}
