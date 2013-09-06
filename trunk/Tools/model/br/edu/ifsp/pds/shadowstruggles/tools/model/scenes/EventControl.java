package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

public class EventControl extends SceneItem {
	public static enum EventManipulation {
		DELETE_EVENT, TOOGLE_VISIBILITY, CHANGE_LAYER, CHANGE_SPRITE
	};

	public EventManipulation manipulationType;
	public String newLayer;
	public String newSprite;

	public EventControl() {
		this.manipulationType = EventManipulation.DELETE_EVENT;
		this.newLayer = "";
		this.newSprite = "";
	}

	public EventControl(EventManipulation manipulationType, String newLayer,
			String newSprite) {
		this.manipulationType = manipulationType;
		this.newLayer = newLayer;
		this.newSprite = newSprite;
	}
}
