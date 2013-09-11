package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.model.profiles.conditions.ProfileCondition;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public abstract class EventAction implements Serializable {
	/**
	 * The conditions for this action to occur.
	 */
	private Array<ProfileCondition> conditions;

	public abstract void act();

	public boolean conditionsFulfilled() {
		if (conditions == null)
			return true;

		for (ProfileCondition condition : conditions) {
			if (!condition.evaluate())
				return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.conditions = json.readValue("conditions", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("conditions", this.conditions);
	}
}
