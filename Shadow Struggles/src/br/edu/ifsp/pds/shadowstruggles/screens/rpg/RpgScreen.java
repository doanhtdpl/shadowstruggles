package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.CharacterMover;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.AStarPathFinder;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.ManhattanHeuristic;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.PathFinder;
import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The main screen of the RPG World. It gets the user input and sends the
 * command to the RPG Controller. Also, renders all the visual elements.
 */
public class RpgScreen extends BaseScreen implements InputProcessor {

	public final static int TILE_SIZE = 32;

	// private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private RpgController rpgController;
	private Character2D character2d;

	private PathFinder finder;
	private Path path;

	/**
	 * The constructor initializes the objects and defines itself as the
	 * controller's viewer.
	 * 
	 * @param game
	 *            The ShadowStruggles instance, used for overview.
	 * @param controller
	 *            The game controller to handle screen interactions.
	 * @param rpgController
	 *            The RPG mechanic controller.
	 */
	public RpgScreen(ShadowStruggles game, Controller controller,
			RpgController rpgController) {
		super(game, controller);
		rpgController.setViewer(this);
		this.rpgController = rpgController;
		// shapeRenderer = new ShapeRenderer();
		character2d = new Character2D(rpgController, game);

		finder = new AStarPathFinder(rpgController.getModel().getRpgMap(), 500,
				false, new ManhattanHeuristic(1));

		// testes com character2d
		this.stage.addActor(character2d);

	}

	/**
	 * Renders the all the graphic for this screen. It's called automatically
	 * and makes the main loop of the game.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		// Gdx.gl.glClearColor(1, 1, 1, 1);
		// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(this);

		float unitScale = 1 / 256f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(
				rpgController.getMap(), unitScale);
		renderer.setView(camera);
		camera.setToOrtho(false, 3.75f, 2.5f);
		renderer.render();
		update(delta);
		character2d.create();
		character2d.render();

		batch = new SpriteBatch();
		batch.begin();
		batch.draw(character2d.getCurrentFrame(), character2d.getX(),
				character2d.getY());
		batch.end();

		// shapeRenderer.begin(ShapeType.Filled);
		// if (walked + platform.getCharacter().getWalkSpeed() >= tileSize) {
		// readyToWalk = true;
		// walked = 0;
		// direction = null;
		// }
		//
		// if (readyToWalk && directionBuffer.size() > 0) {
		// moveCharacter(directionBuffer.get(0));
		// directionBuffer.remove(0);
		// }
		//
		// if (readyToWalk) {
		// shapeRenderer.rect(character.getTileX() * tileSize,
		// character.getTileY() * tileSize, tileSize, tileSize);
		// } else {
		//
		// walked = walked + character.getWalkSpeed();
		//
		// switch (direction) {
		// case WALK_UP:
		// shapeRenderer.rect(character.getTileX() * tileSize,
		// character.getTileY() * tileSize + walked - tileSize,
		// tileSize, tileSize);
		// break;
		// case WALK_DOWN:
		// shapeRenderer.rect(character.getTileX() * tileSize,
		// character.getTileY() * tileSize - walked + tileSize,
		// tileSize, tileSize);
		// break;
		// case WALK_LEFT:
		// shapeRenderer.rect(character.getTileX() * tileSize - walked
		// + tileSize, character.getTileY() * tileSize, tileSize,
		// tileSize);
		// break;
		// case WALK_RIGHT:
		// shapeRenderer.rect(character.getTileX() * tileSize + walked
		// - tileSize, character.getTileY() * tileSize, tileSize,
		// tileSize);
		// break;
		// default:
		// break;
		// }
		// }
		// shapeRenderer.end();
	}

	public void update(float delta) {
		rpgController.updateModel();
		keyInput(delta);
	}

	public void keyInput(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			rpgController.moveCharacter(WalkDirection.WALK_LEFT);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			rpgController.moveCharacter(WalkDirection.WALK_RIGHT);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			rpgController.moveCharacter(WalkDirection.WALK_UP);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			rpgController.moveCharacter(WalkDirection.WALK_DOWN);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			System.out.println("Character in: "
					+ rpgController.getModel().getCharacter().getTileX() + ","
					+ rpgController.getModel().getCharacter().getTileY());
		}
	}

	/**
	 * Method called by the Controller to update the character's visual
	 * representation.
	 */
	public void moveCharacter2D(WalkDirection direction) {
		character2d.move(direction);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int[] currentPos = pixelsToTile((int) character2d.getX(),
				Gdx.graphics.getHeight() - (int) character2d.getY() - 1);
		int[] destinationPos = pixelsToTile(screenX, screenY);

		path = finder.findPath(new CharacterMover(
				CharacterMover.Type.NORMAL_CHARACTER), currentPos[0],
				currentPos[1], destinationPos[0], destinationPos[1]);

		if (path != null)
			rpgController.moveCharacter(path);

		return true;
	}

	private int[] pixelsToTile(int x, int y) {
		int[] tile = { 0, 0 };

		tile[0] = x / TILE_SIZE;
		tile[1] = y / TILE_SIZE;

		return tile;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	// public void moveCharacter(WalkDirection direction) {
	// if (readyToWalk && character.walk(direction, map)) {
	// readyToWalk = false;
	// this.direction = direction;
	// }
	// }

	// public void moveCharacter(WalkDirection direction, int times) {
	// for (int i = 0; i < times; i++)
	// if (readyToWalk) // Remover condição
	// directionBuffer.add(direction);
	// }

}
