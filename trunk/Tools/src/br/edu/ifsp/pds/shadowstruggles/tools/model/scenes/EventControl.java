package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class EventControl extends SceneItem {
	public static enum EventManipulation { DELETE_EVENT, TOOGLE_VISIBILITY, CHANGE_LAYER, CHANGE_SPRITE };
	
	public EventManipulation manipulationType;
	public String newLayer;
	public String newSprite;
	
	public EventControl() {
		// TODO Auto-generated constructor stub
	}
	
	public EventControl(EventManipulation manipulationType, String newLayer,
			String newSprite) {
		this.manipulationType = manipulationType;
		this.newLayer = newLayer;
		this.newSprite = newSprite;
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
