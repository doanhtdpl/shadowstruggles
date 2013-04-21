package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.SoundManager;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.Trap;

/**
 * The game assets are loaded in this screen, which shows a progress bar to the
 * player indicating how much of the resources have been loaded.
 */
public class LoadingScreen extends BaseScreen {
	
	private class LoadingBar extends Image {
		private float percentage = 0.0f;
		private Label percentageLbl;

		public LoadingBar() {
			super(new TextureRegion(new Texture(
					"data/images/objects/loading_bar.png"), 0, 0,
					BAR_MAX_WIDTH, 32));
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
			this.getStage().addActor(percentageLbl);
		}
		
		public float getPercentage() {
			return this.percentage;
		}
	}
	
	private static int BAR_MAX_WIDTH = 1024; // The width of the progress bar.
	
	private Image background;
	private ShadowStruggles game;
	private LoadingBar bar;

	public LoadingScreen(ShadowStruggles game) {
		super(game);

		// Load main textures.
		
		game.getAssets().load("data/images/objects/objects.atlas",
				TextureAtlas.class);
		game.getAssets().load("data/images/cards/cards.atlas",
				TextureAtlas.class);
		game.getAssets().load("data/images/card_attacking/card_attacking.atlas",
				TextureAtlas.class);
		game.getAssets().load("data/images/card_walking/card_walking.atlas",
				TextureAtlas.class);
		game.getAssets().load("data/images/card_effects/card_effects.atlas",
				TextureAtlas.class);
		game.getAssets().load("data/images/objects/energy100.png", Texture.class);
//		game.getAssets().load("data/images/card_images/card_images.atlas",
//				TextureAtlas.class);
		
		// Load music and sound effects.

		game.getAssets().load("data/audio/battle.ogg", Music.class);
		game.getAssets().load("data/audio/button_1.ogg", Sound.class);
		game.getAssets().load("data/audio/button_2.ogg", Sound.class);
		game.getAssets().load("data/audio/button_3.ogg", Sound.class);
		game.getAssets().load("data/audio/button_4.ogg", Sound.class);
		game.getAssets().load("data/audio/button_5.ogg", Sound.class);
		game.getAssets().load("data/audio/button_6.ogg", Sound.class);
		game.getAssets().load("data/audio/button_7.ogg", Sound.class);
		game.getAssets().load("data/audio/button_8.ogg", Sound.class);
		game.getAssets().load("data/audio/intro.ogg", Music.class);

		this.game = game;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		stage.clear();

		background = new Image(
				new TextureRegion(
						new Texture(
								Gdx.files
										.internal("data/images/objects/logo_shadow_struggles.png")),
						512, 512));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 512f);
		background.setY(background.getImageY() + 100);

		bar = new LoadingBar();
		bar.setX(80);
		bar.setY(50);

		stage.addActor(background);
		stage.addActor(bar);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		if (game.getAssets().update()) {
			game.setAudio(new SoundManager(game.getAssets()));
			game.getAudio().setMusic("intro");
			game.setScreenWithTransition(new StartScreen(game, game
					.getController()));
		} else {
			bar.update(Interpolation.linear.apply(bar.getPercentage(), 1.0f, 0.01f));
			bar.drawLabel(super.getSkin());
		}

	}

}
