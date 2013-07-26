package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.model.Card;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Dialog for showing a card's details.
 */
public class CardDialog extends Dialog {
	private ShadowStruggles game;
	private Card card;
	private Skin skin;
	private Image cardImage;
	private Label name;
	private Label description;
	private TextButton okButton;
	private ScrollPane scroll;

	public CardDialog(ShadowStruggles game, Card card, String title, Skin skin) {
		super(title, skin);

		this.setWidth(480);
		this.setHeight(320);

		this.game = game;
		this.card = card;
		this.skin = skin;

		initComponents();
	}

	public CardDialog(ShadowStruggles game, Card card, String title, Skin skin,
			String windowStyleName) {
		super(title, skin, windowStyleName);

		this.setWidth(480);
		this.setHeight(320);

		this.game = game;
		this.card = card;
		this.skin = skin;

		initComponents();
	}

	private void initComponents() {
		Table inTable = new Table();
		inTable.defaults().height(100).width(220);
		if (game.getMode() == RunMode.DEBUG)
			inTable.debug();

		this.cardImage = new Image(game.getTextureRegion(card.getName()
				.toLowerCase(), "cards"));

		this.name = new Label(card.getNameVisualization(), skin);
		this.name.setColor(Color.BLACK);
		this.name.setWrap(true);

		this.description = new Label(card.getDescription(), skin);
		this.description.setColor(Color.BLACK);
		this.description.setWrap(true);
		
		this.okButton = new TextButton("Ok", skin);
		
		scroll = new ScrollPane(description, skin);

		inTable.add(this.name).height(50);
		inTable.row().height(350);
		inTable.add(scroll);
		inTable.add(this.cardImage).padRight(30);
		inTable.row();
		inTable.add(this.okButton).height(50).colspan(2).center();
		
		this.add(inTable);
	}
}
