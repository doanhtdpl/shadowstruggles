package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import java.text.Bidi;

import com.badlogic.gdx.utils.Array;

public class RpgMap {
	private Array<Array<Tile>> tiles;
	private Array<Item> items;
	
	public RpgMap(int width, int height) {
		tiles= new Array<Array<Tile>>();
		for(int i = 0;i < width;i++){
			tiles.add(new Array<Tile>());
			for(int j = 0 ; j< height; j++)
				tiles.get(i).add(new Tile());
				
		}
				
		items= new Array<Item>();
	}
	
	public Tile getTile(int tileX, int tileY){
		return tiles.get(tileX).get(tileY);
	}
	
	
}
