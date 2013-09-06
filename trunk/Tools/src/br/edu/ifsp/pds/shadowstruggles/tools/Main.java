package br.edu.ifsp.pds.shadowstruggles.tools;

import br.edu.ifsp.pds.shadowstruggles.tools.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.tools.data.FileMap;
import br.edu.ifsp.pds.shadowstruggles.tools.utils.MyLogger;
import br.edu.ifsp.pds.shadowstruggles.tools.view.MainMenu;

public class Main {

	public static void main(String[] args) {
		try {
			MyLogger.setup();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		FileMap.initMap();
		DataManager dataManager = new DataManager();		
		Controller controller = new Controller(dataManager);
		new MainMenu(controller);
	}

}
