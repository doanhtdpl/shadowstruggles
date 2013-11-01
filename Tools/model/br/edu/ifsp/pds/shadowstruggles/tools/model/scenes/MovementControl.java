package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;

public class MovementControl extends SceneItem {
	public static enum Direction { NORTH, EAST, SOUTH, WEST };
	
	public Event target;
	public Direction direction;
	public int steps;
	
	public MovementControl() {
		this.target = null;
		this.direction = Direction.NORTH;
		this.steps = 0;
	}
}
