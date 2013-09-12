package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

/**
 * Checks the enemy's energy status.
 */
public class EnergyCondition extends Condition {
	public int baseEnergy;
	public Comparator comparator;

	public EnergyCondition() {
		this.baseEnergy = 0;
		this.comparator = null;
	}

	public EnergyCondition(Comparator comparator, int baseEnergy) {
		this.baseEnergy = baseEnergy;
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

		return "enemy's energy " + notString + comparatorString + " "
				+ baseEnergy;
	}

	@Override
	public void read(Json json, JsonValue jsonValue) {
		super.read(json, jsonValue);

		this.baseEnergy = json
				.readValue("baseEnergy", Integer.class, jsonValue);
		this.comparator = json.readValue("comparator", Comparator.class,
				jsonValue);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("baseEnergy", this.baseEnergy);
		json.writeValue("comparator", this.comparator);
	}
}
