package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Player implements Serializable {
	public Deck deck;
	public int maxHealth;
	public int maxEnergy;
	public int initialEnergy;
	public int maxCardPoints;
	public int deckCapacity;
	public int energyRecovery;
	public float doubleDraw;

	public Player() {
		this.deck = new Deck();
		this.maxHealth = 0;
		this.maxEnergy = 0;
		this.initialEnergy = 0;
		this.maxCardPoints = 0;
		this.deckCapacity = 0;
		this.energyRecovery = 0;
		this.doubleDraw = 0;
	}

	@Override
	public void write(Json json) {
		json.writeValue("deck", this.deck);
		json.writeValue("maxHealth", this.maxHealth);
		json.writeValue("maxEnergy", this.maxEnergy);
		json.writeValue("initialEnergy", this.initialEnergy);
		json.writeValue("maxCardPoints", this.maxCardPoints);
		json.writeValue("deckCapacity", this.deckCapacity);
		json.writeValue("energyRecovery", this.energyRecovery);
		json.writeValue("doubleDraw", this.doubleDraw);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.deck = json.readValue("deck", Deck.class, jsonData);
		this.maxHealth = json.readValue("maxHealth", Integer.class, jsonData);
		this.maxEnergy = json.readValue("maxEnergy", Integer.class, jsonData);
		this.initialEnergy = json.readValue("initialEnergy", Integer.class,
				jsonData);
		this.maxCardPoints = json.readValue("maxCardPoints", Integer.class,
				jsonData);
		this.deckCapacity = json.readValue("deckCapacity", Integer.class,
				jsonData);
		this.energyRecovery = json.readValue("energyRecovery", Integer.class,
				jsonData);
		this.doubleDraw = json.readValue("doubleDraw", Float.class, jsonData);
	}

}
