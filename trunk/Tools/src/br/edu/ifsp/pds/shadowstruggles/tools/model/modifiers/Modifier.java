package br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;

import com.esotericsoftware.jsonbeans.Json.Serializable;

public abstract class Modifier implements Serializable {
	public abstract void modify(Profile profile);
}
