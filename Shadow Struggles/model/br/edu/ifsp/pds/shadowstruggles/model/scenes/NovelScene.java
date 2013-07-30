package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class NovelScene extends Scene {
	public String text;
	public String background;
	
	public NovelScene() {
		super();
		
		this.text = "";
		this.background = "";
	}
	
	@Override
	public void runScene() {
		super.runScene();
		
		// TODO: Controle visual específico para cenas de visual novel.
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.text = json.readValue("text", String.class, jsonData);
		this.background = json
				.readValue("background", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("text", this.text);
		json.writeValue("background", this.background);
	}
}
