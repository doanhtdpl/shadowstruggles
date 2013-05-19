package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * A class representing the player's data. There are multiple profiles
 * available, each for a different player or playthrough.
 */
public class Profile implements Serializable, Comparable<Object> {

	private int id;
	private Scene currentScene;
	private String language;
	private int light;
	private int dark;
	private String path;
	private Array<Float> battlesFought;
	private int money;
	private Array<Card> trunk;
	private String deck;

	public Profile(int id, Scene currentScene, String language, int light,
			int dark, String path, int money, Array<Card> trunk, String deck) {
		this.id = id;
		this.currentScene = currentScene;
		this.language = language;
		this.light = light;
		this.dark = dark;
		this.path = path;
		this.money = money;
		this.trunk = trunk;
		this.deck = deck;
		this.setBattlesFought(new Array<Float>());
	}

	public Profile() {
		this.id = 1;
		this.currentScene = Scene.FIRST_SCENE;
		this.language = "en_us";
		this.dark = 0;
		this.light = 0;
		this.money = 10000;
		this.path = "";
		this.trunk = new Array<Card>();
		this.deck = "Starter Deck";
		this.setBattlesFought(new Array<Float>());
	}

	public Profile(int id) {
		this.id = id;
		this.currentScene = Scene.FIRST_SCENE;
		this.language = "en_us";
		this.dark = 0;
		this.light = 0;
		this.money = 10000;
		this.path = "";
		this.trunk = new Array<Card>();
		this.deck = "Starter Deck";
		this.setBattlesFought(new Array<Float>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}

	public Array<Float> getBattlesFought() {
		return battlesFought;
	}

	public void setBattlesFought(Array<Float> battlesFought) {
		this.battlesFought = battlesFought;
	}

	public Deck getDeck(DataManager data) {
		return DeckDAO.getDeck(deck, data);
	}

	public int getMoney() {
		return money;
	}

	public boolean moveMoney(int value) {

		if ((money + value) >= 0) {
			money += value;
			return true;
		} else
			return false;
	}

	public Array<Card> getTrunk() {
		return trunk;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("currentScene", this.currentScene);
		json.writeValue("language", this.language);
		json.writeValue("light", this.light);
		json.writeValue("dark", this.dark);
		json.writeValue("path", this.path);
		json.writeValue("battlesFought", this.battlesFought);
		json.writeValue("money", this.money);
		json.writeValue("trunk", this.trunk);
		json.writeValue("deck", this.deck);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.currentScene = json.readValue("currentScene", Scene.class,
				jsonData);
		this.language = json.readValue("language", String.class, jsonData);
		this.light = json.readValue("light", Integer.class, jsonData);
		this.dark = json.readValue("dark", Integer.class, jsonData);
		this.path = json.readValue("path", String.class, jsonData);
		this.battlesFought = json.readValue("battlesFought", Array.class,
				jsonData);
		this.money = json.readValue("money", Integer.class, jsonData);
		this.trunk = json.readValue("trunk", Array.class, jsonData);
		this.deck = json.readValue("deck", String.class, jsonData);
	}

	@Override
	public int compareTo(Object o) {
		return this.id - ((Profile) o).getId();
	}

}
