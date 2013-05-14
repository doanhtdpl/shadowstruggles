package br.edu.ifsp.pds.shadowstruggles.rpg.viewer;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		float unitScale = 1 / 256f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(map, unitScale);
		renderer.setView(camera);
		camera.setToOrtho(false, 3.75f, 2.5f);
		renderer.render();
	}

	
	
	
		
	
}
