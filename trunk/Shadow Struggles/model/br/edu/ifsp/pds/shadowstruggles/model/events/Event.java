package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Event implements Serializable {

	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	private int id;
	private String sprite;
	private TriggerType triggerType;
	private Character character;

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
		this.sprite = "";
		this.triggerType = TriggerType.INTERACT;
		this.character = new Character();
		this.actions = new Array<EventAction>();
		this.currentActions = new Array<EventAction>();
	}

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

		for (EventAction action : currentActions) {
			action.act();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
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

	public Array<EventAction> getCurrentActions() {
		return currentActions;
	}

	public void setCurrentActions(Array<EventAction> currentActions) {
		this.currentActions = currentActions;
	}
}
