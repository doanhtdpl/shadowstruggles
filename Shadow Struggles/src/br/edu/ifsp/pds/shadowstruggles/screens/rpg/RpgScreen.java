package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class RpgScreen extends BaseScreen {

	/**
	 * The main screen of the RPG World. It gets the user input and 
	 * sends the command to the RPG Controller.
	 */
	public final static int TILE_SIZE = 32;	
	private ShapeRenderer shapeRenderer;
	private RpgController rpgController;
//	private boolean readyToWalk = true;
//	private float walked = 0;
//	private WalkDirection direction = null;
	private Character2D character2d;
	

	private ArrayList<WalkDirection> directionBuffer = new ArrayList<WalkDirection>();

	public RpgScreen(ShadowStruggles game, Controller controller,
			RpgController rpgController, RpgPlatform rpgPlatform) {
		super(game, controller);		
		rpgController.setViewer(this);		
		this.rpgController=rpgController;
		shapeRenderer = new ShapeRenderer();
		
		character2d = new Character2D("Link",game);
		
		//testes com character2d
		character2d.setX(10);
		character2d.setY(10);
		
	}
	
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		float unitScale = 1 / 256f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(
				rpgController.getMap(), unitScale);
		renderer.setView(camera);
		camera.setToOrtho(false, 3.75f, 2.5f);
		renderer.render();
		update(delta);
		character2d.render();
//		shapeRenderer.begin(ShapeType.Filled);
//		if (walked + platform.getCharacter().getWalkSpeed() >= tileSize) {
//			readyToWalk = true;
//			walked = 0;
//			direction = null;
//		}
//
//		if (readyToWalk && directionBuffer.size() > 0) {
//			moveCharacter(directionBuffer.get(0));
//			directionBuffer.remove(0);
//		}
//
//		if (readyToWalk) {
//			shapeRenderer.rect(character.getTileX() * tileSize,
//					character.getTileY() * tileSize, tileSize, tileSize);
//		} else {
//
//			walked = walked + character.getWalkSpeed();
//			
//			switch (direction) {
//			case WALK_UP:
//				shapeRenderer.rect(character.getTileX() * tileSize,
//						character.getTileY() * tileSize + walked - tileSize,
//						tileSize, tileSize);
//				break;
//			case WALK_DOWN:
//				shapeRenderer.rect(character.getTileX() * tileSize,
//						character.getTileY() * tileSize - walked + tileSize,
//						tileSize, tileSize);
//				break;
//			case WALK_LEFT:
//				shapeRenderer.rect(character.getTileX() * tileSize - walked
//						+ tileSize, character.getTileY() * tileSize, tileSize,
//						tileSize);
//				break;
//			case WALK_RIGHT:
//				shapeRenderer.rect(character.getTileX() * tileSize + walked
//						- tileSize, character.getTileY() * tileSize, tileSize,
//						tileSize);
//				break;
//			default:
//				break;
//			}
//		}
//		shapeRenderer.end();
	}

	public void update(float delta) {
		keyInput(delta);
	}

	public void keyInput(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//			moveCharacter(WalkDirection.WALK_LEFT);			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//			moveCharacter(WalkDirection.WALK_RIGHT);			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//			moveCharacter(WalkDirection.WALK_UP);			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//			moveCharacter(WalkDirection.WALK_DOWN);
			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//			moveCharacter(WalkDirection.WALK_UP, 3);
			
		}
	}

//	public void moveCharacter(WalkDirection direction) {
//		if (readyToWalk && character.walk(direction, map)) {
//			readyToWalk = false;
//			this.direction = direction;
//		}
//	}

//	public void moveCharacter(WalkDirection direction, int times) {
//		for (int i = 0; i < times; i++)
//			if (readyToWalk) // Remover condição
//				directionBuffer.add(direction);
//	}

}
