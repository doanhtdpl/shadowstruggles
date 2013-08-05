package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.data.dao.EventDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Mover;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.TileBasedMap;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
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
	private boolean[][] visited = null;
	private TiledMap map;

	/**
	 * The current object layer.
	 */
	private MapLayer currentLayer = null;

	public RpgMap(TiledMap map) {
		this(map, "default-objects");
	}

	public RpgMap(TiledMap map, String currentLayer) {
		this(map, map.getLayers().get(currentLayer));
	}

	public RpgMap(TiledMap map, MapLayer currentLayer) {
		this.map = map;
		this.currentLayer = currentLayer;
	}

	@Override
	public int getWidthInTiles() {
		return ((TiledMapTileLayer) map.getLayers().get("tiles")).getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return ((TiledMapTileLayer) map.getLayers().get("tiles")).getHeight();
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
	 * Executes the automatic events of the map in the current object layer and
	 * updates them.
	 */
	public void runAutomaticEvents() {
		MapObjects objects = currentLayer.getObjects();

		for (MapObject object : objects) {
			int id = Integer
					.parseInt((String) object.getProperties().get("id"));

			Event event = EventDAO.getEvent(id);
			if (event != null) {
				if (event.getTriggerType() == Event.TriggerType.AUTOMATIC) {
					event.trigger();
					EventDAO.editEvent(event.getId(), event);
				}
			}
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
		// Maps from Tiled are interpreted with the traditional Cartesian
		// coordinate system (y increases upwards); thus, the y parameter must
		// be inverted. Also, y ranges from 0 to height - 1, thus the
		// subtraction.
		int invertY = this.getHeightInTiles() - y - 1;

		if (mover.getClass() == CharacterMover.class) {
			CharacterMover cMover = (CharacterMover) mover;

			if (cMover.getType() == CharacterMover.Type.NORMAL_CHARACTER) {

				// Basic validation: check if the coordinates are within the
				// valid bounds.
				if (x < 0 || x >= this.getWidthInTiles() || y < 0
						|| y >= this.getHeightInTiles())
					return true;

				// Project the character into the desired location to calculate
				// possible collisions.
				Rectangle projectedCharacter = new Rectangle(x, invertY, cMover
						.getRectangle().getWidth(), cMover.getRectangle()
						.getHeight()/2);

				// Check for collidable objects.
				MapObjects objects = currentLayer.getObjects();

				for (MapObject object : objects) {
					int tileSize = SettingsDAO.getSettings().tileSize;

					int objX = (Integer) object.getProperties().get("x")
							/ tileSize;
					int objY = (Integer) object.getProperties().get("y")
							/ tileSize;
					float width = Float.parseFloat((String) object
							.getProperties().get("width"));
					float height = Float.parseFloat((String) object
							.getProperties().get("height"));
					Rectangle rect = new Rectangle(objX, objY, width, height);

					boolean collidable = object.getProperties().containsKey(
							"collidable");

					if (rect.overlaps(projectedCharacter)) {
						int id = Integer.parseInt((String) object
								.getProperties().get("id"));
						Event event = EventDAO.getEvent(id);
						if (triggerTouch
								&& event.getTriggerType() == Event.TriggerType.TOUCH) {
							event.trigger();
							EventDAO.editEvent(id, event);
						}

						if (collidable)
							return true;
					}
				}

				// Check for tile obstacles in the tile itself and its adjacent
				// spots in 4 directions.
				TiledMapTileLayer tileLayer = (TiledMapTileLayer) map
						.getLayers().get("tiles");
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

					if (tile.getProperties().containsKey("obstacle")) {
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
		// Maps from Tiled are interpreted with the traditional Cartesian
		// coordinate system (y increases upwards); thus, the y parameter must
		// be inverted. Also, y ranges from 0 to height - 1, thus the
		// subtraction.
		int invertY = this.getHeightInTiles() - y - 1;
		MapObjects objects = currentLayer.getObjects();

		for (MapObject object : objects) {
			int tileSize = SettingsDAO.getSettings().tileSize;

			int objX = (Integer) object.getProperties().get("x") / tileSize;
			int objY = (Integer) object.getProperties().get("y") / tileSize;
			float width = Float.parseFloat((String) object.getProperties().get(
					"width"));
			float height = Float.parseFloat((String) object.getProperties()
					.get("height"));
			Rectangle rect = new Rectangle(objX, objY, width, height);

			if (rect.overlaps(new Rectangle(x, invertY,
					cMover.getRectangle().width, cMover.getRectangle().height))) {
				int id = Integer.parseInt((String) object.getProperties().get(
						"id"));
				Event event = EventDAO.getEvent(id);

				if (event.getTriggerType() == Event.TriggerType.INTERACT) {
					// Only attempt to change direction if object is collidable.
					boolean collidable = object.getProperties().containsKey("collidable");
					if(!collidable)
						directionTurn = null;
					event.trigger(directionTurn);
					EventDAO.editEvent(id, event);
					return true;
				}
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
		// Maps from Tiled are interpreted with the traditional Cartesian
		// coordinate system (y increases upwards); thus, the y parameter must
		// be inverted. Also, y ranges from 0 to height - 1, thus the
		// subtraction.
		int invertY = this.getHeightInTiles() - y - 1;
		MapObjects objects = currentLayer.getObjects();

		for (MapObject object : objects) {
			int tileSize = SettingsDAO.getSettings().tileSize;

			int objX = (Integer) object.getProperties().get("x") / tileSize;
			int objY = (Integer) object.getProperties().get("y") / tileSize;
			float width = Float.parseFloat((String) object.getProperties().get(
					"width"));
			float height = Float.parseFloat((String) object.getProperties()
					.get("height"));
			Rectangle rect = new Rectangle(objX, objY, width, height);

			if (rect.overlaps(new Rectangle(x, invertY,
					cMover.getRectangle().width, cMover.getRectangle().height))) {
				int id = Integer.parseInt((String) object.getProperties().get(
						"id"));
				Event event = EventDAO.getEvent(id);

				if (event.getTriggerType() == Event.TriggerType.TOUCH) {
					// Only attempt to change direction if object is collidable.
					boolean collidable = object.getProperties().containsKey("collidable");
					if(!collidable)
						directionTurn = null;
					event.trigger(directionTurn);
					EventDAO.editEvent(id, event);
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

	public MapLayer getCurrentLayer() {
		return currentLayer;
	}

	public boolean visited(int x, int y) {
		if (visited == null)
			visited = new boolean[getWidthInTiles()][getHeightInTiles()];
		return visited[x][y];
	}

	public void setCurrentLayer(String currentLayer) {
		this.currentLayer = this.map.getLayers().get(currentLayer);
	}

	public TiledMap getMap() {
		return this.map;
	}
}
