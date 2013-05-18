package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;


import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;

public class TeleportControl extends SceneItem {
	public Event target;
	public boolean targetPlayer;
	public String map;
	public String layer;
	public float x, y;
	
	public TeleportControl() {
		this.target = null;
		this.targetPlayer = false;
		this.map = "";
		this.layer = "";
		this.x = 0;
		this.y = 0;
	}
	
	public TeleportControl(Event target, boolean targetPlayer, String map,
			String layer, float x, float y) {
		this.target = target;
		this.targetPlayer = targetPlayer;
		this.map = map;
		this.layer = layer;
		this.x = x;
		this.y = y;
	}
}
