package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

/**
 * Checks the enemy's health status.
 */
public class HealthCondition extends Condition {
	public int baseHealth;
	public Comparator comparator;

	public HealthCondition() {
		this.baseHealth = 0;
		this.comparator = null;
	}

	public HealthCondition(Comparator comparator, int baseHealth) {
		this.baseHealth = baseHealth;
		this.comparator = comparator;
	}

	@Override
	public String toString() {
		String comparatorString = "";
		String notString = "";

		if (comparator == Comparator.EQUAL_TO)
			comparatorString = "=";
		if (comparator == Comparator.GREATER_THAN)
			comparatorString = ">";
		if (comparator == Comparator.GREATER_THAN_OR_EQUAL_TO)
			comparatorString = ">=";
		if (comparator == Comparator.LESS_THAN)
			comparatorString = "<";
		if (comparator == Comparator.LESS_THAN_OR_EQUAL_TO)
			comparatorString = "<=";

		if (this.not)
			notString = "NOT ";

		return notString + "enemy's life " + comparatorString + " "
				+ baseHealth;
	}

	@Override
	public void read(Json json, JsonValue jsonValue) {
		super.read(json, jsonValue);

		this.baseHealth = json
				.readValue("baseHealth", Integer.class, jsonValue);
		this.comparator = json.readValue("comparator", Comparator.class,
				jsonValue);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("baseHealth", this.baseHealth);
		json.writeValue("comparator", this.comparator);
	}

}
