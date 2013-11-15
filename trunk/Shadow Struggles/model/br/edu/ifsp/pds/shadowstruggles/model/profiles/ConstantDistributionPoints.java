package br.edu.ifsp.pds.shadowstruggles.model.profiles;

/**
 * Always gives the same number of points.
 */
public class ConstantDistributionPoints implements DistributionPointsFormula {
	int constant;

	public ConstantDistributionPoints() {
		this(5);
	}

	public ConstantDistributionPoints(int constant) {
		this.constant = constant;
	}

	@Override
	public int getDistributionPoints(Profile profile) {
		return constant;
	}

}
