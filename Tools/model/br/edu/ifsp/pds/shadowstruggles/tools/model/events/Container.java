package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

public class Container extends Event {
	public ArrayList<Item> items;
	public int money;

	public Container() {
		super();

		this.items = new ArrayList<Item>();
		this.money = 0;
	}

	public Container(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite,
			TriggerType triggerType, ArrayList<Item> items, int money) {
		super(id, x, money, map, layer, quest, triggered, sprite, triggerType);

		this.items = items;
		this.money = money;
	}

}
