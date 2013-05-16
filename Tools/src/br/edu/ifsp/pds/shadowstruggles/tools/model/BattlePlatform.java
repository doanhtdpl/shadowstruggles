package br.edu.ifsp.pds.shadowstruggles.tools.model;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class BattlePlatform implements Serializable {
	public int id;
	public boolean tutorial;
	public Enemy opponent;
	public ArrayList<Modifier> rewards;
	public String map;
	public DefaultRules rules;
	
	public BattlePlatform() {
		this.id = 1;
		this.tutorial = false;
		this.opponent = new Enemy();
		this.rewards = new ArrayList<Modifier>();
		this.map = "";
		this.rules = new DefaultRules();
	}
	
	public BattlePlatform(int id, boolean tutorial, Enemy opponent,
			ArrayList<Modifier> rewards, String map, DefaultRules rules) {
		this.id = id;
		this.tutorial = tutorial;
		this.opponent = opponent;
		this.rewards = rewards;
		this.map = map;
		this.rules = rules;
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
