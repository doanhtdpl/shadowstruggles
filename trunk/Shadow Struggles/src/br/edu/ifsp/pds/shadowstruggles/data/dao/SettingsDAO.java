package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.Settings;

public class SettingsDAO {

	public static Settings getSettings() {
		Settings settings = null;

		try {
			settings = (Settings) DataManager.getInstance()
					.getObjectSet(Settings.class).get(0);
		} catch (Exception ex) {
			ex.printStackTrace(); // TODO: Logging.
		}

		return settings;
	}

}
