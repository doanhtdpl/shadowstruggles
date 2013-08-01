package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.model.Card;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Dialog for showing a card's details.
 */
public class CardDialog extends Dialog {
	private ShadowStruggles game;
	private Card card;
	private Skin skin;
	private Image cardImage;
	private Label description;
	private TextButton okButton;
	private ScrollPane scroll;

	public CardDialog(ShadowStruggles game, Card card, Skin skin) {
		super(card.getNameVisualization(), skin);

		this.game = game;
		this.card = card;
		this.skin = skin;

		initComponents();
	}

	public CardDialog(ShadowStruggles game, Card card, Skin skin,
			String windowStyleName) {
		super(card.getNameVisualization(), skin, windowStyleName);

		this.game = game;
		this.card = card;
		this.skin = skin;

		initComponents();
	}

	private void initComponents() {
		Table inTable = new Table();
		inTable.defaults().height(400).width(400);
		if (game.getMode() == RunMode.DEBUG)
			inTable.debug();

		this.cardImage = new Image(game.getTextureRegion(card.getName()
				.toLowerCase(), "cards"));
		

		this.description = new Label(card.getDescription(), skin);
		this.description.setColor(Color.BLACK);
		this.description.setWrap(true);

		this.okButton = new TextButton("Ok", skin);
		this.okButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				remove();
			}
		});

		scroll = new ScrollPane(description, skin);
		scroll.setFadeScrollBars(false);

		inTable.add(scroll);
		inTable.add(this.cardImage).padRight(30);
		inTable.row();
		inTable.add(this.okButton).height(50).colspan(2).center();
		System.out.println(cardImage.getHeight());
		System.out.println(cardImage.getWidth());
		this.add(inTable);
	}
}
