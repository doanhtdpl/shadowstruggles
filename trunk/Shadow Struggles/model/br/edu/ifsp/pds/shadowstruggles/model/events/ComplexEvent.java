package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;

public class ComplexEvent extends Event {
	public Array<Event> events;

	public ComplexEvent() {
		super();
		this.events = new Array<Event>();
	}

	public ComplexEvent(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, Array<Event> events) {
		super(id, x, y, map, layer, quest, triggered, sprite);

		this.events = events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.events = json.readValue("events", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("events", this.events);
	}

	@Override
	public void trigger() {
		// TODO: Implementar método.
	}
}
