package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventInGame;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;

public class MovementControl extends SceneItem {
	public static enum Direction {
		NORTH, EAST, SOUTH, WEST
	};

	/**
	 * If the target is null, it moves the player instead.
	 */
	public Event target;
	public Direction direction;
	public int steps;

	public MovementControl() {
		this.target = null;
		this.direction = Direction.NORTH;
		this.steps = 0;
	}

	@Override
	public void action() {
		WalkDirection charDirection = null;
		Character character = null;
		Profile profile = ShadowStruggles.getInstance().getProfile();
		
		if (this.direction == Direction.NORTH)
			charDirection = WalkDirection.WALK_UP;
		if (this.direction == Direction.EAST)
			charDirection = WalkDirection.WALK_RIGHT;
		if (this.direction == Direction.SOUTH)
			charDirection = WalkDirection.WALK_DOWN;
		if (this.direction == Direction.WEST)
			charDirection = WalkDirection.WALK_LEFT;

		if (target != null) {
			EventInGame eventInGame = profile.getEvent(target.getId());
			character = eventInGame.getCharacter();
		} else {
			character = profile.getCharacter();
		}

		character.clearMovementBuffer();
		for (int i = 0; i < steps; i++) {
			character.walk(charDirection, character.getCurrentMap(), true);
		}
		
		parentScene.runNextItem();
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.target = json.readValue("target", Event.class, jsonData);
		this.direction = json.readValue("direction", Direction.class, jsonData);
		this.steps = json.readValue("steps", Integer.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("target", this.target);
		json.writeValue("direction", this.direction);
		json.writeValue("steps", this.steps);
	}
}
