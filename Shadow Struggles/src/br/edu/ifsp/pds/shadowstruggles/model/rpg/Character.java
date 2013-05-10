package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

public class Character {
	private Profile profile;
	private int tileX;
	private int tileY;
	
	public Character(Profile profile) {
		this.profile=profile;
	}
	
	public Character(Profile profile, float tileX, float tileY) {
		this.profile=profile;
	}
	
	
	public void walk(int direction){}

}
