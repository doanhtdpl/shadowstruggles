package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

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

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
	}

}
