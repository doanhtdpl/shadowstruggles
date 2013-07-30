package br.edu.ifsp.pds.shadowstruggles.model.profiles;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;

import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventAction;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Ending;

public class Profile implements Serializable, Comparable<Object> {
	private int id;
	private Player player;
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
	private Array<Item> unlockedItems;
	private Array<Quest> quests;
	private Array<EnemyDefeat> defeatedEnemies;
	private Array<Ending> endings;
	/**
	 * Records which actions should be performed for each event.
	 */
	private ObjectMap<Event, Array<EventAction>> events;
	/**
	 * Relates the maps to the object layer which the player character will
	 * access upon visiting them.
	 */
	private ObjectMap<String, String> mapLayers;

	private DistributionPointsFormula distributionPointsFormula;
	private AttributePointsFormula attributePointsFormula;
	private ExperienceNextLevelFormula experienceNextLevelFormula;

	public Profile() {
		this.id = 1;
		this.player = new Player();
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
		this.unlockedItems = new Array<Item>();
		this.quests = new Array<Quest>();
		this.defeatedEnemies = new Array<EnemyDefeat>();
		this.endings = new Array<Ending>();
		this.events = new ObjectMap<Event, Array<EventAction>>();
		this.mapLayers = new ObjectMap<String, String>();

		this.distributionPointsFormula = null;
		this.attributePointsFormula = null;
		this.experienceNextLevelFormula = null;
	}

	public Profile(int id, Player player, String language, int storyPoints,
			String path, Deck selectedDeck, int money, int experience, int level,
			int distributionPoints, int experienceNextLevel,
			Array<Item> inventory, Array<Deck> decks, Array<Item> unlockedItems,
			Array<Quest> quests, Array<EnemyDefeat> defeatedEnemies,
			Array<Ending> endings, ObjectMap<Event, Array<EventAction>> events,
			ObjectMap<String, String> mapLayers,
			DistributionPointsFormula distributionPointsFormula,
			AttributePointsFormula attributePointsFormula,
			ExperienceNextLevelFormula experienceNextLevelFormula) {
		this.id = id;
		this.player = player;
		this.language = language;
		this.storyPoints = storyPoints;
		this.path = path;
		this.selectedDeck = selectedDeck;
		this.money = money;
		this.experience = experience;
		this.level = level;
		this.distributionPoints = distributionPoints;
		this.experienceNextLevel = experienceNextLevel;

		this.inventory = inventory;
		this.decks = decks;
		this.unlockedItems = unlockedItems;
		this.quests = quests;
		this.defeatedEnemies = defeatedEnemies;
		this.endings = endings;
		this.events = events;
		this.mapLayers = mapLayers;

		this.distributionPointsFormula = distributionPointsFormula;
		this.attributePointsFormula = attributePointsFormula;
		this.experienceNextLevelFormula = experienceNextLevelFormula;
	}

	public Profile(int id) {
		this();
		this.setId(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.player = json.readValue("player", Player.class, jsonData);
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
		this.unlockedItems = json.readValue("unlockedItems", Array.class,
				jsonData);
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
		json.writeValue("unlockedItems", this.unlockedItems);
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

	@Override
	public int compareTo(Object o) {
		return this.id - ((Profile) o).getId();
	}
}
