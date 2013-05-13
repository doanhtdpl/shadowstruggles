package br.edu.ifsp.pds.shadowstruggles.rpg.model;

public class Tile {
	private Character character;
	private Item item;
	private boolean walkable;
	
	public Tile(boolean walkable) {
		this.walkable=walkable;
	}
	
	public Character hasCharacter(){return character;}
	
	public Item hasItem(){return item;}
	
	public void removeCharacer(){
		this.character=null;
	}
	
	public void insertCharacter(Character character){
		this.character=character;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	

}
