package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import java.util.ArrayList;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Decision extends SceneItem {

	public class Choice implements Serializable {
		public String text;
		public SceneItem consequence;

		@Override
		public void read(Json arg0, JsonValue arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void write(Json arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public ArrayList<Choice> choices;
	
	public Decision() {
		// TODO Auto-generated constructor stub
	}
	
	public Decision(ArrayList<Choice> choices) {
		this.choices = choices;
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
