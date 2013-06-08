package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

/**
 * The logical representation of the RPG main character (Model) playable. 
 * It's visually represented by Character2d class in object2d.rpg package.
 */
public class Character {
	
	public static enum WalkDirection {
		WALK_UP, WALK_DOWN, WALK_LEFT, WALK_RIGHT;
	}
	
	private Profile profile;
	private int tileX;
	private int tileY;
	private float walkSpeed = 5;
	
	private boolean readyToWalk = true;
	private WalkDirection direction = null;
	
	
	/**
	 * The constructor loads information from saved data.
	 * 
	 * @param profile
	 *            The player profile from where it loads saved data
	 */
	public Character(Profile profile) {
		this.profile = profile;
	}

	public Character(Profile profile, int tileX, int tileY) {
		this(profile);
		this.tileX = tileX;
		this.tileY = tileY;
	}	

	/**
	 * Tries to walk a step into the TiledMap. If possile to walk in the specified
	 *  direction (no obstacle), remove the character from current tile and 
	 *  adds in next tile.
	 *  @param direction 
	 *  	The direction intended to step.
	 *  @param map 
	 *  	The map where the character is.
	 *  @return true if successfully walked. Else, returns false.
	 */
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

	
	public boolean isReadyToWalk() {
		return readyToWalk;
	}

	public void setReadyToWalk(boolean readyToWalk) {
		this.readyToWalk = readyToWalk;
	}

	public WalkDirection getDirection() {
		return direction;
	}

	public void setDirection(WalkDirection direction) {
		this.direction = direction;
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
