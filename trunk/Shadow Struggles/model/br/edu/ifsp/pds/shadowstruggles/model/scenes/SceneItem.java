package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json.Serializable;

public abstract class SceneItem implements Serializable {
	public abstract void action();
}
