package br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions;

/**
 * A condition that always returns true.
 */
public class True extends Condition {
	@Override
	public boolean evaluate() {
		return true;
	}
}
