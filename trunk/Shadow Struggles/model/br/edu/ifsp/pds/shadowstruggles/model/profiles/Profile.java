package br.edu.ifsp.pds.shadowstruggles.model.profiles;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;

import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventAction;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Ending;

public class Profile implements Serializable, Comparable<Object> {
	private int id;
	private Player player;
	private Character character;
	private String language;
	private int storyPoints;
	private String path;
	private Deck selectedDeck;
	private int money;
	private int experience;
	private int level;
	private int distributionPoints;
	private int experienceNextLevel;

	private Array<Item> inventory;
	private Array<Deck> decks;
	private Array<Quest> quests;
	private Array<EnemyDefeat> defeatedEnemies;
	private Array<Ending> endings;
	/**
	 * Records which actions should be performed for each event.
	 */
	private ObjectMap<Event, Array<EventAction>> events;
	/**
	 * Relates the maps to the object layer which the player character will
	 * access when visiting them.
	 */
	private ObjectMap<String, String> mapLayers;

	private DistributionPointsFormula distributionPointsFormula;
	private AttributePointsFormula attributePointsFormula;
	private ExperienceNextLevelFormula experienceNextLevelFormula;

	public Profile() {
		this.id = 1;
		this.player = new Player();
		this.character = new Character();
		this.language = "";
		this.storyPoints = 0;
		this.path = "";
		this.selectedDeck = new Deck();
		this.money = 0;
		this.experience = 0;
		this.level = 1;
		this.distributionPoints = 0;
		this.experienceNextLevel = 1;

		this.inventory = new Array<Item>();
		this.decks = new Array<Deck>();
		this.quests = new Array<Quest>();
		this.defeatedEnemies = new Array<EnemyDefeat>();
		this.endings = new Array<Ending>();
		this.events = new ObjectMap<Event, Array<EventAction>>();
		this.mapLayers = new ObjectMap<String, String>();

		this.distributionPointsFormula = null;
		this.attributePointsFormula = null;
		this.experienceNextLevelFormula = null;
	}

	public Profile(int id) {
		this();
		this.setId(id);
	}
	
	@Override
	public int compareTo(Object o) {
		return this.id - ((Profile) o).getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.player = json.readValue("player", Player.class, jsonData);
		this.character = json.readValue("character", Character.class, jsonData);
		this.language = json.readValue("language", String.class, jsonData);
		this.storyPoints = json.readValue("storyPoints", Integer.class,
				jsonData);
		this.path = json.readValue("path", String.class, jsonData);
		this.selectedDeck = json.readValue("selectedDeck", Deck.class, jsonData);
		this.money = json.readValue("money", Integer.class, jsonData);
		this.experience = json.readValue("experience", Integer.class, jsonData);
		this.level = json.readValue("level", Integer.class, jsonData);
		this.distributionPoints = json.readValue("distributionPoints",
				Integer.class, jsonData);
		this.experienceNextLevel = json.readValue("experienceNextLevel",
				Integer.class, jsonData);

		this.inventory = json.readValue("inventory", Array.class, jsonData);
		this.decks = json.readValue("decks", Array.class, jsonData);
		this.quests = json.readValue("quests", Array.class, jsonData);
		this.defeatedEnemies = json.readValue("defeatedEnemies", Array.class,
				jsonData);
		this.endings = json.readValue("endings", Array.class, jsonData);
		this.events = json.readValue("events", ObjectMap.class, jsonData);
		this.mapLayers = json.readValue("mapLayers", ObjectMap.class, jsonData);

		this.distributionPointsFormula = json.readValue(
				"distributionPointsFormula", DistributionPointsFormula.class,
				jsonData);
		this.attributePointsFormula = json.readValue("attributePointsFormula",
				AttributePointsFormula.class, jsonData);
		this.experienceNextLevelFormula = json.readValue(
				"experienceNextLevelFormula", ExperienceNextLevelFormula.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("player", this.player);
		json.writeValue("character", this.character);
		json.writeValue("language", this.language);
		json.writeValue("storyPoints", this.storyPoints);
		json.writeValue("path", this.path);
		json.writeValue("selectedDeck", this.selectedDeck);
		json.writeValue("money", this.money);
		json.writeValue("experience", this.experience);
		json.writeValue("level", this.level);
		json.writeValue("distributionPoints", this.distributionPoints);
		json.writeValue("experienceNextLevel", this.experienceNextLevel);

		json.writeValue("inventory", this.inventory);
		json.writeValue("decks", this.decks);
		json.writeValue("quests", this.quests);
		json.writeValue("defeatedEnemies", this.defeatedEnemies);
		json.writeValue("endings", this.endings);
		json.writeValue("events", this.events);
		json.writeValue("mapLayers", this.mapLayers);

		json.writeValue("distributionPointsFormula",
				this.distributionPointsFormula);
		json.writeValue("attributePointsFormula", this.attributePointsFormula);
		json.writeValue("experienceNextLevelFormula",
				this.experienceNextLevelFormula);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getMoney() {
		return money;
	}

	public Array<Item> getInventory() {
		return inventory;
	}

	public Array<Deck> getDecks() {
		return decks;
	}

	public Deck getSelectedDeck() {
		return selectedDeck;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Array<Quest> getQuests() {
		return quests;
	}

	public Array<EnemyDefeat> getDefeatedEnemies() {
		return defeatedEnemies;
	}

	public ObjectMap<Event, Array<EventAction>> getEvents() {
		return events;
	}

	public ObjectMap<String, String> getMapLayers() {
		return mapLayers;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
