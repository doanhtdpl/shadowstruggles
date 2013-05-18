package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import java.util.ArrayList;

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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RpgScreen extends BaseScreen {
	private TiledMap map;
	private Character character;
	private ShapeRenderer shapeRenderer;

	private boolean readyToWalk = true;
	private float walked = 0;
	private WalkDirection direction = null;
	private int tileSize = 33;
	private ArrayList<WalkDirection> directionBuffer = new ArrayList<WalkDirection>();

	public RpgScreen(ShadowStruggles game, Controller controller,
			RpgPlatform rpgPlatform) {
		super(game, controller);
		this.map = rpgPlatform.getMap();
		character = new Character(game.getProfile());
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		float unitScale = 1 / 256f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(
				map, unitScale);
		renderer.setView(camera);
		camera.setToOrtho(false, 3.75f, 2.5f);
		renderer.render();
		update(delta);

		shapeRenderer.begin(ShapeType.Filled);

		if (walked + character.getWalkSpeed() >= tileSize) {
			readyToWalk = true;
			walked = 0;
			direction = null;
		}

		if (readyToWalk && directionBuffer.size() > 0) {
			moveCharacter(directionBuffer.get(0));
			directionBuffer.remove(0);
		}

		if (readyToWalk) {
			shapeRenderer.rect(character.getTileX() * tileSize,
					character.getTileY() * tileSize, tileSize, tileSize);
		} else {

			walked = walked + character.getWalkSpeed();
			// System.out.println(":\nReady to walk: " + readyToWalk
			// + "\nWalked: " + walked + "\nDirection: " + direction
			// + "\nCharacter speed: " + character.getWalkSpeed() + "\n");
			switch (direction) {
			case WALK_UP:
				shapeRenderer.rect(character.getTileX() * tileSize,
						character.getTileY() * tileSize + walked - tileSize,
						tileSize, tileSize);
				break;
			case WALK_DOWN:
				shapeRenderer.rect(character.getTileX() * tileSize,
						character.getTileY() * tileSize - walked + tileSize,
						tileSize, tileSize);
				break;
			case WALK_LEFT:
				shapeRenderer.rect(character.getTileX() * tileSize - walked
						+ tileSize, character.getTileY() * tileSize, tileSize,
						tileSize);
				break;
			case WALK_RIGHT:
				shapeRenderer.rect(character.getTileX() * tileSize + walked
						- tileSize, character.getTileY() * tileSize, tileSize,
						tileSize);
				break;
			default:
				break;
			}
		}
		shapeRenderer.end();

	}

	public void update(float delta) {
		keyInput(delta);
	}

	public void keyInput(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			moveCharacter(WalkDirection.WALK_LEFT);
			// System.out.println("Left:\nReady to walk: " + readyToWalk +
			// "\nWalked: "
			// + walked + "\nDirection: " + direction + "\n\n");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			moveCharacter(WalkDirection.WALK_RIGHT);
			// System.out.println("Right:\nReady to walk: " + readyToWalk +
			// "\nWalked: "
			// + walked + "\nDirection: " + direction + "\n\n");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			moveCharacter(WalkDirection.WALK_UP);
			// System.out.println("Up:\nReady to walk: " + readyToWalk +
			// "\nWalked: "
			// + walked + "\nDirection: " + direction + "\n\n");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			moveCharacter(WalkDirection.WALK_DOWN);
			// System.out.println("Down:\nReady to walk: " + readyToWalk +
			// "\nWalked: "
			// + walked + "\nDirection: " + direction + "\n\n");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			moveCharacter(WalkDirection.WALK_UP, 3);
			// System.out.println("Down:\nReady to walk: " + readyToWalk +
			// "\nWalked: "
			// + walked + "\nDirection: " + direction + "\n\n");
		}
	}

	public void moveCharacter(WalkDirection direction) {
		if (readyToWalk && character.walk(direction, map)) {
			readyToWalk = false;
			this.direction = direction;

			/*
			 * switch (direction) { case WALK_UP:
			 * 
			 * break; case WALK_DOWN:
			 * 
			 * break; case WALK_LEFT:
			 * 
			 * break; case WALK_RIGHT:
			 * 
			 * break; default: break; }
			 * System.out.println("moveCharacter:\nReady to walk: " +
			 * readyToWalk + "\nWalked: " + walked + "\nDirection: " + direction
			 * + "\n\n");
			 */
		}
	}

	public void moveCharacter(WalkDirection direction, int times) {
		for (int i = 0; i < times; i++)
			if (readyToWalk) // Remover condição
				directionBuffer.add(direction);
	}

}
