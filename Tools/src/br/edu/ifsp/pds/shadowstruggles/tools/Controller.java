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
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;
import br.edu.ifsp.pds.shadowstruggles.tools.view.MainMenu;
import br.edu.ifsp.pds.shadowstruggles.tools.view.edition.EffectEditor;

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

	public void createBattle(BattlePlatform battle) throws IOException{
		model.insertObject(battle, BattlePlatform.class);
	}

	public void createRule() {
	}

	public void createAction() {
	}

	public void createDeck() {
	}

	public void createEnemy(Enemy enemy) throws IOException{
		model.insertObject(enemy,Enemy.class);
	}
	public void createScene(Scene scene)throws IOException{
		model.insertObject(scene, Scene.class);
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
	
	public ArrayList<Fighter> getFighters(){
		ArrayList<Fighter> fighters = new ArrayList<Fighter>();
		try {
			fighters=model.searchAllObjects(Fighter.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return fighters;
	}
	
	public ArrayList<Effect> getEffects(){
		ArrayList<Effect> effects = new ArrayList<Effect>();
		try {
			effects=model.searchAllObjects(Effect.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return effects;
	}
	
	public ArrayList<Trap> getTraps(){
		ArrayList<Trap> traps = new ArrayList<Trap>();
		try {
			traps=model.searchAllObjects(Trap.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return traps;
	}
	
	
	public Card getCardByName(String name){		
		ArrayList<Fighter> fighters = getFighters();
		for(Fighter fighter : fighters)
			if(fighter.name.equals(name))return fighter;
		ArrayList<Trap> traps = getTraps();
		for(Trap trap : traps)
			if(trap.name.equals(name))return trap;
		ArrayList<Effect> effects = getEffects();
		for(Effect effect : effects)
			if(effect.name.equals(name))return effect;
		return null;	
		
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
		try {
			ArrayList<Scene> scenes = model.searchAllObjects(Scene.class);
			if (scenes != null) {
				int i = 0;
				Object[][] sceneAttributes = new Object[scenes.size()][2];
				for (Iterator<Scene> iterator = scenes.iterator(); iterator
						.hasNext();) {
					sceneAttributes[i][0] = scenes.get(i).id;
					sceneAttributes[i][1] = scenes.get(i).name;
				}
				Object[] columnNames = { "ID", "Name" };
				viewer.getTable().setModel(
						new DefaultTableModel(sceneAttributes, columnNames));
				viewer.getTable().updateUI();
			}

		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	

	public void setViewer(MainMenu viewer) {
		this.viewer = viewer;
	}

}
