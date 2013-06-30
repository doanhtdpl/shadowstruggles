package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.Languages;

public class LanguagesDAO {

	public static Languages getLanguages() {
		Languages languages = null;

		languages = (Languages) DataManager.getInstance()
				.getObjectSet(Languages.class).get(0);

		return languages;
	}

}
