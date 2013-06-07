package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class AmountModifier extends Modifier {
	public static enum ModifiedAttribute {
		HEALTH, MAX_ENERGY, INITIAL_ENERGY, MAX_HEALTH, MAX_CARD_POINTS, PATH, POINTS, MONEY, DOUBLE_DRAW, LEVEL, EXPERIENCE, ENERGY_RECOVERY, DECK_CAPACITY
	};
	
	public float amount;
	public boolean replace;
	public ModifiedAttribute attribute;
	
	/**
	 * Used for modifying text attributes, such as PATH.
	 */
	public String text;

	public AmountModifier() {
		this.amount = 0;
		this.replace = false;
		this.attribute = ModifiedAttribute.HEALTH;
		this.text = "";
	}
	
	public AmountModifier(float amount, boolean replace,
			ModifiedAttribute attribute, String text) {
		this.amount = amount;
		this.replace = replace;
		this.attribute = attribute;
		this.text = text;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.amount = json.readValue("amount", Float.class, jsonData);
		this.replace = json.readValue("replace", Boolean.class, jsonData);
		this.attribute = json.readValue("attribute", ModifiedAttribute.class, jsonData);
		this.text = json.readValue("text", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("amount", this.amount);
		json.writeValue("replace", this.replace);
		json.writeValue("attribute", this.attribute);
		json.writeValue("text", this.text);
	}
}
