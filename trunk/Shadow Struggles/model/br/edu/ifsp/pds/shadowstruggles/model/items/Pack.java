package br.edu.ifsp.pds.shadowstruggles.model.items;

import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Pack extends Item {
	public Array<Card> cards;
	
	public Pack() {
		super();
		
		this.cards = new Array<Card>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.cards = json.readValue("cards", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("cards", this.cards);
	}
}
