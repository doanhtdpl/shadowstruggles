package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.object2d.TransitionControl;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class CheckCardsScreen extends BaseScreen {

	private InGameMenu menu;
	private Image selectedImage;
	private Image background;
	private Label name;
	private Label description;
	private TransitionControl right;
	private TransitionControl left;
	private TextButton exit;
	private Image box;

	private Card selectedCard;
	private Array<Card> cards;
	private Array<Image> cardImages;

	public CheckCardsScreen(ShadowStruggles game, Controller controller,
			InGameMenu menu) {
		super(game, controller);
		this.menu = menu;
		initComponents();
	}

	private void initComponents() {
		final InGameMenu menu = this.menu;
		background = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("msbackground"));
		background.setScale((960f / 512f), (640f / 380f));

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

		box = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"));
		ScreenUtils.defineImage(box, 390, 177, 600, 600, 0.9f, 0.76f);

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

			}
		});

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(name);
		stage.addActor(description);
		stage.addActor(exit);
		stage.addActor(right);
		cards = new Array<Card>();
		cards.add(DeckDAO
				.getDeck(
						menu.getController().getPlatform().getPlayerDeck()
								.getName(), game.getManager()).getCards()
				.get(0));
		for (Card nextCard : DeckDAO.getDeck(
				menu.getController().getPlatform().getPlayerDeck().getName(),
				game.getManager()).getCards()) {
			boolean b = true;
			for (Card card : cards) {
				if (card.getName().equals(nextCard.getName())) {
					b = false;
					break;
				}
			}
			if (b)
				cards.add(nextCard);
		}
		int count = 0;
		cardImages = new Array<Image>();
		for (Card card : cards) {
			Image cardImage = new Image(game.getAssets()
					.get("data/images/cards/cards.atlas", TextureAtlas.class)
					.findRegion(card.getName().toLowerCase()));
			ScreenUtils.defineImage(cardImage, 180 + count * 120, 5,
					cardImage.getWidth(), cardImage.getHeight(),0.6f,0.6f);

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
		changeCard(cards.get(0));
		stage.addActor(right);
		stage.addActor(left);
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
		description.setText(card.getDescription());
		description.setHeight(description.getPrefHeight());
		description.setY(590 - description.getHeight());
		try {
			stage.removeActor(selectedImage);
		} catch (Exception e) {
		}
		selectedImage = new Image(game.getAssets()
				.get("data/images/cards/cards.atlas", TextureAtlas.class)
				.findRegion(card.getName().toLowerCase()));
		selectedImage.setX(20);
		selectedImage.setY(160);
		name.setText(selectedCard.getNameVisualization());
		name.setY(590);
		stage.addActor(selectedImage);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

}
