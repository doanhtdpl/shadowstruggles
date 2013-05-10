package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.Modifier;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class ProfileControl extends SceneItem {
	public Modifier modifier;
	public Profile profile;
	
	public ProfileControl() {
		// TODO Auto-generated constructor stub
	}
	
	public ProfileControl(Modifier modifier, Profile profile) {
		this.modifier = modifier;
		this.profile = profile;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
	}

}
