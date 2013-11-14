package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.object2d.BackCard;
import br.edu.ifsp.pds.shadowstruggles.object2d.Deck2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.EnergyBar;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.FixedImage;
import br.edu.ifsp.pds.shadowstruggles.object2d.FixedLabel;
import br.edu.ifsp.pds.shadowstruggles.object2d.HandBackground;
import br.edu.ifsp.pds.shadowstruggles.object2d.HandCard;
import br.edu.ifsp.pds.shadowstruggles.object2d.Hexagram;
import br.edu.ifsp.pds.shadowstruggles.object2d.LifeBar;
import br.edu.ifsp.pds.shadowstruggles.object2d.BattleMap2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.MenuButton;
import br.edu.ifsp.pds.shadowstruggles.object2d.Timer2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

/***
 * Establishes the interaction between the game and the player. It shows the
 * items in the logic layer and acts as a listener for the {@link Controller}
 * class.
 */

public class BattleScreen extends BaseScreen {

	// public final static float FPS = 65;

	private Timer2D timer;
	private EnergyBar energyBar;
	private LifeBar playerLife;
	private LifeBar enemyLife;
	private int prevTime;
	private MenuButton menu;

	private float timeElapsed;
	private float timeDelay;
	private float time;
	private Deck2D deck;
	protected InputMultiplexer inputSources;
	private Image selectedCard;
	private FixedImage magnifier;
	private boolean dialogActive;
	private Array<Actor> dialogActors;

	protected BattleMap2D map2d;
	private HandBackground background;
	protected boolean initialized = false;
	private Array<Hexagram> hexagrams;
	private Array<BackCard> backcards;
	private Array<FixedLabel> cardInfo;

	protected Deck playerDeck;
	protected BattlePlatform battlePlatform;

	protected Array<FixedImage> fixedImages;

	/**
	 * Informs if the player has accessed the battle through the campaign or,
	 * otherwise, free play.
	 */
	protected boolean isInCampaign;

	/***
	 * Initializes the visual elements.
	 * 
	 * @param game
	 *            A representation of the game itself.
	 * @param player
	 *            The current profile.
	 * @param controller
	 * @param battlePlatform
	 */

	public BattleScreen(ShadowStruggles game, Profile player,
			Controller controller, BattlePlatform battlePlatform,
			boolean isInCampaign) {
		super(game, controller);

		this.isInCampaign = isInCampaign;
		inputSources = new InputMultiplexer();
		cardInfo = new Array<FixedLabel>();
		controller.setCurrentscreen(this);
		controller.setPlatform(battlePlatform);

		this.battlePlatform = battlePlatform;

		timeElapsed = 0;
		timeDelay = 0;
		battlePlatform.getEnemyDeck().shuffle();
		battlePlatform.getPlayerDeck().shuffle();
		doBeforeSet();
		hexagrams = new Array<Hexagram>();
		backcards = new Array<BackCard>();
		fixedImages = new Array<FixedImage>();
	}

