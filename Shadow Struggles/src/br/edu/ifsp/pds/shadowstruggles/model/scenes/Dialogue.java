package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Dialogue extends SceneItem {
	public String text;
	public String characterName;
	public String characterImage;

	public Dialogue() {
		this.text = "";
		this.characterName = "";
		this.characterImage = "";
	}

	public Dialogue(String text, String characterName, String characterImage) {
		this.text = text;
		this.characterName = characterName;
		this.characterImage = characterImage;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.text = json.readValue("text", String.class, jsonData);
		this.characterName = json.readValue("characterName", String.class,
				jsonData);
		this.characterImage = json.readValue("characterImage", String.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("text", this.text);
		json.writeValue("characterName", this.characterName);
		json.writeValue("characterImage", this.characterImage);
	}
}
