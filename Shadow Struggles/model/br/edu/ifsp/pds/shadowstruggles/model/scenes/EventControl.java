package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class EventControl extends SceneItem {
	public static enum EventManipulation {
		DELETE_EVENT, TOOGLE_VISIBILITY, CHANGE_LAYER, CHANGE_SPRITE
	};

	public EventManipulation manipulationType;
	public String newLayer;
	public String newSprite;

	public EventControl() {
		this.manipulationType = EventManipulation.DELETE_EVENT;
		this.newLayer = "";
		this.newSprite = "";
	}

	public EventControl(EventManipulation manipulationType, String newLayer,
			String newSprite) {
		this.manipulationType = manipulationType;
		this.newLayer = newLayer;
		this.newSprite = newSprite;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.manipulationType = json.readValue("manipulationType",
				EventManipulation.class, jsonData);
		this.newLayer = json.readValue("newLayer", String.class, jsonData);
		this.newSprite = json.readValue("newSprite", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("manipulationType", this.manipulationType);
		json.writeValue("newLayer", this.newLayer);
		json.writeValue("newSprite", this.newSprite);
	}
}
