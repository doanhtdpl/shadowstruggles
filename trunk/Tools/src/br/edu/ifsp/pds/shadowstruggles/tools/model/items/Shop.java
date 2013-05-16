package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Shop implements Serializable {
	public int id;
	public boolean mainShop;
	public ArrayList<Item> items;
	
	public Shop() {
		this.id = 1;
		this.mainShop = false;
		this.items = new ArrayList<Item>();
	}
	
	public Shop(int id, boolean mainShop, ArrayList<Item> items) {
		this.id = id;
		this.mainShop = mainShop;
		this.items = items;
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
