package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.Messenger;

/**
 * Applies change to the current profile via a Modifier object.
 */
public class ProfileControl extends SceneItem {
	public Modifier modifier;

	public ProfileControl() {
		this.modifier = null;
	}

	public ProfileControl(Modifier modifier) {
		this.modifier = modifier;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.modifier = json.readValue("modifier", Modifier.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("modifier", this.modifier);
	}

	@Override
	public void action() {
		modifier.modify();
		String message = modifier.getMessage();
		if (message != null) {
			Messenger messenger = new Messenger(message,
					ShadowStruggles.getInstance(), parentScene);
			messenger.displayMessage();
		}
	}
}
