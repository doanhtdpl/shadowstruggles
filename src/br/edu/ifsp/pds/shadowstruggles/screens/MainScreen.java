package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen extends BaseScreen {

	private Image background;
	private TextButton campaign;
	private TextButton freePlay;
	private TextButton config;
	private TextButton shop;
	private TextButton editDeck;
	private ImageButton changeProfile;
	private static MainScreen instance;

	public static MainScreen getInstance(ShadowStruggles game,
			Controller controller) {
		if (instance != null)
			return instance;
		else {
			instance = new MainScreen(game, controller);
			return instance;
		}
	}

	private MainScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	private void initComponents() {
		if (!game.getAudio().getMusicName().equals("intro")) {
			game.getAudio().stop();
			game.getAudio().setMusic("intro");
		}

		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		campaign = new TextButton(game.getManager().getMenuText().campaign,
				super.getSkin());
		campaign = ScreenUtils.defineButton(campaign, 240, 525, 480, 110,
				super.getSkin());
		campaign.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new SceneScreen(game, game
						.getController()));

			}
		});

		freePlay = new TextButton(game.getManager().getMenuText().freePlay,
				super.getSkin());
		freePlay = ScreenUtils.defineButton(freePlay, 240, 405, 480, 110,
				super.getSkin());
		freePlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new FreePlayScreen(game, game
						.getController()));
			}
		});

		editDeck = new TextButton(game.getManager().getMenuText().editDeck,
				super.getSkin());
		editDeck = ScreenUtils.defineButton(editDeck, 240, 285, 480, 110,
				super.getSkin());
		final MainScreen screen = this;
		editDeck.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				EditDeckScreen editScreen = EditDeckScreen.getInstance(game,
						controller, null);
				editScreen.setPreviousScreen(screen);
				editScreen.initComponents();
				game.setScreenWithTransition(editScreen);
			}

		});

		shop = new TextButton(game.getManager().getMenuText().shop,
				super.getSkin());
		shop = ScreenUtils.defineButton(shop, 240, 165, 480, 110,
				super.getSkin());

		shop.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new ShopScreen(game, controller,
						screen));
			}

		});

		config = new TextButton(game.getManager().getMenuText().configurations,
				super.getSkin());
		config = ScreenUtils.defineButton(config, 240, 45, 480, 110,
				super.getSkin());

		config.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				ConfigurationScreen configurationScreen = ConfigurationScreen
						.getInstance(game, controller, null);
				configurationScreen.setPreviousScreen(screen);
				game.setScreenWithTransition(configurationScreen);
			}
		});

		changeProfile = new ImageButton(this.getSkin().getDrawable("profiles"));
		changeProfile.setX(30);
		changeProfile.setY(20);
		changeProfile.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new SaveLoadScreen(game,
						controller, "main", false));
			}

		});

		stage.addActor(background);
		stage.addActor(campaign);
		stage.addActor(freePlay);
		stage.addActor(editDeck);
		stage.addActor(shop);
		stage.addActor(config);
		stage.addActor(changeProfile);

	}

	@Override
	public void loadLanguage() {
		campaign.setText(game.getManager().getMenuText().campaign);
		freePlay.setText(game.getManager().getMenuText().freePlay);
		editDeck.setText(game.getManager().getMenuText().editDeck);
		shop.setText(game.getManager().getMenuText().shop);
		config.setText(game.getManager().getMenuText().configurations);

	}

	@Override
	public void show() {
		super.show();
	}
}
