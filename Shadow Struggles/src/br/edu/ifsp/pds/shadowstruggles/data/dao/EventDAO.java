package br.edu.ifsp.pds.shadowstruggles.data.dao;

import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;

public class EventDAO {

	public static Event getEvent(int id) {
		Event event = null;

		@SuppressWarnings("unchecked")
		Array<Event> events = DataManager.getInstance().getObjectSet(
				Event.class);
		for (Event e : events) {
			if (e.getId() == id)
				event = e;
		}

		return event;
	}

	@SuppressWarnings("unchecked")
	public static Array<Event> getAll() {
		return DataManager.getInstance().getObjectSet(Event.class);
	}

}
