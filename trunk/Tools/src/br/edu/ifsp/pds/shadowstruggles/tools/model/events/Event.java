package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public abstract class Event implements Serializable {
	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	public int id;
	public float x, y;
	public String map;
	public String layer;
	public Quest quest;
	public boolean triggered;
	public String sprite;

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

	@Override
	public void read(Json arg0, JsonValue arg1) {
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void write(Json arg0) {
		try {
			SerializationHelper
					.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
}
