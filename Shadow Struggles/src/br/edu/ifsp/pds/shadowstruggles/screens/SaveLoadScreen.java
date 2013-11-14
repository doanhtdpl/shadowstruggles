package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class SaveLoadScreen extends BaseScreen {
	/**
	 * The mode dictates how some components of this must operate. START - This
	 * screens acts as the start menu. SAVE - This screen acts as a save menu.
	 * LOAD - This screen acts as a loading menu.
	 */
	public static enum Mode {
		START, SAVE, LOAD
	};

	private Mode mode;
	private BaseScreen previousScreen;

	private TextButton firstButton;
	private TextButton secondButton;
	private Image tableTexture;
	private Image book;
	private Image beaker;
	private Image candle;
	private Image tripod;
	private ScrollPane scrollStates;
	private Array<TextButton> states;

	/**
	 * The constructor.
	 * 
	 * @param previousScreen
	 *            The previous screen. Can be null in START mode.
	 */
	public SaveLoadScreen(ShadowStruggles game, Controller controller,
			Mode mode, BaseScreen previousScreen) {
		super(game, controller);
		this.states = new Array<TextButton>();
		this.mode = mode;
		this.previousScreen = previousScreen;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void initComponents() {
		stage.clear();
	}

	@Override
	public void show() {
		super.show();

		tableTexture = new Image(this.getSkin().getDrawable("table_texture"));
		book = new Image(this.getSkin().getDrawable("book"));
		book.setScale(0.9f);
		book.setX(book.getX() + 50);
		book.setY(book.getY() + 10);
		beaker = new Image(this.getSkin().getDrawable("beaker"));
		candle = new Image(this.getSkin().getDrawable("candle"));
		tripod = new Image(this.getSkin().getDrawable("tripod"));

		if (mode == Mode.SAVE)
			firstButton = new TextButton(MenuTextDAO.getMenuText().newGame,
					this.getSkin().get("blur", TextButtonStyle.class));
		else if (mode == Mode.LOAD)
			firstButton = new TextButton(
					MenuTextDAO.getMenuText().returnToStart, this.getSkin()
							.get("blur", TextButtonStyle.class));
		else
			firstButton = new TextButton(
					MenuTextDAO.getMenuText().continueGame, this.getSkin().get(
							"blur", TextButtonStyle.class));
		firstButton.setX(100);
		firstButton.setY(100);
		firstButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float arg1, float arg2) {
				if (mode == Mode.START)
					showProfiles();
				if (mode == Mode.LOAD)
					game.setScreenWithTransition(previousScreen);
				if (mode == Mode.SAVE)
					newProfile();
			}
		});

		if (mode == Mode.SAVE)
			secondButton = new TextButton(
					MenuTextDAO.getMenuText().returnToStart, this.getSkin()
							.get("blur", TextButtonStyle.class));
		else if (mode == Mode.START)
			secondButton = new TextButton(MenuTextDAO.getMenuText().newGame,
					this.getSkin().get("blur", TextButtonStyle.class));
		if (mode != Mode.LOAD) {
			secondButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float arg1, float arg2) {
					if (mode == Mode.START)
						newProfile();
					else if (mode == Mode.SAVE) {
						if (previousScreen instanceof RpgScreen) {
							RpgScreen previousRpgScreen = (RpgScreen) previousScreen;
							Controller controller = previousRpgScreen.getController();
							RpgController rpgController = previousRpgScreen.getRpgController();

							RpgScreen nextScreen = new RpgScreen(game, controller,
									rpgController);
							game.setScreenWithTransition(new LoadingScreen(
									game, nextScreen));
						} else
							game.setScreenWithTransition(previousScreen);
					}
				}
			});
		}

		Table table = new Table();
		table.defaults().padTop(30).width(150).height(130);

		if (game.getMode() == RunMode.DEBUG)
			table.debug();

		table.add(beaker).left().padRight(270);
		table.add(candle).width(190);
		table.add(tripod).padLeft(250);
		table.row();

		Table menu = new Table();
		menu.defaults().width(250).height(200);

		if (game.getMode() == RunMode.DEBUG)
			menu.debug();

		menu.add(firstButton);
		menu.row();
		if (mode != Mode.LOAD)
			menu.add(secondButton);

		table.setPosition(500, 600);
		menu.setPosition(270, 280);

		stage.addActor(tableTexture);
		stage.addActor(book);
		stage.addActor(table);
		stage.addActor(menu);

		if (mode == Mode.SAVE || mode == Mode.LOAD)
			showProfiles();
	}

	private void newProfile() {
		try {
			int id = 1;

			if (mode == Mode.START) {
				// Generate the next sequential ID for the profile.
				for (id = 1; id < 100; id++) {
					if (ProfileDAO.getProfile(id) == null)
						break;
				}
			} else if (mode == Mode.SAVE)
				id = game.getProfile().getId();

			Profile newProfile = new Profile(id);
			newProfile.createEventsInGame(game);
			ProfileDAO.createProfile(newProfile);
			game.setProfile(newProfile);
			game.getProfile().unlockItems();
			DataManager.getInstance().save();
			game.getAudio().playSound("button_4");
			
			if (mode == Mode.START)
				game.setScreenWithTransition(new MainScreen(game, controller));
			else if (mode == Mode.SAVE)
				showProfiles();
		} catch (Exception e) {
			Profile newProfile = new Profile();
			newProfile.createEventsInGame(game);
			ProfileDAO.createProfile(newProfile);
			game.setProfile(newProfile);
			game.getProfile().unlockItems();
			DataManager.getInstance().save();
			game.getAudio().playSound("button_4");
			game.setScreenWithTransition(new MainScreen(game, controller));
		}
	}

	/**
	 * Displays and updates the profiles list shown on the right side of the
	 * screen.
	 */
	private void showProfiles() {
		if (this.scrollStates != null) {
			this.scrollStates = null;
			for (TextButton button : this.states)
				button.remove();
		}

		Table table = new Table();
		table.defaults().width(400).height(200);
		if (game.getMode() == RunMode.DEBUG)
			table.debug();
		table.setPosition(480, 280);

		if (ProfileDAO.profileExists()) {
			Array<Profile> profiles = ProfileDAO.getProfiles();

			for (final Profile profile : profiles) {
				String text = String.valueOf(profile.getId());
				TextButton textButton = new TextButton(text, this.getSkin()
						.get("blur", TextButtonStyle.class));
				textButton = ScreenUtils.defineButton(textButton, 240,
						480 - profile.getId() * 100, text.length() * 30, 90,
						this.getSkin());
				textButton.setClip(true);
				textButton.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						createProfileButton(profile, event);
					}
				});

				this.states.add(textButton);
				table.add(textButton);
				table.row();
			}

			scrollStates = new ScrollPane(table, this.getSkin());
			scrollStates.setBounds(480, 100, 400, 400);
			scrollStates.setFadeScrollBars(false);
			stage.addActor(scrollStates);
		}
	}

	private void createProfileButton(Profile profile, InputEvent event) {
		game.getAudio().playSound("button_2");

		TextButton tx = (TextButton) event.getListenerActor();
		int id = Character.getNumericValue(tx.getText().charAt(0));
		profile = ProfileDAO.getProfile(id);

		if (mode == Mode.START || mode == Mode.LOAD) {
			if (profile.getEvents().size == 0)
				profile.createEventsInGame(game);
			game.setProfile(profile);
			DataManager.getInstance().changeLanguage(profile.getLanguage());
			if (mode == Mode.START)
				game.setScreenWithTransition(new MainScreen(game, controller));
			else
				game.setScreenWithTransition(previousScreen);
		} else if (mode == Mode.SAVE) {
			ProfileDAO.updateProfile(id, game.getProfile());
			DataManager.getInstance().save();
			showProfiles();
		}
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
