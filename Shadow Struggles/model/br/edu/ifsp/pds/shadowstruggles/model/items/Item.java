package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Item implements Serializable {
	public int id;
	public String name;
	public String nameVisualization;
	public String description;
	public int buyCost;
	public int sellCost;
	public boolean sellable;
	public String icon;
	public boolean availableInMainShop;
	public boolean consumable;

	public Item() {
		this.id = 1;
		this.name = "";
		this.description = "";
		this.buyCost = 0;
		this.sellCost = 0;
		this.sellable = false;
		this.icon = "";
		this.availableInMainShop = false;
		this.consumable = false;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.nameVisualization = json.readValue("nameVisualization",
				String.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.buyCost = json.readValue("buyCost", Integer.class, jsonData);
		this.sellCost = json.readValue("sellCost", Integer.class, jsonData);
		this.sellable = json.readValue("sellable", Boolean.class, jsonData);
		this.icon = json.readValue("icon", String.class, jsonData);
		this.availableInMainShop = json.readValue("availableInMainShop",
				Boolean.class, jsonData);
		this.consumable = json.readValue("consumable", Boolean.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
		json.writeValue("nameVisualization", this.nameVisualization);
		json.writeValue("description", this.description);
		json.writeValue("buyCost", this.buyCost);
		json.writeValue("sellCost", this.sellCost);
		json.writeValue("sellable", this.sellable);
		json.writeValue("icon", this.icon);
		json.writeValue("availableInMainShop", this.availableInMainShop);
		json.writeValue("consumable", this.consumable);
	}

}
