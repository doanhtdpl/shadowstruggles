package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.EventAction;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Ending;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class Profile implements Serializable {
	public int id;
	public Player player;
	public String language;
	public int storyPoints;
	public String path;
	public int money;
	public int experience;
	public int level;
	public int distributionPoints;
	public int experienceNextLevel;

	public ArrayList<Item> inventory;
	public ArrayList<Deck> deck;
	public ArrayList<Item> unlockedItems;
	public ArrayList<Quest> quests;
	public ArrayList<EnemyDefeat> defeatedEnemies;
	public ArrayList<Ending> endings;

	/**
	 * Records which actions should be performed for each event.
	 */
	public HashMap<Event, ArrayList<EventAction>> events;
	/**
	 * Relates the maps to the object layer which the player character will
	 * access when visiting them.
	 */
	public HashMap<String, String> mapLayers;

	public DistributionPointsFormula distributionPointsFormula;
	public AttributePointsFormula attributePointsFormula;
	public ExperienceNextLevelFormula experienceNextLevelFormula;

	public Profile() {
		this.id = 1;
		this.player = new Player();
		this.language = "";
		this.storyPoints = 0;
		this.path = "";
		this.money = 0;
		this.experience = 0;
		this.level = 1;
		this.distributionPoints = 0;
		this.experienceNextLevel = 1;

		this.inventory = new ArrayList<Item>();
		this.deck = new ArrayList<Deck>();
		this.unlockedItems = new ArrayList<Item>();
		this.quests = new ArrayList<Quest>();
		this.defeatedEnemies = new ArrayList<EnemyDefeat>();
		this.endings = new ArrayList<Ending>();

		this.events = new HashMap<Event, ArrayList<EventAction>>();
		this.mapLayers = new HashMap<String, String>();

		this.distributionPointsFormula = null;
		this.attributePointsFormula = null;
		this.experienceNextLevelFormula = null;
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void write(Json arg0) {
		try {
			SerializationHelper
					.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}

}
