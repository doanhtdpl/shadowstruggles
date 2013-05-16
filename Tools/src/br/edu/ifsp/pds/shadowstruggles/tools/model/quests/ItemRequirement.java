package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class ItemRequirement extends Requirement {
	public ArrayList<Item> items;
	
	public ItemRequirement() {
		super();
		
		this.items = new ArrayList<Item>();
	}
	
	public ItemRequirement(String name, String description, ArrayList<Item> items) {
		super(name, description);
		
		this.items = items;
	}

	@Override
	public boolean checkCompleted() {
		// TODO Auto-generated method stub
		return false;
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
