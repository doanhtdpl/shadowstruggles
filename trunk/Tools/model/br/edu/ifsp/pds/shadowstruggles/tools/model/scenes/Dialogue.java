package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

public class Dialogue extends SceneItem {
	public String text;
	public String characterName;
	
	public Dialogue() {
		this.text = "";
		this.characterName = "";
	}
	
	public Dialogue(String text, String characterName) {
		this.text = text;
		this.characterName = characterName;
	}
}
