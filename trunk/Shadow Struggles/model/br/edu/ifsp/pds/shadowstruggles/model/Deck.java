package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Basically, an serializable array of cards, with some extra functions, an image, a name.
 */

public class Deck implements Serializable{
	private int minCapacity;
	private int maxCapacity;
	private Array<Card> cards;
	private String deckImage;
	private String name;
	private int totalCardPoints;
	
	
	public Deck() {
		this.cards = new Array<Card>();
		shuffle();
	}
	
	public Card draw(){
		return cards.pop();
	}
	
	public void shuffle(){
		cards.shuffle();
	}
	
	public void addCard(Card card){
		cards.add(card);
		
	}

	public Deck(int minCapacity, int maxCapacity, Array<Card> cards, String deckImage) {
		this.minCapacity = minCapacity;
		this.maxCapacity = maxCapacity;
		this.cards = cards;
		this.deckImage = deckImage;
		//TODO: carregar imagem de acordo com a String recebida
		shuffle();
	}

	public int getMinCapacity() {
		return minCapacity;
	}


	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}


	public int getMaxCapacity() {
		return maxCapacity;
	}


	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}


	public Array<Card> getCards() {
		return cards;
	}


	public void setCards(Array<Card> cards) {
		this.cards = cards;
	}


	public String getDeckImage() {
		return deckImage;
	}


	public void setDeckImage(String deckImage) {
		this.deckImage = deckImage;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void write(Json json) {
		json.writeValue("name", this.name);
		json.writeValue("minCapacity", this.minCapacity);
		json.writeValue("maxCapacity", this.maxCapacity);
		json.writeValue("cards", this.cards);
		
				
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.maxCapacity = json.readValue("maxCapacity", Integer.class, jsonData);
		this.minCapacity = json.readValue("minCapacity", Integer.class, jsonData);
		this.cards = json.readValue("cards", Array.class, jsonData);
	}
	

	
	
}


