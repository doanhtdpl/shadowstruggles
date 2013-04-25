package br.edu.ifsp.pds.shadowstruggles;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.SoundManager;
import br.edu.ifsp.pds.shadowstruggles.dataTest.DataManagerTest;
import br.edu.ifsp.pds.shadowstruggles.logging.MyLogger;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.FadeInTransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.FadeOutTransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.TransitionEffect;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.TransitionScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;

/***
 * Represent the application structure, responsible for the change of screens
 * and other general operations related to the game.
 */

public class ShadowStruggles extends Game {
	public static final String LOG = ShadowStruggles.class.getName();
	public static enum RunMode { DEBUG, TESTS, RELEASE };
	
	private Controller controller;
	private Profile profile;
	private DataManager manager;
	private SoundManager audio;
	private AssetManager assets;
	private RunMode mode;

	public ShadowStruggles() {
		this(RunMode.DEBUG);
	}

	public ShadowStruggles(RunMode mode) {
		this.mode = mode;
	}

	@Override
	public void create() {
		this.manager = new DataManager();
		this.audio = new SoundManager(assets);
		this.controller = new Controller();
		this.setAssets(new AssetManager());
		
		if(this.mode != RunMode.TESTS)
			this.setScreen(new LoadingScreen(this));
		else {
			// Test cases go here.
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

	public DataManager getManager() {
		return this.manager;
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
}
