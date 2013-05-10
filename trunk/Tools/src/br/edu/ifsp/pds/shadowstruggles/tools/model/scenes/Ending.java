package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.events.SceneEvent;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Ending implements Serializable {
	public int id;
	public String name;
	public String path;
	public ArrayList<SceneEvent> returnScenes;
	
	public Ending() {
		// TODO Auto-generated constructor stub
	}
	
	public Ending(int id, String name, String path,
			ArrayList<SceneEvent> returnScenes) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.returnScenes = returnScenes;
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
