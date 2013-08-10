package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;

/**
 * An item which modifies a player's attribute through a {@link Modifier}
 * object.
 */
public class ModifierItem extends Item {
	private Modifier modifier;

	public ModifierItem() {
		super();

		this.modifier = null;
	}

	@Override
	public void useItem() {
		modifier.modify();
		if (consumable)
			consumeItem();
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.modifier = json.readValue("modifier", Modifier.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("modifier", this.modifier);
	}
}
