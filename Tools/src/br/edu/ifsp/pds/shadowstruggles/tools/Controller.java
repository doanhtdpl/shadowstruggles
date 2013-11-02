package br.edu.ifsp.pds.shadowstruggles.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.lingala.zip4j.exception.ZipException;
import br.edu.ifsp.pds.shadowstruggles.tools.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.view.MainMenu;

public class Controller {
	private MainMenu viewer;
	private DataManager model;

	public Controller(DataManager model) {

		this.model = model;

		this.model.setController(this);

	}

	public void newZipClicked() {

		try {
			String name = JOptionPane
					.showInputDialog("Please, inform the ZIP name");
			if(name!=null || name.length()>0 ){
			model.newZip(name + ".zip");
			
			viewer.showElements();
			}else {
				JOptionPane.showMessageDialog(null, "Escolhe um nome pro arquivo, seu vagabundo!");
			}
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (ZipException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}catch(NullPointerException e){
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

	public void openZip(String filePath) throws IOException, ZipException {

		model.openZip(filePath, false);

	}

	public void saveZip() {
		try {
			model.saveToZip();
		} catch (ZipException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

	public void closeZipClicked() {
	}

	public void createCard() {
	}

	public void createEvent(Event event) throws IOException {
		model.insertObject(event, Event.class);
	}

	public void createBattle() {
	}

	public void createRule() {
	}

	public void createAction() {
	}

	public void createDeck() {
	}

	public void createEnemy() {
	}
	
	public void createFighter(Fighter fighter)throws IOException{		
			model.insertObject(fighter, Fighter.class);			
	}
	
	

	public void updateTableToFighter() {		
		System.out.println("updating Fighters");
		ArrayList<Fighter> fighters= new ArrayList<Fighter>();;
		try {
			if(model.searchAllObjects(Fighter.class)!=null)
			fighters=model.searchAllObjects(Fighter.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[fighters.size()][10];
		int i = 0;
		for(Fighter fighter : fighters){			
			tableData[i][0]=fighter.id;
			tableData[i][1]=fighter.name;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToTraps() {
	}

	public void updateTableToEffects() {
	}

	public void updateTableToActions() {
	}
	
	public ArrayList<Enemy> getEnemies(){
		ArrayList<Enemy> enemies= new ArrayList<Enemy>();
		try {
			enemies = model.searchAllObjects(Enemy.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return enemies;				
	}
	public ArrayList<Item> getItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		//TODO: retrieve items from file
		return items;
	}
	
	public ArrayList<Quest> getQuests(){
		ArrayList<Quest> quests = new ArrayList<Quest>();
		//TODO: retrieve quests from file
		return quests;
	}

	public HashMap<String, String> getLanguages() {
		return model.getLanguages().languages;
	}
	
	public void setLanguage(String language){
		model.setCurrentLanguage(language);
	}
	
	public void createLanguage(String code, String name) throws IOException{
		model.insertLanguage(code, name);
	}

	public void updateTableToDecks() {
		try {
			ArrayList<Deck> decks = model.searchAllObjects(Deck.class);
			if (decks != null) {
				int i = 0;
				Object[][] deckAttributes = new Object[decks.size()][2];
				for (Iterator<Deck> iterator = decks.iterator(); iterator
						.hasNext();) {
					deckAttributes[i][0] = decks.get(i).getId();
					deckAttributes[i][1] = decks.get(i).getName();
				}
				Object[] columnNames = { "ID", "Name" };
				viewer.getTable().setModel(
						new DefaultTableModel(deckAttributes, columnNames));
				viewer.getTable().updateUI();
			}

		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

	public void updateTableToEnemies() {
	}

	public void updateTableToRules() {
	}

	public void updateTableToBattles() {
	}

	public void updateTableToEvents() {
		System.out.println("updating Events");
		ArrayList<Event> events= new ArrayList<Event>();;
		try {
			if(model.searchAllObjects(Event.class)!=null)
			events=model.searchAllObjects(Event.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Trigger Type"};
		Object[][] tableData = new Object[events.size()][10];
		int i = 0;
		for(Event event : events){			
			tableData[i][0]=event.id;
			tableData[i][1]=event.triggerType;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToScenes() {
	}
	
	

	public void setViewer(MainMenu viewer) {
		this.viewer = viewer;
	}

}
