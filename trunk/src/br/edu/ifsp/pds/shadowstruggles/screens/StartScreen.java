package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class StartScreen extends BaseScreen {
	private TextButton continueGame;
	private TextButton newGame;
	private Image tableTexture;
	private Image book;
	private Image beaker;
	private Image candle;
	private Image tripod;
	private ScrollPane scrollStates;
	private Array<TextButton> states;

	private boolean loadStates;
	private static StartScreen instance;

	public static StartScreen getInstance(ShadowStruggles game,
			Controller controller) {
		if (instance != null)
			return instance;
		else {
			instance = new StartScreen(game, controller);
			return instance;
		}
	}

	private StartScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
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
		scrollStates = new ScrollPane(null);

		continueGame = new TextButton(
				game.getManager().getMenuText().continueGame, this.getSkin()
						.get("blur", TextButtonStyle.class));
		continueGame.setX(100);
		continueGame.setY(100);
		continueGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float arg1, float arg2) {
				game.getAudio().playSound("button_4");
				SaveLoadScreen saveLoad = SaveLoadScreen.getInstance(game,
						controller, "start", false);
				saveLoad.setReturnScreen("start");
				saveLoad.setSaveMode(false);
				game.setScreenWithTransition(saveLoad);
				saveLoad.initComponents();
			}
		});

		newGame = new TextButton(game.getManager().getMenuText().newGame, this
				.getSkin().get("blur", TextButtonStyle.class));
		newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float arg1, float arg2) {
				try {
					Array<Profile> profiles = ProfileDAO.getProfiles(game
							.getManager());
					ObjectMap<Integer, Profile> prof = new ObjectMap<Integer, Profile>();

					for (Profile profile : profiles) {
						prof.put(profile.getId(), profile);
					}

					int id;
					for (id = 1; id < 100; id++) {
						if (prof.get(id) == null)
							break;
					}

					Profile newProfile = new Profile(id);
					ProfileDAO.createProfile(newProfile, game.getManager());
					game.setProfile(newProfile);
					game.getAudio().playSound("button_4");
					game.setScreenWithTransition(MainScreen.getInstance(game,
							controller));
				} catch (Exception e) {
					Profile newProfile = new Profile();
					ProfileDAO.createProfile(newProfile, game.getManager());
					game.setProfile(newProfile);
					game.getAudio().playSound("button_4");
					game.setScreenWithTransition(MainScreen.getInstance(game,
							controller));
				}

			}
		});

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

		menu.add(continueGame);
		menu.row();
		menu.add(newGame);

		table.setPosition(500, 600);
		menu.setPosition(300, 280);

		stage.addActor(tableTexture);
		stage.addActor(book);
		stage.addActor(table);
		stage.addActor(menu);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
