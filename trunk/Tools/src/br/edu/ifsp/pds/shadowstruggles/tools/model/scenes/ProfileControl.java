package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;

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
}
