package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Shop;
import br.edu.ifsp.pds.shadowstruggles.object2d.Arrow;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class ShopScreen extends BaseScreen {

	private BaseScreen previousScreen;
	private Shop shop;
	private Image selectedImage;
	private Label name;
	private Label description;
	private Label price;
	private Label moneyLabel;
	private Arrow right;
	private Arrow left;
	private TextButton exit;
	private TextButton buyButton;
	private Card selectedCard;
	private Array<Card> cards;
	private Array<Image> cardImages;
	private TextButton packsButton;
	private TextButton extraButton;
	private TextButton cardsButton;
	private TextButton sellButton;

	public ShopScreen(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);
		this.previousScreen = previousScreen;
		this.shop = new Shop(game);
		initComponents();
	}

	private void initComponents() {
		final BaseScreen menu = this.previousScreen;
		
		Table menuTable = new Table();
		menuTable.defaults().padTop(10).width(160).height(50);

		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		
		packsButton = new TextButton("Packs", getSkin());
		packsButton = ScreenUtils.defineButton(packsButton, 0, 0, 0, 0, super.getSkin());
		packsButton.addListener(new ClickListener(){@Override
		public void clicked(InputEvent event, float x, float y) {
			changeShop(1);
		}});
		
		extraButton = new TextButton("Extra", getSkin());
		extraButton = ScreenUtils.defineButton(extraButton, 0, 0, 0, 0, super.getSkin());
		extraButton.addListener(new ClickListener(){@Override
		public void clicked(InputEvent event, float x, float y) {
			changeShop(2);
		}});
		
		cardsButton = new TextButton("Cards", getSkin());
		cardsButton = ScreenUtils.defineButton(cardsButton, 0, 0, 0, 0, super.getSkin());
		cardsButton.addListener(new ClickListener(){@Override
		public void clicked(InputEvent event, float x, float y) {
			changeShop(3);
		}});
		
		sellButton = new TextButton("Sell", getSkin());
		sellButton = ScreenUtils.defineButton(sellButton, 0, 0, 0, 0, super.getSkin());
		sellButton.addListener(new ClickListener(){@Override
		public void clicked(InputEvent event, float x, float y) {
			changeShop(4);
		}});
		
		exit = new TextButton("Back", getSkin());
		exit = ScreenUtils.defineButton(exit, 0, 0, 0, 0, super.getSkin());
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				ProfileDAO.createProfile(game.getProfile());
			}
		});

		buyButton = new TextButton("Buy", getSkin());// TODO: string Buy p/
														// arquivo
		buyButton = ScreenUtils.defineButton(buyButton, 0, 0, 0, 0,
				super.getSkin());
		buyButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				shop.buyCard(selectedCard);
				moneyLabel.setText("Money: $ "
						+ String.valueOf(shop.getPlayerMoney()));// TODO: string
																	// Money
																	// para
																	// arquivo
			}
		});
		
		menuTable.add(packsButton);
		menuTable.row();
		menuTable.add(extraButton);
		menuTable.row();
		menuTable.add(cardsButton);
		menuTable.row();
		menuTable.add(sellButton);
		menuTable.row();
		menuTable.add(buyButton);
		menuTable.row();
		menuTable.add(exit);
		menuTable.row();
		menuTable.setPosition(100, 300);
		
		stage.addActor(menuTable);
		
		
		
		Table cardsTable = new Table();
		cardsTable.defaults().width(150).height(120);
		
		if (game.getMode() == RunMode.DEBUG)
			cardsTable.debug();
		
		ImageButton cardImgButton = new ImageButton(this.getSkin().getDrawable("placeholder"));

		name = new Label("Carta Teste", super.getSkin());
		name.setWrap(true);
		name.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.WHITE));
		
		price = new Label("1000", super.getSkin());
		price.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.WHITE));
		
		cardsTable.add(cardImgButton);
		cardsTable.row().height(40);;
		cardsTable.add(name);
		cardsTable.row().height(40);
		cardsTable.add(price);
		
		cardsTable.setPosition(350, 500);
		stage.addActor(cardsTable);
		
//		description = new Label("", super.getSkin());
//		description.setWrap(true);
//		description.setStyle(new LabelStyle(super.getSkin().getFont(
//				"andalus-font"), Color.BLACK));

//		moneyLabel = new Label("", super.getSkin());
//		moneyLabel.setText("Money: $ " + String.valueOf(shop.getPlayerMoney()));
//		// TODO: string Money para arquivo
//		moneyLabel.setWidth(200);
//		moneyLabel.setHeight(50);
//		moneyLabel.setStyle(new LabelStyle(super.getSkin().getFont(
//				"andalus-font"), Color.BLACK));

		
		
		
		Table rightButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			rightButtonTable.debug();
		rightButtonTable.defaults().width(50).height(50);
		right = new Arrow(1, this.getSkin());
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(1);
			}
		});
		rightButtonTable.add(right);
		rightButtonTable.setPosition(900, 300);

		Table leftButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			leftButtonTable.debug();
		leftButtonTable.defaults().width(50).height(50);
		left = new Arrow(-1, this.getSkin());
		left.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveCards(-1);

			}
		});
		leftButtonTable.add(left).left();
		leftButtonTable.setPosition(250, 300);
		
		stage.addActor(leftButtonTable);
		stage.addActor(rightButtonTable);

//		cards = shop.getAvailableCards();
//		int count = 0;
//		cardImages = new Array<Image>();
//
//		for (Card card : cards) {
//			Image cardImage = new Image(game.getAssets()
//					.get("data/images/cards/cards.atlas", TextureAtlas.class)
//					.findRegion(card.getName().toLowerCase()));
//			cardImage.setY(10);
//			cardImage.setX(180 + count * 135);
//			cardImage.setScaleX(0.5f);
//			cardImage.setScaleY(0.5f);
//			final Card card2 = card;
//			cardImage.addListener(new ClickListener() {
//
//				@Override
//				public void clicked(InputEvent event, float x, float y) {
//					game.getAudio().playSound("button_2");
//					changeCard(card2);
//				}
//			});
//			cardImages.add(cardImage);
//			if (cardImage.getX() >= 180 && cardImage.getX() < 800)
//				stage.addActor(cardImage);
//			count++;
//		}
//		changeCard(cards.get(0));
	}

	protected void changeShop(int tela) {
		// TODO Efetuar a troca de tela quando clicar em um item do menu
		
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
				card.setX(card.getX() - 135 * side);
				if (card.getX() >= 135 && card.getX() < 800)
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
		selectedImage.setX(50);
		selectedImage.setY(256);
		selectedImage.setScaleX(1.3f);
		selectedImage.setScaleY(1.3f);
		name.setText(selectedCard.getNameVisualization());
		name.setY(590);
		stage.addActor(selectedImage);
		buyButton.setText("Buy ($ " + card.getBuyCost() + ")");
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		Table.drawDebug(stage);
	}
}
