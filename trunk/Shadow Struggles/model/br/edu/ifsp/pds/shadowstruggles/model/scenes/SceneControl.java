package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SceneControl extends SceneItem {
	public Scene nextScene;

	public SceneControl() {
		this.nextScene = new Scene();
	}

	public SceneControl(Scene nextScene) {
		this.nextScene = nextScene;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.nextScene = json.readValue("nextScene", Scene.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("nextScene", this.nextScene);
	}

	@Override
	public void action() {
		nextScene.runNextItem();
	}
}
