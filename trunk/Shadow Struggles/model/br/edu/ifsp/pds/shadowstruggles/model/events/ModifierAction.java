package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ModifierAction extends EventAction {
	private Modifier modifier;

	@Override
	public void act() {
		modifier.modify();
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		
		this.modifier = json.readValue("modifier", Modifier.class, jsonData);
	}
	
	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("modifier", this.modifier);
	}
}
