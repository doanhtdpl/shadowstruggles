package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Scene;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a scene from the DataManager
 */

public class SceneDAO {

	public static Scene getScene(int key) {
		Scene scene = null;

		@SuppressWarnings("unchecked")
		Array<Scene> scenes = DataManager.getInstance().getObjectSet(Scene.class);
		for (Scene s : scenes) {
			if (s.getId() == key)
				scene = s;
		}

		return scene;
	}

	@SuppressWarnings("unchecked")
	public static Array<Scene> getScenes() {
		return DataManager.getInstance().getObjectSet(Scene.class);
	}

}
