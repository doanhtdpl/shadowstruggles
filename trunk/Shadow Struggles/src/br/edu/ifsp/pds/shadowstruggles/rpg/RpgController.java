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
	 * designated path) from input to the Model and triggers an interactive
	 * event, if possible.
	 * 
	 * @param destinationPos
	 *            The destiny, in tiles (destinationPos[0] = x,
	 *            destinationPos[1] = y).
	 */
	public void moveCharacter(Path path, int[] destinationPos) {
		model.moveCharacter(path, destinationPos);
	}

	/**
	 * Method called by the Viewer to update the model (e.g., try clearing the
	 * character's movement buffer). It also checks for changes in direction.
	 */
	public void updateModel() {
		if (viewer.getCharacter2d() != null) {
			if (model.getCharacter().getDirection() != viewer.getCharacter2d()
					.getDirection()
					&& model.getCharacter().getDirection() != null
					&& !viewer.getCharacter2d().isWalking()) {
				viewer.turnCharacter2d(model.getCharacter().getDirection());
			}

			WalkDirection direction = model.getCharacter().update();
			if (direction != null)
				viewer.moveCharacter2d(direction);
		}
	}

	/**
	 * Method called by the Viewer to execute the automatic events of the map in
	 * the current object layer.
	 */
	public void runAutomaticEvents() {
		model.getRpgMap().runAutomaticEvents();
	}

	/**
	 * Method called by the Viewer to send the trigger command to the model.
	 */
	public void triggerEvent() {
		model.triggerEvent();
	}

	/**
	 * Method called only from the Model. It sends a command to the Viewer to
	 * update the character sprite as the model's character moves.
	 */
	public void characterMoved(WalkDirection direction) {
		viewer.moveCharacter2d(direction);
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
