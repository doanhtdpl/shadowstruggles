package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.AStarPathFinder;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.ManhattanHeuristic;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.PathFinder;
import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;
import br.edu.ifsp.pds.shadowstruggles.rpg.MyOrthogonalTiledMapRenderer;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

/**
 * The main screen of the RPG World. It gets the user input and sends the
 * command to the RPG Controller. Also, renders all the visual elements.
 */
public class RpgScreen extends BaseScreen implements InputProcessor {
	// The maximum range for the map region shown on screen. This is done so
	// that large screens can't see huge portions of the map at once, which
	// could be bad for certain level designs such as mazes.
	private static final int maxWidthRange = 960;
	private static final int maxHeightRange = 640;

	private RpgController rpgController;
	private MyOrthogonalTiledMapRenderer renderer;

	private PathFinder finder;
	private Path path;

	private boolean firstRender = true;
	private float unitScale = 1f;

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

		// The super constructor does some nasty stuff with the camera which we
		// don't want for RpgScreen, so let's create a new one.
		this.camera = new OrthographicCamera(this.width, this.height);
		this.camera.position.set(CAMERA_INITIAL_X, CAMERA_INITIAL_Y, 0);

		rpgController.setViewer(this);
		this.rpgController = rpgController;
		renderer = new MyOrthogonalTiledMapRenderer(rpgController.getModel()
				.getRpgMap(), unitScale, getBatch(), game, rpgController
				.getModel().getCharacter());
	}

	@Override
	public Array<Asset> textureRegionsToLoad() {
		Array<Asset> assets = renderer.textureRegionsToLoad();
		if (assets.size == 0)
			return null;
		return assets;
	}

	@Override
	public Array<Asset> mapsToLoad() {
		Array<Asset> assets = new Array<Asset>();
		assets.add(new Asset(rpgController.getModel().getRpgMap().getMapName()
				+ ".tmx", "rpg_maps"));
		return assets;
	}

	@Override
	public void initComponents() {
		rpgController.getModel().getRpgMap().loadMap();
		renderer.setMap(rpgController.getMap());
		finder = new AStarPathFinder(rpgController.getModel().getRpgMap(), 500,
				false, new ManhattanHeuristic(1));

		renderer.prepareCharacters();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;

		float viewportWidth = width * unitScale;
		float viewportHeight = height * unitScale;
		if (width >= maxWidthRange)
			viewportWidth = maxWidthRange * unitScale;
		if (height >= maxHeightRange)
			viewportHeight = maxHeightRange * unitScale;

		camera.setToOrtho(false, viewportWidth, viewportHeight);
	}

	/**
	 * Renders the all the graphic for this screen. It's called automatically
	 * and makes the main loop of the game.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(this);

		camera.update();

		renderer.setView(camera);
		renderer.render();
		renderer.renderGameObjects();
		if (game.getMode() == RunMode.DEBUG) {
			renderer.renderGameObjectsDebug();
			renderer.renderTilesDebug();
		}
		stage.act(delta);
		stage.draw();

		update(delta);

		if (firstRender && game.getScreen() == this) {
			this.rpgController.runAutomaticEvents();
			firstRender = false;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		game.getLoader().disposeAtlas();
		game.getLoader().disposeMaps();
	}

	public void update(float delta) {
		rpgController.updateModel();
		keyInput(delta);
	}

	public void keyInput(float delta) {
		if (renderer.getPlayerCharacter() != null) {
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
	}

	/**
	 * Method called by the Controller to update the character's visual
	 * representation (walking event).
	 */
	public void moveCharacter2d(WalkDirection direction) {
		if(renderer.getPlayerCharacter() != null)
			renderer.getPlayerCharacter().move(direction);
	}

	/**
	 * Method called by the Controller to update the character's visual
	 * representation (direction change event).
	 */
	public void turnCharacter2d(WalkDirection direction) {
		renderer.getPlayerCharacter().setWalking(false);
		renderer.getPlayerCharacter().setDirection(direction);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int[] currentPos = pixelsToTile((int) renderer.getPlayerCharacter()
				.getX(), Gdx.graphics.getHeight()
				- (int) renderer.getPlayerCharacter().getY() - 1);
		int[] destinationPos = pixelsToTile(screenX, screenY);

		path = finder.findPath(rpgController.getModel().getCharacter()
				.getMover(), currentPos[0], currentPos[1], destinationPos[0],
				destinationPos[1]);

		rpgController.moveCharacter(path, destinationPos);

		return true;
	}

	private int[] pixelsToTile(int x, int y) {
		int[] tile = { 0, 0 };
		int tileSize = SettingsDAO.getSettings().rpgTileSize;

		tile[0] = x / tileSize;
		tile[1] = y / tileSize;

		return tile;
	}

	public Character2D getCharacter2d() {
		return this.renderer.getPlayerCharacter();
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

	public RpgController getRpgController() {
		return this.rpgController;
	}

}
