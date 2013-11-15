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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * The map representation used by the pathfinder algorithm and other classes
 * which manipulate the map in some way. It serves as a wrapper for the tiled
 * map itself.
 */
public class RpgMap implements TileBasedMap, Serializable {
	private String mapName;
	private TiledMap map;
	private String objectLayer;
	private String tileLayerString;
	private TiledMapTileLayer tileLayer = null;

	private boolean visited[][] = null;

	public RpgMap() {
		this.mapName = "";
		this.map = null;
		this.objectLayer = "";
		this.tileLayerString = "";
		this.tileLayer = null;
	}

	public RpgMap(String mapName) {
		this(mapName, SettingsDAO.getSettings().defaultObjLayer, SettingsDAO
				.getSettings().defaultTileLayer);
	}

	public RpgMap(String mapName, String objectLayer, String tileLayer) {
		this.mapName = mapName;
		this.map = ShadowStruggles.getInstance().getTiledMap(mapName);
		this.objectLayer = objectLayer;
		this.tileLayerString = tileLayer;
		if (map != null)
			this.tileLayer = (TiledMapTileLayer) map.getLayers().get(tileLayer);
	}

	public RpgMap(RpgMap rpgMap) {
		this.mapName = rpgMap.getMapName();
		this.map = rpgMap.getMap();
		this.objectLayer = rpgMap.getObjectLayer();
		this.tileLayer = rpgMap.getTileLayer();
	}

	@Override
	public boolean blocked(Mover mover, int x, int y) {
		return this.blocked(mover, x, y, false);
	}

	/**
	 * Checks if the given location is blocked.
	 * 
	 * @param triggerTouch
	 *            Indicates if touch events may be triggered if they block the
	 *            mover's path.
	 */
	public boolean blocked(Mover mover, int x, int y, boolean triggerTouch) {
		if (mover.getClass() == CharacterMover.class) {
			CharacterMover cMover = (CharacterMover) mover;

			if (cMover.getType() == CharacterMover.Type.NORMAL_CHARACTER) {
				// Basic validation: check if the coordinates are within the
				// valid bounds.
				if (x < 0 || x >= this.getWidthInTiles() || y < 0
						|| y >= this.getHeightInTiles()) {
					return true;
				}

				// Project the character into the desired location to calculate
				// possible collisions.
				Rectangle projectedCharacter = new Rectangle(x, y, cMover
						.getRectangle().getWidth(), cMover.getRectangle()
						.getHeight());

				// Check if there's an event touching the projected character,
				// triggering it if applicable.
				EventInGame touchedEvent = getTouchingEvent(projectedCharacter);
				if (touchedEvent != null) {
					if (triggerTouch
							&& touchedEvent.getTriggerType() == EventInGame.TriggerType.TOUCH)
						touchedEvent.trigger();
					if (touchedEvent.isCollidable())
						return true;
				}

				// The final check is against tile obstacles.
				return checkTileCollision(projectedCharacter);
			} else {
				throw new UnsupportedOperationException(
						"Invalid CharacterMover type");
			}
		} else {
			throw new UnsupportedOperationException("Invalid Mover object");
		}
	}

	/**
	 * Tries triggering an interactive event with the specified Rectangle,
	 * returning whether an event was found or not.
	 * 
	 * @param directionTurn
	 *            The direction which the event should face when triggered. If
	 *            null, the event won't change its direction. This is only
	 *            applicable for collidable events.
	 */
	public boolean triggerEvent(Rectangle charRect, WalkDirection directionTurn) {
		EventInGame touchedEvent = getTouchingEvent(charRect);

		if (touchedEvent != null) {
			if (touchedEvent.getTriggerType() == EventInGame.TriggerType.INTERACT) {
				if (!touchedEvent.isCollidable())
					directionTurn = null;
				touchedEvent.trigger(directionTurn);
				return true;
			}
		}

		return false;
	}

