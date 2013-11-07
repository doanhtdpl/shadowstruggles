package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Scene implements Serializable {
	private int id;
	private Ending ending;
	private String name;
	private String description;
	private Array<SceneItem> sceneItems;

	// Non-serializable attributes
	private int currentIndex = 0;

	public Scene() {
		this.id = 1;
		this.ending = new Ending();
		this.name = "";
		this.description = "";
		this.sceneItems = new Array<SceneItem>();
	}

	/**
	 * Executes the next scene item, specified by the currentIndex. The items
	 * themselves are responsible for continuing the scene by calling this
	 * method again, typically after player input.
	 */
	public void runNextItem() {
		if (currentIndex < sceneItems.size) {
			SceneItem item = sceneItems.get(currentIndex);
			item.setParentScene(this);
			item.action();
			currentIndex++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.ending = json.readValue("ending", Ending.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.sceneItems = json.readValue("sceneItems", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("ending", this.ending);
		json.writeValue("name", this.name);
		json.writeValue("description", this.description);
		json.writeValue("sceneItems", this.sceneItems);
	}
	
	public int getId() {
		return id;
	}
}
