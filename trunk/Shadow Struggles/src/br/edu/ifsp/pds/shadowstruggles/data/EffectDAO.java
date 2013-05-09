package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of an effect card from the DataManager
 */
public class EffectDAO {

	public static Effect getEffect(String key, DataManager manager) {
		Effect card = null;

		Array<Effect> cards = manager.getEffectList();
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

	public static Array<Effect> getEffects(DataManager manager) {
		return manager.getEffectList();
	}

}
