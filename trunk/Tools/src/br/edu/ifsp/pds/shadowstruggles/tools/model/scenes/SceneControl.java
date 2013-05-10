package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SceneControl extends SceneItem {
	public Scene nextScene;
	
	public SceneControl() {
		// TODO Auto-generated constructor stub
	}
	
	public SceneControl(Scene nextScene) {
		this.nextScene = nextScene;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
	}

}
