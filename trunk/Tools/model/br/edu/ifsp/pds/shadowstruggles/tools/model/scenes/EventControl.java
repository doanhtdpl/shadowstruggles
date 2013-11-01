package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

public class EventControl extends SceneItem {
	public static enum EventManipulation {
		CHANGE_LAYER, CHANGE_SPRITE
	};

	public EventManipulation manipulationType;
	public String newValue;

	public EventControl() {
		this.manipulationType = null;
		this.newValue = "";
	}
}
