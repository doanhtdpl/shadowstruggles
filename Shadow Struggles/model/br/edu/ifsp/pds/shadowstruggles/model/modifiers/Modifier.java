package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import com.badlogic.gdx.utils.Json.Serializable;

/**
 * A Modifier is used by scenes, items and events to affect the player's status
 * in some way, such as increasing maximum health and modifying the inventory.
 */
public abstract class Modifier implements Serializable {

	/**
	 * Activate the modifier's effect.
	 */
	public abstract void modify();
}
