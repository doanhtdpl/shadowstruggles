package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import com.badlogic.gdx.utils.Json.Serializable;

public abstract class Modifier implements Serializable {

	/**
	 * Activate the modifier's effect.
	 */
	public abstract void modify();
}
