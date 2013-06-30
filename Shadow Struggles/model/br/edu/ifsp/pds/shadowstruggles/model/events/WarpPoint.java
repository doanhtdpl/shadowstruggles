package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;

public class WarpPoint extends Event {
	public WarpPoint destination;
	public boolean active;

	public WarpPoint() {
		super();
		
		this.destination = this;
		this.active = false;
	}

	public WarpPoint(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, WarpPoint destination, boolean active) {
		super(id, x, y, map, layer, quest, triggered, sprite);
		
		this.destination = destination;
		this.active = active;
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.destination = json.readValue("destination", WarpPoint.class, jsonData);
		this.active = json.readValue("active", Boolean.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("destination", this.destination);
		json.writeValue("active", this.active);
	}

	@Override
	public void trigger() {
		// TODO: Implementar método.
	}
}
