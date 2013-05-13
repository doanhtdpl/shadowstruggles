package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

public class Character {
	private Profile profile;
	private int tileX;
	private int tileY;
	public static enum WalkDirection{
		WALK_UP,WALK_DOWN, WALK_LEFT,WALK_RIGHT;
	}
	
	public Character(Profile profile) {
		this.profile=profile;
	}
	
	public Character(Profile profile, int tileX, int tileY) {
		this.profile=profile;
		this.tileX=tileX;
		this.tileY=tileY;
		
	}
	
	
	public void walk(WalkDirection direction, RpgMap map){
		map.getTile(tileX, tileY).removeCharacer();
		switch(direction){
		case WALK_UP:tileY++;break;
			case WALK_DOWN:tileY--;break;
			case WALK_LEFT:tileX--;break;
			case WALK_RIGHT:tileX++;break;
		default:break;
		}
		map.getTile(tileX, tileY).insertCharacter(this);
	}

	public int getTileX() {
		return tileX;
	}
	
	public int getTileY() {
		return tileY;
	}
}
