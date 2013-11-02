package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Scene;

public class SceneAction extends EventAction {
	private Scene scene;
	
	public SceneAction() {
		this.scene = new Scene();
	}
	
	public SceneAction(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void act() {
		this.scene.runNextItem();
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.scene = json.readValue("scene", Scene.class, jsonData);
	}
	
	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("scene", this.scene);
	}



}
