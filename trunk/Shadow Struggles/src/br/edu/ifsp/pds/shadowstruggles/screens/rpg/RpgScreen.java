package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.test.CharacterTest;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;



public class RpgScreen extends BaseScreen{
	private TiledMap map;
	private Character character;
	
	public RpgScreen(ShadowStruggles game, Controller controller, RpgPlatform rpgPlatform) {
		super(game, controller);
		this.map = rpgPlatform.getMap();
		character = new Character(game.getProfile());
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
		update(delta);
	}
	
	public void update(float delta){
		keyInput(delta);
	} 

	public void keyInput(float delta){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			moveCharacter(WalkDirection.WALK_LEFT);
			System.out.println("LEFT");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			moveCharacter(WalkDirection.WALK_RIGHT);
			System.out.println("RIGHT");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			moveCharacter(WalkDirection.WALK_UP);
			System.out.println("UP");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			moveCharacter(WalkDirection.WALK_DOWN);
			System.out.println("DOWN");
		}
	}
	
	public void moveCharacter (WalkDirection direction){
		character.walk(direction, map);
		
		switch (direction) {
		case WALK_UP:
			
			break;
		case WALK_DOWN:
			
			break;
		case WALK_LEFT:
			
			break;
		case WALK_RIGHT:
			
			break;
		default:
			break;
		}
				
	}
		
	
}
