package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.Scene;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a scene from the DataManager
 */

public class SceneDAO {

	public static Scene getScene(int key, DataManager manager) {
		Scene scene = null;

		Array<Scene> scenes = manager.getScenesList();
		for (Scene s : scenes) {
			if (s.getId() == key)
				scene = s;
		}

		return scene;
	}

	public static Array<Scene> getScenes(DataManager manager) {
		return manager.getScenesList();
	}

}
