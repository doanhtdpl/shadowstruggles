package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
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
	private SpriteBatch batch;
	private RpgController rpgController;
	private Character2D character2d;
	private OrthogonalTiledMapRenderer renderer;

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
		character2d = new Character2D(rpgController.getModel().getCharacter(),
				game);

		finder = new AStarPathFinder(rpgController.getModel().getRpgMap(), 500,
				false, new ManhattanHeuristic(1));

		batch = new SpriteBatch();
		float unitScale = 1 / 256f;
		renderer = new OrthogonalTiledMapRenderer(rpgController.getMap(),
				unitScale);

		this.stage.addActor(character2d);

		this.rpgController.runAutomaticEvents();
	}

	/**
	 * Renders the all the graphic for this screen. It's called automatically
	 * and makes the main loop of the game.
	 */
	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.input.setInputProcessor(this);

		renderer.setView(camera);
		camera.setToOrtho(false, 3.75f, 2.5f);
		renderer.render();
		update(delta);
		character2d.create();
		character2d.render();

		batch.begin();
		batch.draw(character2d.getCurrentFrame(), character2d.getX(),
				character2d.getY());
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		character2d.dispose();
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
		if (Gdx.input.isKeyPressed(Input.Keys.Z)
				|| Gdx.input.isKeyPressed(Input.Keys.ENTER)
				|| Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			rpgController.triggerEvent();
		}
	}

	/**
	 * Method called by the Controller to update the character's visual
	 * representation (walking event).
	 */
	public void moveCharacter2d(WalkDirection direction) {
		character2d.move(direction);
	}

	/**
	 * Method called by the Controller to update the character's visual
	 * representation (direction change event).
	 */
	public void turnCharacter2d(WalkDirection direction) {
		character2d.setWalking(false);
		character2d.setDirection(direction);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int[] currentPos = pixelsToTile((int) character2d.getX(),
				Gdx.graphics.getHeight() - (int) character2d.getY() - 1);
		int[] destinationPos = pixelsToTile(screenX, screenY);

		path = finder.findPath(rpgController.getModel().getCharacter()
				.getMover(), currentPos[0], currentPos[1], destinationPos[0],
				destinationPos[1]);

		rpgController.moveCharacter(path, destinationPos);

		return true;
	}

	private int[] pixelsToTile(int x, int y) {
		int[] tile = { 0, 0 };
		int tileSize = SettingsDAO.getSettings().tileSize;

		tile[0] = x / tileSize;
		tile[1] = y / tileSize;

		return tile;
	}

	public Character2D getCharacter2d() {
		return this.character2d;
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

}
