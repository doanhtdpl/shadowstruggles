package br.edu.ifsp.pds.shadowstruggles.model.profiles.conditions;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.EnemyDefeat;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Checks if the player has met the conditions for a battle (e.g., defeated
 * Enemy A at least twice).
 */
public class BattleCondition extends ProfileCondition {
	private Array<EnemyDefeat> battleRequirements;

	public BattleCondition() {
		this.battleRequirements = new Array<EnemyDefeat>();
	}

	@Override
	public boolean evaluate() {
		Profile profile = ShadowStruggles.getInstance().getProfile();

		for (EnemyDefeat battleRequirement : battleRequirements) {
			if (!profile.getDefeatedEnemies()
					.contains(battleRequirement, false))
				return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.battleRequirements = json.readValue("battleRequirements",
				Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("battleRequirements", this.battleRequirements);
	}
}
