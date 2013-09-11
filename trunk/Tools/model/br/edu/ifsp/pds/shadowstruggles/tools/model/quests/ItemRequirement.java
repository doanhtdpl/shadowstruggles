package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class ItemRequirement extends Requirement {
	public ArrayList<Item> items;

	public ItemRequirement() {
		super();

		this.items = new ArrayList<Item>();
	}

}
