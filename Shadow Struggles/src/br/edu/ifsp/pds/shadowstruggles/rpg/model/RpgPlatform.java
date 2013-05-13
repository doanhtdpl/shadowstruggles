package br.edu.ifsp.pds.shadowstruggles.rpg.model;

public class RpgPlatform {
	private RpgMap map;
	private Character character;
	public RpgPlatform(RpgMap map, Character character) {
		//definir mapa pela ID da fase;
		this.map=map;
		this.character=character;		
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public RpgMap getMap() {
		return map;
	}

}