	@Override
	public Array<Asset> textureRegionsToLoad() {
		System.out.println("BattleScreen: texturesRegionToLoad");
		Array<Asset> assets = new Array<Asset>(new Asset[] {
				new Asset("magnifier.png", "game_ui_images"),
				new Asset("back_card.png", "game_ui_images"),
				new Asset("deck.png", "game_ui_images"),
				new Asset("deckNotReady.png", "game_ui_images"),
				new Asset("energy100.png", "game_ui_images"),
				new Asset("background.png", "game_ui_images"),
				new Asset("hexagram.png", "game_ui_images"),
				new Asset("life100.png", "game_ui_images"),
				new Asset("pause.png", "game_ui_images"),
				new Asset("box.png", "game_ui_images") });
		Array<String> previousCards = new Array<String>();

		for (Card card : battlePlatform.getPlayerDeck().getCards()) {
			String cardName = card.getName().toLowerCase();
			if (!previousCards.contains(cardName, false)) {
				assets.add(new Asset(cardName + ".png", "cards"));

				if (card instanceof Fighter) {
					assets.add(new Asset(cardName + ".png", "card_walking"));
					assets.add(new Asset(cardName + ".png", "card_attacking"));
				}
				if (card instanceof Effect || card instanceof Trap)
					assets.add(new Asset(cardName + ".png", "card_effects"));
				previousCards.add(cardName);
			}
		}
		for (Card card : battlePlatform.getEnemyDeck().getCards()) {
			String cardName = card.getName().toLowerCase();
			if (!previousCards.contains(cardName, false)) {
				assets.add(new Asset(cardName + ".png", "cards"));

				if (card instanceof Fighter) {
					assets.add(new Asset(cardName + ".png", "card_walking"));
					assets.add(new Asset(cardName + ".png", "card_attacking"));
				}
				if (card instanceof Effect || card instanceof Trap)
					assets.add(new Asset(cardName + ".png", "card_effects"));
				previousCards.add(cardName);
			}
		}

		return assets;
	}

	@Override
	public Array<Asset> texturesToLoad() {
		Array<Asset> assets = new Array<Asset>(new Asset[] {
				new Asset(battlePlatform.getMap().getName() + ".png",
						"battle_maps"),
				new Asset("energy100.png", "game_ui_images") });
		return assets;
	}

