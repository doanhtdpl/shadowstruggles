package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

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

	@Override
	public void modify(Profile profile) {
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
