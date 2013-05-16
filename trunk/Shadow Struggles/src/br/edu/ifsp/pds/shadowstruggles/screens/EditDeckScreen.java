package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.object2d.TransitionControl;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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
	private TextButton moveCard;
	private Card selectedCard;
	private Deck selectedDeck;
	private Array<Image> cardImages;
	private Array<Card> trunk;
	private static EditDeckScreen instance;
	
	public static EditDeckScreen getInstance(ShadowStruggles game, Controller controller,
			BaseScreen previousScreen) {
		if(instance != null)
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
		menuTable.defaults().padTop(10).width(50).height(50);
		
		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		
		deckName = new Label("", super.getSkin());
		deckName.setText("Deck A");
		deckName.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		
		decks = new Label("", super.getSkin());
		decks.setText("Deck B");
		decks.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		
		exit = new TextButton("", super.getSkin().get("blur", TextButtonStyle.class));
		exit.setText("Exit");
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
		menuTable.add(decks);
		menuTable.row();
		menuTable.add(exit);
		
		menuTable.setPosition(100, 500);
		
		stage.addActor(background);
		stage.addActor(menuTable);
		
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
