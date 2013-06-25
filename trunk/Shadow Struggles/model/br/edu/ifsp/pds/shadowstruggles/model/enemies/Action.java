package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Action implements Serializable {
	public static enum Attribute { LANE, TILE, CARD };
	
	public Attribute type;
	public Object value;
	
	public Action() {
		this.type = Attribute.LANE;
		this.value = new Object();
	}
	
	public Action(Attribute type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.type = json.readValue("type", Attribute.class, jsonData);
		this.value = json.readValue("value", Object.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("type", this.type);
		json.writeValue("value", this.value);
	}
}
