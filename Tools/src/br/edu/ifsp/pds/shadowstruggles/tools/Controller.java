package br.edu.ifsp.pds.shadowstruggles.tools;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.lingala.zip4j.exception.ZipException;
import br.edu.ifsp.pds.shadowstruggles.tools.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;
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
			//e.printStackTrace();
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

	public void createEvent() {
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
		/*try {
			ArrayList<Fighter> fighters = model.searchAllObjects(Fighter.class);
			System.out.println(fighters.size());
			if (fighters != null) {
				int i = 0;
				Object[][] fighterAttributes = new Object[fighters.size()][2];
				for (Iterator<Fighter> iterator = fighters.iterator(); iterator
						.hasNext();) {
					fighterAttributes[i][0] = fighters.get(i).getId();
					fighterAttributes[i][1] = fighters.get(i).getName();
				}
				Object[] columnNames = { "ID", "Name" };
				viewer.getTable().setModel(
						new DefaultTableModel(fighterAttributes, columnNames));
				viewer.getTable().updateUI();
			}

		} catch (IOException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}*/
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

	public HashMap<String, String> getLanguages() {
		return model.getLanguages().languages;
	}
	
	public void setLanguage(String language){
		model.setCurrentLanguage(language);
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
	}

	public void updateTableToScenes() {
	}

	public void setViewer(MainMenu viewer) {
		this.viewer = viewer;
	}

}
