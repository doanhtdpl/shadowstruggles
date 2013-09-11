package br.edu.ifsp.pds.shadowstruggles.model.quests;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public abstract class Requirement implements Serializable {
	private String name;
	private String description;
	private boolean fulfilled;

	public Requirement() {
		this.name = "";
		this.description = "";
		this.fulfilled = false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Requirement)
			return ((Requirement) obj).getName().equals(this.name);
		return false;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.fulfilled = json.readValue("fulfilled", Boolean.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("name", this.name);
		json.writeValue("description", this.description);
		json.writeValue("fulfilled", this.fulfilled);
	}

	public String getName() {
		return this.name;
	}
	
	public boolean isFulfilled() {
		return this.fulfilled;
	}
	
	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}
}
