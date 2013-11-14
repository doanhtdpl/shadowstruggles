package br.edu.ifsp.pds.shadowstruggles;

import br.edu.ifsp.pds.shadowstruggles.data.FileMap;
import br.edu.ifsp.pds.shadowstruggles.data.Loader;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.MyFileHandleResolver;
import br.edu.ifsp.pds.shadowstruggles.data.SoundManager;
import br.edu.ifsp.pds.shadowstruggles.dataTest.LoaderTest;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.InGameMenu;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen.Mode;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.FadeInTransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.FadeOutTransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.TransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.TransitionScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
	private SpriteBatch batch;
	private Skin skin;

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
		this(RunMode.DEBUG);
	}

	private ShadowStruggles(RunMode mode) {
		this.mode = mode;
	}

	@Override
	public void create() {
		this.audio = new SoundManager(assets);
		this.controller = new Controller();
		this.setAssets(new AssetManager(new MyFileHandleResolver()));

		FileMap.initMap();

		if (this.mode != RunMode.TESTS) {
			initLoading();
			SaveLoadScreen saveLoad = new SaveLoadScreen(this, controller,
					Mode.START, null);
			this.setScreen(new LoadingScreen(this, saveLoad));
		} else {
			// Test cases go here.
			LoaderTest loaderTest = new LoaderTest();
			loaderTest.testLoading(this);
			loaderTest.testGetRegion(this);
			loaderTest.testDispose(this);

			// End of test cases.
			Gdx.app.exit();
		}
	}

	/**
	 * Starts loading the initial resources for the game.
	 */
	private void initLoading() {
		this.loader = new Loader(this);
		Array<Asset> textureRegions = null;

		Array<Asset> textures = null;

		Array<Asset> sounds = new Array<Asset>(new Asset[] {
				new Asset("intro.ogg", "soundtrack"),
				new Asset("m2.ogg", "soundtrack"),
				new Asset("m1.ogg", "soundtrack"),
				new Asset("m3.ogg", "soundtrack"),
				new Asset("m4.ogg", "soundtrack"),
				new Asset("button_1.ogg", "sound_effects"),
				new Asset("button_2.ogg", "sound_effects"),
				new Asset("button_3.ogg", "sound_effects"),
				new Asset("button_4.ogg", "sound_effects"),
				new Asset("button_5.ogg", "sound_effects"),
				new Asset("button_6.ogg", "sound_effects"),
				new Asset("button_7.ogg", "sound_effects"),
				new Asset("button_8.ogg", "sound_effects") });

		Array<Asset> rpgMaps = null;

		loader.setAssetsToLoad(textureRegions, textures, sounds, rpgMaps);
		assets.load(FileMap.resourcesToDirectory.get("skin") + "skin.atlas",
				TextureAtlas.class);
		loader.loadAssets();
	}

	public void setScreen(Screen screen) {
		if (this.getScreen() != null) {
			if (!(this.getScreen() instanceof BattleScreen)
					&& !(screen instanceof InGameMenu)) {
				this.getScreen().dispose();
			}
		}
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
		if (this.getScreen() != null)
			this.getScreen().dispose();
		if (batch != null)
			batch.dispose();
		if (assets != null)
			assets.dispose();
		if (audio != null)
			audio.dispose();
		if (skin != null)
			skin.dispose();
		if (loader != null)
			loader.dispose();
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

	public SpriteBatch getBatch() {
		if (batch == null)
			batch = new SpriteBatch();
		return batch;
	}

	public Loader getLoader() {
		return this.loader;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	public Skin getSkin() {
		if (skin == null) {
			String atlasPath = FileMap.resourcesToDirectory.get("skin")
					+ "skin.atlas";
			String jsonPath = "data/skin.json";
			skin = new Skin(Gdx.files.internal(jsonPath), assets.get(atlasPath,
					TextureAtlas.class));
		}
		return skin;
	}
}
