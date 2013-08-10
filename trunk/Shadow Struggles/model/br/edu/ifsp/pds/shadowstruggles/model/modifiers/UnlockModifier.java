package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Makes cards available in a shop, including the one on the main screen.
 */
public class UnlockModifier extends Modifier {
	private Array<Item> items;
	private Shop shop;
	private boolean inMainShop;

	public UnlockModifier() {
		this.items = new Array<Item>();
		this.shop = null;
	}

	public UnlockModifier(Array<Item> items, Shop shop, boolean inMainShop) {
		this.items = items;
		this.shop = shop;
		this.inMainShop = inMainShop;
	}

	@Override
	public void modify() {
		if (shop != null) {
			shop.getItems().addAll(items);
		}

		if (inMainShop) {
			for (Item item : items) {
				item.setAvailableInMainShop(true);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.items = json.readValue("items", Array.class, jsonData);
		this.shop = json.readValue("shop", Shop.class, jsonData);
		this.inMainShop = json.readValue("inMainShop", Boolean.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("items", this.items);
		json.writeValue("shop", this.shop);
		json.writeValue("inMainShop", this.inMainShop);
	}
}
