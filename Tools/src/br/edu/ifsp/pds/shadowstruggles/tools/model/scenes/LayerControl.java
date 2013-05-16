package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class LayerControl extends SceneItem {
	public String nextLayer;
	public String map;
	
	public LayerControl() {
		this.nextLayer = "";
		this.map = "";
	}
	
	public LayerControl(String nextLayer, String map) {
		this.nextLayer = nextLayer;
		this.map = map;
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
