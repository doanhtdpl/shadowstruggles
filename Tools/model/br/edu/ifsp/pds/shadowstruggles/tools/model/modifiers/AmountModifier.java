package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

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
		/*this.amount = 0;
		this.replace = false;
		this.attribute = ModifiedAttribute.HEALTH;
		this.text = "";*/
	}
	
	public AmountModifier(float amount, boolean replace,
			ModifiedAttribute attribute, String text) {
		this.amount = amount;
		this.replace = replace;
		this.attribute = attribute;
		this.text = text;
	}
	
	
	@Override
	public void write(Json arg0) {
		try {
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
}
