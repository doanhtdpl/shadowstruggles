package br.edu.ifsp.pds.shadowstruggles.model.rpg;

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
		return false;
//		if (mover.getClass() == CharacterMover.class) {
//			CharacterMover cMover = (CharacterMover) mover;
//
//			if (cMover.getType() == CharacterMover.Type.NORMAL_CHARACTER) {
//
//				// Check for collidable objects.
//				MapObjects objects = this.currentLayer.getObjects();
//				for (MapObject object : objects) {
//					int objX = (Integer) object.getProperties().get("x");
//					int objY = (Integer) object.getProperties().get("y");
//					boolean passable = object.getProperties().containsKey(
//							"collidable");
//
//					if (objX == x && objY == y && !passable)
//						System.out
//								.println("Um objeto te bloqueia! "
//										+ object.getName() + " - " + objX
//										+ ", " + objY);
//					return (objX == x && objY == y && !passable);
//				}
//
//				// Check for tile obstacles.
//				TiledMapTile tile = this.currentLayer.getCell(x, y).getTile();
//				boolean obstacle = tile.getProperties().containsKey("obstacle");
//				if (obstacle)
//					System.out.println("Propriedade obstacle encontrada em: "
//							+ x + "," + y);
//				return obstacle;
//			} else {
//				throw new UnsupportedOperationException("Invalid Mover type");
//			}
//		} else {
//			throw new UnsupportedOperationException("Invalid Mover object");
//		}
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