	@Override
	public Array<Asset> soundsToLoad() {
		Array<Asset> assets = new Array<Asset>(new Asset[] { new Asset(
				"battle.ogg", "soundtrack") });
		return assets;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (game.getScreen().equals(this)) {
			InputMultiplexer clone = new InputMultiplexer();
			clone.getProcessors().addAll(inputSources.getProcessors());
			for (InputProcessor ip : clone.getProcessors()) {
				if (ip.getClass().equals(HandCard.class)) {
					if (!this.stage.getActors().contains((HandCard) ip, true)) {
						inputSources.removeProcessor(ip);
					}
				}
			}
			controller.updateTimer(delta);
			Gdx.input.setInputProcessor(inputSources);
			for (int i = 0; i < stage.getActors().size; i++) {
				Actor a = stage.getActors().get(i);
				if (a.getClass().equals(Fighter2D.class))
					((Fighter2D) (a)).render();
				else if (a.getClass().equals(Effect2D.class))
					((Effect2D) (a)).render();
				else if (a.getClass().equals(Trap2D.class))
					((Trap2D) (a)).render();

			}

			// if (time >= (float) (1 / FPS)) {
			setTime(0);
			update(delta);

			// }
			battlePlatform.getEnemy().action(battlePlatform, this, delta);
			setTime(getTime() + delta);
			camera.update();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		game.getLoader().disposeAtlas();
		game.getLoader().disposeTextures();
		game.getLoader().unloadSounds(
				new Array<Asset>(new Asset[] { new Asset("battle.ogg",
						"soundtrack") }));
	}

	public void update(float delta) {
		// Verificar vit�ria
		if (this.battlePlatform.getRules().gameState()
				.equals(DefaultRules.ENEMY_VICTORY)) {
			playerLose();
		} else if (this.battlePlatform.getRules().gameState()
				.equals(DefaultRules.PLAYER_VICTORY)) {
			VictoryScreen victory = new VictoryScreen(game, controller, null,
					null, false);
			victory.setBattleScreen(this);
			victory.setIsInCampaign(isInCampaign);
			victory.setMessage(MenuTextDAO.getMenuText().victory);
			game.setScreenWithTransition(victory);
		}
		keyInput(delta);

		this.timeDelay -= delta;
		if (timeDelay <= 0 && !deck.isReady()
				&& this.battlePlatform.getPlayerDeck().getCards().size > 0) {
			deck.setReady(true);
		}
		if (prevTime != (int) timeElapsed) {
			controller.playerEnergyChanged(1);
			controller.enemyEnergyChanged(1);
		}
		this.prevTime = (int) timeElapsed;
		this.timeElapsed += delta;
		moveFixedObjects();
		for (int i = 0; i < stage.getActors().size; i++) {
			Actor a = stage.getActors().get(i);
			if (a.getClass().equals(Fighter2D.class))
				((Fighter2D) (a)).update(delta);
			else if (a.getClass().equals(Effect2D.class)) {
				((Effect2D) (a)).update(delta);
			} else if (a.getClass().equals(Trap2D.class)) {
				((Trap2D) (a)).update(delta);
			}
		}

	}

	public void doBeforeSet() {
	}

	public void moveFixedObjects() {
		for (int i = 0; i < stage.getActors().size; i++) {
			Actor a = stage.getActors().get(i);
			if (a.getClass().equals(HandCard.class)) {
				((HandCard) a).move(this.stage, CAMERA_INITIAL_X);
			}
		}

		deck.move(this.stage, CAMERA_INITIAL_X);
		energyBar.move(this.stage, CAMERA_INITIAL_X);
		background.move(this.stage, CAMERA_INITIAL_X);
		timer.move(this.stage, CAMERA_INITIAL_X);
		playerLife.move(this.stage, CAMERA_INITIAL_X);
		enemyLife.move(this.stage, CAMERA_INITIAL_X);
		menu.move(this.stage, CAMERA_INITIAL_X);
		magnifier.move(this.stage, CAMERA_INITIAL_X);
		playerLife.update(battlePlatform.getRules().getPlayerHP(),
				battlePlatform.getRules().getPlayerHPmax());
		enemyLife.update(battlePlatform.getRules().getEnemyHP(), battlePlatform
				.getRules().getEnemyHPmax());
		energyBar.update();
		enemyLife.drawLife(battlePlatform.getRules().getEnemyHP(),
				battlePlatform.getRules().getEnemyHPmax());
		playerLife.drawLife(battlePlatform.getRules().getPlayerHP(),
				battlePlatform.getRules().getPlayerHPmax());
		energyBar.drawEnergy(battlePlatform.getRules().getPlayerEnergy(),
				battlePlatform.getRules().getPlayerEnergyMax());
		for (FixedImage image : fixedImages)
			image.move(stage, CAMERA_INITIAL_X);
		for (FixedLabel label : cardInfo)
			label.move(stage, CAMERA_INITIAL_X);
	}

	/***
	 * Manages input from the keyboard.
	 */

	private void keyInput(float delta) {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
				|| Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (camera.position.x > 480)
				camera.position.x -= 10;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
				|| Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (camera.position.x < 1440)
				camera.position.x += 10;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.P)
				|| Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			controller.menuButtonClicked(game);
		}
		if (game.getMode() == RunMode.DEBUG
				&& Gdx.input.isKeyPressed(Input.Keys.UP)) {
			this.controller.playerLifeChanged(4);
		}
		if (game.getMode() == RunMode.DEBUG
				&& Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			this.controller.playerLifeChanged(-4);
		}
		if (game.getMode() == RunMode.DEBUG
				&& Gdx.input.isKeyPressed(Input.Keys.W)) {
			this.controller.playerEnergyChanged(4);
		}
		if (game.getMode() == RunMode.DEBUG
				&& Gdx.input.isKeyPressed(Input.Keys.S)) {
			this.controller.playerEnergyChanged(-4);
		}
		if (game.getMode() == RunMode.DEBUG
				&& Gdx.input.isKeyPressed(Input.Keys.I)) {
			this.controller.enemyLifeChanged(4);
		}
		if (game.getMode() == RunMode.DEBUG
				&& Gdx.input.isKeyPressed(Input.Keys.K)) {
			this.controller.enemyLifeChanged(-4);
		}
	}

	/***
	 * Invoked by resizing events. It establishes the visual elements'
	 * disposition on the screen and adds the actors to the stage.
	 */

	public void initComponents() {
		System.out.println("BattleScreen: initComponents");
		if (!initialized) {
			TextureRegion mapImage = new TextureRegion(game.getTexture(
					battlePlatform.getMap().getName(), "battle_maps"),
					SettingsDAO.getSettings().backgroundWidth / 2,
					SettingsDAO.getSettings().backgroundHeight / 2);
			map2d = new BattleMap2D(controller, mapImage);
			map2d.setWidth(SettingsDAO.getSettings().backgroundWidth);
			map2d.setHeight(SettingsDAO.getSettings().backgroundHeight);
			map2d.setX(0);
			map2d.setY(BACKGROUND_Y);

			background = new HandBackground(0, game);
			background.setY(0);

			menu = new MenuButton(controller, game);
			menu.setScale(1.5f);

			inputSources.addProcessor(menu);
			inputSources.addProcessor(map2d);
			deck = new Deck2D(game, SettingsDAO.getSettings().deckX);
			deck.setY(SettingsDAO.getSettings().bottomElementY);
			inputSources.addProcessor(deck);

			energyBar = new EnergyBar(SettingsDAO.getSettings().energyX - 40,
					game);
			energyBar.setY(SettingsDAO.getSettings().bottomElementY);

			playerLife = new LifeBar(SettingsDAO.getSettings().playerLifeX,
					game);
			playerLife.setY(SettingsDAO.getSettings().lifeBarY);

			enemyLife = new LifeBar(SettingsDAO.getSettings().enemyLifeX, game);
			enemyLife.setY(SettingsDAO.getSettings().lifeBarY);

			timer = new Timer2D(this, SettingsDAO.getSettings().mapWidth / 2);
			timer.setY(SettingsDAO.getSettings().mapHeight - 40);

			magnifier = new FixedImage(game.getTextureRegion("magnifier",
					"game_ui_images"), 0, this) {
				@Override
				public boolean touchUp(int screenX, int screenY, int pointer,
						int button) {

					int deltaCamX = (int) (getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);
					int invertY = (int) ((controller.getCurrentScreen()
							.getHeight() - screenY) * (float) ((float) SettingsDAO
							.getSettings().mapHeight / (float) controller
							.getCurrentScreen().getHeight()));
					screenX = (int) (screenX * (float) ((float) SettingsDAO
							.getSettings().mapWidth / (float) controller
							.getCurrentScreen().getWidth()));

					if (screenX + deltaCamX >= magnifier.getX()
							&& screenX + deltaCamX <= magnifier.getX()
									+ magnifier.getWidth()
									* magnifier.getScaleX()
							&& invertY >= magnifier.getY()
							&& invertY <= magnifier.getY()
									+ magnifier.getHeight()
									* magnifier.getScaleY()) {
						if (!dialogActive) {
							if (controller.getCardFromImage(selectedCard) != null)
								showCardInfo();
						} else {
							closeDialog();
						}
					}

					return false;
				}

			};
			magnifier.setY(152);
			magnifier.setScale(0.5f);
			inputSources.addProcessor(magnifier);
			initialized = true;

		}

		stage.addActor(background);
		stage.addActor(deck);
		stage.addActor(energyBar);
		stage.addActor(map2d);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				hexagrams.add(new Hexagram(SettingsDAO.getSettings().tileWidth
						* 2 + SettingsDAO.getSettings().tileWidth * 2 * i,
						BACKGROUND_Y + 60 + 72 * j, game));
				stage.addActor(hexagrams.get(i * 4 + j));
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				BackCard bc = new BackCard(SettingsDAO.getSettings().tileWidth
						* 2 + SettingsDAO.getSettings().tileWidth * 2 * i
						+ SettingsDAO.getSettings().tileWidth / 2, BACKGROUND_Y
						+ 60 + 72 * j, game);

				backcards.add(bc);
				stage.addActor(backcards.get(i * 4 + j));
			}
		}

		for (int i = 30; i > 25; i--) {
			for (int j = 0; j < 4; j++) {
				BackCard bc = new BackCard(SettingsDAO.getSettings().tileWidth
						* 2 + SettingsDAO.getSettings().tileWidth * 2 * i
						+ SettingsDAO.getSettings().tileWidth / 2, BACKGROUND_Y
						+ 60 + 72 * j, game);

				backcards.add(bc);
				stage.addActor(bc);

			}
		}

		stage.addActor(playerLife);
		stage.addActor(enemyLife);

		stage.addActor(timer);
		stage.addActor(menu);
		stage.addActor(magnifier);
		enemyLife.drawLife(battlePlatform.getRules().getEnemyHP(),
				battlePlatform.getRules().getEnemyHPmax(), super.getSkin());
		playerLife.drawLife(battlePlatform.getRules().getPlayerHP(),
				battlePlatform.getRules().getPlayerHPmax(), super.getSkin());
		energyBar
				.initEnergy(battlePlatform.getRules().getPlayerEnergy(),
						battlePlatform.getRules().getPlayerEnergyMax(),
						super.getSkin());
		playerLife.update(battlePlatform.getRules().getPlayerHP(),
				battlePlatform.getRules().getPlayerHPmax());
		enemyLife.update(battlePlatform.getRules().getEnemyHP(), battlePlatform
				.getRules().getEnemyHPmax());
		energyBar.update();

		for (int i = 0; i < 5; i++) {
			Card temp = battlePlatform.getPlayerDeck().draw();
			battlePlatform.getPlayerHandCards().add(temp);
			HandCard h = new HandCard(game, temp.getName(),
					SettingsDAO.getSettings().firstCardX + 130 * i, temp);
			h.setY(SettingsDAO.getSettings().bottomElementY);
			h.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					super.clicked(event, x, y);
					showResumedCardInfo();
				}
			});
			stage.addActor(h);
		}
		for (int i = 0; i < 5; i++) {
			Card temp = battlePlatform.getEnemyDeck().draw();
			battlePlatform.getEnemyHandCards().add(temp);
		}

		for (Actor a : stage.getActors()) {
			if (a.getClass().equals(HandCard.class)) {
				if (!inputSources.getProcessors().contains((HandCard) a, false))
					inputSources.addProcessor((HandCard) a);
			}
		}

	}

	public void insertHandCard(Card card) {
		HandCard handCard = new HandCard(game, card.getName(),
				SettingsDAO.getSettings().firstCardX + 130
						* (battlePlatform.getPlayerHandCards().size), card);
		handCard.setY(SettingsDAO.getSettings().bottomElementY);
		handCard.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showResumedCardInfo();
				super.clicked(event, x, y);
			}

		});
		stage.addActor(handCard);
		this.inputSources.addProcessor(handCard);
	}

	public void changeHexagram(boolean isSelected) {
		if (isSelected) {
			for (int i = 0; i < 20; i++) {
				if (backcards.get(i).isVisible() != true)
					hexagrams.get(i).setVisible(true);

			}
		} else {
			for (int i = 0; i < 20; i++) {

				hexagrams.get(i).setVisible(false);

			}
		}

	}

	public void playerLose() {
		DefeatScreen defeatScreen = new DefeatScreen(game, controller, null,
				null);
		defeatScreen.setMessage(MenuTextDAO.getMenuText().defeat);
		defeatScreen.setBattleScreen(this);
		game.setScreenWithTransition(defeatScreen);
	}

	private void closeDialog() {
		for (Actor actor : dialogActors) {
			actor.remove();
		}
		dialogActive = false;
	}

	private void showCardInfo() {
		dialogActors = new Array<Actor>();

		Card card = controller.getCardFromImage(selectedCard);
		// NOME
		Label name = new Label(card.getName(), super.getSkin());
		name.setX(410);
		name.setWidth(500);
		name.setHeight(1200);
		name.setWrap(true);
		name.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.WHITE));
		// DESCRI��O
		Label description = new Label(card.getDescription(), super.getSkin());
		description.setX(410);
		description.setWidth(500);
		description.setHeight(800);
		description.setWrap(true);
		description.setStyle(new LabelStyle(super.getSkin().getFont(
				"andalus-font"), Color.WHITE));
		// BOX
		Image box = new Image(game.getTextureRegion("box", "game_ui_images"));
		ScreenUtils.defineImage(box, 50, 177, 940, 600, 0.9f, 0.76f);
		// IMAGEM
		Image cardImage = new Image(game.getTextureRegion(card.getName()
				.toLowerCase(), "cards"));

		ScreenUtils.defineImage(cardImage, 60, 205, cardImage.getWidth(),
				cardImage.getHeight(), 1.5f, 1.5f);

		cardImage.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_2");

			}
		});
		stage.addActor(box);
		stage.addActor(name);
		stage.addActor(description);
		stage.addActor(cardImage);
		dialogActors.add(box);
		dialogActors.add(name);
		dialogActors.add(description);
		dialogActors.add(cardImage);
		dialogActive = true;
	}

	public void showResumedCardInfo() {

		removeResumedCardInfo();
		Card card = controller.getCardFromImage(selectedCard);
		Label cardName = new FixedLabel(card.getLocalizedName(), 60, this);
		cardName = ScreenUtils.defineLabel(cardName, 60, 150, 200, 70);
		Label energyCost = ScreenUtils
				.defineLabel(
						new FixedLabel(String.valueOf(card.getEnergyCost()),
								365, this), 365, 150, 200, 70);
		if (card.getClass().equals(Fighter.class)
				&& !card.getName().equals("Rock")) {
			FixedLabel life = (FixedLabel) ScreenUtils.defineLabel(
					new FixedLabel("HP : "
							+ String.valueOf(((Fighter) card).getHealth()),
							440, this), 440, 150, 200, 70);
			stage.addActor(life);
			cardInfo.add(life);
			FixedLabel atk = (FixedLabel) ScreenUtils.defineLabel(
					new FixedLabel("ATK : "
							+ String.valueOf(((Fighter) card).getDamage()),
							860, this), 860, 150, 200, 70);
			stage.addActor(atk);
			cardInfo.add(atk);
			FixedLabel spd = (FixedLabel) ScreenUtils.defineLabel(
					new FixedLabel("SPD : "
							+ String.valueOf(((Fighter) card).getSpeedValue()),
							600, this), 600, 150, 200, 70);
			stage.addActor(spd);
			cardInfo.add(spd);
		}
		stage.addActor(cardName);
		stage.addActor(energyCost);
		cardInfo.add((FixedLabel) cardName);
		cardInfo.add((FixedLabel) energyCost);
	}

	public void removeResumedCardInfo() {

		for (Label label : cardInfo) {

			stage.removeActor(label);

		}
		cardInfo.clear();

	}

	public Array<BackCard> getBackcards() {
		return backcards;
	}

	public void setBackcards(Array<BackCard> backcards) {
		this.backcards = backcards;
	}

	public BattlePlatform getBattlePlatform() {
		return battlePlatform;
	}

	public void setBattlePlatform(BattlePlatform battlePlatform) {
		this.battlePlatform = battlePlatform;
	}

	public InputMultiplexer getInputSources() {
		return inputSources;
	}

	public void setInputSources(InputMultiplexer inputSources) {
		this.inputSources = inputSources;
	}

	public float getTimeDelay() {
		return timeDelay;
	}

	public void setTimeDelay(float timeDelay) {
		this.timeDelay = timeDelay;
	}

	public Deck2D getDeck() {
		return deck;
	}

	public LifeBar getPlayerLife() {
		return playerLife;
	}

	public void setEnemyLife(LifeBar enemyLife) {
		this.enemyLife = enemyLife;
	}

	public LifeBar getEnemyLife() {
		return enemyLife;
	}

	public EnergyBar getEnergyBar() {
		return energyBar;
	}

	public Timer2D getTimer() {
		return timer;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

}
