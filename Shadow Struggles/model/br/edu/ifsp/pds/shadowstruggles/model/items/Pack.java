package br.edu.ifsp.pds.shadowstruggles.model.items;

import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.modifiers.InventoryModifier;
import br.edu.ifsp.pds.shadowstruggles.model.modifiers.InventoryModifier.ItemOperation;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * A Pack is a set of cards which are randomly selected when the pack is
 * purchased.
 */
public class Pack extends Item {
	public Array<Card> cards;
	/**
	 * The amount of cards which will compose the subset of randomly selected
	 * cards.
	 */
	int quantity;

	public Pack() {
		super();

		this.cards = new Array<Card>();
		this.quantity = 0;
		this.consumable = true;
	}

	@Override
	public void useItem() {
		Array<Card> randomCards = getRandomCards();
		Array<Item> items = new Array<Item>();
		items.addAll(randomCards);
		InventoryModifier invModifier = new InventoryModifier(items,
				ItemOperation.ADD);
		invModifier.modify();

		consumeItem();
	}

	private Array<Card> getRandomCards() {
		Array<Card> randomCards = new Array<Card>();
		randomCards.addAll(cards);
		randomCards.shuffle();
		randomCards.truncate(quantity);
		
		return randomCards;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.cards = json.readValue("cards", Array.class, jsonData);
		this.quantity = json.readValue("quantity", Integer.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("cards", this.cards);
		json.writeValue("quantity", this.quantity);
	}
}
