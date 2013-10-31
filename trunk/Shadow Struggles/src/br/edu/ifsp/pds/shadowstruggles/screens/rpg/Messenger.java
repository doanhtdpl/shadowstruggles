package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

/**
 * Handles the displaying of messages on an {@link RpgScreen}.
 */
public class Messenger {
	/**
	 * The character limit for the amount of text displayed at a time.
	 */
	private static final int CHAR_COUNT = 120;

	private Image background;
	private Label textLbl;
	private RpgScreen screen;

	private Array<String> splittedText;
	private int textIndex = 0;

	public Messenger(String text, ShadowStruggles game) {
		this.screen = (RpgScreen) game.getScreen();
		Skin skin = screen.getSkin();

		splitText(text);

		background = new Image(skin, "default-window");
		background = ScreenUtils.defineImage(background, 0, 0, 960, 180);
		background.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				advanceMessage();
			}
		});

		textLbl = new Label(splittedText.get(textIndex), skin);
		textLbl = ScreenUtils.defineLabel(textLbl, 80, 5, 830, 180);
		textLbl.setStyle(new LabelStyle(skin.getFont("andalus-font"),
				Color.BLACK));
		textLbl.setWrap(true);
		textLbl.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				advanceMessage();
			}
		});
	}

	public void displayMessage() {
		screen.getStage().addActor(background);
		screen.getStage().addActor(textLbl);
		screen.setMessenger(this);
	}

	public void advanceMessage() {
		// There's no more text, so our job is done.
		if (textIndex >= splittedText.size - 1) {
			screen.getStage().removeActor(background);
			screen.getStage().removeActor(textLbl);
			screen.setMessenger(null);
		} else {
			// Display the next chunk of text.
			textIndex++;
			textLbl.setText(splittedText.get(textIndex));
		}
	}

	private void splitText(String text) {
		splittedText = new Array<String>();
		int charIndex = 0;

		while (charIndex < text.length()) {
			int endIndex = charIndex + CHAR_COUNT;
			if (endIndex >= text.length())
				endIndex = text.length();
			splittedText.add(text.substring(charIndex, endIndex));

			charIndex = endIndex;
		}
	}
}
