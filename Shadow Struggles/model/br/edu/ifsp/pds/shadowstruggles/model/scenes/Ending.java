package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Ending implements Serializable {
	public int id;
	public String name;
	public String path;

	public Ending() {
		this.id = 1;
		this.name = "";
		this.path = "";
	}

	public Ending(int id, String name, String path) {
		this.id = id;
		this.name = name;
		this.path = path;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ending)
			return ((Ending) obj).getId() == this.id;
		return false;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.path = json.readValue("path", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
		json.writeValue("path", this.path);
	}

	public int getId() {
		return this.id;
	}
}
