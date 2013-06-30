package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.events.Event;

public class MovementControl extends SceneItem {
	public static enum Direction {
		NORTH, EAST, SOUTH, WEST
	};

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
	public void read(Json json, JsonValue jsonData) {
		this.targetEvent = json.readValue("targetEvent", Event.class, jsonData);
		this.targetPlayer = json.readValue("targetPlayer", Boolean.class,
				jsonData);
		this.direction = json.readValue("direction", Direction.class, jsonData);
		this.steps = json.readValue("steps", Integer.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("targetEvent", this.targetEvent);
		json.writeValue("targetPlayer", this.targetPlayer);
		json.writeValue("direction", this.direction);
		json.writeValue("direction", this.direction);
	}

	@Override
	public void action() {
		// TODO: Implementar método.
	}
}
