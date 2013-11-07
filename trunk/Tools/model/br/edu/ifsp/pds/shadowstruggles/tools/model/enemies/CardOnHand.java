package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;


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
