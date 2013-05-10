package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class NovelScene extends Scene {
	public String text;
	public String background;
	
	public NovelScene() {
		// TODO Auto-generated constructor stub
	}
	
	public NovelScene(int id, Ending ending, String name, String description, String text, String background) {
		super(id, ending, name, description);
		
		this.text = text;
		this.background = background;
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		super.read(arg0, arg1);
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		super.write(arg0);
	}
}
