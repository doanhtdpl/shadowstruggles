package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class ItemRequirement extends Requirement {
	public ArrayList<Item> itens;
	
	public ItemRequirement() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemRequirement(String name, String description, ArrayList<Item> itens) {
		super(name, description);
		
		this.itens = itens;
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
