package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class TeleportControl extends SceneItem {
	public Event target;
	public boolean targetPlayer;
	public String map;
	public String layer;
	public float x, y;
	
	public TeleportControl() {
		this.target = null;
		this.targetPlayer = false;
		this.map = "";
		this.layer = "";
		this.x = 0;
		this.y = 0;
	}
	
	public TeleportControl(Event target, boolean targetPlayer, String map,
			String layer, float x, float y) {
		this.target = target;
		this.targetPlayer = targetPlayer;
		this.map = map;
		this.layer = layer;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void action() {
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
