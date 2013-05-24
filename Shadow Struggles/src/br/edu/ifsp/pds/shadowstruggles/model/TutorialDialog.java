package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.screens.BattleTutorial.EventType;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleTutorial.TutorialEvent;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class TutorialDialog implements Serializable {

	private int id;
	private String text;
	private int indicatorX;
	private int indicatorY;
	private boolean indicatorVisible;
	private EventType eventType;
	private String eventTarget;

	public TutorialDialog() {
	}

	public TutorialDialog(int id, String text, int indicatorX, int indicatorY,
			boolean indicatorVisible, EventType eventType, String eventTarget) {
		this.id = id;
		this.text = text;
		this.indicatorX = indicatorX;
		this.indicatorY = indicatorY;
		this.indicatorVisible = indicatorVisible;
		this.eventType=eventType;
		this.eventTarget=eventTarget;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setIndicatorX(int indicatorX) {
		this.indicatorX = indicatorX;
	}

	public void setIndicatorY(int indicatorY) {
		this.indicatorY = indicatorY;
	}

	public void setIndicatorVisible(boolean indicatorVisible) {
		this.indicatorVisible = indicatorVisible;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public int getIndicatorX() {
		return indicatorX;
	}

	public int getIndicatorY() {
		return indicatorY;
	}

	public boolean isIndicatorVisible() {
		return indicatorVisible;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	public String getEventTarget() {
		return eventTarget;
	}
	
	public void setEventTarget(String eventTarget) {
		this.eventTarget = eventTarget;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("text", this.text);
		json.writeValue("indicatorX", this.indicatorX);
		json.writeValue("indicatorY", this.indicatorY);
		json.writeValue("indicatorVisible", this.indicatorVisible);
		json.writeValue("eventType", this.eventType);
		json.writeValue("eventTarget", this.eventTarget);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.text = json.readValue("text", String.class, jsonData);
		this.indicatorX = json.readValue("indicatorX", Integer.class, jsonData);
		this.indicatorY = json.readValue("indicatorY", Integer.class, jsonData);
		this.indicatorVisible = json.readValue("indicatorVisible",
				Boolean.class, jsonData);
		this.eventType = json.readValue("eventType", EventType.class, jsonData);
		this.eventTarget = json.readValue("eventTarget", String.class, jsonData);
	}
}
