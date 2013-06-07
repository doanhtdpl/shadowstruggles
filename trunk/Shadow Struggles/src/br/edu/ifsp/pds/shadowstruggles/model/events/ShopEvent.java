package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;

public class ShopEvent extends Event {
	public Shop shop;
	
	public ShopEvent() {
		super();
		
		this.shop = new Shop();
	}
	
	public ShopEvent(Shop shop) {
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
}
