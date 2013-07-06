package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entries;

/**
 * This class manages the reading/writing of  a language.
 */

public class Languages implements Serializable{

	private ObjectMap<String, String> languages;

	public Languages() {
		this.languages = new ObjectMap<String, String>();
	}

	@Override
	public void write(Json json) {
		json.writeValue("languages", this.languages);

	}

	public String get(String name) {
		return languages.get(name);
	}

	public ObjectMap<String, String> getLanguages() {
		return languages;
	}
	
	public Entries<String, String> entries() {
		return languages.entries();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		languages = json.readValue("languages", ObjectMap.class, jsonData);
	}
}
