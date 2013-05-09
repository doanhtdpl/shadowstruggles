package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.utils.Array;

/**
 * This class may represent the player or enemy field. A field is an mxn set of
 * tiles in which the player can place his cards.
 */

public class Field {

	private Array<Array<Card>> tiles;

	public Field() {
		tiles = new Array<Array<Card>>();
		for (int i = 0; i < 5; i++) {
			tiles.add(new Array<Card>());
			for (int j = 0; j < 4; j++) {
				tiles.get(i).add(null);
			}
		}
	}

	public Array<Array<Card>> getTiles() {
		return tiles;
	}

	public void setTiles(Array<Array<Card>> tiles) {
		this.tiles = tiles;
	}

}
