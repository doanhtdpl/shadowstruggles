package br.edu.ifsp.pds.shadowstruggles.rpg;

import com.badlogic.gdx.maps.tiled.TiledMap;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;

/**
 * Establishes the connection between the model (RpgPlatform) and the viewer
 * (RpgScreen) of the RPG World. It gets the user input from the Viewer and send
 * to the Model. Then, returns the processed data from Model to Viewer.
 */
public class RpgController {

	private RpgScreen viewer;
	private RpgPlatform model;

	/**
	 * Method called only from the Viewer. Sends the move command from input to
	 * the Model.
	 */
	public void moveCharacter(WalkDirection direction) {
		model.moveCharacter(direction);
	}

	/**
	 * Method called only from the Viewer. Sends the move command (in a
	 * designated path) from input to the Model.
	 */
	public void moveCharacter(Path path) {
		model.moveCharacter(path);
	}

	/**
	 * Method called only from the Model. It sends a command to the Viewer to
	 * update the character sprite as the model's character moves.
	 */
	public void characterMoved(WalkDirection direction) {
		viewer.moveCharacter2D(direction);
	}

	/**
	 * Method called by Character2D to lock the model's movement.
	 */
	public void lockCharacterModel() {
		model.getCharacter().setReadyToWalk(false);
	}

	/**
	 * Method called by Character2D to unlock the model's movement.
	 */
	public void unlockCharacterModel() {
		model.getCharacter().setReadyToWalk(true);
	}

	/**
	 * Method called by the Viewer to update the model (e.g., try clearing the
	 * character's movement buffer).
	 */
	public void updateModel() {
		WalkDirection direction = model.getCharacter().update();
		if (direction != null)
			viewer.moveCharacter2D(direction);
	}

	public RpgScreen getViewer() {
		return viewer;
	}

	public void setViewer(RpgScreen viewer) {
		this.viewer = viewer;
	}

	public RpgPlatform getModel() {
		return model;
	}

	public void setModel(RpgPlatform model) {
		this.model = model;
	}

	public TiledMap getMap() {
		return model.getMap();
	}

}
