package br.edu.ifsp.pds.shadowstruggles.tools;

import java.io.IOException;

import net.lingala.zip4j.exception.ZipException;
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
	public void openZip(String filePath) throws IOException, ZipException{
		model.openZip(filePath, true);
	}
	public void saveZip(){}
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

