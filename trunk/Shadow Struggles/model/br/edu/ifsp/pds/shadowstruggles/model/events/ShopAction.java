package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.ShopScreen;

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
		ShadowStruggles game = ShadowStruggles.getInstance();
		game.setScreenWithTransition(new ShopScreen(game, shop, game
				.getController(), (BaseScreen) game.getScreen()));
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
