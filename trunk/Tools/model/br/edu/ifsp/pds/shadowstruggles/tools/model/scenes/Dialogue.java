package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

public class Dialogue extends SceneItem {
	public String text;
	public String characterName;
	public String characterImage;
	
	public Dialogue() {
		this.text = "";
		this.characterName = "";
		this.characterImage = "";
	}
	
	public Dialogue(String text, String characterName, String characterImage) {
		this.text = text;
		this.characterName = characterName;
		this.characterImage = characterImage;
	}
}
