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

	public Array<Item> inventory;
	public Array<Deck> deck;
	public Array<Item> unlockedItems;
	public Array<Quest> quests;
	public Array<EnemyDefeat> defeatedEnemies;
	public Array<Ending> endings;
	/**
	 * Records which actions should be performed for each event.
	 */
	public ObjectMap<Event, Array<EventAction>> events;

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

		this.inventory = new Array<Item>();
		this.deck = new Array<Deck>();
		this.unlockedItems = new Array<Item>();
		this.quests = new Array<Quest>();
		this.defeatedEnemies = new Array<EnemyDefeat>();
		this.endings = new Array<Ending>();
		this.events = new ObjectMap<Event, Array<EventAction>>();

		this.distributionPointsFormula = null;
		this.attributePointsFormula = null;
		this.experienceNextLevelFormula = null;
	}

	public Profile(int id, Player player, String language, int storyPoints,
			String path, int money, int experience, int level,
			int distributionPoints, int experienceNextLevel,
			Array<Item> inventory, Array<Deck> deck, Array<Item> unlockedItems,
			Array<Quest> quests, Array<EnemyDefeat> defeatedEnemies,
			Array<Ending> endings, ObjectMap<Event, Array<EventAction>> events,
			DistributionPointsFormula distributionPointsFormula,
			AttributePointsFormula attributePointsFormula,
			ExperienceNextLevelFormula experienceNextLevelFormula) {
		this.id = id;
		this.player = player;
		this.language = language;
		this.storyPoints = storyPoints;
		this.path = path;
		this.money = money;
		this.experience = experience;
		this.level = level;
		this.distributionPoints = distributionPoints;
		this.experienceNextLevel = experienceNextLevel;

		this.inventory = inventory;
		this.deck = deck;
		this.unlockedItems = unlockedItems;
		this.quests = quests;
		this.defeatedEnemies = defeatedEnemies;
		this.endings = endings;
		this.events = events;

		this.distributionPointsFormula = distributionPointsFormula;
		this.attributePointsFormula = attributePointsFormula;
		this.experienceNextLevelFormula = experienceNextLevelFormula;
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
		this.money = json.readValue("money", Integer.class, jsonData);
		this.experience = json.readValue("experience", Integer.class, jsonData);
		this.level = json.readValue("level", Integer.class, jsonData);
		this.distributionPoints = json.readValue("distributionPoints",
				Integer.class, jsonData);
		this.experienceNextLevel = json.readValue("experienceNextLevel",
				Integer.class, jsonData);

		this.inventory = json.readValue("inventory", Array.class, jsonData);
		this.deck = json.readValue("deck", Array.class, jsonData);
		this.unlockedItems = json.readValue("unlockedItems", Array.class,
				jsonData);
		this.quests = json.readValue("quests", Array.class, jsonData);
		this.defeatedEnemies = json.readValue("defeatedEnemies", Array.class,
				jsonData);
		this.endings = json.readValue("endings", Array.class, jsonData);
		this.events = json.readValue("events", ObjectMap.class, jsonData);

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
		json.writeValue("money", this.money);
		json.writeValue("experience", this.experience);
		json.writeValue("level", this.level);
		json.writeValue("distributionPoints", this.distributionPoints);
		json.writeValue("experienceNextLevel", this.experienceNextLevel);

		json.writeValue("inventory", this.inventory);
		json.writeValue("deck", this.deck);
		json.writeValue("unlockedItems", this.unlockedItems);
		json.writeValue("quests", this.quests);
		json.writeValue("defeatedEnemies", this.defeatedEnemies);
		json.writeValue("endings", this.endings);
		json.writeValue("events", this.events);

		json.writeValue("distributionPointsFormula",
				this.distributionPointsFormula);
		json.writeValue("attributePointsFormula", this.attributePointsFormula);
		json.writeValue("experienceNextLevelFormula",
				this.experienceNextLevelFormula);
	}

}
