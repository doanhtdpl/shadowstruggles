package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.Modifier;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Quest implements Serializable {
	public static enum QuestStatus {
		NOT_ACCEPTED, ONGOING, REQUIREMENTS_COMPLETED, QUEST_COMPLETED
	};

	public int id;
	public String name;
	public String description;
	public ArrayList<Requirement> requirements;
	public ArrayList<Modifier> rewards;
	public QuestStatus status;

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub

	}

}
