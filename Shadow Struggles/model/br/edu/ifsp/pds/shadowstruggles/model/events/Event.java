package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Event implements Serializable {

	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	private int id;
	private float x, y, width, height;
	private String map;
	private String layer;
	private String sprite;
	private TriggerType triggerType;

	/**
	 * Specifies all possible actions which can be performed by this event.
	 */
	private Array<EventAction> actions;

	/**
	 * Specifies which actions from the actions list should be currently
	 * performed by the event.
	 */
	private Array<EventAction> currentActions;

	public Event() {
		this.id = 1;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.map = "";
		this.layer = "";
		this.sprite = "";
		this.triggerType = TriggerType.INTERACT;
		this.actions = new Array<EventAction>();
		this.currentActions = new Array<EventAction>();
	}

	public Event(int id, float x, float y, float width, float height,
			String map, String layer, boolean triggered, String sprite,
			TriggerType triggerType, Array<EventAction> actions,
			Array<EventAction> currentActions) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.map = map;
		this.layer = layer;
		this.sprite = sprite;
		this.triggerType = triggerType;
		this.actions = actions;
		this.currentActions = currentActions;
	}

	/**
	 * Activates the event.
	 */
	public void trigger() {
		for (EventAction action : currentActions) {
			action.act();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.x = json.readValue("x", Float.class, jsonData);
		this.y = json.readValue("y", Float.class, jsonData);
		this.width = json.readValue("width", Float.class, jsonData);
		this.height = json.readValue("height", Float.class, jsonData);
		this.map = json.readValue("map", String.class, jsonData);
		this.layer = json.readValue("layer", String.class, jsonData);
		this.sprite = json.readValue("sprite", String.class, jsonData);
		this.triggerType = json.readValue("triggerType", TriggerType.class,
				jsonData);
		this.actions = json.readValue("actions", Array.class, jsonData);
		this.currentActions = json.readValue("currentActions", Array.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("x", this.x);
		json.writeValue("y", this.y);
		json.writeValue("width", this.width);
		json.writeValue("height", this.height);
		json.writeValue("map", this.map);
		json.writeValue("layer", this.layer);
		json.writeValue("sprite", this.sprite);
		json.writeValue("triggerType", this.triggerType);
		json.writeValue("actions", this.actions);
		json.writeValue("currentActions", this.currentActions);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Event.class)
			return false;

		Event event = (Event) obj;
		return event.getId() == this.id;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
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

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public TriggerType getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(TriggerType triggerType) {
		this.triggerType = triggerType;
	}

	public Array<EventAction> getActions() {
		return actions;
	}

	public void setActions(Array<EventAction> actions) {
		this.actions = actions;
	}

	public Array<EventAction> getCurrentActions() {
		return currentActions;
	}

	public void setCurrentActions(Array<EventAction> currentActions) {
		this.currentActions = currentActions;
	}
}
