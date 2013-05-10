package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class WarpPoint extends Event {
	public WarpPoint destination;
	public boolean active;

	public WarpPoint() {
		// TODO Auto-generated constructor stub
	}

	public WarpPoint(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, WarpPoint destination, boolean active) {
		super(id, x, y, map, layer, quest, triggered, sprite);
		
		this.destination = destination;
		this.active = active;
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub

	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub

	}
}
