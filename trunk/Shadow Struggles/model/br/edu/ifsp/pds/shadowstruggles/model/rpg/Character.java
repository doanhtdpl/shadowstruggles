package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

/**
 * The logical representation of the RPG main character (Model) playable. It's
 * visually represented by Character2d class in object2d.rpg package.
 */
public class Character {

	public static enum WalkDirection {
		WALK_UP, WALK_DOWN, WALK_LEFT, WALK_RIGHT;
	}

	private Profile profile;
	private int tileX;
	private int tileY;
	private int size = 2;
	private float walkSpeed = 5;

	private boolean readyToWalk = true;
	private WalkDirection direction = null;
	private CharacterMover mover;
	private RpgMap currentMap;

	/**
	 * Stores the steps, in order, which must be performed before doing anything
	 * else.
	 */
	private Array<WalkDirection> movementBuffer;

	/**
	 * The constructor loads information from saved data.
	 * 
	 * @param profile
	 *            The player profile from where it loads saved data
	 */
	public Character(Profile profile) {
		this(profile, 0, 0);
	}

	public Character(Profile profile, int tileX, int tileY) {
		this.profile = profile;
		this.tileX = tileX;
		this.tileY = tileY;

		this.mover = new CharacterMover(CharacterMover.Type.NORMAL_CHARACTER);
		this.mover.setRectangle(new Rectangle(tileX, tileY, size, size / 2));
		this.movementBuffer = new Array<WalkDirection>();
	}

	/**
	 * Tries to walk a step into the TiledMap. If possible to walk in the
	 * specified direction (no obstacle), remove the character from current tile
	 * and adds in next tile.
	 * 
	 * @param direction
	 *            The direction intended to step.
	 * @param map
	 *            The map where the character is.
	 * @return true if successfully walked. Else, returns false.
	 */
	public boolean walk(WalkDirection direction, RpgMap map) {
		return walk(direction, map, false);
	}

	/**
	 * Tries to walk a step into the TiledMap. If possible to walk in the
	 * specified direction (no obstacle), remove the character from current tile
	 * and adds in next tile. Else, change direction to face the obstacle.
	 * 
	 * @param direction
	 *            The direction intended to step.
	 * @param map
	 *            The map where the character is.
	 * @param inPath
	 *            Indicates whether or not the designated movement is part of a
	 *            path. If it is, then it's stored in the movement buffer it the
	 *            movement can't be performed immediately.
	 * @return true if successfully walked. Else, returns false.
	 */
	public boolean walk(WalkDirection direction, RpgMap map, boolean inPath) {
		if (!readyToWalk) {
			if (inPath)
				movementBuffer.add(direction);
			return false;
		}

		this.direction = direction;
		this.currentMap = map;
		boolean walked = false;

		// If the movement buffer is empty, that means it's the end of the path
		// or it's not part of a path, so touch events may be triggered. Else,
		// wait until the movement is over.
		boolean emptyBuffer = this.movementBuffer.size == 0;

		switch (direction) {
		case WALK_UP:
			if (!map.blocked(this.mover, tileX, tileY - 1, emptyBuffer)) {
				tileY--;
				walked = true;
			}
			break;
		case WALK_DOWN:
			if (!map.blocked(this.mover, tileX, tileY + 1, emptyBuffer)) {
				tileY++;
				walked = true;
			}
			break;
		case WALK_LEFT:
			if (!map.blocked(this.mover, tileX - 1, tileY, emptyBuffer)) {
				tileX--;
				walked = true;
			}
			break;
		case WALK_RIGHT:
			if (!map.blocked(this.mover, tileX + 1, tileY, emptyBuffer)) {
				tileX++;
				walked = true;
			}
			break;
		default:
			break;
		}

		mover.setRectanglePos(tileX, tileY);

		return walked;
	}

	/**
	 * Notifies the Character to check for buffered movements and perform them
	 * in order.
	 */
	public WalkDirection update() {
		WalkDirection direction = null;

		if (movementBuffer.size > 0 && currentMap != null) {
			direction = movementBuffer.first();
			if (walk(direction, currentMap, false)) {
				movementBuffer.removeIndex(0);
			}
		}

		return direction;
	}

	public void clearMovementBuffer() {
		this.movementBuffer.clear();
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

	public CharacterMover getMover() {
		return this.mover;
	}

	public float getSize() {
		return this.size;
	}
}
