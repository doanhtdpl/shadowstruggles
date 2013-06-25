package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.model.items.Item;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class InventoryModifier extends Modifier {
	public static enum ItemOperation { ADD, REPLACE, REMOVE };
	
	public Array<Item> items;
	public ItemOperation operation;

	public InventoryModifier() {
		this.items = new Array<Item>();
		this.operation = ItemOperation.ADD;
	}

	public InventoryModifier(Array<Item> items, ItemOperation operation) {
		this.items = items;
		this.operation = operation;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.items = json.readValue("items", Array.class, jsonData);
		this.operation = json.readValue("operation", ItemOperation.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("items", this.items);
		json.writeValue("operation", this.operation);
	}
}
