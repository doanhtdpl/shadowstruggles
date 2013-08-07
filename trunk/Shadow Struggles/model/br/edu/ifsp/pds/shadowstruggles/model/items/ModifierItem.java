package br.edu.ifsp.pds.shadowstruggles.model.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;

public class ModifierItem extends Item {
	public Modifier modifier;
	
	public ModifierItem() {
		super();
		
		this.modifier = null;
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
