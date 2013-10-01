package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;

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

	@Override
	public String getMessage() {
		String message = "";

		if (operation == ItemOperation.ADD)
			message = MenuTextDAO.getMenuText().acquired + " ";
		else if (operation == ItemOperation.REMOVE)
			message = MenuTextDAO.getMenuText().removedItem + " ";

		ObjectMap<Item, Integer> itemsQuantity = itemsQuantity();
		// Array for sequential access of items.
		Array<Item> keys = itemsQuantity.keys().toArray();
		for (int i = 0; i < keys.size; i++) {
			Item item = keys.get(i);
			int quantity = itemsQuantity.get(item);
			message += item.getLocalizedName();

			if (quantity > 1)
				message += " x" + quantity;

			if (i < keys.size - 2) // More than one item left.
				message += ", ";
			else if (i == keys.size - 2) // One item left.
				message += " and ";
			else if (i == keys.size - 1) // This is the final item.
				message += "!";
		}

		return message;
	}

	/**
	 * Returns a map relating items to the number of times they appear on the
	 * items array.
	 */
	private ObjectMap<Item, Integer> itemsQuantity() {
		ObjectMap<Item, Integer> itemsQuantity = new ObjectMap<Item, Integer>();

		for (Item item : items) {
			if (!itemsQuantity.containsKey(item))
				itemsQuantity.put(item, 1);
			else
				itemsQuantity.put(item, itemsQuantity.get(item) + 1);
		}

		return itemsQuantity;
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
