package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * This class manages the reading/writing of  a language.
 */

public class Languages implements Serializable{

	private ObjectMap<String, String> language;

	public Languages() {
		this.language = new ObjectMap<String, String>();
	}

	@Override
	public void write(Json json) {
		json.writeValue("language", this.language);

	}

	

	public String get(String name) {
		return language.get(name);
	}

	public ObjectMap<String, String> getLanguages() {
		return language;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		language = json.readValue("language", ObjectMap.class, jsonData);
		
	}
}
