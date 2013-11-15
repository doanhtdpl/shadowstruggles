package br.edu.ifsp.pds.shadowstruggles.model.profiles;

/**
 * Calculates the experience points needed for level up according to a linear
 * function applied to the current level (f(x) = ax + b, where x = level).
 */
public class LinearExperience implements ExperienceNextLevelFormula {
	private int a, b;

	/**
	 * Default constructor, with fixed values for a and b.
	 */
	public LinearExperience() {
		this(1, 2);
	}

	/**
	 * Full constructor.
	 * 
	 * @param a
	 *            The linear coefficient.
	 * @param b
	 *            A constant.
	 */
	public LinearExperience(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int getExperienceNextLevel(Profile profile) {
		return a * profile.getLevel() + b;
	}

}
