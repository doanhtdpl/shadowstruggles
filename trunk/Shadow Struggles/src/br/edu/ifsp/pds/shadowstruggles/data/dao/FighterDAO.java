package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a fighter card from the DataManager
 */

public class FighterDAO {

	public static Fighter getFighter(String key) {
		Fighter fighter = null;

		@SuppressWarnings("unchecked")
		Array<Fighter> fighters = DataManager.getInstance().getObjectSet(
				Fighter.class);
		for (Fighter f : fighters) {
			if (f.getName().equals(key)) {
				/*
				 * This makes a deep copy of the fighter so that similar
				 * fighters don't share the same memory blocks.
				 */
				fighter = new Fighter(f.getName(), f.getNameVisualization(),
						f.getEnergyCost(), f.getDescription(), f.getBuyCost(),
						f.getAction(), f.getHealth(), f.getDamage(),
						f.getSpeed(), f.getRange(), f.isHasEffect(),
						f.getSize(), f.getAttackDelay(), f.getPreRequisites());
			}
		}

		return fighter;
	}

	@SuppressWarnings("unchecked")
	public static Array<Fighter> getFighters() {
		return DataManager.getInstance().getObjectSet(Fighter.class);
	}

}
