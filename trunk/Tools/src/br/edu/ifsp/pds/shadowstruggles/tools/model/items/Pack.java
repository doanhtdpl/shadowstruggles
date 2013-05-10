package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Pack extends Item {
	public ArrayList<Card> cards;
	
	public Pack() {
		// TODO Auto-generated constructor stub
	}
	
	public Pack(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, ArrayList<Card> cards) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		
		this.cards = cards;
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		super.read(arg0, arg1);
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		super.write(arg0);
	}
	
}
