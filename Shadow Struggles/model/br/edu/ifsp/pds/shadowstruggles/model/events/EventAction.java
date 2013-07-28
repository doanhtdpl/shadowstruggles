package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json.Serializable;

public abstract class EventAction implements Serializable {
	public abstract void act();
}
