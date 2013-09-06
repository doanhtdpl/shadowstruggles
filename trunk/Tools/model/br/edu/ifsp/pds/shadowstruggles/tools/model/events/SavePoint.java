package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

public class SavePoint extends Event {

	public SavePoint() {
		super();
	}

	public SavePoint(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, TriggerType triggerType,
			String sprite) {
		super(id, x, y, map, layer, quest, triggered, sprite, triggerType);
	}
}
