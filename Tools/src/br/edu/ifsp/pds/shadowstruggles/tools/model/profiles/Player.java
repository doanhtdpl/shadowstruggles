package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles;

import br.edu.ifsp.pds.shadowstruggles.tools.model.Deck;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

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
		// TODO Auto-generated constructor stub
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
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		
	}

}
