package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.AStarPathFinder;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.ManhattanHeuristic;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Path;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.PathFinder;
import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgRenderer;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

/**
 * The main screen of the RPG World. It gets the user input and sends the
 * command to the RPG Controller.
 */
public class RpgScreen extends BaseScreen implements InputProcessor {
	// The maximum range for the map region shown on screen. This is done so
	// that larger screens can't see huge portions of the map at once, which
	// could be bad for certain level designs such as mazes.
	private static final int maxWidthRange = 960;
	private static final int maxHeightRange = 640;

	private static final int tileSize = SettingsDAO.getSettings().rpgTileSize;

	private RpgController rpgController;
	private RpgMap map;
	private OrthographicCamera stageCamera;
	private RpgRenderer renderer;
	private InputMultiplexer inputSources;
	private Messenger messenger;

	private PathFinder finder;
	private Path path;

	private boolean firstRender = true;
	private float unitScale = 1f;
	// Keeps track of the camera position and dimensions, in logical units.
	private Rectangle cameraRect;

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

		// The super constructor does some nasty stuff to the camera which we
		// don't want for RpgScreen, so let's create a new one.
		this.camera = new OrthographicCamera(this.width, this.height);
		this.camera.position.set(CAMERA_INITIAL_X, CAMERA_INITIAL_Y, 0);
		this.cameraRect = new Rectangle();

		this.stageCamera = new OrthographicCamera(this.width, this.height);
		this.stageCamera.position.set(CAMERA_INITIAL_X, CAMERA_INITIAL_Y, 0);
		this.stage.setCamera(stageCamera);

		rpgController.setViewer(this);
		this.rpgController = rpgController;
		this.map = rpgController.getModel().getRpgMap();
		renderer = new RpgRenderer(map, unitScale, getBatch(), game,
				rpgController.getModel().getCharacter());

		this.inputSources = new InputMultiplexer();
		inputSources.addProcessor(stage);
		inputSources.addProcessor(this);
	}

	@Override
	public Array<Asset> textureRegionsToLoad() {
		Array<Asset> assets = renderer.textureRegionsToLoad();
		assets.addAll(new Asset[] { new Asset("red_bar.png", "game_ui_images"),
				new Asset("blue_bar.png", "game_ui_images"),
				new Asset("blue2_bar.png", "game_ui_images"),
				new Asset("purple_bar.png", "game_ui_images"),
				new Asset("green_bar.png", "game_ui_images"),
				new Asset("yellow_bar.png", "game_ui_images"),
				new Asset("rpg_balloon.png", "game_ui_images") });
		return assets;
	}

	@Override
	public Array<Asset> mapsToLoad() {
		Array<Asset> assets = new Array<Asset>();
		assets.add(new Asset(map.getMapName() + ".tmx", "rpg_maps"));
		return assets;
	}

	@Override
	public void initComponents() {
		if (!game.getAudio().getMusicName().equals("m3")) {
			game.getAudio().stop();
			game.getAudio().setMusic("m3");
		}

		map.loadMap();
		renderer.setMap(rpgController.getMap());
		finder = new AStarPathFinder(map, 500, false, new ManhattanHeuristic(1));

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

		// Keep the dimensions for the stage elements absolute.
		stageCamera.setToOrtho(false, 960, 640);
	}

	/**
	 * Renders the all the graphic for this screen. It's called automatically
	 * and makes the main loop of the game.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(inputSources);

		camera.update();
		cameraRect.set((camera.position.x - camera.viewportWidth / 2)
				/ tileSize, (camera.position.y - camera.viewportHeight / 2)
				/ tileSize, camera.viewportWidth / tileSize,
				camera.viewportHeight / tileSize);
		checkCameraPosition();

		renderer.setView(camera);
		renderer.render();
		renderer.renderGameObjects();
		if (game.getMode() == RunMode.DEBUG) {
			renderer.renderTilesDebug(false);
			renderer.renderGameObjectsDebug();
		}
		stage.act(delta);
		stage.draw();

		update(delta);

		if (firstRender && game.getScreen() == this) {
			this.rpgController.runAutomaticEvents();
			firstRender = false;
		}

		if (game.getMode() == RunMode.DEBUG)
			Table.drawDebug(stage);
	}

	/**
	 * Checks if the character is out of the player's vision and moves the
	 * camera appropriately.
	 */
	private void checkCameraPosition() {
		Character2D playerChar = renderer.getPlayerCharacter();
		if (playerChar != null) {
			Rectangle playerCharRect = playerChar.getCharModel().getMover()
					.getRectangle();

			if (cameraRect.x != playerCharRect.x
					|| cameraRect.y != playerCharRect.y) {
				if ((playerCharRect.x < cameraRect.x && cameraRect.x > 0)
						|| (playerCharRect.x > cameraRect.x && cameraRect.x < map
								.getWidthInTiles() - cameraRect.width))
					camera.translate(playerCharRect.x - cameraRect.x, 0);
				if ((playerCharRect.y < cameraRect.y && cameraRect.y > 0)
						|| (playerCharRect.y > cameraRect.y && cameraRect.y < map
								.getHeightInTiles() - cameraRect.height))
					camera.translate(0, playerCharRect.y - cameraRect.y);
			}
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
		// Key input can sometimes be detected during intervals between screen
		// transitions, so we need to make sure that the conditions are valid
		// before processing it.
		if (renderer.getPlayerCharacter() != null && game.getScreen() == this) {
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
				if (messenger != null)
					messenger.advanceMessage();
				else
					rpgController.triggerEvent();
			}
		}
	}

	/**
	 * Method called by the Controller to update the character's visual
	 * representation (walking event).
	 */
	public void moveCharacter2d(WalkDirection direction) {
		Character2D playerChar = renderer.getPlayerCharacter();
		if (playerChar != null) {
			playerChar.move(direction);
		}
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
		if (screenX < 60 && screenY < 60) {
			game.setScreenWithTransition(new RpgMenu(this));
		} else {
			int[] currentPos = pixelsToTile((int) renderer.getPlayerCharacter()
					.getX(), (int) renderer.getPlayerCharacter().getY());
			int[] destinationPos = pixelsToTile(screenX, height - screenY);

			path = finder.findPath(rpgController.getModel().getCharacter()
					.getMover(), currentPos[0], currentPos[1],
					destinationPos[0], destinationPos[1]);

			rpgController.moveCharacter(path, destinationPos);
		}
		return true;
	}

	private static int[] pixelsToTile(int x, int y) {
		int[] tile = { 0, 0 };

		tile[0] = x / tileSize;
		tile[1] = y / tileSize;

		return tile;
	}

	public Character2D getCharacter2d() {
		return this.renderer.getPlayerCharacter();
	}

	public InputMultiplexer getInputSources() {
		return this.inputSources;
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

	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	public OrthographicCamera getStageCamera() {
		return this.stageCamera;
	}
}
