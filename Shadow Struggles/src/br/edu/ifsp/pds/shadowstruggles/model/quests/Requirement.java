package br.edu.ifsp.pds.shadowstruggles.model.quests;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public abstract class Requirement implements Serializable {
	public String name;
	public String description;

	public Requirement() {
		this.name = "";
		this.description = "";
	}

	public Requirement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("name", this.name);
		json.writeValue("description", this.description);
	}

}
