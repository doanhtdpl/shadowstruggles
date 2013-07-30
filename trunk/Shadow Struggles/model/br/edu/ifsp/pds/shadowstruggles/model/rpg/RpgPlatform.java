package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path.Step;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * This is the main core of the RPG exploring mechanic. It handles the
 * interaction between the RPG Elements.
 */

public class RpgPlatform {
	private RpgMap map;
	private Character character;
	private RpgController controller;

	public RpgPlatform(RpgController controller, Character character) {
		this(controller, character,
				new RpgMap(
						new TmxMapLoader(new InternalFileHandleResolver())
								.load("data/rpg_maps/map.tmx")));
	}

	public RpgPlatform(RpgController controller, Character character, RpgMap map) {
		controller.setModel(this);
		this.controller = controller;
		this.map = map;
		this.character = character;
	}

	public Character getCharacter() {
		return character;
	}

	public TiledMap getMap() {
		return map.getMap();
	}

	public RpgMap getRpgMap() {
		return this.map;
	}

	/**
	 * Moves the character towards a specified path, step by step.
	 */
	public void moveCharacter(Path path, int[] destination) {
		// Stop any ongoing path to start another one.
		this.getCharacter().clearMovementBuffer();

		// Since these directions are being buffered, the character is not
		// updated immediately, so we deal with an imaginary character in x-y
		// coordinates updated in real time.
		int currentX = character.getTileX();
		int currentY = character.getTileY();

		if (path != null) {
			for (Step step : path.getSteps()) {
				WalkDirection direction = null;

				if (step.getX() == currentX && step.getY() < currentY) {
					direction = WalkDirection.WALK_UP;
					currentY--;
				}
				if (step.getX() == currentX && step.getY() > currentY) {
					direction = WalkDirection.WALK_DOWN;
					currentY++;
				}
				if (step.getY() == currentY && step.getX() < currentX) {
					direction = WalkDirection.WALK_LEFT;
					currentX--;
				}
				if (step.getY() == currentY && step.getX() > currentX) {
					direction = WalkDirection.WALK_RIGHT;
					currentX++;
				}

				this.moveCharacter(direction, true, destination);
			}
		} else {
			// If the path is null, just turn the character to face the
			// obstacle.
			WalkDirection direction = null;
			if (destination[0] == currentX && destination[1] < currentY)
				direction = WalkDirection.WALK_UP;
			if (destination[0] == currentX && destination[1] > currentY)
				direction = WalkDirection.WALK_DOWN;
			if (destination[0] < currentX && destination[1] == currentY)
				direction = WalkDirection.WALK_LEFT;
			if (destination[0] > currentX && destination[1] == currentY)
				direction = WalkDirection.WALK_RIGHT;
			this.character.setDirection(direction);
		}

	}

	/**
	 * Moves the character in one tile in the specified direction and notifies
	 * the RpgController.
	 */
	public boolean moveCharacter(WalkDirection direction) {
		return this.moveCharacter(direction, false, null);
	}

	public boolean moveCharacter(WalkDirection direction, boolean inPath,
			int[] destination) {
		if (direction != null) {
			if (character.walk(direction, map, inPath, destination)) {
				controller.characterMoved(direction);
				return true;
			}
		}
		return false;
	}

	/**
	 * Triggers the event immediately in front of the character, if possible.
	 */
	public void triggerEvent() {
		int tileX = character.getTileX();
		int tileY = character.getTileY();

		WalkDirection oppositeDirection = null;

		if (character.getDirection() == WalkDirection.WALK_DOWN) {
			tileY++;
			oppositeDirection = WalkDirection.WALK_UP;
		}
		if (character.getDirection() == WalkDirection.WALK_LEFT) {
			tileX--;
			oppositeDirection = WalkDirection.WALK_RIGHT;
		}
		if (character.getDirection() == WalkDirection.WALK_RIGHT) {
			tileX++;
			oppositeDirection = WalkDirection.WALK_LEFT;
		}
		if (character.getDirection() == WalkDirection.WALK_UP) {
			tileY--;
			oppositeDirection = WalkDirection.WALK_DOWN;
		}

		this.map.triggerEvent(tileX, tileY, character.getMover(), oppositeDirection);
	}

}
