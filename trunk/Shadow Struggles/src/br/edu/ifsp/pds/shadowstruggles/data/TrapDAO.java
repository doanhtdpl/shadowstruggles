package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.Trap;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a trap card from the DataManager
 */

public class TrapDAO {

	public static Trap getTrap(String key, DataManager manager) {
		Trap trap = null;

		Array<Trap> cards = manager.getTrapList();
		for (Trap card : cards) {
			if (card.getName().equals(key)) {
				/*
				 * This makes a deep copy of the effect so that similar effects
				 * don't share the same memory blocks.
				 */
				trap = new Trap(card.getName(), card.getNameVisualization(),
						card.getEnergyCost(), card.getDescription(),
						card.getBuyCost(), card.getAction(),
						card.getDuration(), card.isHasImmediateEffect());
			}
		}

		return trap;
	}

	public static Array<Trap> getTraps(DataManager manager) {
		return manager.getTrapList();
	}
}
