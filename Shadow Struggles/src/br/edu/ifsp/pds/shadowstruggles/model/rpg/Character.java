package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

public class Character {
	private Profile profile;
	private int tileX;
	private int tileY;

	public static enum WalkDirection {
		WALK_UP, WALK_DOWN, WALK_LEFT, WALK_RIGHT;
	}

	public Character(Profile profile) {
		this.profile = profile;
	}

	public Character(Profile profile, int tileX, int tileY) {
		this.profile = profile;
		this.tileX = tileX;
		this.tileY = tileY;

	}

	public boolean walk(WalkDirection direction, RpgMap map) {
		map.getTile(tileX, tileY).removeCharacer();
		boolean walked = false;
		switch (direction) {
		case WALK_UP:
			if (tileY < map.getHeight() - 1) {
				tileY++;
				walked = true;
			}
			break;
		case WALK_DOWN:
			if (tileY > 0) {
				tileY--;
				walked = true;
			}
			break;
		case WALK_LEFT:
			if (tileX > 0) {
				tileX--;
				walked = true;
			}
			break;
		case WALK_RIGHT:
			if (tileX < map.getWidth() - 1) {
				tileX++;
				walked = true;
			}
			break;
		default:
			break;
		}
		map.getTile(tileX, tileY).insertCharacter(this);
		return walked;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}
}
