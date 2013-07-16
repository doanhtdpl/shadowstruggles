package br.edu.ifsp.pds.shadowstruggles.tools;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import net.lingala.zip4j.exception.ZipException;
import br.edu.ifsp.pds.shadowstruggles.tools.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.view.Window;
import br.edu.ifsp.pds.shasdowstruggles.view.edition.FighterEditor;

public class Controller {
	private Window viewer;
	private DataManager model;
	
	public Controller(DataManager model) {
		
		this.model=model;
		
		this.model.setController(this);
		
	}
	public void newZipClicked(){}
	public void openZip(String filePath) throws IOException, ZipException{
		
		
		
		model.openZip(filePath, false);		
		this.model.setCurrentLanguage("en_us");
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
	
	public void updateTableToFighter(){
		try {
			model.searchAllObjects(Card.class.getClass());
		} catch (IOException e) {			
			e.printStackTrace();
		}
		//viewer.getTable().se
	}
	public void updateTableToTraps(){}
	public void updateTableToEffects(){}
	public void updateTableToActions(){}
	public void updateTableToDecks(){
		try {
			ArrayList<Deck> decks=model.searchAllObjects(Deck.class);
			int i =0;
			Object[][] deckAttributes= new Object[decks.size()][2];
			for(Deck deck:decks){
				deckAttributes[i][0]=decks.get(i).getId();
				deckAttributes[i][1]=decks.get(i).getName();
			}
			Object[] columnNames= {"ID","Name"};
			viewer.getTable().setModel(new DefaultTableModel(deckAttributes, columnNames));
			viewer.getTable().updateUI();
			System.out.println(decks.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateTableToEnemies(){}
	public void updateTableToRules(){}
	public void updateTableToBattles(){}
	public void updateTableToEvents(){}
	public void updateTableToScenes(){}
	public void setViewer(Window viewer) {
		this.viewer = viewer;
	}
	
}

