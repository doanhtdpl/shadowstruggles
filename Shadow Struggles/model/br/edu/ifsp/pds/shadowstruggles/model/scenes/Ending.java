package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.model.events.SceneEvent;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Ending implements Serializable {
	public int id;
	public String name;
	public String path;
	public Array<SceneEvent> returnScenes;

	public Ending() {
		this.id = 1;
		this.name = "";
		this.path = "";
		this.returnScenes = new Array<SceneEvent>();
	}

	public Ending(int id, String name, String path,
			Array<SceneEvent> returnScenes) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.returnScenes = returnScenes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.path = json.readValue("path", String.class, jsonData);
		this.returnScenes = json.readValue("returnScenes", Array.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
		json.writeValue("path", this.path);
		json.writeValue("returnScenes", this.returnScenes);
	}
}
