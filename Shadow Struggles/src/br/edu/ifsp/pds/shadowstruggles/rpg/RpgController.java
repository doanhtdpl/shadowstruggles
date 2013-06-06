package br.edu.ifsp.pds.shadowstruggles.rpg;


import com.badlogic.gdx.maps.tiled.TiledMap;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;

/**
 * Estabilishes the connection between the model (RpgPlatform) and the viewer (RpgScreen)
 * of the RPG World.
 * It gets the user inout from the Viewer and send to the Model.
 * Then, returns the processed data from Model to Viewer.
 */
public class RpgController {
	
	private RpgScreen viewer;
	private RpgPlatform model;
	
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
	
	public TiledMap getMap(){
		return model.getMap();
	}
	
	public void moveCharacter(){	
		
		
	}
	public void render(){
		
	}
	
	
}
