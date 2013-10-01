package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public abstract class BaseScreen implements Screen {
	protected static final int BACKGROUND_Y = 160;
	public static final int CAMERA_INITIAL_X = 480;
	public static final int CAMERA_INITIAL_Y = 320;

	protected final ShadowStruggles game;
	protected MyStage stage;
	protected BitmapFont font;
	protected Controller controller;
	protected int height;
	protected int width;
	protected int screenWidth;
	protected int screenHeight;
	protected OrthographicCamera camera;

	public BaseScreen(ShadowStruggles game, Controller controller) {
		this.game = game;
		this.controller = controller;
		this.controller.setCurrentscreen(this);
		this.stage = new MyStage(0, 0, true, game.getBatch());
		this.camera = new OrthographicCamera(this.width, this.height);
		this.camera.position.set(CAMERA_INITIAL_X, CAMERA_INITIAL_Y, 0);
		this.camera.zoom = ((float) 960 / (float) width);
		this.camera.position.x = CAMERA_INITIAL_X;
		this.stage.setCamera(camera);
	}

	public BaseScreen(ShadowStruggles game) {
		this.game = game;
		this.stage = new MyStage(0, 0, true, game.getBatch());
		this.camera = new OrthographicCamera(this.width, this.height);
		this.camera.position.set(CAMERA_INITIAL_X, CAMERA_INITIAL_Y, 0);
		this.camera.zoom = ((float) 960 / (float) width);
		this.camera.position.x = CAMERA_INITIAL_X;
		this.stage.setCamera(camera);
	}

	/**
	 * Initialize the graphic components of this screen.
	 */
	public void initComponents() {

	}

	/**
	 * Specifies the particular texture regions which must be loaded for this screen.
	 * It returns null by default.
	 */
	public Array<Asset> textureRegionsToLoad() {
		Array<Asset> assets = null;
		return assets;
	}
	
	/**
	 * Specifies the particular textures which must be loaded for this screen.
	 * It returns null by default.
	 */
	public Array<Asset> texturesToLoad() {
		Array<Asset> assets = null;
		return assets;
	}
	
	/**
	 * Specifies the particular sounds which must be loaded for this screen.
	 * It returns null by default.
	 */
	public Array<Asset> soundsToLoad() {
		Array<Asset> assets = null;
		return assets;
	}
	
	/**
	 * Specifies the particular maps which must be loaded for this screen.
	 * It returns null by default.
	 */
	public Array<Asset> mapsToLoad() {
		Array<Asset> assets = null;
		return assets;
	}

	public Skin getSkin() {
		return game.getSkin();
	}

	public SpriteBatch getBatch() {
		return game.getBatch();
	}

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont();
		}
		return font;
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public void dispose() {
		stage.dispose();
		if (font != null)
			font.dispose();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		float cameraX = camera.position.x;
		float cameraY = camera.position.y;
		stage.clear();
		float zoom;
		if (((float) 960 / (float) width) > ((float) 640 / (float) height)) {

			this.height = (int) (width / 1.5);
			this.width = (int) (this.height * 1.5);

			zoom = ((float) 960 / (float) width);
		} else {
			this.width = (int) (height * 1.5);
			this.height = (int) (this.width / 1.5);
			zoom = ((float) 640 / (float) height);
		}
		camera.viewportHeight = this.height;
		camera.viewportWidth = this.width;
		camera.zoom = zoom;
		camera.position.x = CAMERA_INITIAL_X;
		camera.position.y = 320;
		this.width = width;
		this.height = height;
		camera.position.x = cameraX;
		camera.position.y = cameraY;
		for (Actor a : stage.getCurrentActors()) {
			stage.insertActor(a);
		}

	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	public ShadowStruggles getGame() {
		return game;
	}

	public MyStage getStage() {
		return stage;
	}

	public void addGameObject(Image f2d) {
		stage.addActor(f2d);
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void loadLanguage() {
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public BaseScreen copy() {
		return null;
	}

}
