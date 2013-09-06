package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;

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
	
	public Quest() {
		this.id = 1;
		this.name = "";
		this.description = "";
		this.requirements = new ArrayList<Requirement>();
		this.rewards = new ArrayList<Modifier>();
		this.status = QuestStatus.NOT_ACCEPTED;
	}

	public Quest(int id, String name, String description,
			ArrayList<Requirement> requirements, ArrayList<Modifier> rewards,
			QuestStatus status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.requirements = requirements;
		this.rewards = rewards;
		this.status = status;
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(Json arg0) {
		try {
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

}
