package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public class Event implements Serializable {
	public static enum TriggerType {
		TOUCH, INTERACT, AUTOMATIC
	};

	public int id;
	public int x, y;
	public float width, height;
	public String map;
	public String layer;
	public String sprite;
	public TriggerType triggerType;
	public ArrayList<EventAction> actions;
	public boolean collidable;

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
		this.actions = new ArrayList<EventAction>();
		this.collidable = true;
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
