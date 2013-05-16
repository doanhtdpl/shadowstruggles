package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SceneEvent extends Event {
	public Scene scene;
	
	public SceneEvent() {
		super();
		
		this.scene = new Scene();
	}
	
	public SceneEvent(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, Scene scene) {
		super(id, x, y, map, layer, quest, triggered, sprite);
		
		this.scene = scene;
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
