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

	/**
	 * The constructor loads the map according to the stage name.
	 * 
	 * @param controller
	 *            The controller of the MVC scheme.
	 * @param stageName
	 *            Defines which attributes the stage will take.
	 * @param character
	 *            The player's character for this stage.
	 * 
	 */
	public RpgPlatform(RpgController controller, String stageName,
			Character character) {
		this(controller, stageName, "", character);
	}

	/**
	 * The constructor loads the map according to the stage name and layer.
	 * 
	 * @param controller
	 *            The controller of the MVC scheme.
	 * @param stageName
	 *            Defines which attributes the stage will take.
	 * @param mapLayer
	 *            The map layer to interpret.
	 * @param character
	 *            The player's character for this stage.
	 * 
	 */
	public RpgPlatform(RpgController controller, String stageName,
			String mapLayer, Character character) {
		controller.setModel(this);
		this.controller = controller;
		this.map = new RpgMap(
				new TmxMapLoader(new InternalFileHandleResolver())
						.load("data/rpg_maps/map.tmx"));

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
	public void moveCharacter(Path path) {

		// Stop any ongoing path to start another one.
		this.getCharacter().clearMovementBuffer();

		// Since these directions are being buffered, the character is not
		// updated immediately, so we deal with an imaginary character in x-y
		// coordinates updated in real time.
		int currentX = character.getTileX();
		int currentY = character.getTileY();

		for (Step step : path.getSteps()) {
			Character.WalkDirection direction = null;

			if (step.getX() == currentX && step.getY() < currentY) {
				direction = Character.WalkDirection.WALK_UP;
				currentY--;
			}
			if (step.getX() == currentX && step.getY() > currentY) {
				direction = Character.WalkDirection.WALK_DOWN;
				currentY++;
			}
			if (step.getY() == currentY && step.getX() < currentX) {
				direction = Character.WalkDirection.WALK_LEFT;
				currentX--;
			}
			if (step.getY() == currentY && step.getX() > currentX) {
				direction = Character.WalkDirection.WALK_RIGHT;
				currentX++;
			}

			this.moveCharacter(direction, true);
		}

	}

	/**
	 * Moves the character in one tile in the specified direction and notifies
	 * the RpgController.
	 */
	public boolean moveCharacter(WalkDirection direction) {
		return this.moveCharacter(direction, false);
	}

	public boolean moveCharacter(WalkDirection direction, boolean inPath) {
		if (direction != null) {
			if (character.walk(direction, map, inPath)) {
				controller.characterMoved(direction);
				return true;
			}
		}
		return false;
	}

}
