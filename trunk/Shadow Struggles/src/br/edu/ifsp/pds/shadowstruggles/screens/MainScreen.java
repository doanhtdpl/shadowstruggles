package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen extends BaseScreen {

	private Image background;
	private Image locker;
	private TextButton campaign;
	private TextButton freePlay;
	private TextButton shop;
	private TextButton editDeck;
	private ImageButton changeProfile;
	private ImageButton config;
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

		final MainScreen screen = this;

		// Images.

		background = new Image(this.getSkin().getDrawable("msbackground2"));

		// Text Buttons.

		campaign = new TextButton(MenuTextDAO.getMenuText().campaign,
				super.getSkin(), "drawer");
		campaign.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new SceneScreen(game, game
						.getController()));

			}
		});

		freePlay = new TextButton(MenuTextDAO.getMenuText().freePlay,
				super.getSkin(), "drawer");
		freePlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				FreePlayScreen freePlay = FreePlayScreen.getInstance(game,
						game.getController());
				game.setScreenWithTransition(freePlay);
				freePlay.initComponents();
			}
		});

		editDeck = new TextButton(MenuTextDAO.getMenuText().editDeck,
				super.getSkin(), "drawer");
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

		shop = new TextButton(MenuTextDAO.getMenuText().shop, super.getSkin(),
				"drawer");
		shop.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new ShopScreen(game, controller,
						screen));
			}

		});

		// Image Buttons.

		changeProfile = new ImageButton(this.getSkin().getDrawable(
				"change_profile"));
		changeProfile.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				SaveLoadScreen saveLoad = SaveLoadScreen.getInstance(game,
						controller, "main", false);
				saveLoad.setReturnScreen("main");
				saveLoad.setSaveMode(false);
				game.setScreenWithTransition(saveLoad);
				saveLoad.initComponents();
			}

		});

		config = new ImageButton(this.getSkin().getDrawable("settings"));
		config.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				SettingsScreen configurationScreen = SettingsScreen
						.getInstance(game, controller, null);
				configurationScreen.setPreviousScreen(screen);
				game.setScreenWithTransition(configurationScreen);
			}

		});

		// Tables.

		Table changeTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			changeTable.debug();
		changeTable.defaults();
		changeTable.add(changeProfile);
		changeTable.setPosition(80, 80);

		Table menuTable = new Table();
		menuTable.defaults().width(480).height(110).padTop(10);
		if(game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		menuTable.defaults();
		
		menuTable.add(campaign);
		menuTable.row();
		menuTable.add(freePlay);
		menuTable.row();
		menuTable.add(shop);
		menuTable.row();
		menuTable.add(editDeck);
		menuTable.setPosition(480, 330);
		
		Table configTable = new Table();
		if(game.getMode() == RunMode.DEBUG)
			configTable.debug();
		configTable.add(config);
		configTable.setPosition(900, 80);
		
		stage.addActor(background);
		stage.addActor(changeTable);
		stage.addActor(menuTable);
		stage.addActor(configTable);
	}

	@Override
	public void loadLanguage() {
		campaign.setText(MenuTextDAO.getMenuText().campaign);
		freePlay.setText(MenuTextDAO.getMenuText().freePlay);
		editDeck.setText(MenuTextDAO.getMenuText().editDeck);
		shop.setText(MenuTextDAO.getMenuText().shop);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
