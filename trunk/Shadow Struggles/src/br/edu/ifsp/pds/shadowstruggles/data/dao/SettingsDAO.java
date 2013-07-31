package br.edu.ifsp.pds.shadowstruggles.data.dao;

import com.badlogic.gdx.Gdx;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.Settings;

public class SettingsDAO {
	public static final String LOG = SettingsDAO.class.getName();

	public static Settings getSettings() {
		Settings settings = null;

		try {
			settings = (Settings) DataManager.getInstance()
					.getObjectSet(Settings.class).get(0);
		} catch (Exception ex) {
			Gdx.app.error(LOG, "Error getting Settings", ex);
		}

		return settings;
	}

}
