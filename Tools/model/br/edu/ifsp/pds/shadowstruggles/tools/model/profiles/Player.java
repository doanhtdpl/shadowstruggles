package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;

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
	public void read(Json arg0, JsonValue arg1) {
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(Json arg0) {
		try {
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

}
