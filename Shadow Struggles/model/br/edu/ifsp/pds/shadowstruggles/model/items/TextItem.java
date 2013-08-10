package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class TextItem extends Item {
	public String text;
	
	public TextItem() {
		super();
		
		this.text = "";
		this.consumable = false;
	}
	
	@Override
	public void useItem() {
		// TODO: Implementar método.
		super.useItem();
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.text = json.readValue("text", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("text", this.text);
	}
}
