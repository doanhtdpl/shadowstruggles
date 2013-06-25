package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;

public class Container extends Event {
	public Array<Item> items;
	public int money;
	
	public Container() {
		super();
		
		this.items = new Array<Item>();
		this.money = 0;
	}
	
	public Container(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, Array<Item> items, int money) {
		super(id, x, money, map, layer, quest, triggered, sprite);
		
		this.items = items;
		this.money = money;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.items = json.readValue("items", Array.class, jsonData);
		this.money = json.readValue("money", Integer.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("items", this.items);
		json.writeValue("money", this.money);
	}

}
