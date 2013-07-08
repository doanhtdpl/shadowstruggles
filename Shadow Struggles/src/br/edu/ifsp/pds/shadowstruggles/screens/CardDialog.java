package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.Card;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Dialog for showing a card's details.
 */
public class CardDialog extends Dialog {
	private ShadowStruggles game;
	private Card card;
	private Skin skin;
	private Image box;
	private Image cardImage;
	private Label name;
	private Label description;

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
		this.cardImage = new Image(game.getTextureRegion(card.getName()
				.toLowerCase(), "cards"));
		this.box = new Image(skin.getDrawable("box"));

		this.name = new Label(card.getNameVisualization(), skin);
		this.name.setColor(Color.BLACK);
		this.name.setWrap(true);

		this.description = new Label(card.getDescription(), skin);
		this.description.setColor(Color.BLACK);
		this.description.setWrap(true);

		this.add(cardImage);
		this.add(box);
		this.add(name);
		this.add(description);
		this.button("Ok", true);
	}
}
