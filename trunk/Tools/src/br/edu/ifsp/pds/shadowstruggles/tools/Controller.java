package br.edu.ifsp.pds.shadowstruggles.tools;

import br.edu.ifsp.pds.shadowstruggles.tools.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.tools.view.Window;

public class Controller {
	private Window viewer;
	private DataManager model;
	
	public Controller(DataManager model) {
		
		this.model=model;
		
		this.model.setController(this);
	}
	public void newZipClicked(){}
	public void openZipClicked(){}
	public void saveZipClicked(){}
	public void closeZipClicked(){}
	
	public void createCard(){} 
	public void createEvent(){}
	public void createBattle(){}
	public void createRule(){}
	public void createAction(){}
	public void createDeck(){}
	public void createEnemie(){} 
	
	public void updateTableToFighter(){}
	public void updateTableToTraps(){}
	public void updateTableToEffects(){}
	public void updateTableToActions(){}
	public void updateTableToDecks(){}
	public void updateTableToEnemies(){}
	public void updateTableToRules(){}
	public void updateTableToBattles(){}
	public void updateTableToEvents(){}
	public void updateTableToScenes(){}
	public void setViewer(Window viewer) {
		this.viewer = viewer;
	}
	
}

