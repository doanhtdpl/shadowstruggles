package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InGameMenu extends BaseScreen {
	private Image background;
	private TextButton returnToGame;
	private TextButton exitGame;
	private TextButton config;
	private TextButton checkCards;
	private BattleScreen battleScreen;

	public InGameMenu(ShadowStruggles game, Controller controller,
			BattleScreen battleScreen) {
		super(game, controller);
		this.battleScreen = battleScreen;
		initComponents();
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void initComponents() {
		background = new Image(this.getSkin().getDrawable("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		returnToGame = new TextButton(MenuTextDAO.getMenuText().returnToGame,
				getSkin());
		returnToGame = ScreenUtils.defineButton(returnToGame, 230, 500, 500,
				100, super.getSkin());
		returnToGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(battleScreen);

			}
		});
		checkCards = new TextButton(MenuTextDAO.getMenuText().checkCards,
				getSkin());
		checkCards = ScreenUtils.defineButton(checkCards, 230, 380, 500, 100,
				super.getSkin());
		final InGameMenu menu = this;
		checkCards.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				CheckCardsScreen cardsScreen = new CheckCardsScreen(game,
						controller, menu);
				cardsScreen.setMenu(menu);
				game.setScreenWithTransition(cardsScreen);

			}
		});
		config = new TextButton(MenuTextDAO.getMenuText().configurations,
				getSkin());
		config = ScreenUtils.defineButton(config, 230, 260, 500, 100,
				super.getSkin());

		config.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				SettingsScreen configurationScreen = new SettingsScreen(game,
						controller, menu);
				game.setScreenWithTransition(configurationScreen);
			}
		});

		exitGame = new TextButton(MenuTextDAO.getMenuText().exit, getSkin());
		exitGame = ScreenUtils.defineButton(exitGame, 230, 80, 500, 100,
				super.getSkin());
		exitGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(new MainScreen(game, controller));
			}

		});
		stage.addActor(background);
		stage.addActor(checkCards);
		stage.addActor(config);
		stage.addActor(exitGame);
		stage.addActor(returnToGame);
	}

	@Override
	public void loadLanguage() {
		checkCards.setText(MenuTextDAO.getMenuText().checkCards);
		config.setText(MenuTextDAO.getMenuText().configurations);
		exitGame.setText(MenuTextDAO.getMenuText().exit);
		returnToGame.setText(MenuTextDAO.getMenuText().returnToGame);
	}

	public void setBattleScreen(BattleScreen battleScreen) {
		this.battleScreen = battleScreen;
	}

	public BattleScreen getBattleScreen() {
		return battleScreen;
	}

	public void dispose() {

	}

}
