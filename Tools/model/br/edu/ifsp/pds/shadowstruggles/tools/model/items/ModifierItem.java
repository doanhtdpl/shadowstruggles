package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;

public class ModifierItem extends Item {
	public Modifier modifier;
	
	public ModifierItem() {
		super();
		
		this.modifier = null;
	}
	
	public ModifierItem(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, Modifier modifier) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		
		this.modifier = modifier;
	}
}
