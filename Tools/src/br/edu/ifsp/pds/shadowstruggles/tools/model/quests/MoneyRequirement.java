package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class MoneyRequirement extends Requirement {
	public int money;
	
	public MoneyRequirement() {
		super();
		
		this.money = 0;
	}
	
	public MoneyRequirement(String name, String description, int money) {
		super(name, description);
		
		this.money = money;
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
