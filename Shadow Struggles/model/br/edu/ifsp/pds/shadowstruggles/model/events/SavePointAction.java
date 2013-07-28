package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SavePointAction extends EventAction {

	@Override
	public void act() {
		// TODO Auto-generated method stub
		System.out.println("Save Point activated!");
	}
	
	@Override
	public void read(Json json, JsonValue jsonData) {}
	
	@Override
	public void write(Json json) {}
}
