package br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;

/**
 * Checks for the presence of a card on the map.
 */
public class CardOnMap extends Condition {
	private Card card;
	/**
	 * The searched lane. All lanes are searched if lane = -1.
	 */
	private int lane;
	/**
	 * The player field to search, either the player's (1) or the enemy's (-1).
	 */
	private int player;

	@Override
	public boolean evaluate() {
		BattleMap map = ShadowStruggles.getInstance().getController()
				.getPlatform().getMap();
		return map.cardOnMap(card, lane, player);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("card", this.card);
		json.writeValue("lane", this.lane);
		json.writeValue("player", this.player);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.card = json.readValue("card", Card.class, jsonData);
		this.lane = json.readValue("lane", Integer.class, jsonData);
		this.player = json.readValue("player", Integer.class, jsonData);
	}

}
