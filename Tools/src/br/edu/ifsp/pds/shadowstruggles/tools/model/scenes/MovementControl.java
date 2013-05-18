package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;

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
}
