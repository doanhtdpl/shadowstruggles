package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Scene implements Serializable {
	public int id;
	public Ending ending;
	public String name;
	public String description;
	
	public Scene() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene(int id, Ending ending, String name, String description) {
		this.id = id;
		this.ending = ending;
		this.name = name;
		this.description = description;
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
