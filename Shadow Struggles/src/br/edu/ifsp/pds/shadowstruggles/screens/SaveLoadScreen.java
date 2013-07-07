package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SceneDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class SaveLoadScreen extends BaseScreen implements InputProcessor {
	private static SaveLoadScreen instance;

	private Image background;
	private TextButton returnButton;
	private Array<TextButton> slots;

	private int touchY;
	private boolean touched;
	private boolean justTouched;

	private InputMultiplexer inputSources;
	private boolean saveMode;

	/**
	 * Indicates which screen the return button should reference: "start" - A
	 * StartScreen; "main" - A MainScreen; "scene" - A SceneScreen.
	 */
	private String returnScreen;

	public static SaveLoadScreen getInstance(ShadowStruggles game,
			Controller controller, String returnScreen, boolean saveMode) {
		if (instance != null)
			return instance;
		else {
			instance = new SaveLoadScreen(game, controller, returnScreen,
					saveMode);
			return instance;
		}
	}

	private SaveLoadScreen(ShadowStruggles game, Controller controller,
			String returnScreen, boolean saveMode) {
		super(game, controller);

		this.returnScreen = returnScreen;
		this.saveMode = saveMode;
		this.slots = new Array<TextButton>();
		this.inputSources = new InputMultiplexer();
	}

	public void setReturnScreen(String returnScreen) {
		this.returnScreen = returnScreen;
	}

	public void setSaveMode(boolean saveMode) {
		this.saveMode = saveMode;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
		Gdx.input.setInputProcessor(inputSources);
	}

	public void initComponents() {

		background = new Image(this.getSkin().getDrawable("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		returnButton = new TextButton(MenuTextDAO.getMenuText().returnToStart,
				super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 10, 530, 220, 90,
				super.getSkin());
		returnButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				if (returnScreen.toLowerCase().equals("start"))
					game.setScreenWithTransition(StartScreen.getInstance(game,
							controller));
				else if (returnScreen.toLowerCase().equals("main"))
					game.setScreenWithTransition(MainScreen.getInstance(game,
							controller));
				else if (returnScreen.toLowerCase().equals("scene"))
					game.setScreenWithTransition(new SceneScreen(game,
							controller));
			}
		});

		stage.addActor(background);

		stage.addActor(returnButton);

		if (ProfileDAO.profileExists()) {
			Array<Profile> profiles = ProfileDAO.getProfiles();

			for (Profile profile : profiles) {
				String text = String.valueOf(profile.getId()) + " - "
						+ profile.getCurrentScene().getName();
				TextButton textButton = new TextButton(text, super.getSkin());

				textButton = ScreenUtils.defineButton(textButton, 240,
						630 - profile.getId() * 100, text.length() * 30, 90,
						super.getSkin());
				textButton.setClip(true);
				textButton.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						game.getAudio().playSound("button_2");

						if (saveMode) {
							TextButton tx = (TextButton) event
									.getListenerActor();
							game.getProfile().setId(
									Character.getNumericValue(tx.getText()
											.charAt(0)));
							ProfileDAO.createProfile(game.getProfile());
							game.setScreenWithTransition(new SaveLoadScreen(
									game, controller, returnScreen, true));
						} else {
							TextButton tx = (TextButton) event
									.getListenerActor();
							int id = Character.getNumericValue(tx.getText()
									.charAt(0));
							game.setProfile(ProfileDAO.getProfile(id));
							DataManager.getInstance().changeLanguage(
									ProfileDAO.getProfile(id).getLanguage());
							game.getProfile().setCurrentScene(
									SceneDAO.getScene(game.getProfile()
											.getCurrentScene().getId()));
							game.setScreenWithTransition(MainScreen
									.getInstance(game, controller));
						}
					}
				});

				this.slots.add(textButton);
				stage.addActor(textButton);
			}
		}

		if (this.saveMode) {
			String text = "Empty slot";
			TextButton textButton = new TextButton(text, super.getSkin());

			float lastY = 530;
			if (slots.size > 0)
				lastY = slots.peek().getY();
			textButton = ScreenUtils.defineButton(textButton, 240, lastY - 100,
					text.length() * 30, 90, super.getSkin());

			textButton.setClip(true);

			textButton.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getAudio().playSound("button_2");

					try {
						Array<Profile> profiles = ProfileDAO.getProfiles();
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
						ProfileDAO.createProfile(newProfile);
						game.setProfile(newProfile);
						game.setScreenWithTransition(new SaveLoadScreen(game,
								controller, returnScreen, true));
					} catch (Exception e) {
						Profile newProfile = new Profile();
						ProfileDAO.createProfile(newProfile);
						game.setProfile(newProfile);
						game.setScreenWithTransition(new SaveLoadScreen(game,
								controller, returnScreen, true));
					}

				}

			});

			this.slots.add(textButton);
			stage.addActor(textButton);
		}

		inputSources.addProcessor(this.stage);
		inputSources.addProcessor(this);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		background.setY(this.camera.position.y - CAMERA_INITIAL_Y);
		returnButton.setY(this.camera.position.y - CAMERA_INITIAL_Y + 530);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.DOWN) {
			if (controller.getCurrentScreen().camera.position.y - 4 < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y - 4 > slots
							.peek().getHeight()) {
				controller.getCurrentScreen().camera.position.y -= 4;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			return true;
		}

		if (keycode == Keys.UP) {
			if (controller.getCurrentScreen().camera.position.y + 4 < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y + 4 > slots
							.peek().getHeight()) {
				controller.getCurrentScreen().camera.position.y += 4;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		x = (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) controller
				.getCurrentScreen().getWidth()));

		touched = true;

		if (!justTouched) {
			justTouched = true;
			touchY = y;
		}

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		x = (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (touched) {

			touched = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		x = (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) controller
				.getCurrentScreen().getWidth()));
		if (touched) {

			if (controller.getCurrentScreen().camera.position.y - touchY + y < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y - touchY
							+ y > slots.peek().getHeight()) {
				controller.getCurrentScreen().camera.position.y -= touchY - y;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			this.touchY = y;
			return true;

		}

		return false;
	}

	// @Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

}
