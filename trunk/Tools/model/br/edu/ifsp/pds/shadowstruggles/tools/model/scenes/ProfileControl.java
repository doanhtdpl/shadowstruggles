package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;

public class ProfileControl extends SceneItem {
	public Modifier modifier;
	
	public ProfileControl() {
		
	}
	
	public ProfileControl(Modifier modifier) {
		this.modifier = modifier;
	}
}
