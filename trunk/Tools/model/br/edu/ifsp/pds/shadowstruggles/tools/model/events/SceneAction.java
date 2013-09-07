package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;

public class SceneAction extends EventAction {
	public Scene scene;
	
	public SceneAction() {
		this.scene = new Scene();
	}
	
	public SceneAction(Scene scene) {
		this.scene = scene;
	}
}
