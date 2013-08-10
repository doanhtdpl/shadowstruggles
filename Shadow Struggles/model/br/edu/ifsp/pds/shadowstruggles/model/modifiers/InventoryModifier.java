package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Modifies the player's inventory.
 */
public class InventoryModifier extends Modifier {
	public static enum ItemOperation {
		ADD, REMOVE
	};

	private Array<Item> items;
	private ItemOperation operation;

	public InventoryModifier() {
		this.items = new Array<Item>();
		this.operation = null;
	}

	public InventoryModifier(Array<Item> items, ItemOperation operation) {
		this.items = items;
		this.operation = operation;
	}

	@Override
	public void modify() {
		if (operation != null) {
			Profile profile = ShadowStruggles.getInstance().getProfile();
			if (operation == ItemOperation.ADD) {
				profile.getInventory().addAll(items);
			}
			if (operation == ItemOperation.REMOVE) {
				profile.getInventory().removeAll(items, false);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.items = json.readValue("items", Array.class, jsonData);
		this.operation = json.readValue("operation", ItemOperation.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("items", this.items);
		json.writeValue("operation", this.operation);
	}
}
