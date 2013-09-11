package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

/**
 * Checks if the player's inventory contains the specified items.
 */
public class ItemCondition extends ProfileCondition {
	public ArrayList<Item> items;

	public ItemCondition() {
		this.items = new ArrayList<Item>();
	}
}
