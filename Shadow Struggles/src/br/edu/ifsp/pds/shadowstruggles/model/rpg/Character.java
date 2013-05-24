package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

public class Character {
	private Profile profile;
	private int tileX;
	private int tileY;
	private float walkSpeed = 5;
	
	private boolean readyToWalk = true;
	private float walked = 0;
	private WalkDirection direction = null;

	public boolean isReadyToWalk() {
		return readyToWalk;
	}

	public void setReadyToWalk(boolean readyToWalk) {
		this.readyToWalk = readyToWalk;
	}

	public float getWalked() {
		return walked;
	}

	public void setWalked(float walked) {
		this.walked = walked;
	}

	public WalkDirection getDirection() {
		return direction;
	}

	public void setDirection(WalkDirection direction) {
		this.direction = direction;
	}

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

	public boolean walk(WalkDirection direction, TiledMap map) {
		boolean walked = false;
		TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers()
				.get(0);

		switch (direction) {
		case WALK_UP:
			if (tileY < tileLayer.getHeight() - 1
					&& !tileLayer.getCell(tileX, tileY + 1).getTile()
							.getProperties().containsKey("obstacle")) {
				tileY++;
				walked = true;
			}
			break;
		case WALK_DOWN:
			if (tileY > 0
					&& !tileLayer.getCell(tileX, tileY - 1).getTile()
							.getProperties().containsKey("obstacle")) {
				tileY--;
				walked = true;
			}
			break;
		case WALK_LEFT:
			if (tileX > 0
					&& !tileLayer.getCell(tileX - 1, tileY).getTile()
							.getProperties().containsKey("obstacle")) {
				tileX--;
				walked = true;
			}
			break;
		case WALK_RIGHT:
			if (tileX < tileLayer.getWidth() - 1
					&& !tileLayer.getCell(tileX + 1, tileY).getTile()
							.getProperties().containsKey("obstacle")) {
				tileX++;
				walked = true;
			}
			break;
		default:
			break;
		}

		return walked;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
}
