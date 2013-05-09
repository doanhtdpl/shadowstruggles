package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Basically, an serializable array of cards, with some extra functions, an image, a name.
 */

public class Deck implements Serializable{
	private int minCapacity;
	private int maxCapacity;
	private Array<Card> cards;
	private Image deckImage;
	private String name;
	
	
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

	public Deck(int minCapacity, int maxCapacity, Array<Card> cards, Image deckImage) {
		this.minCapacity = minCapacity;
		this.maxCapacity = maxCapacity;
		this.cards = cards;
		this.deckImage = deckImage;
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


	public Image getDeckImage() {
		return deckImage;
	}


	public void setDeckImage(Image deckImage) {
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
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.maxCapacity = json.readValue("maxCapacity", Integer.class, jsonData);
		this.minCapacity = json.readValue("minCapacity", Integer.class, jsonData);
		this.cards = json.readValue("cards", Array.class, jsonData);
	}
	

	
	
}


