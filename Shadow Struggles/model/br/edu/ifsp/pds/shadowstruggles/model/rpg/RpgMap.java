package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventInGame;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Mover;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.TileBasedMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * The map representation used by the pathfinder algorithm. It serves as a
 * wrapper for the tiled map itself.
 */
public class RpgMap implements TileBasedMap {
	private String mapName;
	private TiledMap map;
	private String objectLayer;
	private TiledMapTileLayer tileLayer = null;
	private ShadowStruggles game;

	private boolean visited[][] = null;

	public RpgMap(ShadowStruggles game, String mapName) {
		this(game, mapName, SettingsDAO.getSettings().defaultObjLayer,
				SettingsDAO.getSettings().defaultTileLayer);
	}

	public RpgMap(ShadowStruggles game, String mapName, String objectLayer,
			String tileLayer) {
		this.game = game;
		this.mapName = mapName;
		this.map = game.getTiledMap(mapName);
		this.objectLayer = objectLayer;
		this.tileLayer = (TiledMapTileLayer) map.getLayers().get(tileLayer);
	}

	public RpgMap(RpgMap rpgMap) {
		this.game = rpgMap.getGame();
		this.mapName = rpgMap.getMapName();
		this.map = rpgMap.getMap();
		this.objectLayer = rpgMap.getObjectLayer();
		this.tileLayer = rpgMap.getTileLayer();
	}

	@Override
	public int getWidthInTiles() {
		return tileLayer.getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return tileLayer.getHeight();
	}

	public void clearVisited() {
		for (int x = 0; x < getWidthInTiles(); x++) {
			for (int y = 0; y < getHeightInTiles(); y++) {
				visited[x][y] = false;
			}
		}
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		if (visited == null)
			visited = new boolean[getWidthInTiles()][getHeightInTiles()];
	}

	/**
	 * Executes the automatic events of the map in the current object layer.
	 */
	public void runAutomaticEvents() {
		Array<EventInGame> events = game.getProfile().getEvents(mapName,
				objectLayer);
		for (EventInGame event : events) {
			if (event.getTriggerType() == EventInGame.TriggerType.AUTOMATIC)
				event.trigger();
		}
	}

	@Override
	public boolean blocked(Mover mover, int x, int y) {
		return this.blocked(mover, x, y, false);
	}

	/**
	 * Checks if the given location is blocked; if it is blocked by a touch
	 * event, it may be triggered.
	 * 
	 * @param triggerTouch
	 *            Indicates if the events may be triggered by touch.
	 */
	public boolean blocked(Mover mover, int x, int y, boolean triggerTouch) {
		if (mover.getClass() == CharacterMover.class) {
			CharacterMover cMover = (CharacterMover) mover;

			if (cMover.getType() == CharacterMover.Type.NORMAL_CHARACTER) {
				// Basic validation: check if the coordinates are within the
				// valid bounds.
				if (x < 0 || x >= this.getWidthInTiles() || y < 0
						|| y >= this.getHeightInTiles()) {
					System.out.println("Out of bounds!");
					return true;
				}

				// Project the character into the desired location to calculate
				// possible collisions.
				Rectangle projectedCharacter = new Rectangle(x, y, cMover
						.getRectangle().getWidth(), cMover.getRectangle()
						.getHeight() / 2);

				// Check for collidable events, triggering them if applicable.
				Array<EventInGame> events = game.getProfile().getEvents(
						mapName, objectLayer);
				for (EventInGame event : events) {
					Rectangle rect = event.getCharacter().getMover()
							.getRectangle();
					if (rect.overlaps(projectedCharacter)) {
						if (triggerTouch
								&& event.getTriggerType() == EventInGame.TriggerType.TOUCH)
							event.trigger();
						return event.isCollidable();
					}
				}

				// Maps from Tiled are interpreted with the traditional
				// Cartesian coordinate system (y increases upwards); thus, the
				// y parameter must be inverted. Also, y ranges from 0 to height
				// - 1.
				int invertY = this.getHeightInTiles() - y - 1;
				projectedCharacter.y = invertY;

				// Check for tile obstacles in the tile itself and its adjacent
				// spots in 4 directions.
				Array<Vector2> adjacentTiles = new Array<Vector2>();
				adjacentTiles.add(new Vector2(x, invertY));

				if (x < this.getWidthInTiles())
					adjacentTiles.add(new Vector2(x + 1, invertY));
				if (x > 0)
					adjacentTiles.add(new Vector2(x - 1, invertY));
				if (invertY < this.getHeightInTiles() - 1)
					adjacentTiles.add(new Vector2(x, invertY + 1));
				if (invertY > 0)
					adjacentTiles.add(new Vector2(x, invertY - 1));

				for (Vector2 tilePos : adjacentTiles) {
					TiledMapTile tile = null;
					if (tileLayer.getCell((int) tilePos.x, (int) tilePos.y) != null) {
						tile = tileLayer.getCell((int) tilePos.x,
								(int) tilePos.y).getTile();
					} else {
						return true;
					}

					if (tile.getProperties().containsKey(
							SettingsDAO.getSettings().collidableTile)) {
						Rectangle tileRect = new Rectangle(tilePos.x,
								tilePos.y, 1, 1);
						if (tileRect.overlaps(projectedCharacter)) {
							return true;
						}
					}
				}

				return false;
			} else {
				throw new UnsupportedOperationException(
						"Invalid CharacterMover type");
			}
		} else {
			throw new UnsupportedOperationException("Invalid Mover object");
		}
	}

	/**
	 * Tries triggering the interactive event in the specified location with the
	 * specified CharacterMover, returning whether the event was found or not.
	 */
	public boolean triggerEvent(int x, int y, CharacterMover cMover,
			WalkDirection directionTurn) {
		Array<EventInGame> events = game.getProfile().getEvents(mapName,
				objectLayer);
		for (EventInGame event : events) {
			Rectangle rect = event.getCharacter().getMover().getRectangle();

			if (rect.overlaps(new Rectangle(x, y, cMover.getRectangle().width,
					cMover.getRectangle().height))
					&& event.getTriggerType() == EventInGame.TriggerType.INTERACT) {
				if (!event.isCollidable())
					directionTurn = null;
				event.trigger(directionTurn);
				return true;
			}
		}

		return false;
	}

	/**
	 * Tries triggering the touch event in the specified location with the
	 * specified CharacterMover, returning whether the event was found or not.
	 */
	public boolean touchEvent(int x, int y, CharacterMover cMover,
			WalkDirection directionTurn) {
		Array<EventInGame> events = game.getProfile().getEvents(mapName,
				objectLayer);
		for (EventInGame event : events) {
			Rectangle rect = event.getCharacter().getMover().getRectangle();

			if (rect.overlaps(new Rectangle(x, y, cMover.getRectangle().width,
					cMover.getRectangle().height))
					&& event.getTriggerType() == EventInGame.TriggerType.TOUCH) {
				if (!event.isCollidable())
					directionTurn = null;
				event.trigger(directionTurn);
				return true;
			}
		}

		return false;
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

	public boolean visited(int x, int y) {
		if (visited == null)
			visited = new boolean[getWidthInTiles()][getHeightInTiles()];
		return visited[x][y];
	}

	public TiledMap getMap() {
		return this.map;
	}

	public String getMapName() {
		return this.mapName;
	}

	public String getObjectLayer() {
		return this.objectLayer;
	}

	public TiledMapTileLayer getTileLayer() {
		return this.tileLayer;
	}

	private ShadowStruggles getGame() {
		return this.game;
	}
}
