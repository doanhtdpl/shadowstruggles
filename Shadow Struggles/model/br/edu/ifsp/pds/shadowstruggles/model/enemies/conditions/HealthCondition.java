package br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;

/**
 * Checks the enemy's health status.
 */
public class HealthCondition extends Condition {
	private int baseHealth;
	private Comparator comparator;

	@Override
	public boolean evaluate() {
		DefaultRules rules = ShadowStruggles.getInstance().getController()
				.getPlatform().getRules();

		if (comparator == Comparator.EQUAL_TO)
			return baseHealth == rules.getEnemyHP();
		if (comparator == Comparator.GREATER_THAN)
			return baseHealth > rules.getEnemyHP();
		if (comparator == Comparator.GREATER_THAN_OR_EQUALS_TO)
			return baseHealth >= rules.getEnemyHP();
		if (comparator == Comparator.LESS_THAN)
			return baseHealth < rules.getEnemyHP();
		if (comparator == Comparator.LESS_THAN_OR_EQUAL_TO)
			return baseHealth <= rules.getEnemyHP();

		return false;
	}

	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("baseHealth", this.baseHealth);
		json.writeValue("comparator", this.comparator);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.baseHealth = json.readValue("baseHealth", Integer.class, jsonData);
		this.comparator = json.readValue("comparator", Comparator.class,
				jsonData);
	}

}
