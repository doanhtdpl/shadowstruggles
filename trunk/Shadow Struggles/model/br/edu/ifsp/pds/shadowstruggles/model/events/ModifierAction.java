package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.Messenger;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ModifierAction extends EventAction {
	private Modifier modifier;

	public ModifierAction() {
		this.modifier = null;
	}

	public ModifierAction(Modifier modifier) {
		this.modifier = modifier;
	}

	@Override
	public void act() {
		modifier.modify();
		String message = modifier.getMessage();
		if (message != null) {
			Messenger messenger = new Messenger(message,
					ShadowStruggles.getInstance(), null);
			messenger.displayMessage();
		}
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.modifier = json.readValue("modifier", null, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("modifier", this.modifier, ModifierAction.class);
	}
}
