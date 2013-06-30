package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public abstract class Event implements Serializable {
	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	private int id;
	private float x, y;
	private String map;
	private String layer;
	private Quest quest;
	private boolean triggered;
	private String sprite;

	public Event() {
		this.id = 1;
		this.x = 0;
		this.y = 0;
		this.map = "";
		this.layer = "";
		this.quest = new Quest();
		this.triggered = false;
		this.sprite = "";
	}

	public Event(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.map = map;
		this.layer = layer;
		this.quest = quest;
		this.triggered = triggered;
		this.sprite = sprite;
	}
	
	/**
	 * Activates the event.
	 */
	public abstract void trigger();

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.x = json.readValue("x", Float.class, jsonData);
		this.y = json.readValue("y", Float.class, jsonData);
		this.map = json.readValue("map", String.class, jsonData);
		this.layer = json.readValue("layer", String.class, jsonData);
		this.triggered = json.readValue("triggered", Boolean.class, jsonData);
		this.sprite = json.readValue("sprite", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("x", this.x);
		json.writeValue("y", this.y);
		json.writeValue("map", this.map);
		json.writeValue("layer", this.layer);
		json.writeValue("triggered", this.triggered);
		json.writeValue("sprite", this.sprite);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public boolean isTriggered() {
		return triggered;
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	
	
}
