package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Container extends Event {
	public ArrayList<Item> items;
	public int money;
	
	public Container() {
		super();
		
		this.items = new ArrayList<Item>();
		this.money = 0;
	}
	
	public Container(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, ArrayList<Item> itens, int money) {
		super(id, x, money, map, layer, quest, triggered, sprite);
		
		this.items = itens;
		this.money = money;
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
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
