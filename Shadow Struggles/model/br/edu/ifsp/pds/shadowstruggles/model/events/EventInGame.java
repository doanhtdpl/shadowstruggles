package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

/**
 * A model for events used only within the game. See {@link Event} for the
 * general class compatible with the game editor.
 */
public class EventInGame implements Serializable {
	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	private int id;
	private String sprite;
	private String map;
	private String layer;
	private TriggerType triggerType;
	private Array<EventAction> actions;
	private Character character;
	private boolean collidable;

	/**
	 * Activates the event.
	 */
	public void trigger() {
		trigger(null);
	}

	/**
	 * Activates the event.
	 * 
	 * @param directionTurn
	 *            The direction which the event should turn. If null, the
	 *            direction remains the same.
	 */
	public void trigger(WalkDirection directionTurn) {
		if (directionTurn != null)
			this.character.setDirection(directionTurn);

		for (EventAction action : actions) {
			if (action.conditionsFulfilled()) {
				action.act();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.sprite = json.readValue("sprite", String.class, jsonData);
		this.map = json.readValue("map", String.class, jsonData);
		this.layer = json.readValue("layer", String.class, jsonData);
		this.triggerType = json.readValue("triggerType", TriggerType.class,
				jsonData);
		this.actions = json.readValue("actions", Array.class, jsonData);
		this.character = json.readValue("character", Character.class, jsonData);
		this.collidable = json.readValue("collidable", Boolean.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("sprite", this.sprite);
		json.writeValue("map", this.map);
		json.writeValue("layer", this.layer);
		json.writeValue("triggerType", this.triggerType);
		json.writeValue("actions", this.actions);
		json.writeValue("character", this.character);
		json.writeValue("collidable", this.collidable);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != EventInGame.class)
			return false;

		EventInGame event = (EventInGame) obj;
		return event.getId() == this.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Array<EventAction> getActions() {
		return actions;
	}

	public void setActions(Array<EventAction> actions) {
		this.actions = actions;
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

	public boolean isCollidable() {
		return this.collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
}
