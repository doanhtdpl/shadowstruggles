package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;

public class SceneEvent extends Event {
	public Scene scene;

	public SceneEvent() {
		super();

		this.scene = new Scene();
	}

	public SceneEvent(int id, float x, float y, String map, String layer,
			Quest quest, boolean triggered, String sprite,
			TriggerType triggerType, Scene scene) {
		super(id, x, y, map, layer, quest, triggered, sprite, triggerType);

		this.scene = scene;
	}

}
