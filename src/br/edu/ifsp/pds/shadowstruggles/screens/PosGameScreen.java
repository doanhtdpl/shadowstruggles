package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.pds.shadowstruggles.games.Practice;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PosGameScreen extends BaseScreen {

	private Image background;
	private Label text;
	private TextButton continueButton;
	private TextButton mainMenu;
	private BattleScreen battleScreen;

	/**
	 * Informs if the player has accessed the battle through the campaign or,
	 * otherwise, free play. If he has accessed it through free play, it doesn't
	 * show the option to continue and doesn't advance the plot.
	 */
	private boolean isInCampaign;

	public PosGameScreen(ShadowStruggles game, Controller controller,
			String message, boolean isInCampaign) {
		super(game, controller);
		this.isInCampaign = isInCampaign;
		initComponents(message);
	}

	public PosGameScreen(ShadowStruggles game, Controller controller,
			String message, BattleScreen battleScreen, boolean isInCampaign) {
		super(game, controller);
		this.isInCampaign = isInCampaign;
		this.battleScreen = battleScreen;
		initComponents(message);

	}

	public void initComponents(String message) {
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		mainMenu = new TextButton(
				game.getManager().getMenuText().mainMenuButton, super.getSkin());
		mainMenu = ScreenUtils.defineButton(mainMenu, 500, 100,game.getManager()
				.getMenuText().mainMenuButton.length() * 32, 100, super.getSkin());
		mainMenu.setClip(true);
		
		mainMenu.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				game.setScreenWithTransition(MainScreen.getInstance(game, controller));
			}

		});
		this.text = new Label(message, super.getSkin());
		text.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.YELLOW));
		text.setOriginX((960 - text.getPrefWidth()) / 2);
		text.setOriginY(300);
		

		this.getStage().addActor(background);
		this.getStage().addActor(text);
		this.getStage().addActor(mainMenu);

		if (isInCampaign) {
			continueButton = new TextButton(
					game.getManager().getMenuText().continueButton, super.getSkin());
			continueButton = ScreenUtils.defineButton(continueButton, 100, 100, 300, 100, super.getSkin());
			continueButton.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getAudio().playSound("button_4");
					game.setScreenWithTransition(new SceneScreen(game, controller));

				}
			});
			
			this.getStage().addActor(continueButton);
			
			//TODO: Perguntar ao jogador se ele gostaria de salvar ao invés de save automático.
			
			// Moves the plot forward to the next scene.
			game.getProfile().setCurrentScene(
					SceneDAO.getScene(game.getProfile().getCurrentScene()
							.getNextId(), game.getManager()));
			//ProfileDAO.createProfile(game.getProfile(), game.getManager());

			// Adds the battles to the array of fought battles.
			if (battleScreen.getClass() == Practice.class) {
				if (!game.getProfile().getBattlesFought().contains(1f, true)) {
					game.getProfile().getBattlesFought().add(1f);
					//game.getManager().writeProfile(game.getProfile());
				}
			}
		}
	}
}
