package br.edu.ifsp.pds.shadowstruggles.model.profiles;

import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Player implements Serializable {
	private Deck deck;
	private int maxHealth;
	private int maxEnergy;
	private int initialEnergy;
	private int maxCardPoints;
	private int deckCapacity;
	private int energyRecovery;
	private float doubleDraw;

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

	public Player(Deck deck, int maxHealth, int maxEnergy, int initialEnergy,
			int maxCardPoints, int deckCapacity, int energyRecovery,
			float doubleDraw) {
		this.deck = deck;
		this.maxHealth = maxHealth;
		this.maxEnergy = maxEnergy;
		this.initialEnergy = initialEnergy;
		this.maxCardPoints = maxCardPoints;
		this.deckCapacity = deckCapacity;
		this.energyRecovery = energyRecovery;
		this.doubleDraw = doubleDraw;
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
		this.deckCapacity = json.readValue("deckCapacity", Integer.class, jsonData);
		this.energyRecovery = json.readValue("energyRecovery", Integer.class,
				jsonData);
		this.doubleDraw = json.readValue("doubleDraw", Float.class, jsonData);
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

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
}
