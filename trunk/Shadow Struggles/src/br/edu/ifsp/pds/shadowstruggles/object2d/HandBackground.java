package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/***
 * Represents the background of the player's hand on the screen.
 */

public class HandBackground extends FixedObject {

	public HandBackground(int initialX, ShadowStruggles game) {
		super(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("background"), initialX);
	}

}
