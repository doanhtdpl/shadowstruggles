package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * The logical representation of an RPG character (Model) playable. It's
 * visually represented by the {@link Character2D} class.
 */
public class Character {
	public static enum WalkDirection {
		WALK_UP, WALK_DOWN, WALK_LEFT, WALK_RIGHT;
	}

	private int tileX;
	private int tileY;
	private float widthInTiles;
	private float heightInTiles;
	private float walkSpeed = 5;

	private boolean readyToWalk = true;
	private boolean inPath = false;
	private WalkDirection direction = null;
	private CharacterMover mover;
	private RpgMap currentMap;

	/**
	 * Stores the steps, in order, which must be performed before doing anything
	 * else.
	 */
	private Array<WalkDirection> movementBuffer;
	/**
	 * If the character is moving along a path, this parameter stores the target
	 * location (destination[0] = x, destination[1] = y).
	 */
	private int[] destination;

	public Character() {
		this(0, 0);
	}

	public Character(int tileX, int tileY) {
		this(tileX, tileY, 2, 2);
	}

	public Character(int tileX, int tileY, float widthInTiles,
			float heightInTiles) {
		this(tileX, tileY, widthInTiles, heightInTiles, null);
	}

	public Character(int tileX, int tileY, float widthInTiles,
			float heightInTiles, RpgMap currentMap) {
		this.tileX = tileX;
		this.tileY = tileY;
		this.widthInTiles = widthInTiles;
		this.heightInTiles = heightInTiles;
		this.currentMap = currentMap;

		this.mover = new CharacterMover(CharacterMover.Type.NORMAL_CHARACTER);
		this.mover.setRectangle(new Rectangle(tileX, tileY, widthInTiles,
				heightInTiles));
		this.movementBuffer = new Array<WalkDirection>();
		this.destination = null;
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
		return walk(direction, map, false, null);
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
	 * @param destination
	 *            The movement's target, if it belongs to a path (destination[0]
	 *            = x, destination[1] = y).
	 * @return true if successfully walked. Else, returns false.
	 */
	public boolean walk(WalkDirection direction, RpgMap map, boolean inPath,
			int[] destination) {
		if (destination != null)
			this.destination = destination;
		if (!readyToWalk) {
			if (inPath) {
				this.inPath = true;
				movementBuffer.add(direction);
			}
			return false;
		}

		this.direction = direction;
		this.currentMap = map;
		boolean walked = false;

		switch (direction) {
		case WALK_UP:
			if (!map.blocked(this.mover, tileX, tileY - 1, destination == null)) {
				tileY--;
				walked = true;
			}
			break;
		case WALK_DOWN:
			if (!map.blocked(this.mover, tileX, tileY + 1, destination == null)) {
				tileY++;
				walked = true;
			}
			break;
		case WALK_LEFT:
			if (!map.blocked(this.mover, tileX - 1, tileY, destination == null)) {
				tileX--;
				walked = true;
			}
			break;
		case WALK_RIGHT:
			if (!map.blocked(this.mover, tileX + 1, tileY, destination == null)) {
				tileX++;
				walked = true;
			}
			break;
		default:
			break;
		}

		mover.setRectanglePos(tileX, tileY);

		// If the path has been completed and there is a specified target,
		// try triggering an event. Also, change directions of the character and
		// the target to face each other.
		if (movementBuffer.size <= 1 && this.inPath && destination != null) {
			WalkDirection oppositeDirection = null;

			if (destination[0] == tileX && destination[1] < tileY) {
				this.direction = WalkDirection.WALK_UP;
				oppositeDirection = WalkDirection.WALK_DOWN;
			}
			if (destination[0] == tileX && destination[1] > tileY) {
				this.direction = WalkDirection.WALK_DOWN;
				oppositeDirection = WalkDirection.WALK_UP;
			}
			if (destination[0] < tileX && destination[1] == tileY) {
				this.direction = WalkDirection.WALK_LEFT;
				oppositeDirection = WalkDirection.WALK_RIGHT;
			}
			if (destination[0] > tileX && destination[1] == tileY) {
				this.direction = WalkDirection.WALK_RIGHT;
				oppositeDirection = WalkDirection.WALK_LEFT;
			}

			map.triggerEvent(mover.getRectangle(), oppositeDirection);
			map.touchEvent(mover.getRectangle(), oppositeDirection);

			this.destination = null;
			this.inPath = false;
		}

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
			if (walk(direction, currentMap, false, this.destination)) {
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

	public void setTileX(int tileX) {
		this.tileX = tileX;
		this.mover.getRectangle().setX(tileX);
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
		this.mover.getRectangle().setY(tileY);
	}

	public void setPosition(int tileX, int tileY) {
		this.setTileX(tileX);
		this.setTileY(tileY);
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

	public float getHeightInTiles() {
		return heightInTiles;
	}

	public float getWidthInTiles() {
		return widthInTiles;
	}

	public RpgMap getCurrentMap() {
		return this.currentMap;
	}

	public void setCurrentMap(RpgMap currentMap) {
		this.currentMap = currentMap;
	}
}
