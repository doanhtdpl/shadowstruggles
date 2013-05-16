package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Action implements Serializable {
	public static enum Attribute { LANE, TILE, CARD };
	
	public Attribute type;
	public Object value;
	
	public Action() {
		this.type = Attribute.LANE;
		this.value = new Object();
	}
	
	public Action(Attribute type, Object value) {
		this.type = type;
		this.value = value;
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
