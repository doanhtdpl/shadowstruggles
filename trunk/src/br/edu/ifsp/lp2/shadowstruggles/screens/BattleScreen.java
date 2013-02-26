package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Deck;
import br.edu.ifsp.lp2.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.lp2.shadowstruggles.model.Profile;
import br.edu.ifsp.lp2.shadowstruggles.object2d.BackCard;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Deck2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.EnergyBar;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.HandBackground;
import br.edu.ifsp.lp2.shadowstruggles.object2d.HandCard;
import br.edu.ifsp.lp2.shadowstruggles.object2d.LifeBar;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Map2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.MenuButton;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Pentagram;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Timer2D;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Trap2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
	private InputMultiplexer inputSources;

	private Map2D map2d;
	private HandBackground background;
	private boolean inicializado = false;
	private Array<Pentagram> pentagrams;
	private Array<BackCard> backcards;
	
	protected Deck playerDeck;
	protected BattlePlatform battlePlatform;
	protected String name;
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
			Controller controller, BattlePlatform battlePlatform, String name,
			boolean isInCampaign) {
		super(game, controller);

		this.name = name;
		this.isInCampaign = isInCampaign;
		inputSources = new InputMultiplexer();

		controller.setCurrentscreen(this);
		controller.setPlatform(battlePlatform);

		this.battlePlatform = battlePlatform;
		String mapPath = "data/images/maps/"
				+ battlePlatform.getMap().getName() + ".png";
		TextureRegion mapImage = new TextureRegion(new Texture(
				Gdx.files.internal(mapPath)), settings.backgroundWidth / 2,
				settings.backgroundHeight / 2);
		map2d = new Map2D(controller, mapImage);

		timeElapsed = 0;
		timeDelay = 0;
		battlePlatform.getEnemyDeck().shuffle();
		battlePlatform.getPlayerDeck().shuffle();
		pentagrams = new Array<Pentagram>();
		backcards = new Array<BackCard>();
		initComponents();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (game.getScreen().equals(this)) {
			InputMultiplexer clone = new InputMultiplexer();
			clone.getProcessors().addAll(inputSources.getProcessors());
			for (InputProcessor ip : clone.getProcessors()) {
				if (ip.getClass().equals(HandCard.class)) {
					if (!this.stage.getActors().contains((HandCard) ip)) {
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

	public void update(float delta) {
		if (this.battlePlatform.getRules().gameState()
				.equals(DefaultRules.ENEMY_VICTORY)) {
			playerLose();
		} else if (this.battlePlatform.getRules().gameState()
				.equals(DefaultRules.PLAYER_VICTORY)) {
			game.setScreenWithTransition(new PosGameScreen(game, controller,
					game.getManager().getMenuText().victory, this, isInCampaign));
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
		if (game.isDebugMode() && Gdx.input.isKeyPressed(Input.Keys.UP)) {
			this.controller.playerLifeChanged(4);
		}
		if (game.isDebugMode() && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			this.controller.playerLifeChanged(-4);
		}
		if (game.isDebugMode() && Gdx.input.isKeyPressed(Input.Keys.W)) {
			this.controller.playerEnergyChanged(4);
		}
		if (game.isDebugMode() && Gdx.input.isKeyPressed(Input.Keys.S)) {
			this.controller.playerEnergyChanged(-4);
		}
		if (game.isDebugMode() && Gdx.input.isKeyPressed(Input.Keys.I)) {
			this.controller.enemyLifeChanged(4);
		}
		if (game.isDebugMode() && Gdx.input.isKeyPressed(Input.Keys.K)) {
			this.controller.enemyLifeChanged(-4);
		}
	}

	/***
	 * Invoked by resizing events. It establishes the visual elements'
	 * disposition on the screen and adds the actors to the stage.
	 */

	private void initComponents() {
		map2d.setWidth(settings.backgroundWidth);
		map2d.setHeight( settings.backgroundHeight);
		if (!inicializado) {

			map2d.setX(0);
			map2d.setY( BACKGROUND_Y);

			background = new HandBackground(0, game);
			background.setY(0);

			menu = new MenuButton(controller, game);
			menu.setScale( 1.5f);
			
			inputSources.addProcessor(menu);
			inputSources.addProcessor(map2d);
			deck = new Deck2D(game, settings.deckX);
			deck.setY( settings.bottomElementY);
			inputSources.addProcessor(deck);

			energyBar = new EnergyBar(settings.energyX - 40, game);
			energyBar.setY( settings.bottomElementY);

			playerLife = new LifeBar(settings.playerLifeX, game);
			playerLife.setY(settings.lifeBarY);

			enemyLife = new LifeBar(settings.enemyLifeX, game);
			enemyLife.setY( settings.lifeBarY);

			timer = new Timer2D(this, settings.screenWidth / 2);
			timer.setY( settings.screenHeight - 40);

			inicializado = true;

		}

		stage.addActor(background);
		stage.addActor(deck);
		stage.addActor(energyBar);
		stage.addActor(map2d);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				pentagrams.add(new Pentagram(settings.tileWidth * 2
						+ settings.tileWidth * 2 * i, BACKGROUND_Y + 60 + 72
						* j, game));
				stage.addActor(pentagrams.get(i * 4 + j));
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				BackCard bc = new BackCard(settings.tileWidth * 2
						+ settings.tileWidth * 2 * i + settings.tileWidth / 2,
						BACKGROUND_Y + 60 + 72 * j, game);

				backcards.add(bc);
				stage.addActor(backcards.get(i * 4 + j));
			}
		}

		for (int i = 30; i > 25; i--) {
			for (int j = 0; j < 4; j++) {
				BackCard bc = new BackCard(settings.tileWidth * 2
						+ settings.tileWidth * 2 * i + settings.tileWidth / 2,
						BACKGROUND_Y + 60 + 72 * j, game);

				backcards.add(bc);
				stage.addActor(bc);

			}
		}

		stage.addActor(playerLife);
		stage.addActor(enemyLife);

		stage.addActor(timer);
		stage.addActor(menu);

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
					settings.firstCardX + 130 * i, temp);
			h.setY( settings.bottomElementY);
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
				settings.firstCardX + 130
						* (battlePlatform.getPlayerHandCards().size), card);
		handCard.setY( settings.bottomElementY);
		stage.addActor(handCard);
		this.inputSources.addProcessor(handCard);
	}

	public void changePentagram(boolean isSelected) {
		if (isSelected) {
			for (int i = 0; i < 20; i++) {
				if (backcards.get(i).isVisible() != true)
					pentagrams.get(i).setVisible(true);

			}
		} else {
			for (int i = 0; i < 20; i++) {

				pentagrams.get(i).setVisible(false);

			}
		}

	}

	public void playerLose() {
		game.setScreenWithTransition(new DefeatScreen(game, controller, game
				.getManager().getMenuText().defeat, this));
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
