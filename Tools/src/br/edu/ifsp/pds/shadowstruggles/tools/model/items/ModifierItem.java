package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import br.edu.ifsp.pds.shadowstruggles.tools.model.Modifier;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class ModifierItem extends Item {
	public Modifier modifier;
	
	public ModifierItem() {
		// TODO Auto-generated constructor stub
	}
	
	public ModifierItem(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, Modifier modifier) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		
		this.modifier = modifier;
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		super.read(arg0, arg1);
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		super.write(arg0);
	}

}
