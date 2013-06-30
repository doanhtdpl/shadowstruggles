package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path.Step;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.TileBasedMap;
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

	public TileBasedMap getRpgMap() {
		return this.map;
	}

	public void moveCharacter(Path path) {
		for (Step step : path.getSteps()) {
			Character.WalkDirection direction = null;

			if (step.getX() == character.getTileX()
					&& step.getY() < character.getTileY())
				direction = Character.WalkDirection.WALK_UP;
			if (step.getX() == character.getTileX()
					&& step.getY() > character.getTileY())
				direction = Character.WalkDirection.WALK_DOWN;
			if (step.getY() == character.getTileY()
					&& step.getX() < character.getTileY())
				direction = Character.WalkDirection.WALK_LEFT;
			if (step.getY() == character.getTileY()
					&& step.getX() > character.getTileY())
				direction = Character.WalkDirection.WALK_RIGHT;

			character.walk(direction, map.getMap());
		}
	}

}
