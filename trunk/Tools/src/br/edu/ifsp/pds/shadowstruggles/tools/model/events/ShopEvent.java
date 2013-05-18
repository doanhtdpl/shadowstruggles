package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;

public class ShopEvent extends Event {
	public Shop shop;
	
	public ShopEvent() {
		super();
		
		this.shop = new Shop();
	}
	
	public ShopEvent(Shop shop) {
		this.shop = shop;
	}
}
