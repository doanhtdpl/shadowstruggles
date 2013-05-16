package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class UnlockModifier extends Modifier {

	public ArrayList<Item> items;
	
	public UnlockModifier() {
		this.items = new ArrayList<Item>();
	}
	
	public UnlockModifier(ArrayList<Item> items) {
		this.items = items;
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
