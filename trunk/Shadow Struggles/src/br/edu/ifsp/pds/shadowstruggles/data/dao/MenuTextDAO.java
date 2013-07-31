package br.edu.ifsp.pds.shadowstruggles.data.dao;

import com.badlogic.gdx.Gdx;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.MenuText;

public class MenuTextDAO {
	public static final String LOG = MenuTextDAO.class.getName();

	public static MenuText getMenuText() {
		MenuText menuText = null;

		try {
			menuText = (MenuText) DataManager.getInstance()
					.getObjectSet(MenuText.class).get(0);
		} catch (Exception ex) {
			Gdx.app.error(LOG, "Error getting MenuText", ex);
		}

		return menuText;
	}

}
