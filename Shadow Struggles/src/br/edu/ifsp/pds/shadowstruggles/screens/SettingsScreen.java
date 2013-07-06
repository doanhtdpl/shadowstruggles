package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.dao.LanguagesDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Entry;

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

	private static SettingsScreen instance;

	private Label titleLabel;
	private Image bar;
	private Label musicLabel;
	private CheckBox musicBox;
	private Label volumeLabel;
	private Slider volumeSlider;
	private Label languagesLabel;
	private ImageButton confirmButton;
	private ImageButton cancelButton;

	private BaseScreen previousScreen;
	private PreviousSettings previousSettings;
	private static boolean musicOn;

	public static SettingsScreen getInstance(ShadowStruggles game,
			Controller controller, BaseScreen screen) {
		if (instance != null)
			return instance;
		else {
			instance = new SettingsScreen(game, controller, screen);
			return instance;
		}
	}

	private SettingsScreen(ShadowStruggles game, Controller controller,
			BaseScreen screen) {
		super(game, controller);

		this.previousScreen = screen;
		this.previousSettings = new PreviousSettings(game.getAudio()
				.getVolume(), game.getAudio().isMusicOn(), DataManager
				.getInstance().getCurrentLanguage());
	}

	public void setPreviousScreen(BaseScreen previousScreen) {
		this.previousScreen = previousScreen;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		initComponents();
	}

	private void initComponents() {
		// Images.

		bar = new Image(this.getSkin().getDrawable("blur_line"));
		bar.setPosition(70, 580);
		bar.setHeight(800);
		bar.rotate(270);

		// Labels.

		titleLabel = new Label(MenuTextDAO.getMenuText().configurations,
				super.getSkin());
		musicLabel = new Label(MenuTextDAO.getMenuText().music + ":",
				super.getSkin());
		volumeLabel = new Label(MenuTextDAO.getMenuText().volume + ":",
				super.getSkin());
		languagesLabel = new Label(MenuTextDAO.getMenuText().languageSelection
				+ ":", super.getSkin());

		// Image Buttons.

		confirmButton = new ImageButton(this.getSkin().getDrawable("confirm"));
		confirmButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
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
				game.getProfile()
						.setLanguage(previousSettings.previousLanguage);
				previousScreen.loadLanguage();
				game.setScreenWithTransition(previousScreen);
			}
		});

		// Other elements.

		musicOn = game.getAudio().isMusicOn();
		if(musicOn) {
			musicBox = new CheckBox(MenuTextDAO.getMenuText().on, super.getSkin());
			musicBox.setChecked(true);
		} else {
			musicBox = new CheckBox(MenuTextDAO.getMenuText().on, super.getSkin());
			musicBox.setChecked(false);
		}
		
		musicBox.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				musicOn = !musicOn;
				game.getAudio().setMusicOn(musicOn);
			}
		});

		// TODO: Implementar listener do slider.
		volumeSlider = new Slider(0, 1, 0.1f, false, super.getSkin());
		volumeSlider.setValue(game.getAudio().getVolume());

		// Languages.
		Array<String> languages = new Array<String>();
		for (Entry<String, String> entry : LanguagesDAO.getLanguages()
				.entries()) {
			String language = entry.value;
			languages.add(language);
		}
		SelectBox languagesBox = new SelectBox(languages.toArray(),
				super.getSkin());
		// TODO: Adicionar listener da caixa de seleção.

		// Tables.

		Table settingsTable = new Table();
		settingsTable.setPosition(400, 570);
		settingsTable.addActor(titleLabel);
		if (game.getMode() == RunMode.DEBUG)
			settingsTable.debug();

		Table soundTable = new Table();
		soundTable.setPosition(240, 480);
		soundTable.add(musicLabel).left().padRight(20);
		soundTable.add(musicBox).left();
		soundTable.row();
		soundTable.add(volumeLabel).left().padRight(20);
		soundTable.add(volumeSlider).width(300);
		if (game.getMode() == RunMode.DEBUG)
			soundTable.debug();

		Table languagesTable = new Table();
		languagesTable.setPosition(700, 480);
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
