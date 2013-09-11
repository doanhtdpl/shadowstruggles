package br.edu.ifsp.pds.shadowstruggles.model.profiles.conditions;

import com.badlogic.gdx.utils.Json.Serializable;

/**
 * A ProfileCondition object is used by events and scenes for flow control
 * according to the current profile's attributes.
 */
public abstract class ProfileCondition implements Serializable {
	/**
	 * Used to compare numeric attributes such as story points and experience.
	 */
	public static enum NumericComparator {
		EQUAL_TO, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO
	};

	public abstract boolean evaluate();
}
