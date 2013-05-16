package br.edu.ifsp.pds.shadowstruggles.model.rpg;



import com.badlogic.gdx.utils.Array;

public class RpgMap {
	private Array<Array<Tile>> tiles;
	private Array<Item> items;
	private int width;
	private int height;
	
	public RpgMap(int width, int height) {
		tiles= new Array<Array<Tile>>();
		this.width=width;
		this.height=height;
		for(int i = 0;i < width;i++){
			tiles.add(new Array<Tile>());
			for(int j = 0 ; j< height; j++)
				tiles.get(i).add(new Tile(true));
				
		}
				
		items= new Array<Item>();
	}
	
	public Tile getTile(int tileX, int tileY){
		return tiles.get(tileX).get(tileY);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	
}
