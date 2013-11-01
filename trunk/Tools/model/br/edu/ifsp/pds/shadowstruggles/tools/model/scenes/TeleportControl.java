package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;

public class TeleportControl extends SceneItem {
	public int destinyX, destinyY;
	public String destinyMap;
	public String destinyLayer;
	public Event event;

	public TeleportControl() {
		this.destinyX = 0;
		this.destinyY = 0;
		this.destinyMap = "";
		this.destinyLayer = "";
		this.event = null;
	}
}
