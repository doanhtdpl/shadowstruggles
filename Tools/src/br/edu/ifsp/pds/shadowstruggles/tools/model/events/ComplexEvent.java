package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class ComplexEvent extends Event {
	public ArrayList<Event> events;
	
	public ComplexEvent() {
		// TODO Auto-generated constructor stub
	}
	
	public ComplexEvent(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, ArrayList<Event> events) {
		super(id, x, y, map, layer, quest, triggered, sprite);
		
		this.events = events;
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
