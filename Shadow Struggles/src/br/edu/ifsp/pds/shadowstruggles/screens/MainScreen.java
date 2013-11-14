package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen.Mode;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgMenu;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen extends BaseScreen {

	private Image background;
	private Image locker;
	private Image tableBg;
	private TextButton campaign;
	private TextButton freePlay;
	private TextButton shop;
	private TextButton editDeck;
	private ImageButton changeProfile;
	private ImageButton config;

	public MainScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		if (!game.getAudio().getMusicName().equals("intro")) {
			game.getAudio().stop();
			game.getAudio().setMusic("intro");
		}

		final MainScreen screen = this;

		// Images.

		background = new Image(this.getSkin().getDrawable("msbackground2"));
		tableBg = new Image(this.getSkin().getDrawable("armario"));
		tableBg.setBounds(120, 20, 720, 600);

		// Text Buttons.

		campaign = new TextButton(MenuTextDAO.getMenuText().campaign,
				super.getSkin(), "drawer");
		campaign.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				RpgController rpgController = new RpgController();
				RpgMap rpgMap = new RpgMap("map");
				Character character = new Character(0, 0, 2, 2, rpgMap);
				game.getProfile().setCharacter(character);
				@SuppressWarnings("unused")
				RpgPlatform platform = new RpgPlatform(rpgController,
						character, rpgMap);
				RpgScreen nextScreen = new RpgScreen(game, controller,
						rpgController);
				game.setScreenWithTransition(new LoadingScreen(game, nextScreen));
				
				
				

			}
		});

		freePlay = new TextButton(MenuTextDAO.getMenuText().freePlay,
				super.getSkin(), "drawer");
		freePlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				FreePlayScreen freePlay = new FreePlayScreen(game, game
						.getController());
				game.setScreenWithTransition(freePlay);
			}
		});

		editDeck = new TextButton(MenuTextDAO.getMenuText().editDeck,
				super.getSkin(), "drawer");
		editDeck.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				EditDeckScreen editScreen = new EditDeckScreen(game,
						controller, null);
				editScreen.setPreviousScreen(screen);
				game.setScreenWithTransition(new LoadingScreen(game, editScreen));
			}

		});

		shop = new TextButton(MenuTextDAO.getMenuText().shop, super.getSkin(),
				"drawer");
		shop.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				ShopScreen nextScreen = new ShopScreen(game, null, controller,
						screen);
				game.setScreenWithTransition(nextScreen);
			}

		});

		// Image Buttons.

		changeProfile = new ImageButton(this.getSkin().getDrawable(
				"change_profile"));
		changeProfile.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				SaveLoadScreen saveLoad = new SaveLoadScreen(game, controller,
						Mode.LOAD, screen);
				game.setScreenWithTransition(saveLoad);
			}

		});

		config = new ImageButton(this.getSkin().getDrawable("settings"));
		config.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				SettingsScreen configurationScreen = new SettingsScreen(game,
						controller, screen);
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
		if (game.getMode() == RunMode.DEBUG)
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
		if (game.getMode() == RunMode.DEBUG)
			configTable.debug();
		configTable.add(config);
		configTable.setPosition(900, 80);

		
		stage.addActor(background);
		stage.addActor(tableBg);
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
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
