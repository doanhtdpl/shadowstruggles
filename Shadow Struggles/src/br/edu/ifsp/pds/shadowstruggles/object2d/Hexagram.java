package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * A Hexagram object is shown when the human player selects a card, in order to
 * inform him where it's possible to place his cards.
 */
public class Hexagram extends Image {

	public Hexagram(float x, float y, ShadowStruggles game) {
		super(game.getTextureRegion("hexagram", "game_ui_images"));
		this.setScaleX(((float) SettingsDAO.getSettings().tileWidth * 2 / (float) 128));
		this.setScaleY(((float) SettingsDAO.getSettings().tileHeight / (float) 128));
		this.setX(x);
		this.setY(y);
		this.setVisible(false);
	}

	public void update() {

	}

}
