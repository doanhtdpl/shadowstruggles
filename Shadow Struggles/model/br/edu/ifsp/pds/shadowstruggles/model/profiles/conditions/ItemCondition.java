package br.edu.ifsp.pds.shadowstruggles.model.profiles.conditions;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Checks if the player's inventory contains the specified items.
 */
public class ItemCondition extends ProfileCondition {
	private Array<Item> items;

	public ItemCondition() {
		this.items = new Array<Item>();
	}

	@Override
	public boolean evaluate() {
		Profile profile = ShadowStruggles.getInstance().getProfile();
		for (Item item : items) {
			if (!profile.getInventory().contains(item, false))
				return false;
		}

		return true;
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
}
