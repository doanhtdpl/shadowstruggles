package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class TextItem extends Item {
	public String text;
	
	public TextItem() {
		super();
		
		this.text = "";
	}
	
	public TextItem(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, String text) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		
		this.text = text;
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
