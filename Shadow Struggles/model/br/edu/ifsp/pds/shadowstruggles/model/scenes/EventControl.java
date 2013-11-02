package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventInGame;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class EventControl extends SceneItem {
	public static enum EventManipulation {
		CHANGE_LAYER, CHANGE_SPRITE
	};

	private Event event;
	private EventManipulation manipulationType;
	private String newValue;

	public EventControl() {
		this.manipulationType = null;
		this.newValue = "";
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.event = json.readValue("event", Event.class, jsonData);
		this.manipulationType = json.readValue("manipulationType",
				EventManipulation.class, jsonData);
		this.newValue = json.readValue("newValue", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("event", this.event);
		json.writeValue("manipulationType", this.manipulationType);
		json.writeValue("newValue", this.newValue);
	}

	@Override
	public void action() {
		Profile profile = ShadowStruggles.getInstance().getProfile();
		EventInGame eventInGame = profile.getEvent(event.getId());

		if (manipulationType == EventManipulation.CHANGE_LAYER) {
			eventInGame.setLayer(newValue);
		} else if (manipulationType == EventManipulation.CHANGE_SPRITE) {
			eventInGame.setSprite(newValue);
		}
		
		parentScene.runNextItem();
	}
}
