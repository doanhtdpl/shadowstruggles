package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

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
	public void trigger() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub

	}
}
