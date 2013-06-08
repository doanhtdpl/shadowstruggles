package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Mover;

public class CharacterMover implements Mover {

	public static enum Type { NORMAL_CHARACTER };
	
	private Type type;
	
	public CharacterMover(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
