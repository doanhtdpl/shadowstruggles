package br.edu.ifsp.pds.shadowstruggles.model.items;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.InventoryModifier;
import br.edu.ifsp.pds.shadowstruggles.model.modifiers.InventoryModifier.ItemOperation;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Item implements Serializable {
	protected int id;
	protected String name;
	protected String localizedName;
	protected String description;
	protected int buyCost;
	protected int sellCost;
	protected boolean sellable;
	protected String icon;
	protected boolean consumable;

	public Item() {
		this.id = 1;
		this.name = "";
		this.description = "";
		this.buyCost = 0;
		this.sellCost = 0;
		this.sellable = false;
		this.icon = "";
		this.consumable = false;
	}

	/**
	 * Triggers the item's effect. By default, nothing happens; subsequent
	 * classes should overwrite this method.
	 */
	public void useItem() {
	}

	/**
	 * Removes the item from the player's inventory. Subsequent methods should
	 * use this method after the use of consumable items.
	 */
	protected void consumeItem() {
		Array<Item> items = new Array<Item>();
		items.add(this);
		InventoryModifier invModifier = new InventoryModifier(items,
				ItemOperation.REMOVE);
		invModifier.modify();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item itemObj = (Item) obj;
			return itemObj.getId() == this.id;
		}

		return false;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.localizedName = json.readValue("localizedName", String.class,
				jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.buyCost = json.readValue("buyCost", Integer.class, jsonData);
		this.sellCost = json.readValue("sellCost", Integer.class, jsonData);
		this.sellable = json.readValue("sellable", Boolean.class, jsonData);
		this.icon = json.readValue("icon", String.class, jsonData);
		this.consumable = json.readValue("consumable", Boolean.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
		json.writeValue("localizedName", this.localizedName);
		json.writeValue("description", this.description);
		json.writeValue("buyCost", this.buyCost);
		json.writeValue("sellCost", this.sellCost);
		json.writeValue("sellable", this.sellable);
		json.writeValue("icon", this.icon);
		json.writeValue("consumable", this.consumable);
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public String getDescription() {
		return description;
	}

	public int getBuyCost() {
		return buyCost;
	}
}
