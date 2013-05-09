package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * A Hexagram object is shown when the human player selects a card, in order to
 * inform him where it's possible to place his cards.
 */
public class Hexagram extends Image {

	public Hexagram(float x, float y, ShadowStruggles game) {
		super(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("hexagram"));
		this.setScaleX (((float) game.getManager().getSettings().tileWidth*2 / (float) 128));
		this.setScaleY( ((float) game.getManager().getSettings().tileHeight / (float) 128));
		this.setX(x);
		this.setY(y);
		this.setVisible(false);
	}

	public void update() {

	}

}
