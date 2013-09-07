package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;

public class ShopAction extends EventAction {
	public Shop shop;

	public ShopAction() {
		this.shop = new Shop();
	}

	public ShopAction(Shop shop) {
		this.shop = shop;
	}
}
