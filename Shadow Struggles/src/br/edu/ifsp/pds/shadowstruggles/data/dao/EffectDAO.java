package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Effect;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of an effect card from the DataManager
 */
public class EffectDAO {

	public static Effect getEffect(String key) {
		Effect card = null;

		@SuppressWarnings("unchecked")
		Array<Effect> cards = DataManager.getInstance().getObjectSet(Effect.class);
		for (Effect effect : cards) {
			if (effect.getName().equals(key)) {
				/*
				 * This makes a deep copy of the effect so that similar effects
				 * don't share the same memory blocks.
				 */
				card = new Effect(effect.getName(), effect.getNameVisualization(),
						effect.getEnergyCost(), effect.getDescription(), effect.getBuyCost(),
						effect.getAction(), effect.getDuration(),
						effect.isImmediateEffect(), effect.isOnFighter());
			}
		}

		return card;
	}

	@SuppressWarnings("unchecked")
	public static Array<Effect> getEffects() {
		return DataManager.getInstance().getObjectSet(Effect.class);
	}

}
