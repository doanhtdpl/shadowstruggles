package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class WarpAction extends EventAction {
	private Event destination;

	public WarpAction() {
		this.destination = new Event();
	}

	public WarpAction(Event destination) {
		this.destination = destination;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.destination = json.readValue("destination", Event.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("destination", this.destination);
	}

}
