package br.edu.ifsp.pds.shadowstruggles.model.events;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.model.Scene;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;

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

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.scene = json.readValue("scene", Scene.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("scene", this.scene);
	}

	@Override
	public void trigger() {
		// TODO: Implementar método.
	}

}
