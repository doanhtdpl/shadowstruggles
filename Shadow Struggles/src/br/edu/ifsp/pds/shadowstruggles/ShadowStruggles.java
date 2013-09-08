package br.edu.ifsp.pds.shadowstruggles;

import br.edu.ifsp.pds.shadowstruggles.data.FileMap;
import br.edu.ifsp.pds.shadowstruggles.data.Loader;
import br.edu.ifsp.pds.shadowstruggles.data.SoundManager;
import br.edu.ifsp.pds.shadowstruggles.dataTest.DataManagerTest;
import br.edu.ifsp.pds.shadowstruggles.dataTest.LoaderTest;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.test.CharacterTest;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.FadeInTransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.FadeOutTransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.TransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.TransitionScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

/**
 * Represents the application structure, responsible for the change of screens
 * and other general operations related to the game.
 */

public class ShadowStruggles extends Game {
	public static final String LOG = ShadowStruggles.class.getName();

	public static enum RunMode {
		DEBUG, TESTS, RELEASE
	};

	private static ShadowStruggles instance;

	private Controller controller;
	private Profile profile;
	private SoundManager audio;
	private AssetManager assets;
	private RunMode mode;
	private Loader loader;

	public static ShadowStruggles getInstance() {
		if (instance == null)
			instance = new ShadowStruggles();
		return instance;
	}

	public static ShadowStruggles getInstance(RunMode mode) {
		if (instance == null)
			instance = new ShadowStruggles(mode);
		return instance;
	}

	private ShadowStruggles() {
		this(RunMode.TESTS);
	}

	private ShadowStruggles(RunMode mode) {
		this.mode = mode;
	}

	@Override
	public void create() {
		this.audio = new SoundManager(assets);
		this.controller = Controller.getInstance();
		this.setAssets(new AssetManager());

		FileMap.initMap();

		if (this.mode != RunMode.TESTS)
			this.setScreen(new LoadingScreen(this));
		else {
			// Test cases go here.
			LoaderTest loaderTest = new LoaderTest();
			loaderTest.testStaticTextureAtlasStrategy(this);
			loaderTest.testGetStaticRegion(this);
			loaderTest.testDynamicLoading(this);
			loaderTest.testGetDynamicRegion(this);
			loaderTest.testDynamicDispose(this);

			CharacterTest characterTest = new CharacterTest();
			characterTest.WalkDownTest();
			characterTest.WalkUpTest();
			characterTest.WalkRightTest();
			characterTest.WalkLeftTest();

			DataManagerTest dataManagerTest = new DataManagerTest();
			dataManagerTest.testEncodingDecoding();

			// End of test cases.
			Gdx.app.exit();
		}
		// try {
		// if (mode == RunMode.DEBUG)
		// MyLogger.setup();
		// } catch (IOException e) {
		// e.printStackTrace();
		// throw new RuntimeException("Problems with creating the log files");
		// }
	}

	public void setScreen(Screen screen) {
		super.setScreen(screen);
		controller.setCurrentscreen((BaseScreen) screen);
	}

	/***
	 * Sets the current screen with a fade transition effect.
	 */

	public void setScreenWithTransition(Screen screen) {

		Array<TransitionEffect> effects = new Array<TransitionEffect>();

		effects.add(new FadeOutTransitionEffect(0.7f));
		effects.add(new FadeInTransitionEffect(0.5f));

		BaseScreen transitionScreen = new TransitionScreen(this,
				(BaseScreen) this.getScreen(), (BaseScreen) screen, effects,
				this.controller);
		setScreen(screen);
		super.setScreen(transitionScreen);
		controller.setCurrentscreen((BaseScreen) screen);

	}

	/**
	 * Retrieves a TextureRegion from the file system.
	 * 
	 * @param regionName
	 *            The name of the region.
	 * @param resourceType
	 *            The resource type (card, skin, map etc.). These names are
	 *            specified in the {@link FileMap} class.
	 */
	public TextureRegion getTextureRegion(String regionName, String resourceType) {
		return loader.getTextureRegion(regionName, resourceType);
	}

	/**
	 * Retrieves a Texture from the file system.
	 * 
	 * @param textureName
	 *            The name of the texture.
	 * @param resourceType
	 *            The resource type (card, skin, map etc.). These names are
	 *            specified in the {@link FileMap} class.
	 */
	public Texture getTexture(String textureName, String resourceType) {
		return loader.getTexture(textureName, resourceType);
	}

	/**
	 * Retrieves a TiledMap from the file system.
	 * 
	 * @param mapName
	 *            The name of the map.
	 */
	public TiledMap getTiledMap(String mapName) {
		return loader.getTiledMap(mapName, "rpg_maps");
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();

	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Controller getController() {
		return this.controller;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setAudio(SoundManager audio) {
		this.audio = audio;
	}

	public SoundManager getAudio() {
		return this.audio;
	}

	public AssetManager getAssets() {
		return assets;
	}

	public void setAssets(AssetManager assets) {
		this.assets = assets;
	}

	public RunMode getMode() {
		return this.mode;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}
}
