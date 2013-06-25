package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;

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
	
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.modifier = json.readValue("modifier", Modifier.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("modifier", this.modifier);
	}
}
