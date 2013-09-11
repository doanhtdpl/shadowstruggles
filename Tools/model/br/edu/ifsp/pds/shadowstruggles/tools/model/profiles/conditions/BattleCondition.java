package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.conditions;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.EnemyDefeat;

/**
 * Checks if the player has met the conditions for a battle (e.g., defeated
 * Enemy A at least twice).
 */
public class BattleCondition extends ProfileCondition {
	public ArrayList<EnemyDefeat> battleRequirements;

	public BattleCondition() {
		this.battleRequirements = new ArrayList<EnemyDefeat>();
	}
}
