package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public abstract class Requirement implements Serializable {
	public String name;
	public String description;

	public Requirement() {
		this.name = "";
		this.description = "";
	}
	
	public Requirement(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public abstract boolean checkCompleted();

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		
	}

}
