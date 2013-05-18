package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

public class SceneControl extends SceneItem {
	public Scene nextScene;
	
	public SceneControl() {
		this.nextScene = new Scene();
	}
	
	public SceneControl(Scene nextScene) {
		this.nextScene = nextScene;
	}

}
