package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class UnlockModifier extends Modifier {

	public ArrayList<Item> items;
	
	public UnlockModifier() {
		this.items = new ArrayList<Item>();
	}
	
	public UnlockModifier(ArrayList<Item> items) {
		this.items = items;
	}
}
