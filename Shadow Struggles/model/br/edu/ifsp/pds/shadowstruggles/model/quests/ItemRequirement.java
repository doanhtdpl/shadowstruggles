package br.edu.ifsp.pds.shadowstruggles.model.quests;

import br.edu.ifsp.pds.shadowstruggles.model.items.Item;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ItemRequirement extends Requirement {
	private Array<Item> items;

	public ItemRequirement() {
		super();

		this.items = new Array<Item>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.items = json.readValue("items", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("items", this.items);
	}
}
