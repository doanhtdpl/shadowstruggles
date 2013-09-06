package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

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
}
