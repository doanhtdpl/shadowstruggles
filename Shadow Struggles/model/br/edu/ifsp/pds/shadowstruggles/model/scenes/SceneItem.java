package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json.Serializable;

public abstract class SceneItem implements Serializable {
	protected Scene parentScene;

	public abstract void action();

	public Scene getParentScene() {
		return this.parentScene;
	}

	public void setParentScene(Scene scene) {
		this.parentScene = scene;
	}
}
