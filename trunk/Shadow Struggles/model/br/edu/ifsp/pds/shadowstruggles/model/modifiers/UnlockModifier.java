package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.model.items.Item;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class UnlockModifier extends Modifier {

	public Array<Item> items;

	public UnlockModifier() {
		this.items = new Array<Item>();
	}

	public UnlockModifier(Array<Item> items) {
		this.items = items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.items = json.readValue("items", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("items", this.items);
	}

	@Override
	public void modify() {
		// TODO: Implementar método.
	}
}
