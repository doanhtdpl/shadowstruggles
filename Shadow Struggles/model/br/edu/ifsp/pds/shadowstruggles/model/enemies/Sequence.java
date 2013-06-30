package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Sequence implements Serializable {
	public Array<Condition> conditions;
	public Array<Action> actions;

	public Sequence() {
		this.conditions = new Array<Condition>();
		this.actions = new Array<Action>();
	}

	public Sequence(Array<Condition> conditions, Array<Action> actions) {
		this.conditions = conditions;
		this.actions = actions;
	}

	// TODO: Implementar método.
	public boolean evaluateConditions() {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.conditions = json.readValue("conditions", Array.class, jsonData);
		this.actions = json.readValue("actions", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("conditions", this.conditions);
		json.writeValue("actions", this.actions);
	}

}
