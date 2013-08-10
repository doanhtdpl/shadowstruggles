package br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;

/**
 * Checks for attacks on the enemy base on the specified lane.
 */
public class BaseAttacked extends Condition {
	private int baseLane;
	private Comparator comparator;

	@Override
	public boolean evaluate() {
		BattleMap map = ShadowStruggles.getInstance().getController()
				.getPlatform().getMap();
		int attackedLane = map.enemyBaseAttacked();

		if (comparator == Comparator.EQUAL_TO)
			return baseLane == attackedLane;
		if (comparator == Comparator.GREATER_THAN)
			return baseLane > attackedLane;
		if (comparator == Comparator.GREATER_THAN_OR_EQUALS_TO)
			return baseLane >= attackedLane;
		if (comparator == Comparator.LESS_THAN)
			return baseLane < attackedLane;
		if (comparator == Comparator.LESS_THAN_OR_EQUAL_TO)
			return baseLane <= attackedLane;

		return false;
	}

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
