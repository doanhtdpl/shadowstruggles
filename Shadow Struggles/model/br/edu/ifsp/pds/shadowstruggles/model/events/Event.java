package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

/**
 * Represents the events in a way that's compatible with the game editor. See
 * {@link EventInGame} for the model used only within the game.
 */
public class Event implements Serializable {
	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	private int id;
	private int x, y;
	private float width, height;
	private String map;
	private String layer;
	private String sprite;
	private TriggerType triggerType;
	private Array<EventAction> actions;
	private boolean collidable;

	public Event() {
		this.id = 1;
		this.x = 0;
		this.y = 0;
		this.width = 2;
		this.height = 2;
		this.map = "";
		this.layer = "";
		this.sprite = "";
		this.triggerType = TriggerType.TOUCH;
		this.actions = new Array<EventAction>();
		this.collidable = true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.x = json.readValue("x", Integer.class, jsonData);
		this.y = json.readValue("y", Integer.class, jsonData);
		this.width = json.readValue("width", Float.class, jsonData);
		this.height = json.readValue("height", Float.class, jsonData);
		this.map = json.readValue("map", String.class, jsonData);
		this.layer = json.readValue("layer", String.class, jsonData);
		this.sprite = json.readValue("sprite", String.class, jsonData);
		this.triggerType = json.readValue("triggerType", TriggerType.class,
				jsonData);
		this.actions = json.readValue("actions", Array.class, jsonData);
		this.collidable = json.readValue("collidable", Boolean.class, jsonData);
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
		json.writeValue("collidable", this.collidable);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Event.class)
			return false;

		Event event = (Event) obj;
		return event.getId() == this.id;
	}

	/**
	 * Creates a character from this object's parameters.
	 */
	public Character getCharacter(ShadowStruggles game) {
		RpgMap rpgMap = new RpgMap(map, layer,
				SettingsDAO.getSettings().defaultTileLayer);
		Character character = new Character(x, y, width, height, rpgMap);
		return character;
	}

	/**
	 * Returns an EventInGame.TriggerType value from this object's TriggerType
	 * parameter.
	 */
	public EventInGame.TriggerType getConvertedTriggerType() {
		EventInGame.TriggerType type = null;

		if (this.triggerType == TriggerType.AUTOMATIC)
			type = EventInGame.TriggerType.AUTOMATIC;
		else if (this.triggerType == TriggerType.INTERACT)
			type = EventInGame.TriggerType.INTERACT;
		else if (this.triggerType == TriggerType.TOUCH)
			type = EventInGame.TriggerType.TOUCH;

		return type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
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

	public boolean isCollidable() {
		return this.collidable;
	}
}
