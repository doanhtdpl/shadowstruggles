package br.edu.ifsp.pds.shadowstruggles.model.profiles;

/**
 * Attributes a point to the specified field, calculating how much the field
 * will increase according to it.
 */
public interface AttributePointsFormula {
	public static enum PlayerField {
		MAX_HEALTH, MAX_ENERGY, ENERGY_RECOVERY, DECK_CAPACITY, MAX_CARD_POINTS, DOUBLE_DRAW
	};

	public abstract void attributePoint(Profile profile, PlayerField field);
}
