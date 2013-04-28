package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.games.Practice;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class FreePlayScreen extends BaseScreen {
	private Image background;
	private ShadowStruggles game;
	private Array<TextButton> battles;
	private TextButton returnButton;
	BattleScreen battle;

	public FreePlayScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		this.game = game;
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}
	
	private void initTempButton() {
		TextButton tempButton = ScreenUtils.defineButton(new TextButton("Tutorial", getSkin()), 10, 500, 200, 100, getSkin());
		tempButton.addListener(new ClickListener(){@Override
		public void clicked(InputEvent event, float x, float y) {
			
			super.clicked(event, x, y);
			game.setScreenWithTransition(new BattleTutorial(game));
		}});
		stage.addActor(tempButton);
	}

	private void initComponents() {
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);
		stage.addActor(background);

		battles = new Array<TextButton>();

		for (Float id : game.getProfile().getBattlesFought()) {
			TextButton button = new TextButton("",getSkin());
			switch (id.intValue()) {
			case 1:
				button.setText(game.getManager().getMenuText().practiceBattle);
				button = ScreenUtils.defineButton(button, 100, 300, 300, 100, super.getSkin());
				battle = new Practice(game, false);
				break;
			}

			button.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (!game.getAudio().getMusicName().equals("battle")) {
						game.getAudio().stop();
						game.getAudio().setMusic("battle");
					}
					game.setScreenWithTransition(battle);
				}

			});
			
			stage.addActor(button);
			battles.add(button);
		}

		returnButton = new TextButton(
				game.getManager().getMenuText().returnToStart, super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 100, 100, 250, 100, super.getSkin());
		returnButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(MainScreen.getInstance(game, controller));

			}
		});

		stage.addActor(returnButton);
		initTempButton();
	}
}
