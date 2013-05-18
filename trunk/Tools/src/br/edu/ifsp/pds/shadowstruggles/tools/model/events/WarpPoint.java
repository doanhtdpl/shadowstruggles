package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

public class WarpPoint extends Event {
	public WarpPoint destination;
	public boolean active;

	public WarpPoint() {
		super();
		
		this.destination = this;
		this.active = false;
	}

	public WarpPoint(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite, WarpPoint destination, boolean active) {
		super(id, x, y, map, layer, quest, triggered, sprite);
		
		this.destination = destination;
		this.active = active;
	}
}
