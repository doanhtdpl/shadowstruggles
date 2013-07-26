package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Shop;
import br.edu.ifsp.pds.shadowstruggles.object2d.Arrow;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

// TODO: Acrescentar label para o dinheiro.
public class ShopScreen extends BaseScreen {

	private static enum Category {
		PACK, EXTRA, CARD
	};

	private static enum Mode {
		BUY, SELL
	};

	private BaseScreen previousScreen;
	private Shop shop;

	private Arrow right;
	private Arrow left;
	private TextButton exit;
	private TextButton packsButton;
	private TextButton extraButton;
	private TextButton cardsButton;
	private TextButton buySellButton;

	private Mode currentMode;
	
	private Table tmpTable;

	public ShopScreen(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);

		this.previousScreen = previousScreen;
		this.shop = new Shop(game);
		this.currentMode = Mode.BUY;

		initComponents();
	}

	private void initComponents() {
		final BaseScreen menu = this.previousScreen;

		Table menuTable = new Table();
		menuTable.defaults().padTop(10).width(160).height(50);

		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();

		packsButton = new TextButton(MenuTextDAO.getMenuText().packs, getSkin());
		packsButton = ScreenUtils.defineButton(packsButton, 0, 0, 0, 0,
				super.getSkin());
		packsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				changeCategory(Category.PACK);
			}
		});

		extraButton = new TextButton(MenuTextDAO.getMenuText().extra, getSkin());
		extraButton = ScreenUtils.defineButton(extraButton, 0, 0, 0, 0,
				super.getSkin());
		extraButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				changeCategory(Category.EXTRA);
			}
		});

		cardsButton = new TextButton(MenuTextDAO.getMenuText().cards, getSkin());
		cardsButton = ScreenUtils.defineButton(cardsButton, 0, 0, 0, 0,
				super.getSkin());
		cardsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				changeCategory(Category.CARD);
			}
		});

		String buySell = currentMode == Mode.BUY ? MenuTextDAO.getMenuText().sell
				: MenuTextDAO.getMenuText().buy;
		buySellButton = new TextButton(buySell, getSkin());
		buySellButton = ScreenUtils.defineButton(buySellButton, 0, 0, 0, 0,
				super.getSkin());
		buySellButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				changeMode(Mode.SELL);
			}
		});

		exit = new TextButton(MenuTextDAO.getMenuText().returnToStart,
				getSkin());
		exit = ScreenUtils.defineButton(exit, 0, 0, 0, 0, super.getSkin());
		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(menu);
				ProfileDAO.createProfile(game.getProfile());
			}
		});

		menuTable.add(packsButton);
		menuTable.row();
		menuTable.add(extraButton);
		menuTable.row();
		menuTable.add(cardsButton);
		menuTable.row();
		menuTable.add(buySellButton);
		menuTable.row();
		menuTable.add(exit).padTop(150);
		menuTable.row();
		menuTable.setPosition(100, 350);

		stage.addActor(menuTable);

		Table cardsTable = new Table();
//		if (game.getMode() == RunMode.DEBUG)
//			cardsTable.debug();
		cardsTable.setPosition(570, 200);

		Array<Card> cards = shop.getAvailableCards();
		// Auxiliary variable for adding rows in the table as necessary.
		int aux = 0;
		for (final Card card : cards) {
			Image cardImage = new Image(game.getTextureRegion(card.getName()
					.toLowerCase(), "cards"));
			ImageButton cardImgButton = new ImageButton(cardImage.getDrawable());
			cardImgButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					tmpTable = new Table();
					if (game.getMode() == RunMode.DEBUG)
						tmpTable.debug();
					
					tmpTable.setPosition(450, 340);
					tmpTable.add(new CardDialog(game, card, card.getNameVisualization(),
							getSkin())).width(500).height(500);
					stage.addActor(tmpTable);
				}
			});

			Label name = new Label(card.getNameVisualization(), super.getSkin());
			name.setWrap(true);
			name.setStyle(new LabelStyle(super.getSkin()
					.getFont("andalus-font"), Color.WHITE));

			Label price = new Label("1000", super.getSkin());
			price.setStyle(new LabelStyle(super.getSkin().getFont(
					"andalus-font"), Color.WHITE));

			Table cardTable = new Table();
			cardTable.defaults().width(150).height(120);
//			if (game.getMode() == RunMode.DEBUG)
//				cardTable.debug();
			cardTable.add(cardImgButton);
			cardTable.row().height(40);
			cardTable.add(name);
			cardTable.row().height(40);
			cardTable.add(price);

			if (aux == 3) {
				cardsTable.row();
				aux = 0;
			}
			cardsTable.add(cardTable);
			aux++;
		}
		stage.addActor(cardsTable);

		Table rightButtonTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			rightButtonTable.debug();
		rightButtonTable.defaults().width(50).height(50);
		right = new Arrow(1, this.getSkin());
		right.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				moveItems(1);
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
				moveItems(-1);
			}
		});
		leftButtonTable.add(left).left();
		leftButtonTable.setPosition(250, 300);

		stage.addActor(leftButtonTable);
		stage.addActor(rightButtonTable);
	}

	/**
	 * Changes the current category.
	 */
	private void changeCategory(Category category) {
		// TODO Efetuar a troca de tela quando clicar em um item do menu
	}

	/**
	 * Changes to another visualization mode (buying, selling).
	 */
	private void changeMode(Mode mode) {
		// TODO: Implementar m�todo.
	}

	/**
	 * Shows additional or previous items from the selected category.
	 */
	private void moveItems(int side) {
		// TODO: Implementar m�todo.
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
