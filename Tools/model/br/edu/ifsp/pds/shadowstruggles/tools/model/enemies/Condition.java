package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

/**
 * A Condition evaluates a specific circumstance in battles in order to help the
 * AI in making decisions.
 */
public abstract class Condition implements Serializable {
	/**
	 * Symbols for logical operators used in numeric comparisons.
	 */
	public static enum Comparator {
		EQUAL_TO, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO
	};

	/**
	 * Indicates that the condition is fulfilled when the opposite of the target
	 * fact is verified (e.g., the enemy's health is NOT less than 10).
	 */
	public boolean not;

	@Override
	public void read(Json json, JsonValue jsonValue) {
		this.not = json.readValue("not", Boolean.class, jsonValue);
	}

	@Override
	public void write(Json json) {
		json.writeValue("not", this.not);
	}
}
