package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.SavePoint;
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
	public SavePoint savePoint;
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
	
	public DistributionPointsFormula distributionPointsFormula;
	public AttributePointsFormula attributePointsFormula;
	public ExperienceNextLevelFormula experienceNextLevelFormula;

	public Profile() {
		this.id = 1;
		this.player = new Player();
		this.language = "";
		this.savePoint = new SavePoint();
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
		
		this.distributionPointsFormula = null;
		this.attributePointsFormula = null;
		this.experienceNextLevelFormula = null;
	}
	
	public Profile(int id, Player player, String language, SavePoint savePoint,
			int storyPoints, String path, int money, int experience, int level,
			int distributionPoints, int experienceNextLevel,
			ArrayList<Item> inventory, ArrayList<Deck> deck,
			ArrayList<Item> unlockedItems, ArrayList<Quest> quests,
			ArrayList<EnemyDefeat> defeatedEnemies, ArrayList<Ending> endings,
			DistributionPointsFormula distributionPointsFormula,
			AttributePointsFormula attributePointsFormula,
			ExperienceNextLevelFormula experienceNextLevelFormula) {
		this.id = id;
		this.player = player;
		this.language = language;
		this.savePoint = savePoint;
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
		this.distributionPointsFormula = distributionPointsFormula;
		this.attributePointsFormula = attributePointsFormula;
		this.experienceNextLevelFormula = experienceNextLevelFormula;
	}

	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		
	}

}
