package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

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

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		language = json.readValue("language", ObjectMap.class, jsonData);

	}

	public String get(String name) {
		return language.get(name);
	}

	public ObjectMap<String, String> getLanguages() {
		return language;
	}
}
