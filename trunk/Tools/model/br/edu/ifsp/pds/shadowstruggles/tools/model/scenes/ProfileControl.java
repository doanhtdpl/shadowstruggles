package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;

public class ProfileControl extends SceneItem {
	public Modifier modifier;
	
	public ProfileControl() {
		this.modifier = null;
	}
	
	public ProfileControl(Modifier modifier) {
		this.modifier = modifier;
	}
}
