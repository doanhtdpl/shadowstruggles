package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import com.esotericsoftware.jsonbeans.Json.Serializable;

//TODO: Implementar condições abstratas.

public abstract class Condition implements Serializable {
	public static enum Comparator {
		EQUALS_TO, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUALS_TO, LESS_THAN_OR_EQUALS_TO
	};
	
	public abstract boolean evaluate();
}
