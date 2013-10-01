package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.items.Pack;
import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.object2d.Arrow;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

// TODO: Acrescentar label para o dinheiro.
public class ShopScreen extends BaseScreen {
	/**
	 * Maximum size for the items displayed on screen.
	 */
	private final int SELECTION_SIZE = 9;

	private static enum Category {
		PACK, EXTRA, CARD
	};

	private static enum Mode {
		BUY, SELL
	};

	private BaseScreen previousScreen;
	private boolean mainShop;
	private Shop shop;
	private Mode currentMode;
	private Category currentCategory;
	/**
	 * Start index for the items' current selection.
	 */
	private int startIndex = 0;

	private Arrow right;
	private Arrow left;
	private TextButton exit;
	private TextButton packsButton;
	private TextButton extraButton;
	private TextButton cardsButton;
	private TextButton buySellButton;

	/**
	 * Temporary table used for CardDialog.
	 */
	private Table tmpTable;
	private Table itemsTable;

	/**
	 * A screen for displaying the shop.
	 * 
	 * @param shop
	 *            If null, it assumes this is the main shop.
	 */
	public ShopScreen(ShadowStruggles game, Shop shop, Controller controller,
			BaseScreen previousScreen) {
		super(game, controller);

		this.previousScreen = previousScreen;
		this.shop = shop;
		this.currentMode = Mode.BUY;
		this.currentCategory = Category.CARD;
		if (shop == null)
			this.mainShop = true;
	}

	public void initComponents() {
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

		showItems();

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
	 * Creates and displays the items table according to the current category
	 * and mode.
	 */
	private void showItems() {
		if (itemsTable != null)
			stage.removeActor(itemsTable);
		itemsTable = new Table();
		if (game.getMode() == RunMode.DEBUG)
			itemsTable.debug();
		itemsTable.setPosition(570, 320);

		Array<Item> items = new Array<Item>();

		if (this.currentMode == Mode.SELL)
			items = game.getProfile().getInventory();
		else {
			if (this.mainShop)
				items = game.getProfile().getUnlockedItems();
			else
				items = shop.getItems();
		}

		// Auxiliary variables for adding rows in the table as necessary,
		// according to the maximum amount of columns.
		int cols = 0, maxCols = 3;
		for (int i = startIndex; i < startIndex + SELECTION_SIZE;) {
			if (i >= items.size)
				break;

			Item item = items.get(i);
			if ((currentCategory == Category.CARD && !(item instanceof Card))
					|| (currentCategory == Category.PACK && !(item instanceof Pack)))
				continue;

			String resourceType = "item_icons";
			if (this.currentCategory == Category.CARD)
				resourceType = "cards";

			Image cardImage = new Image(game.getTextureRegion(item.getName()
					.toLowerCase(), resourceType));
			ImageButton cardImgButton = new ImageButton(cardImage.getDrawable());

			// If card, display CardDialog when clicked.
			if (item instanceof Card) {
				final Card itemAsCard = (Card) item;
				cardImgButton.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						tmpTable = new Table();
						if (game.getMode() == RunMode.DEBUG)
							tmpTable.debug();

						tmpTable.setPosition(450, 340);
						tmpTable.add(
								new CardDialog(game, itemAsCard, getSkin()))
								.width(900).height(500);
						stage.addActor(tmpTable);
					}
				});
			}

			Label name = new Label(item.getLocalizedName(), super.getSkin());
			name.setWrap(true);
			name.setStyle(new LabelStyle(super.getSkin()
					.getFont("andalus-font"), Color.WHITE));

			Label price = new Label("1000", super.getSkin());
			price.setStyle(new LabelStyle(super.getSkin().getFont(
					"andalus-font"), Color.WHITE));

			// Table for showing short info (name, price).
			Table itemTable = new Table();
			itemTable.defaults().width(150).height(120);
			if (game.getMode() == RunMode.DEBUG)
				itemTable.debug();
			itemTable.add(cardImgButton);
			itemTable.row().height(40);
			itemTable.add(name);
			itemTable.row().height(40);
			itemTable.add(price);

			if (cols == maxCols) {
				itemsTable.row();
				cols = 0;
			}
			itemsTable.add(itemTable);
			cols++;
			i++;
		}
		stage.addActor(itemsTable);
	}

	/**
	 * Changes the current category.
	 */
	private void changeCategory(Category category) {
		this.currentCategory = category;
		this.startIndex = 0;
		showItems();
	}

	/**
	 * Changes to another visualization mode (buying, selling).
	 */
	private void changeMode(Mode mode) {
		this.currentMode = mode;
		this.startIndex = 0;
		showItems();
	}

	/**
	 * Shows additional or previous items from the selected category.
	 * 
	 * @param side
	 *            1 moves to the right and -1 moves to the left.
	 */
	private void moveItems(int side) {
		this.startIndex += side * SELECTION_SIZE;
		if (this.startIndex < 0)
			this.startIndex = 0;
		showItems();
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public Array<Asset> textureRegionsToLoad() {
		Array<Asset> assets = new Array<Asset>();
		// Array for keeping track of items, making sure that there are no
		// duplicates.
		Array<String> previousItems = new Array<String>();

		Array<Item> shopItems = null;
		if (this.mainShop)
			shopItems = game.getProfile().getUnlockedItems();
		else
			shopItems = shop.getItems();

		for (Item i : shopItems) {
			String itemName = i.getName().toLowerCase();
			if (!previousItems.contains(itemName, false)) {
				if (i instanceof Card)
					assets.add(new Asset(itemName + ".png", "cards"));
				else
					assets.add(new Asset(itemName + ".png", "item_icons"));
				previousItems.add(itemName);
			}
		}

		for (Item i : game.getProfile().getInventory()) {
			String itemName = i.getName().toLowerCase();
			if (!previousItems.contains(itemName, false)) {
				if (i instanceof Card)
					assets.add(new Asset(itemName + ".png", "cards"));
				else
					assets.add(new Asset(itemName + ".png", "item_icons"));
				previousItems.add(i.getName());
			}
		}

		return assets;
	}

	@Override
	public void dispose() {
		super.dispose();
		game.getLoader().disposeAtlas();
	}
}
