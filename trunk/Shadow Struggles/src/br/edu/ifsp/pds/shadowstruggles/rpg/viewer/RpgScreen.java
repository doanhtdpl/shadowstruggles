package br.edu.ifsp.pds.shadowstruggles.rpg.viewer;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;



public class RpgScreen extends BaseScreen{
	private TiledMap map;
	
	public RpgScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		map = new TmxMapLoader(new InternalFileHandleResolver()).load("data/images/maps/example/map.tmx");
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		
	}
	
	@Override
	public void render(float delta) {		
		super.render(delta);
		float unitScale = 1 / 256f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(map, unitScale);
		renderer.setView(camera);
		renderer.render();
	}

	
	
	
		
	
}
