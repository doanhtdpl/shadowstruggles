package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SavePoint extends Event {

	public SavePoint() {
		super();
	}
	
	public SavePoint(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite) {
		super(id, x, y, map, layer, quest, triggered, sprite);
	}
	
	@Override
	public void trigger() {
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		super.read(arg0, arg1);
	}

	@Override
	public void write(Json arg0) {
		super.write(arg0);
	}
}
