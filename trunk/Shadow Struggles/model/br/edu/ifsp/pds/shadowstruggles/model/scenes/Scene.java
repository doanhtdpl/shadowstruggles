package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Scene implements Serializable {
	public int id;
	public Ending ending;
	public String name;
	public String description;

	public Scene() {
		this.id = 1;
		this.ending = new Ending();
		this.name = "";
		this.description = "";
	}

	public Scene(int id, Ending ending, String name, String description) {
		this.id = id;
		this.ending = ending;
		this.name = name;
		this.description = description;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.ending = json.readValue("ending", Ending.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("ending", this.ending);
		json.writeValue("name", this.name);
		json.writeValue("description", this.description);
	}
}
