package br.edu.ifsp.pds.shadowstruggles.data.dao;

import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;

public class BattlePlatformDAO {

	public static BattlePlatform getBattle(int id) {
		BattlePlatform battle = null;

		@SuppressWarnings("unchecked")
		Array<BattlePlatform> battles = DataManager.getInstance().getObjectSet(
				BattlePlatform.class);
		for (BattlePlatform b : battles) {
			if (b.getId() == id)
				battle = b;
		}

		return battle;
	}
}
