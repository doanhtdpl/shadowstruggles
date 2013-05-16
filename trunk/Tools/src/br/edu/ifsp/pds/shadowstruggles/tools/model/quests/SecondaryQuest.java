package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SecondaryQuest extends Requirement {
	public Quest quest;
	
	public SecondaryQuest() {
		super();
		
		this.quest = new Quest();
	}
	
	public SecondaryQuest(String name, String description, Quest quest) {
		super(name, description);
		this.quest = quest;
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
