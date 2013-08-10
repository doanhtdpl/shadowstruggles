package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Modifies the player's path in the story.
 */
public class PathModifier extends Modifier {
	private String path;

	@Override
	public void write(Json json) {
		json.writeValue("path", this.path);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.path = json.readValue("path", String.class, jsonData);
	}

	@Override
	public void modify() {
		Profile profile = ShadowStruggles.getInstance().getProfile();
		profile.setPath(path);
	}
}
