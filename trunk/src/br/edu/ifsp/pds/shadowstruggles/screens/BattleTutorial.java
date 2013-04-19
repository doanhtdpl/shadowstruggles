package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
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
import br.edu.ifsp.pds.shadowstruggles.object2d.Map2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.MenuButton;
import br.edu.ifsp.pds.shadowstruggles.object2d.Timer2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class BattleTutorial extends BaseScreen{
	final static private String TUTORIAL_MAP="cena1";
	private Timer2D timer;
	private EnergyBar energyBar;
	private LifeBar playerLife;
	private LifeBar enemyLife;
	private int prevTime;
	private float timeElapsed;
	private float timeDelay;
	private float time;
	private Deck2D deck;
	private InputMultiplexer inputSources;
	private Map2D map2d;
	private HandBackground background;
	private Array<Hexagram> hexagrams;
	private Array<BackCard> backcards;
	private Array<Label> cardInfo;
	private Array<FixedImage> fixedImages;
	private FixedImage dialogBox;
	private FixedLabel dialogText;
	private FixedImage menu;
	
	private Deck tutorialDeck;
	
	
	public BattleTutorial(ShadowStruggles game) {
		super(game);
		this.controller=new Controller(){@Override
		public void mapClicked(float x, float y) {			
			System.out.println("clicou no mapaa");
		}};
		controller.setCurrentscreen(this);
		inputSources = new InputMultiplexer();
		String mapPath = "data/images/maps/"
				+ TUTORIAL_MAP + ".png";
		TextureRegion mapImage = new TextureRegion(new Texture(
				Gdx.files.internal(mapPath)), settings.backgroundWidth / 2,
				settings.backgroundHeight / 2);
		map2d = new Map2D(controller, mapImage){@Override
		public void moveFixedObjects() {moveObjects();}};
		hexagrams = new Array<Hexagram>();
		backcards = new Array<BackCard>();
		
		tutorialDeck= DeckDAO.getDeck("Tutorial Deck", game.getManager());
		tutorialDeck.shuffle();
		initComponents();
	}
	
	private void initComponents() {
		map2d.setWidth(settings.backgroundWidth);
		map2d.setHeight(settings.backgroundHeight);
		map2d.setX(0);
		map2d.setY(BACKGROUND_Y);

		background = new HandBackground(0, game);
		background.setY(0);

		menu = new FixedImage(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("pause"), 10, this){@Override
				public void clicked() {
					System.out.println("clicouuu");
					//super.clicked();
				}};
		menu.setScale(1.5f);
		menu.setY(560);
		

		
		inputSources.addProcessor(map2d);
		inputSources.addProcessor(menu);
		deck = new Deck2D(game, settings.deckX);
		deck.setY(settings.bottomElementY);
		inputSources.addProcessor(deck);

		energyBar = new EnergyBar(settings.energyX - 40, game);
		energyBar.setY(settings.bottomElementY);

		playerLife = new LifeBar(settings.playerLifeX, game);
		playerLife.setY(settings.lifeBarY);

		enemyLife = new LifeBar(settings.enemyLifeX, game);
		enemyLife.setY(settings.lifeBarY);

		timer = new Timer2D(this, settings.screenWidth / 2);
		timer.setY(settings.screenHeight - 40);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				hexagrams.add(new Hexagram(settings.tileWidth * 2
						+ settings.tileWidth * 2 * i, BACKGROUND_Y + 60 + 72
						* j, game));
				stage.addActor(hexagrams.get(i * 4 + j));
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
		stage.addActor(background);
		for (int i = 0; i < 5; i++) {
			Card temp = tutorialDeck.draw();			
			HandCard h = new HandCard(game, temp.getName(), settings.firstCardX
					+ 130 * i, temp){@Override
					public void unSelect() {
						
					}@Override
						public void clicked() {
							
						}};
			h.setY(settings.bottomElementY);
			h.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					
					super.clicked(event, x, y);
					//showResumedCardInfo();
				}
			});
			stage.addActor(h);
		}

		for (Actor a : stage.getActors()) {
			if (a.getClass().equals(HandCard.class)) {
				if (!inputSources.getProcessors().contains((HandCard) a, false))
					inputSources.addProcessor((HandCard) a);
			}
		}
		
		dialogBox = new FixedImage(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"), 150, this);
		dialogBox=(FixedImage)ScreenUtils.defineImage(dialogBox, 150, 370, 650, 200);
		dialogText= new FixedLabel("Seja bem vindo ao tutorial! Toque aqui para prosseguir.", 170, this);
		dialogText = (FixedLabel)ScreenUtils.defineLabel(dialogText, 170, 400, 550, 200);
		dialogText.setWrap(true);
		
		
		stage.addActor(deck);
		stage.addActor(energyBar);
		stage.addActor(map2d);
		stage.addActor(playerLife);
		stage.addActor(enemyLife);
		stage.addActor(timer);
		stage.addActor(menu);
		stage.addActor(dialogBox);
		stage.addActor(dialogText);
		playerLife.drawLife(100, 100, getSkin());
		enemyLife.drawLife(100, 100, getSkin());
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
			playerLife.drawLife(100, 100);
			enemyLife.drawLife(100, 100);
			 
			time=0;
			this.timeDelay -= delta;
			this.prevTime = (int) timeElapsed;
			this.timeElapsed += delta;
			timer.setTime(timeElapsed);
			 
			
			time+=delta;
			camera.update();
		}
	}
	
	public void moveObjects() {
		//try{
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
		//energyBar.update();		
		//for(FixedImage image : fixedImages) image.move(stage, CAMERA_INITIAL_X);
		dialogBox.move(stage, CAMERA_INITIAL_X);
		dialogText.move(stage, CAMERA_INITIAL_X);
		//}catch (Exception unknownError){}
	}

}
