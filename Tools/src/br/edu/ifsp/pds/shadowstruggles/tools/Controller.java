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
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;
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

	public void createDeck(Deck deck) throws IOException {
		model.insertObject(deck, Deck.class);
	}
	
	public void createItem(Item item)throws IOException{
		model.insertObject(item, Item.class);
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
	
	public void createEffect(Effect effect)throws IOException{		
		model.insertObject(effect, Effect.class);			
	}
	
	public void createTrap(Trap trap)throws IOException{		
		model.insertObject(trap, Trap.class);			
	}
	public void createShop(Shop shop)throws IOException{		
		model.insertObject(shop, Shop.class);			
	}
	

	public void createLanguage(String code, String name) throws IOException{
		model.insertLanguage(code, name);
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
	
	public ArrayList<Scene> getScenes(){
		ArrayList<Scene> scenes= new ArrayList<Scene>();
		try {
			scenes = model.searchAllObjects(Scene.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return scenes;				
	}
	
	public ArrayList<BattlePlatform> getBattles(){
		ArrayList<BattlePlatform> battles= new ArrayList<BattlePlatform>();
		try {
			battles = model.searchAllObjects(BattlePlatform.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return battles;				
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
	
	public ArrayList<Card> getCards(){
		ArrayList<Card> cards = new ArrayList<Card>();
		try {			
			for(Object obj : model.searchAllObjects(Fighter.class))
			cards.add((Card)obj);
			for(Object obj : model.searchAllObjects(Trap.class))
				cards.add((Card)obj);
			for(Object obj : model.searchAllObjects(Effect.class))
				cards.add((Card)obj);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return cards;
	}
	
	public ArrayList<Item> getItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			items=model.searchAllObjects(Item.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return items;
	}
	
	public ArrayList<Shop> getShops(){
		ArrayList<Shop> shops = new ArrayList<Shop>();
		try {
			shops=model.searchAllObjects(Shop.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return shops;
	}
	

	
	public ArrayList<Quest> getQuests(){
		ArrayList<Quest> quests = new ArrayList<Quest>();
		try {
			quests= model.searchAllObjects(Quest.class);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return quests;
	}

	public HashMap<String, String> getLanguages() {
		return model.getLanguages().languages;
	}
	
	public Object getObject(int id, Class <?> c) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException{
		return model.searchObject(id, c);
	}
	
	public void updateObject(int id, Class<?> c, Object editedObject) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException{
		model.update(id, c, editedObject);
	}
	
	public void deleteObject(int id, Class<?> c) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException{
		model.deleteObject(id, c);
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
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToTraps() {
		System.out.println("updating Traps");
		ArrayList<Trap> traps= new ArrayList<Trap>();;
		try {
			if(model.searchAllObjects(Trap.class)!=null)
				traps=model.searchAllObjects(Trap.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[traps.size()][10];
		int i = 0;
		for(Trap trap : traps){			
			tableData[i][0]=trap.id;
			tableData[i][1]=trap.name;
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToEffects() {
		System.out.println("updating Effects");
		ArrayList<Effect> effects= new ArrayList<Effect>();;
		try {
			if(model.searchAllObjects(Effect.class)!=null)
				effects=model.searchAllObjects(Effect.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[effects.size()][10];
		int i = 0;
		for(Effect effect : effects){			
			tableData[i][0]=effect.id;
			tableData[i][1]=effect.name;
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}
	
	

	public void updateTableToDecks() {
		System.out.println("updating Decks");
		ArrayList<Deck> decks= new ArrayList<Deck>();;
		try {
			if(model.searchAllObjects(Deck.class)!=null)
			decks=model.searchAllObjects(Deck.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[decks.size()][10];
		int i = 0;
		for(Deck deck : decks){			
			tableData[i][0]=deck.id;
			tableData[i][1]=deck.name;
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToActions() {
	}

	public void updateTableToEnemies() {
		System.out.println("updating Enemies");
		ArrayList<Enemy> enemies= new ArrayList<Enemy>();;
		try {
			if(model.searchAllObjects(Enemy.class)!=null)
			enemies=model.searchAllObjects(Enemy.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[enemies.size()][10];
		int i = 0;
		for(Enemy enemy : enemies){			
			tableData[i][0]=enemy.id;
			tableData[i][1]=enemy.name;
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToRules() {
	}

	public void updateTableToBattles() {
		System.out.println("updating Battles");
		ArrayList<BattlePlatform> battles= new ArrayList<BattlePlatform>();;
		try {
			if(model.searchAllObjects(BattlePlatform.class)!=null)
			battles=model.searchAllObjects(BattlePlatform.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID"};
		Object[][] tableData = new Object[battles.size()][10];
		int i = 0;
		for(BattlePlatform battle : battles){			
			tableData[i][0]=battle.id;			
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}
	
	public void updateTableToItems(){
		System.out.println("updating Items");
		ArrayList<Item> items= new ArrayList<Item>();;
		try {
			if(model.searchAllObjects(Item.class)!=null)
			items=model.searchAllObjects(Item.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[items.size()][10];
		int i = 0;
		for(Item item : items){			
			tableData[i][0]=item.id;
			tableData[i][1]=item.name;
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
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
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}

	public void updateTableToScenes() {		
		System.out.println("updating Scenes");
		ArrayList<Scene> scenes= new ArrayList<Scene>();;
		try {
			if(model.searchAllObjects(Scene.class)!=null)
				scenes=model.searchAllObjects(Scene.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID","Name"};
		Object[][] tableData = new Object[scenes.size()][10];
		int i = 0;
		for(Scene scene : scenes){			
			tableData[i][0]=scene.id;
			tableData[i][1]=scene.name;
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}
	
	public void updateTableToShop() {		
		System.out.println("updating Shops");
		ArrayList<Shop> shops= new ArrayList<Shop>();;
		try {
			if(model.searchAllObjects(Shop.class)!=null)
				shops=model.searchAllObjects(Shop.class);
			
		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();			
		}
		String[] columnNames={"ID"};
		Object[][] tableData = new Object[shops.size()][10];
		int i = 0;
		for(Shop shop : shops){			
			tableData[i][0]=shop.id;			
			i++;
		}
		DefaultTableModel dataModel = new DefaultTableModel(tableData, columnNames);
		viewer.getTable().setModel(dataModel);			
		
		dataModel.fireTableDataChanged();
	}
	
	

	public void setViewer(MainMenu viewer) {
		this.viewer = viewer;
	}

	public void setLanguage(String language){
		model.setCurrentLanguage(language);
	}

}
