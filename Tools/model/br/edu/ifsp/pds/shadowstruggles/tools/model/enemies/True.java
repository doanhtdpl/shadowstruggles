package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

/**
 * A condition that always returns true.
 */
public class True extends Condition {

	@Override
	public String toString() {
		return this.not ? "false" : "true";
	}
}
