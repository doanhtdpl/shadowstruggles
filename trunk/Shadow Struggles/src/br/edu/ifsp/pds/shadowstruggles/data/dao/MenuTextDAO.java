package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.MenuText;

public class MenuTextDAO {

	public static MenuText getMenuText() {
		MenuText menuText = null;

		try {
			menuText = (MenuText) DataManager.getInstance()
					.getObjectSet(MenuText.class).get(0);
		} catch (Exception ex) {
			ex.printStackTrace(); // TODO: Logging.
		}

		return menuText;
	}

}
