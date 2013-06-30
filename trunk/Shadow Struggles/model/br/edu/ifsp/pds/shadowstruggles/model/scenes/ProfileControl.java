package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

public class ProfileControl extends SceneItem {
	public Modifier modifier;
	public Profile profile;

	public ProfileControl() {
		this.modifier = null;
		this.profile = new Profile();
	}

	public ProfileControl(Modifier modifier, Profile profile) {
		this.modifier = modifier;
		this.profile = profile;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.modifier = json.readValue("modifier", Modifier.class, jsonData);
		this.profile = json.readValue("profile", Profile.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("modifier", this.modifier);
		json.writeValue("profile", this.profile);
	}

	@Override
	public void action() {
		// TODO: Implementar método.
	}
}
