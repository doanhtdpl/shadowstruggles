package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions.Condition;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

/**
 * A Sequence consists of a series of conditions followed by a series of
 * actions. The actions will be executed if and only if all of the conditions
 * are true.
 */
public class Sequence implements Serializable {
	private Array<Condition> conditions;
	private Array<Action> actions;

	public Sequence() {
		this.conditions = new Array<Condition>();
		this.actions = new Array<Action>();
	}

	/**
	 * Evaluates the conditions for performing the actions.
	 */
	public boolean evaluateConditions() {
		for (Condition condition : conditions) {
			if ((!condition.isNot() && !condition.evaluate())
					|| (condition.isNot() && condition.evaluate()))
				return false;
		}

		return true;
	}

	/**
	 * Updates the actions according to the current battle state.
	 */
	public void updateActions() {
		for (Action action : actions) {
			action.update();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.conditions = json.readValue("conditions", Array.class, jsonData);
		this.actions = json.readValue("actions", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("conditions", this.conditions);
		json.writeValue("actions", this.actions);
	}

	public Array<Action> getActions() {
		return actions;
	}

	public void setConditions(Array<Condition> conditions) {
		this.conditions = conditions;
	}

	public void setActions(Array<Action> actions) {
		this.actions = actions;
	}
}
