package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.FileMap;
import br.edu.ifsp.pds.shadowstruggles.data.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The game assets are loaded during this screen, which shows a progress bar to
 * the player indicating how much of the resources have been loaded.
 */
public class LoadingScreen extends BaseScreen {

	private class LoadingBar extends Image {
		private float percentage = 0.0f;
		private Label percentageLbl;

		public LoadingBar() {
			super(new TextureRegion(new Texture(
					FileMap.resourcesToDirectory.get("game_ui_images")
							+ "loading_bar.png"), 0, 0, BAR_MAX_WIDTH, 32));
			this.setScaleX(0.8f);
			this.setScaleY(1.9f);
		}

		public void update(float currentProgress) {
			percentage = currentProgress;
			this.setScaleX(0.8f * percentage);
		}

		public void drawLabel(Skin skin) {
			if (percentageLbl != null)
				this.percentageLbl.remove();

			String lifeString = String.valueOf((int) (percentage * 100)) + "%";
			this.percentageLbl = new Label(lifeString, skin);
			this.percentageLbl.setX(this.getX() + 400);
			this.percentageLbl.setY(this.getY() + 20);
			this.percentageLbl.setStyle(new LabelStyle(skin
					.getFont("andalus-font"), Color.WHITE));
			if(this.getStage() != null)
				this.getStage().addActor(percentageLbl);
		}
	}

	private static int BAR_MAX_WIDTH = 1024; // The width of the progress bar.

	private ShadowStruggles game;
	private BaseScreen nextScreen;
	private boolean initialized;

	private Image background;
	private LoadingBar bar;
	private float percent;

	public LoadingScreen(ShadowStruggles game, BaseScreen nextScreen) {
		super(game);
		this.nextScreen = nextScreen;
		// The game attribute must be set again, otherwise it's not initialized
		// properly.
		this.game = game;

		game.getLoader().setAssetsToLoad(nextScreen.textureRegionsToLoad(),
				nextScreen.texturesToLoad(), nextScreen.soundsToLoad(),
				nextScreen.mapsToLoad());
		game.getLoader().loadAssets();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		stage.clear();

		background = new Image(new TextureRegion(new Texture(
				Gdx.files.internal(FileMap.resourcesToDirectory
						.get("game_ui_images") + "logo_shadow_struggles.png")),
				512, 512));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 512f);
		background.setY(background.getImageY() + 100);

		stage.addActor(background);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (game.getAssets().update()) {
			game.setScreenWithTransition(nextScreen);

			if (nextScreen instanceof SaveLoadScreen) {
				game.setAudio(new SoundManager(game.getAssets()));
				game.getAudio().setMusic("intro");
			}

			nextScreen.initComponents();
		} else if (game.getAssets().isLoaded(
				FileMap.resourcesToDirectory.get("skin") + "skin.atlas")) {
			if (!initialized) {
				bar = new LoadingBar();
				bar.setX(80);
				bar.setY(50);
				stage.addActor(bar);
				initialized = true;
			}
			
			percent += 0.005f;
			if (game.getAssets().getProgress() - percent > 0.0f)
				percent += (game.getAssets().getProgress() - percent) * 0.9f;
			bar.update(percent);
			bar.drawLabel(super.getSkin());
		}
	}
}
