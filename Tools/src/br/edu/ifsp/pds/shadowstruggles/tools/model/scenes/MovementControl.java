package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class MovementControl extends SceneItem {
	public static enum Direction { NORTH, EAST, SOUTH, WEST };
	
	public Event targetEvent;
	public boolean targetPlayer;
	public Direction direction;
	public int steps;
	
	public MovementControl() {
		this.targetEvent = null;
		this.targetPlayer = false;
		this.direction = Direction.EAST;
		this.steps = 0;
	}
	
	public MovementControl(Event targetEvent, boolean targetPlayer,
			Direction direction, int steps) {
		this.targetEvent = targetEvent;
		this.targetPlayer = targetPlayer;
		this.direction = direction;
		this.steps = steps;
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
