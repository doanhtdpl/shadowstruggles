package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.dao.LanguagesDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen extends BaseScreen {

	private class PreviousSettings {
		public float volume;
		public boolean musicOn;
		public String previousLanguage;

		public PreviousSettings(float volume, boolean musicOn,
				String previousLanguage) {
			this.volume = volume;
			this.musicOn = musicOn;
			this.previousLanguage = previousLanguage;
		}
	}

	private Label titleLabel;
	private Image bar;
	private Label musicLabel;
	private CheckBox musicBox;
	private Label volumeLabel;
	private Slider volumeSlider;
	private Label languagesLabel;
	private SelectBox languagesBox;
	private ImageButton confirmButton;
	private ImageButton cancelButton;

	private BaseScreen previousScreen;
	private PreviousSettings previousSettings;
	private static boolean musicOn;

	public SettingsScreen(ShadowStruggles game, Controller controller,
			BaseScreen screen) {
		this(game, controller, screen, null);
	}

	public SettingsScreen(ShadowStruggles game, Controller controller,
			BaseScreen screen, PreviousSettings previousSettings) {
		super(game, controller);

		this.previousScreen = screen;

		if (previousSettings == null)
			previousSettings = new PreviousSettings(
					game.getAudio().getVolume(), game.getAudio().isMusicOn(),
					DataManager.getInstance().getCurrentLanguage());
		this.previousSettings = previousSettings;
	}

	public void setPreviousScreen(BaseScreen previousScreen) {
		this.previousScreen = previousScreen;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	public void initComponents() {
		// Images.

		bar = new Image(this.getSkin().getDrawable("blur_line"));
		bar.setPosition(70, 570);
		bar.setHeight(750);
		bar.rotate(270);

		// Labels.

		titleLabel = new Label(MenuTextDAO.getMenuText().configurations,
				super.getSkin());
		titleLabel.setFontScale(2.5f);
		musicLabel = new Label(MenuTextDAO.getMenuText().music + ":",
				super.getSkin());
		musicLabel.setFontScale(1.5f);
		volumeLabel = new Label(MenuTextDAO.getMenuText().volume + ":",
				super.getSkin());
		volumeLabel.setFontScale(1.5f);
		languagesLabel = new Label(MenuTextDAO.getMenuText().languageSelection
				+ ":", super.getSkin());
		languagesLabel.setFontScale(1.5f);

		// Image Buttons.

		confirmButton = new ImageButton(this.getSkin().getDrawable("confirm"));
		confirmButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().setLanguage(DataManager.getInstance().getCurrentLanguage());
				System.out.println("SettingsScreen: profile language: "+game.getProfile().getLanguage());
				ProfileDAO.updateProfile(game.getProfile().getId(), game.getProfile());
				DataManager.getInstance().save();
				game.getAudio().playSound("button_4");
				previousScreen.loadLanguage();
				game.setScreenWithTransition(previousScreen);
			}
		});

		cancelButton = new ImageButton(this.getSkin().getDrawable("cancel"));
		cancelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.getAudio().setVolume(previousSettings.volume);
				game.getAudio().setMusicOn(previousSettings.musicOn);
				DataManager.getInstance().changeLanguage(
						previousSettings.previousLanguage);
				previousScreen.loadLanguage();
				game.setScreenWithTransition(previousScreen);
			}
		});

		// Other elements.

		musicOn = game.getAudio().isMusicOn();
		musicBox = new CheckBox(MenuTextDAO.getMenuText().on, super.getSkin());
		if (musicOn)
			musicBox.setChecked(true);
		else
			musicBox.setChecked(false);

		musicBox.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				musicOn = !musicOn;
				game.getAudio().setMusicOn(musicOn);
			}
		});

		volumeSlider = new Slider(0, 1, 0.1f, false, super.getSkin());
		volumeSlider.setValue(game.getAudio().getVolume());
		volumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getAudio().setVolume(volumeSlider.getValue());
			}
		});

		languagesBox = new SelectBox(LanguagesDAO.getLanguages().values()
				.toArray().toArray(), super.getSkin());
		languagesBox.setSelection(DataManager.getInstance()
				.getCurrentLanguage());
		languagesBox.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				int selected = languagesBox.getSelectionIndex();
				if (selected >= 0) {
					String language = LanguagesDAO.getLanguages().keys()
							.toArray().get(selected);
					if (!DataManager.getInstance().getCurrentLanguage()
							.equals(language)) {
						DataManager.getInstance().changeLanguage(language);
						game.setScreenWithTransition(new SettingsScreen(game,
								controller, previousScreen, previousSettings));
					}
				}
			}
		});

		// Tables.

		Table settingsTable = new Table();
		settingsTable.setPosition(460, 590);
		settingsTable.add(titleLabel);
		if (game.getMode() == RunMode.DEBUG)
			settingsTable.debug();

		Table soundTable = new Table();
		soundTable.setPosition(260, 430);
		soundTable.add(musicLabel).left().padRight(20);
		soundTable.add(musicBox).left();
		soundTable.row().padTop(50);
		soundTable.add(volumeLabel).left().padRight(20);
		soundTable.add(volumeSlider).width(300);
		if (game.getMode() == RunMode.DEBUG)
			soundTable.debug();

		Table languagesTable = new Table();
		languagesTable.setPosition(750, 460);
		languagesTable.add(languagesLabel);
		languagesTable.row();
		languagesTable.add(languagesBox).fillX();
		if (game.getMode() == RunMode.DEBUG)
			languagesTable.debug();

		Table buttonsTable = new Table();
		buttonsTable.setPosition(850, 50);
		buttonsTable.add(cancelButton).left().padRight(20);
		buttonsTable.add(confirmButton).left();
		if (game.getMode() == RunMode.DEBUG)
			buttonsTable.debug();

		stage.addActor(settingsTable);
		stage.addActor(bar);
		stage.addActor(soundTable);
		stage.addActor(languagesTable);
		stage.addActor(buttonsTable);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(this.getStage());
	}
}
