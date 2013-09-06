package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class InventoryModifier extends Modifier {
	public static enum ItemOperation { ADD, REPLACE, REMOVE };
	
	public ArrayList<Item> items;
	public ItemOperation operation;

	public InventoryModifier() {
		this.items = new ArrayList<Item>();
		this.operation = ItemOperation.ADD;
	}

	public InventoryModifier(ArrayList<Item> items, ItemOperation operation) {
		this.items = items;
		this.operation = operation;
	}
}
