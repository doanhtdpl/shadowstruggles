package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Sequence implements Serializable {
	public ArrayList<Condition> conditions;
	public ArrayList<Action> actions;
	
	public Sequence() {
		// TODO Auto-generated constructor stub
	}
	
	public Sequence(ArrayList<Condition> conditions, ArrayList<Action> actions) {
		this.conditions = conditions;
		this.actions = actions;
	}
	
	public ArrayList<Action> getActions() {
		return this.actions;
	}
	
	public boolean evaluateConditions() {
		boolean b = false;
		
		return b;
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
