package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.object2d.TransitionControl;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class EditDeckScreen extends BaseScreen {
	private BaseScreen previousScreen;

	private Image background;
	private Image box;
	private Label name;
	private Label description;
	private Label decks;
	private TransitionControl right;
	private TransitionControl left;
	private TextButton exit;
	private TextButton moveCard;
	private Card selectedCard;
	private Deck selectedDeck;
	private Array<Image> cardImages;
	private Array<Card> trunk;

	public EditDeckScreen(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);
		this.previousScreen = previousScreen;
		initComponents();

	}

	private void initComponents() {
		final BaseScreen menu = this.previousScreen;
		this.selectedDeck = game.getProfile().getDeck(game.getManager());
		this.trunk = game.getProfile().getTrunk();
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);

		name = new Label("", super.getSkin());
		name.setX(410);
		name.setWidth(500);
		name.setHeight(50);
		name.setWrap(true);
		name.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		
		description = new Label("", super.getSkin());
		description.setX(410);
		description.setWidth(500);
		description.setWrap(true);
		description.setStyle(new LabelStyle(super.getSkin().getFont(
				"andalus-font"), Color.BLACK));

		decks = new Label("", super.getSkin());
		decks.setText("Decks:");
		decks.setX(30);
		decks.setY(590);
		decks.setWidth(200);
		decks.setHeight(50);
		decks.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));

		box = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"));
		box.setWidth(780);
		box.setHeight(600);
		box.setX(180);
		box.setY(177);
		box.setScaleX(0.9f);
		box.setScaleY(0.76f);

		right = new TransitionControl(1, this.getSkin());
		right.setY(150);
		right.setX(900);
		right.setScaleY(4f);
		right.setScaleX(1.5f);
		right.setRotation(180);
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(1);

			}
		});
		left = new TransitionControl(-1, this.getSkin());
		left.setY(20);
		left.setX(120);
		left.setScaleY(4f);
		left.setScaleX(1.5f);
		left.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(-1);

			}
		});

		exit = new TextButton("M", getSkin());
		exit = ScreenUtils.defineButton(exit, 15, 15, 80, 80, super.getSkin());
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				game.getManager().writeProfile(game.getProfile());
			}
		});

		moveCard = new TextButton("Move", getSkin());// TODO: string Move p/
														// arquivo
		moveCard = ScreenUtils.defineButton(moveCard, 15, 180, 170, 70,
				super.getSkin());
		moveCard.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");

			}
		});

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(name);
		stage.addActor(description);
		stage.addActor(exit);
		stage.addActor(right);
		stage.addActor(decks);
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
		stage.addActor(right);
		stage.addActor(left);
		stage.addActor(moveCard);
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
}
