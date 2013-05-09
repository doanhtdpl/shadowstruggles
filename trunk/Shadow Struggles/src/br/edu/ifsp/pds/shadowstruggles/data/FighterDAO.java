package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.Fighter;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a fighter card from the DataManager
 */

public class FighterDAO {

	public static Fighter getFighter(String key, DataManager manager) {
		Fighter fighter = null;

		Array<Fighter> fighters = manager.getFighterList();
		for (Fighter f : fighters) {
			if (f.getName().equals(key)) {
				/* This makes a deep copy of the fighter so that similar fighters
				don't share the same memory blocks. */
				fighter = new Fighter(f.getName(), f.getNameVisualization(), f.getEnergyCost(),
						f.getDescription(), f.getBuyCost(), f.getAction(),
						f.getHealth(), f.getDamage(), f.getSpeed(),
						f.getRange(), f.isHasEffect(), f.getSize(),
						f.getAttackDelay(),f.getPreRequisites());
			}
		}

		return fighter;
	}

	public static Array<Fighter> getFighters(DataManager manager) {
		return manager.getFighterList();
	}

}

