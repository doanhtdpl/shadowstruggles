package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	private Image background;
	private Image volumePlus;
	private Image volumeMinus;
	private TextButton returnButton;
	private Label volume;
	private Label volumeLabel;
	private Image soundOnOff;
	private Array<TextButton> languages;
	private BaseScreen previousScreen;
	private static SettingsScreen instance;

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
		Table volumeTable = new Table();
		volumeTable.defaults().width(200).height(110).padTop(10);
		volumeTable.setY(500);
		volumeTable.setX(130);
		if (game.getMode() == RunMode.DEBUG)
			volumeTable.debug();
		
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		volumePlus = new Image(this.getSkin().getDrawable("plus"));
		volumePlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				game.getAudio().setVolume(0.05f);
				updateVolume();
				game.getAudio().playSound("button_2");

			}
		});

		volumeMinus = new Image(this.getSkin().getDrawable("minus"));
		volumeMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().setVolume(0.05f * (-1));
				updateVolume();
				game.getAudio().playSound("button_2");

			}
		});

		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 100, 100, 300,
				100, super.getSkin());
		returnButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				previousScreen.loadLanguage();
				game.setScreenWithTransition(previousScreen);

			}
		});

		volume = new Label(game.getAudio().getVolumeNumber(), super.getSkin());
		volume.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));

		volumeLabel = new Label(
				this.getGame().getManager().getMenuText().volume,
				super.getSkin());
		volumeLabel.setStyle(new LabelStyle(super.getSkin().getFont(
				"default-font"), Color.BLACK));

		soundOnOff = new Image(this.getSkin().getDrawable("mute"));
		soundOnOff.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().setVolume(-1);
				updateVolume();
			}
		});

		Table languagesTable = new Table();
		languagesTable.defaults().width(300).height(300).padTop(10);
		languagesTable.setY(540);
		languagesTable.setX(800);
		if (game.getMode() == RunMode.DEBUG)
			languagesTable.debug();
		
		this.languages = new Array<TextButton>();
		int i = 0;
		stage.addActor(background);
		for (Entry<String, String> language : game.getManager().getLanguages()
				.entries()) {
			final String lang = language.value;
			TextButton button = new TextButton(language.key, super.getSkin());
			button = ScreenUtils.defineButton(button, 500, 540 - (i * 90), 300,
					80, super.getSkin());
			button.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getAudio().playSound("button_7");
					game.getManager().changeLanguage(lang);
					game.getProfile().setLanguage(lang);
					game.getProfile().setCurrentScene(
							SceneDAO.getScene(game.getProfile()
									.getCurrentScene().getId(),
									game.getManager()));
					game.getManager().writeProfile(game.getProfile());
					game.setScreenWithTransition(new SettingsScreen(game,
							controller, previousScreen));

				}
			});
			this.languages.add(button);
			languagesTable.add(button).width(300).height(80);
			languagesTable.row();
			
			i++;
		}
		
		volumeTable.add(soundOnOff).width(100).height(100);
		volumeTable.add(volumeLabel).width(50).height(100);
		volumeTable.row();
		volumeTable.add(volumeMinus).width(100).height(100);
		volumeTable.add(volume).width(50).height(100);
		volumeTable.add(volumePlus).width(100).height(100);
		
		Table returnTable = new Table();
		returnTable.defaults().width(200).height(100);
		returnTable.setX(200);
		returnTable.setY(100);
		returnTable.add(returnButton);
		if (game.getMode() == RunMode.DEBUG)
			returnTable.debug();
		

		stage.addActor(volumeTable);
		stage.addActor(languagesTable);
		stage.addActor(returnTable);
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
