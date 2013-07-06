package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.dao.LanguagesDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SceneDAO;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
	private Label settingsLabel;
	private Image volumePlus;
	private Image volumeMinus;
	private TextButton returnButton;
	private Label volume;
	private Label volumeLabel;
	private Image soundOnOff;
	private Array<TextButton> languages;
	private BaseScreen previousScreen;
	private PreviousSettings previousSettings;

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
		
		// Tables.

		Table settingsTable = new Table();
		settingsTable.setPosition(400, 570);
		settingsTable.addActor(titleLabel);
		if(game.getMode() == RunMode.DEBUG)
			settingsTable.debug();
		
		stage.addActor(settingsTable);
		stage.addActor(bar);
	}

	private void updateVolume() {
		this.volume.setText(game.getAudio().getVolumeNumber());
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(this.getStage());
	}

}
