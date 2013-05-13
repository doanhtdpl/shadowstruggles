package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import com.badlogic.gdx.utils.Array;

public class RpgMap {
	private Array<Array<Tile>> tiles;
	private Array<Item> items;
	
	public Tile getTile(int tileX, int tileY){
		return tiles.get(tileX).get(tileY);
	}
}
