package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Languages implements Serializable {

	public HashMap<String, String> languages;

	public Languages() {
		this.languages = new HashMap<String, String>();
	}
	
	public Set<String> keySet() {
		return languages.keySet();
	}

	public String get(String name) {
		return languages.get(name);
	}
	
	public void put(String key, String value) {
		this.languages.put(key, value);
	}

	public boolean containsKey(String key) {
		return this.languages.containsKey(key);
	}
	
	public void remove(String key) {
		this.languages.remove(key);
	}
	
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
			SerializationHelper
					.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return languages.toString();
	}
}
