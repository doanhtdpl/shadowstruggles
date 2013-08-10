package br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Checks the enemy's energy status.
 */
public class EnergyCondition extends Condition {
	private int baseEnergy;
	private Comparator comparator;

	@Override
	public boolean evaluate() {
		DefaultRules rules = ShadowStruggles.getInstance().getController()
				.getPlatform().getRules();

		if (comparator == Comparator.EQUAL_TO)
			return baseEnergy == rules.getEnemyEnergy();
		if (comparator == Comparator.GREATER_THAN)
			return baseEnergy > rules.getEnemyEnergy();
		if (comparator == Comparator.GREATER_THAN_OR_EQUALS_TO)
			return baseEnergy >= rules.getEnemyEnergy();
		if (comparator == Comparator.LESS_THAN)
			return baseEnergy < rules.getEnemyEnergy();
		if (comparator == Comparator.LESS_THAN_OR_EQUAL_TO)
			return baseEnergy <= rules.getEnemyEnergy();

		return false;
	}

	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("baseEnergy", this.baseEnergy);
		json.writeValue("comparator", this.comparator);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.baseEnergy = json.readValue("baseEnergy", Integer.class, jsonData);
		this.comparator = json.readValue("comparator", Comparator.class,
				jsonData);
	}

}
