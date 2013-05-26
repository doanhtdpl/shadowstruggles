package br.edu.ifsp.pds.shadowstruggles.tools.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class TutorialDialog implements Serializable {

	public static enum EventType {
		NONE, CARD_SELECTED, CARD_SUMMONED, PREPARE_EVENT
	};

	public int id;
	public String text;
	public int indicatorX;
	public int indicatorY;
	public boolean indicatorVisible;
	public EventType eventType;
	public String eventTarget;

	public TutorialDialog() {
	}

	public TutorialDialog(int id, String text, int indicatorX, int indicatorY,
			boolean indicatorVisible, EventType eventType, String eventTarget) {
		this.id = id;
		this.text = text;
		this.indicatorX = indicatorX;
		this.indicatorY = indicatorY;
		this.indicatorVisible = indicatorVisible;
		this.eventType = eventType;
		this.eventTarget = eventTarget;
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
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

}
