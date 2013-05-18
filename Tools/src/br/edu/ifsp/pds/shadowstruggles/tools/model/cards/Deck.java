package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class Deck extends Item {
	public ArrayList<Card> cards;
	public String deckImage;
	public int totalCardPoints;

	public Deck() {
		super();
		this.cards = new ArrayList<Card>();
		this.deckImage = "";
		this.totalCardPoints = 0;
	}

	public Deck(int id, String name, ArrayList<Card> cards, String deckImage,
			int totalCardPoints) {
		super(id, name, "", 0, 0, false, "", false, false);
		this.cards = cards;
		this.deckImage = deckImage;
		this.totalCardPoints = totalCardPoints;
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		super.read(arg0, arg1);
		
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
		super.write(arg0);
		
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
