package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.object2d.TransitionControl;
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

public class EditDeckScreen extends BaseScreen {
	private BaseScreen previousScreen;

	private Image background;
	private Image box;
	private Label name;
	private Label description;
	private Label decks;
	private Label deckName;
	private TransitionControl right;
	private TransitionControl left;
	private TextButton exit;
	private TextButton newDeck;
	private TextButton moveCard;
	private Card selectedCard;
	private Deck selectedDeck;
	private Array<Image> cardImages;
	private Array<Card> trunk;
	private static EditDeckScreen instance;

	public static EditDeckScreen getInstance(ShadowStruggles game,
			Controller controller, BaseScreen previousScreen) {
		if (instance != null)
			return instance;
		else {
			instance = new EditDeckScreen(game, controller, previousScreen);
			return instance;
		}
	}

	private EditDeckScreen(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);
		this.previousScreen = previousScreen;
	}

	public void setPreviousScreen(BaseScreen previousScreen) {
		this.previousScreen = previousScreen;
	}

	public void initComponents() {
		stage.clear();
		
		final BaseScreen menu = this.previousScreen;
		this.selectedDeck = game.getProfile().getDeck(game.getManager());
		this.trunk = game.getProfile().getTrunk();
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		Table menuTable = new Table();
		menuTable.defaults().padTop(10).width(160).height(50);
		
		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		
		deckName = new Label("", super.getSkin());
		deckName.setText("Select a Deck");
		deckName.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		
		newDeck = new TextButton(game.getManager().getMenuText().newDeck, super.getSkin());
		newDeck = ScreenUtils.defineButton(newDeck, 0, 0, 0,
				0, super.getSkin());
		//TODO: criar método para criacao de novos decks
		
		decks = new Label("", super.getSkin());
		decks.setText("Deck B");
		decks.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		
		exit = new TextButton(game.getManager().getMenuText().returnToStart, super.getSkin());
		exit = ScreenUtils.defineButton(exit, 0, 0, 0,
				0, super.getSkin());
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				game.getManager().writeProfile(game.getProfile());
			}
		});
		
		
		
		menuTable.add(deckName);
		menuTable.row();
		menuTable.add(decks).height(400);
		menuTable.row();
		menuTable.add(newDeck);
		menuTable.row();
		menuTable.add(exit);
		
		menuTable.setPosition(100, 300);
		
		Table deckTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			deckTable.debug();
		box = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"));
		deckTable.defaults().width(600).height(400);
		box.setWidth(600);
		box.setHeight(400);
		
		deckTable.add(box);
		
		deckTable.setPosition(580, 380);


		Table leftButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			leftButtonTable.debug();
		
		leftButtonTable.defaults().width(100).height(100);
		
		left = new TransitionControl(-1, this.getSkin());
		left.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(-1);

			}
		});
		
		Table rightButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			rightButtonTable.debug();
		
		rightButtonTable.defaults().width(100).height(100);
		
		right = new TransitionControl(1, this.getSkin());
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(1);

			}
		});

		
		int count = 0;
		cardImages = new Array<Image>();
		for (Card card : trunk) {
			Image cardImage = new Image(game.getAssets()
					.get("data/images/cards/cards.atlas", TextureAtlas.class)
					.findRegion(card.getName().toLowerCase()));
			cardImage.setY(5);
			cardImage.setX(180 + count * 120);
			cardImage.setScaleX(0.3f);
			cardImage.setScaleY(0.3f);
			final Card card2 = card;
			cardImage.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.getAudio().playSound("button_2");
					changeCard(card2);
				}
			});
			cardImages.add(cardImage);
			if (cardImage.getX() >= 180 && cardImage.getX() < 900)
				stage.addActor(cardImage);
			count++;
		}
		
		leftButtonTable.add(left).left();
		rightButtonTable.add(right);
		
		leftButtonTable.setPosition(250, 80);
		rightButtonTable.setPosition(900, 80);
		
		stage.addActor(background);
		stage.addActor(menuTable);
		stage.addActor(deckTable);
		stage.addActor(leftButtonTable);
		stage.addActor(rightButtonTable);
		
	}

	private void moveCards(int side) {
		boolean movableToRight = false, movableToLeft = false;
		for (Image card : cardImages) {
			if (card.getX() < 180)
				movableToRight = true;
			if (card.getX() > 780)
				movableToLeft = true;
		}

		if ((side > 0 && movableToLeft) || (side < 0 && movableToRight))
			for (Image card : cardImages) {
				card.setX(card.getX() - 120 * side);
				if (card.getX() >= 120 && card.getX() < 900)
					stage.addActor(card);
				else {
					try {
						stage.removeActor(card);

					} catch (Exception e) {
					}
				}
			}

	}

	private void changeCard(Card card) {
		this.selectedCard = card;

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Table.drawDebug(stage);
	}
}
