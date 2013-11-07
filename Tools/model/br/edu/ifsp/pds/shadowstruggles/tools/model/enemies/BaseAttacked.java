package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;



/**
 * Checks for attacks on the enemy base on the specified lane.
 */
public class BaseAttacked extends Condition {
	private int baseLane;
	private Comparator comparator;



	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("baseLane", this.baseLane);
		json.writeValue("comparator", this.comparator);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.baseLane = json.readValue("baseLane", Integer.class, jsonData);
		this.comparator = json.readValue("comparator", Comparator.class,
				jsonData);
	}

}
