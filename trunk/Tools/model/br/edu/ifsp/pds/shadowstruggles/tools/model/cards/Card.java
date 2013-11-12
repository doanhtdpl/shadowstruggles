package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Card extends Item implements Serializable {
	public int energyCost;
	public CardAction action;	
	public ArrayList<String> preRequisites = new ArrayList<String>();

	public Card() {
		super();
		this.energyCost = 0;
		this.action = new DefaultAction();			
		this.preRequisites = new ArrayList<String>();
	}
	
	public Card(String name) {
		this.name = name;
	}
	
	@Override
	public void write(Json arg0) {
		ArrayList<String> skipFields = new ArrayList<String>();	
		skipFields.add("action");
		try {
			SerializationHelper.writeToJson(this, arg0, skipFields);
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
		arg0.writeValue("action", this.action);
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		ArrayList<String> skipFields = new ArrayList<String>();		
		skipFields.add("action");
		try {
			SerializationHelper.read(this, arg0, arg1, skipFields);
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
		this.action = arg0.readValue("action", CardAction.class, arg1);
		
	}
}
