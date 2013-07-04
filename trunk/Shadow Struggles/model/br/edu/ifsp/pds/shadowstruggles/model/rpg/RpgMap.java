package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Mover;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.TileBasedMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * The map representation used by the pathfinder algorithm. It serves as a
 * wrapper for the tiled map itself.
 */
public class RpgMap implements TileBasedMap {
	private TiledMapTileLayer currentLayer = null;
	private boolean[][] visited = null;
	private TiledMap map;

	public RpgMap(TiledMap map) {
		this(map, (TiledMapTileLayer) map.getLayers().get(0));
	}

	public RpgMap(TiledMap map, String currentLayer) {
		this(map, (TiledMapTileLayer) map.getLayers().get(currentLayer));
	}

	public RpgMap(TiledMap map, TiledMapTileLayer currentLayer) {
		this.map = map;
		this.currentLayer = currentLayer;
	}

	@Override
	public int getWidthInTiles() {
		return this.currentLayer.getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return this.currentLayer.getHeight();
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

	// TODO: Corrigir verificação de bloqueio.
	@Override
	public boolean blocked(Mover mover, int x, int y) {
		// Maps from Tiled are interpreted with the traditional Cartesian
		// coordinate system (y increases upwards); thus, the y parameter must
		// be inverted. Also, y ranges from 0 to height - 1, thus the
		// subtraction.
		int invertY = this.getHeightInTiles() - y - 1;

		if (mover.getClass() == CharacterMover.class) {
			CharacterMover cMover = (CharacterMover) mover;

			if (cMover.getType() == CharacterMover.Type.NORMAL_CHARACTER) {

				// Basic validation: check if the coordinates are within the
				// valid
				// bounds.
				if (x < 0 || x >= this.getWidthInTiles() || y < 0
						|| y >= this.getHeightInTiles())
					return true;

				// Check for collidable objects.
				MapObjects objects = this.map.getLayers()
						.get("Camada de Objetos 1").getObjects();

				for (MapObject object : objects) {
					int tileSize = SettingsDAO.getSettings().tileSize;

					int objX = (Integer) object.getProperties().get("x")
							/ tileSize;
					int objY = (Integer) object.getProperties().get("y")
							/ tileSize;
					int width = Integer.parseInt((String) object
							.getProperties().get("width"));
					int height = Integer.parseInt((String) object
							.getProperties().get("height"));

					boolean collidable = object.getProperties().containsKey(
							"collidable");
					if ((x >= objX && x <= objX + width)
							&& (invertY >= objY && invertY <= objY + height)
							&& collidable)
						return true;
				}

				// Check for tile obstacles.
				TiledMapTile tile = this.currentLayer.getCell(x, invertY)
						.getTile();
				boolean obstacle = tile.getProperties().containsKey("obstacle");
				return obstacle;
			} else {
				throw new UnsupportedOperationException(
						"Invalid CharacterMover type");
			}
		} else {
			throw new UnsupportedOperationException("Invalid Mover object");
		}
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

	public TiledMapTileLayer getCurrentLayer() {
		return currentLayer;
	}

	public boolean visited(int x, int y) {
		if (visited == null)
			visited = new boolean[getWidthInTiles()][getHeightInTiles()];
		return visited[x][y];
	}

	public void setCurrentLayer(String currentLayer) {
		this.currentLayer = (TiledMapTileLayer) this.map.getLayers().get(
				currentLayer);
	}

	public TiledMap getMap() {
		return this.map;
	}
}
