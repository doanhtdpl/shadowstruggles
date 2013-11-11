package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class Deck extends Item {
	public int minCapacity;
	public int maxCapacity;
	public int totalCardPoints;	
	public ArrayList<Card> cards;	
	//TODO: consertar leitura e gravação de CardAction

	public Deck() {
		super();
		this.cards = new ArrayList<Card>();		
		this.totalCardPoints = 0;
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {		
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>(Arrays.asList(new String[] {"totalCardPoints"})));
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
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>(Arrays.asList(new String[] {})));
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
		
		
	}
	
	
}
