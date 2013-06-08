package br.edu.ifsp.pds.shadowstruggles.rpg;

import com.badlogic.gdx.maps.tiled.TiledMap;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
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

	}

	/**
	 * Method called only from the Model. It sends a command to the Viewer to
	 * update the character sprite as the model's character moves.
	 */
	public void characterMoved() {

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
