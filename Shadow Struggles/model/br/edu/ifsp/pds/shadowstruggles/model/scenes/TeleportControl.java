package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.events.Event;

public class TeleportControl extends SceneItem {
	public Event target;
	public boolean targetPlayer;
	public String map;
	public String layer;
	public float x, y;

	public TeleportControl() {
		this.target = null;
		this.targetPlayer = false;
		this.map = "";
		this.layer = "";
		this.x = 0;
		this.y = 0;
	}

	public TeleportControl(Event target, boolean targetPlayer, String map,
			String layer, float x, float y) {
		this.target = target;
		this.targetPlayer = targetPlayer;
		this.map = map;
		this.layer = layer;
		this.x = x;
		this.y = y;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.target = json.readValue("target", Event.class, jsonData);
		this.targetPlayer = json.readValue("targetPlayer", Boolean.class,
				jsonData);
		this.map = json.readValue("map", String.class, jsonData);
		this.layer = json.readValue("layer", String.class, jsonData);
		this.x = json.readValue("x", Float.class, jsonData);
		this.y = json.readValue("y", Float.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("target", this.target);
		json.writeValue("targetPlayer", this.targetPlayer);
		json.writeValue("map", this.map);
		json.writeValue("layer", this.layer);
		json.writeValue("x", this.x);
		json.writeValue("y", this.y);
	}

	@Override
	public void action() {
		// TODO: Implementar método.
	}
}
