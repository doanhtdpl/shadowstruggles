package br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PathGenerator {
	private TiledMap tiledMap;
	private GameMap map = new GameMap();
	private PathFinder finder;
	private Path path;
	private Vector2 origin;
	private Vector2 target;
	
	
	public PathGenerator(TiledMap tiledMap,Vector2 origin, Vector2 target) {	
		this.tiledMap=tiledMap;
		this.target=target;
		this.origin=origin;
	}
	
	
	public Array<Vector2> generatePath(){
				
		return null;
	}
	
	private TiledMapTileLayer getLayer(){
		return (TiledMapTileLayer) tiledMap.getLayers().get(0);
	}	
	
	
	
	public class GameMap implements TileBasedMap{
		private boolean[][] visited = new boolean[getWidthInTiles()][getHeightInTiles()];
		
		public void clearVisited() {
			for (int x=0;x<getWidthInTiles();x++) {
				for (int y=0;y<getHeightInTiles();y++) {
					visited[x][y] = false;
				}
			}
		}
		
		public boolean visited(int x, int y) {
			return visited[x][y];
		}
		@Override
		public int getWidthInTiles() {			
			return getLayer().getWidth();
		}

		@Override
		public int getHeightInTiles() {			
			return getLayer().getHeight();
		}

		@Override
		public void pathFinderVisited(int x, int y) {			
			visited[x][y]=true;
		}

		@Override
		public boolean blocked(Mover mover, int x, int y) {			
			return true;
		}

		@Override
		public float getCost(Mover mover, int sx, int sy, int tx, int ty) {			
			return 1;
		}}
	public class UnitMover implements Mover{}
}
