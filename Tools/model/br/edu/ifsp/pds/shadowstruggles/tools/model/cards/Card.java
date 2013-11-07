package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class Card extends Item implements Serializable {
	public int energyCost;
	public CardAction action;
	public BattlePlatform platform;	
	public ArrayList<String> preRequisites = new ArrayList<String>();

	public Card() {
		super();
		this.energyCost = 0;
		this.action = new DefaultAction();
		this.platform = new BattlePlatform();		
		this.preRequisites = new ArrayList<String>();
	}
	
	public Card(String name) {
		this.name = name;
	}
	
	@Override
	public void write(Json arg0) {
		ArrayList<String> skipFields = new ArrayList<String>();
		skipFields.add("platform");
		
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
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		ArrayList<String> skipFields = new ArrayList<String>();
		skipFields.add("platform");
		
		try {
			SerializationHelper.read(this, arg0, arg1, skipFields);
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
}
