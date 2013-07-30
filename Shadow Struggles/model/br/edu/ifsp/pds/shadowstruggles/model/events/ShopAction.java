package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;

public class ShopAction extends EventAction {
	private Shop shop;
	
	public ShopAction() {
		this.shop = new Shop();
	}
	
	public ShopAction(Shop shop) {
		this.shop = shop;
	}

	@Override
	public void act() {
		this.shop.show();
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.shop = json.readValue("shop", Shop.class, jsonData);
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("shop", this.shop);
	}
}
