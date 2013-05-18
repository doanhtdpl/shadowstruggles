package br.edu.ifsp.pds.shadowstruggles.tools.model.quests;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

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
