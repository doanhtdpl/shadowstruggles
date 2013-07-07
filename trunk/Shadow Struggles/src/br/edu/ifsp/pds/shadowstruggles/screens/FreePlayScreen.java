package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.games.Practice;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.object2d.Arrow;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class FreePlayScreen extends BaseScreen {
	private Image background;
	private ShadowStruggles game;
	private Array<TextButton> battles;
	private TextButton returnButton;
	private static FreePlayScreen instance;
	private Label chapterSelect;
	private SelectBox chapterBox;
	private Arrow right;
	private Arrow left;
	BattleScreen battle;
	private ImageButton battleButton;

	public static FreePlayScreen getInstance(ShadowStruggles game,
			Controller controller) {
		if (instance != null)
			return instance;
		else {
			instance = new FreePlayScreen(game, controller);
			return instance;
		}
	}

	private FreePlayScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		this.game = game;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	public void initTempButton() {
		ImageButton tempButton = new ImageButton(this.getSkin().getDrawable("placeholder"));
		tempButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.setScreenWithTransition(new BattleTutorial(game));

			}
		});

		ImageButton tempButton2 = new ImageButton(this.getSkin().getDrawable("placeholder"));
		tempButton2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				RpgController rpgController = new RpgController();
				@SuppressWarnings("unused")
				RpgPlatform platform = new RpgPlatform(rpgController,
						"example", new Character(game.getProfile(), 0, 19));

				game.setScreenWithTransition(new RpgScreen(game, controller,
						rpgController));
			}
		});
		
		Table battleTable = new Table();
		battleTable.defaults().width(150).height(150);
		
		if (game.getMode() == RunMode.DEBUG)
			battleTable.debug();
		
		battleButton = new ImageButton(this.getSkin().getDrawable("placeholder"));
		
		Label lteste11 = new Label("Battle 1", super.getSkin());
		lteste11.setStyle(new LabelStyle(super.getSkin()
				.getFont("andalus-font"), Color.WHITE));
		
		Label lteste12 = new Label("Tutorial", super.getSkin());
		lteste11.setStyle(new LabelStyle(super.getSkin()
				.getFont("andalus-font"), Color.WHITE));
		
		Label lteste13 = new Label("RPG", super.getSkin());
		lteste11.setStyle(new LabelStyle(super.getSkin()
				.getFont("andalus-font"), Color.WHITE));
		
		Label lteste21 = new Label("Reward: 30", super.getSkin());
		lteste21.setStyle(new LabelStyle(super.getSkin()
				.getFont("andalus-font"), Color.WHITE));
		
		battleTable.add(battleButton);
		battleTable.add(tempButton);
		battleTable.add(tempButton2);
		battleTable.row().height(50);
		battleTable.add(lteste11);
		battleTable.add(lteste12);
		battleTable.add(lteste13);
		battleTable.row().height(50);
		battleTable.add(lteste21);
		
		battleTable.setPosition(600, 500);

		stage.addActor(battleTable);
	}

	public void initComponents() {
		stage.clear();
		
		Table menuTable = new Table();
		menuTable.defaults().padTop(10).width(180).height(50);
		
		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		
		chapterSelect = new Label("", super.getSkin());
		chapterSelect.setText("Select a Chapter");
		chapterSelect.setStyle(new LabelStyle(super.getSkin()
				.getFont("andalus-font"), Color.WHITE));
		
		chapterBox = new SelectBox(new String[]{"Chapter 1", "Chapter 2", "Chapter 3"}, super.getSkin());

		returnButton = new TextButton(MenuTextDAO.getMenuText().returnToStart,
				super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 0, 0, 0,
				0, super.getSkin());
		returnButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(MainScreen.getInstance(game,
						controller));

			}
		});
		
		menuTable.add(chapterSelect);
		menuTable.row();
		menuTable.add(chapterBox);
		menuTable.row();
		menuTable.add().height(400);
		menuTable.row();
		menuTable.add(returnButton);
		
		menuTable.setPosition(100, 330);
		
		Table leftButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			leftButtonTable.debug();
		leftButtonTable.defaults().width(100).height(100);
		
		left = new Arrow(-1, this.getSkin());
		left.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveBattles(-1);

			}
		});
		
		leftButtonTable.add(left).left();

		leftButtonTable.setPosition(250, 300);
		
		Table rightButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			rightButtonTable.debug();

		rightButtonTable.defaults().width(100).height(100);
		
		right = new Arrow(1, this.getSkin());
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveBattles(1);

			}
		});
		
		rightButtonTable.add(right);
		
		rightButtonTable.setPosition(900, 300);
		
		battles = new Array<TextButton>();

		for (Float id : game.getProfile().getBattlesFought()) {
			TextButton button = new TextButton("", getSkin());
			switch (id.intValue()) {
			case 1:
				button.setText(MenuTextDAO.getMenuText().practiceBattle);
				button = ScreenUtils.defineButton(button, 100, 300, 300, 100,
						super.getSkin());
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

			stage.addActor(menuTable);
			battles.add(button);
		}
		
		stage.addActor(menuTable);
		stage.addActor(leftButtonTable);
		stage.addActor(rightButtonTable);
		initTempButton();
	}
	
	protected void moveBattles(int i) {
		// TODO Change the battles displayed at the battleTable
		
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
