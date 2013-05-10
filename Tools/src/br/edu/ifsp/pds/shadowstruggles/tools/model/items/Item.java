package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

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
		// TODO Auto-generated constructor stub
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
	
	public void activate() { }

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		
	}
}
