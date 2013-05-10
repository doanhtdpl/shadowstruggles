package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public abstract class Event implements Serializable {
	public static enum TriggerType { TOUCH, INTERACT, AUTOMATIC };
	
	public int id;
	public float x, y;
	public String map;
	public String layer;
	public Quest quest;
	public boolean triggered;
	public String sprite;
	
	public abstract void trigger();
	
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.map = map;
		this.layer = layer;
		this.quest = quest;
		this.triggered = triggered;
		this.sprite = sprite;
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
