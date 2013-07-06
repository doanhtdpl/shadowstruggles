package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;

public class ShopEvent extends Event {
	public Shop shop;

	public ShopEvent() {
		super();

		this.shop = new Shop();
	}

	public ShopEvent(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite,
			TriggerType triggerType, Shop shop) {
		super(id, x, y, map, layer, quest, triggered, sprite, triggerType);
		this.shop = shop;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.shop = json.readValue("shop", Shop.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("shop", this.shop);
	}

	@Override
	public void trigger() {
		// TODO: Implementar método.
	}
}
