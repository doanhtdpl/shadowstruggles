package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

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
	public void read(Json json, JsonValue jsonData) {
		this.nextLayer = json.readValue("nextLayer", String.class, jsonData);
		this.map = json.readValue("map", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("nextLayer", this.nextLayer);
		json.writeValue("map", this.map);
	}
}
