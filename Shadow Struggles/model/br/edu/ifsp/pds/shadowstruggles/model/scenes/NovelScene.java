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
	
	public NovelScene(int id, Ending ending, String name, String description, String text, String background) {
		super(id, ending, name, description);
		
		this.text = text;
		this.background = background;
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
