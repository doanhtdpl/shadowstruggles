package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;

public class ComplexEvent extends Event {
	public ArrayList<Event> events;

	public ComplexEvent() {
		super();
		this.events = new ArrayList<Event>();
	}

	public ComplexEvent(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite,
			TriggerType triggerType, ArrayList<Event> events) {
		super(id, x, y, map, layer, quest, triggered, sprite, triggerType);

		this.events = events;
	}
}
