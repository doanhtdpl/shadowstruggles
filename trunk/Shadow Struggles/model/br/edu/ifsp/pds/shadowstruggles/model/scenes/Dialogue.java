package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.Messenger;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Dialogue extends SceneItem {
	public String text;
	public String characterName;

	public Dialogue() {
		this.text = "";
		this.characterName = "";
	}

	public Dialogue(String text, String characterName) {
		this.text = text;
		this.characterName = characterName;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.text = json.readValue("text", String.class, jsonData);
		this.characterName = json.readValue("characterName", String.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("text", this.text);
		json.writeValue("characterName", this.characterName);
	}

	@Override
	public void action() {
		String message = "";
		if (!characterName.equals(""))
			message = characterName + ": ";
		message += text;

		Messenger messenger = new Messenger(message,
				ShadowStruggles.getInstance(), parentScene);
		messenger.displayMessage();
	}
}