	/**
	 * Tries triggering a touch event with the specified CharacterMover,
	 * returning whether the event was found or not.
	 * 
	 * @param directionTurn
	 *            The direction which the event should face when triggered. If
	 *            null, the event won't change its direction. This is only
	 *            applicable for collidable events.
	 */
	public boolean touchEvent(Rectangle charRect, WalkDirection directionTurn) {
		EventInGame touchedEvent = getTouchingEvent(charRect);

		if (touchedEvent != null) {
			if (touchedEvent.getTriggerType() == EventInGame.TriggerType.TOUCH) {
				if (!touchedEvent.isCollidable())
					directionTurn = null;
				touchedEvent.trigger(directionTurn);
				return true;
			}
		}

		return false;
	}

	/**
	 * Executes the automatic events of the map in the current object layer.
	 */
	public void runAutomaticEvents() {
		Array<EventInGame> events = ShadowStruggles.getInstance().getProfile()
				.getEvents(mapName, objectLayer);
		for (EventInGame event : events) {
			if (event.getTriggerType() == EventInGame.TriggerType.AUTOMATIC) {
				event.trigger();
			}
		}
	}

	/**
	 * Returns an event touching the character specified by the charRect
	 * Rectangle object.
	 * 
	 * @param charRect
	 *            The character's rectangle to be checked against possible
	 *            events.
	 */
	public EventInGame getTouchingEvent(Rectangle charRect) {
		Array<EventInGame> events = ShadowStruggles.getInstance().getProfile()
				.getEvents(mapName, objectLayer);

		for (EventInGame event : events) {
			Rectangle rect = event.getCharacter().getMover().getRectangle();
			if (rect.overlaps(charRect)) {
				return event;
			}
		}

		return null;
	}

	/**
	 * Checks if a given character is colliding with a tile obstacle on the
	 * current tile layer.
	 * 
	 * @param charRect
	 *            The character's rectangle to be checked against possible
	 *            obstacles.
	 */
	public boolean checkTileCollision(Rectangle charRect) {
		int x = (int) charRect.x;
		int y = (int) charRect.y;

		// Check for tile obstacles in the tile itself and its adjacent
		// spots in 4 directions.
		Array<Vector2> adjacentTiles = new Array<Vector2>();
		adjacentTiles.add(new Vector2(x, y));

		if (x < this.getWidthInTiles())
			adjacentTiles.add(new Vector2(x + 1, y));
		if (x > 0)
			adjacentTiles.add(new Vector2(x - 1, y));
		if (y < this.getHeightInTiles() - 1)
			adjacentTiles.add(new Vector2(x, y + 1));
		if (y > 0)
			adjacentTiles.add(new Vector2(x, y - 1));

		for (Vector2 tilePos : adjacentTiles) {
			TiledMapTile tile = null;
			if (tileLayer.getCell((int) tilePos.x, (int) tilePos.y) != null) {
				tile = tileLayer.getCell((int) tilePos.x, (int) tilePos.y)
						.getTile();
			} else {
				return true;
			}

			if (tile.getProperties().containsKey(
					SettingsDAO.getSettings().collidableTile)) {
				Rectangle tileRect = new Rectangle(tilePos.x, tilePos.y, 1, 1);
				if (tileRect.overlaps(charRect)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

	@Override
	public int getWidthInTiles() {
		return tileLayer.getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return tileLayer.getHeight();
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		if (visited == null)
			visited = new boolean[getWidthInTiles()][getHeightInTiles()];
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

	/**
	 * Retrieves the TiledMap and the current layer using the mapName and
	 * tileLayer attributes.
	 */
	public void loadMap() {
		this.map = ShadowStruggles.getInstance().getTiledMap(mapName);
		this.tileLayer = (TiledMapTileLayer) map.getLayers().get(
				tileLayerString);
	}

	@Override
	public void write(Json json) {
		json.writeValue("mapName", mapName);
		json.writeValue("objectLayer", objectLayer);
		json.writeValue("tileLayerString", tileLayerString);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		json.readValue("mapName", String.class, jsonData);
		json.readValue("objectLayer", String.class, jsonData);
		json.readValue("tileLayerString", String.class, jsonData);

	}

}
