package br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;

/**
 * Checks for a card on the enemy's hand.
 */
public class CardOnHand extends Condition {
	private Card card;
	
	public CardOnHand() {
		
	}

	public CardOnHand(Card card) {
		this.card = card;
	}
	
	@Override
	public boolean evaluate() {
		BattlePlatform platform = ShadowStruggles.getInstance().getController()
				.getPlatform();
		return platform.cardOnEnemyHand(card.getName());
	}

	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("card", this.card);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.card = json.readValue("card", Card.class, jsonData);
	}

}
