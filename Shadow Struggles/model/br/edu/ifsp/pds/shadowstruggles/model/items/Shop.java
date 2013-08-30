package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Shop implements Serializable {
	public int id;
	public Array<Item> items;

	public Shop() {
		this.id = 1;
		this.items = new Array<Item>();
	}

	public Shop(int id, boolean mainShop, Array<Item> items) {
		this.id = id;
		this.items = items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.items = json.readValue("items", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("items", this.items);
	}

	public Array<Item> getItems() {
		return this.items;
	}
}
