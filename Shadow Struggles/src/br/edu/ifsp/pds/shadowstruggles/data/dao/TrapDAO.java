package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Trap;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a trap card from the DataManager
 */

public class TrapDAO {

	public static Trap getTrap(String key) {
		Trap trap = null;

		@SuppressWarnings("unchecked")
		Array<Trap> cards = DataManager.getInstance().getObjectSet(Trap.class);
		for (Trap card : cards) {
			if (card.getName().equals(key)) {
				/*
				 * This makes a deep copy of the effect so that similar effects
				 * don't share the same memory blocks.
				 */
				trap = new Trap(card.getName(), card.getLocalizedName(),
						card.getEnergyCost(), card.getDescription(),
						card.getBuyCost(), card.getAction(),
						card.getDuration(), card.isHasImmediateEffect());
			}
		}

		return trap;
	}

	@SuppressWarnings("unchecked")
	public static Array<Trap> getTraps() {
		return DataManager.getInstance().getObjectSet(Trap.class);
	}
}
