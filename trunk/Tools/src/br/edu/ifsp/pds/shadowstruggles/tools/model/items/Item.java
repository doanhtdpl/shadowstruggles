package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Item implements Serializable {
	public int id;
	public String name;
	public String description;
	public int buyCost;
	public int sellCost;
	public boolean sellable;
	public String icon;
	public boolean availableInMainShop;
	public boolean consumable;
	
	public Item() {
		this.id = 1;
		this.name = "";
		this.description = "";
		this.buyCost = 0;
		this.sellCost = 0;
		this.sellable = false;
		this.icon = "";
		this.availableInMainShop = false;
		this.consumable = false;
	}
	
	public Item(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.buyCost = buyCost;
		this.sellCost = sellCost;
		this.sellable = sellable;
		this.icon = icon;
		this.availableInMainShop = availableInMainShop;
		this.consumable = consumable;
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
