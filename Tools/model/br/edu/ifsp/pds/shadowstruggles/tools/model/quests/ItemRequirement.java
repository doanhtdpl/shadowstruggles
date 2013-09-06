package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class ItemRequirement extends Requirement {
	public ArrayList<Item> items;

	public ItemRequirement() {
		super();

		this.items = new ArrayList<Item>();
	}

	public ItemRequirement(String name, String description,
			ArrayList<Item> items) {
		super(name, description);

		this.items = items;
	}

}
