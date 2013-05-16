package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Deck implements Serializable{
	public int id;
	public ArrayList<Card> cards;
	public String deckImage;
	public String name;
	
	public Deck() {
		this.id = 1;
		this.cards = new ArrayList<Card>();
		this.deckImage = "";
		this.name = "";
	}
	
	public Deck(int id, ArrayList<Card> cards, String deckImage, String name) {
		this.id = id;
		this.cards = cards;
		this.deckImage = deckImage;
		this.name = name;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
	}	
}