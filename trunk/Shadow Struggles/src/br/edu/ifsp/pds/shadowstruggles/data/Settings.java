package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/***
 * Class used as a container for the game's settings, so that they can be loaded
 * from a file.
 * 
 */
public class Settings implements Serializable {

	// Game settings retrieved from game_settings.
	public int tileWidth = 48;
	public int tileHeight = 48;
	public int playerMaxHP = 100;
	public int enemyMaxHP = 100;
	public int playerMaxEnergy = 100;
	public int enemyMaxEnergy = 100;
	public int playerInitialEnergy = 45;
	public int enemyInitialEnergy = 45;
	public int deckSize = 30;
	public int handMaxSize = 5;
	public int drawInterval = 10;
	public int energyIncrease = 5;
	public int energyIncreaseInterval = 5;
	public String language = "en_us";

	// GUI settings retrieved from gui_settings.
	public int backgroundWidth = 1920;
	public int backgroundHeight = 480;
	public int bottomElementY = 10; // Y-coordinate of an element at the bottom
									// GUI.
	public int deckX = 830;
	public int energyX = 100; // X-coordinate of energy bar.
	public int firstCardX = 200; // X-coordinate of the first card on the
									// player's hand.
	public int cardSpacing = 0;
	public int lifeBarY = 520; // Y-coordinate of both of the life bars.
	public int playerLifeX = 20;
	public int enemyLifeX = 840;
	public int mapHeight = 640;
	public int mapWidth = 960;

	// RPG settings.
	public int rpgTileSize = 32;
	public String defaultObjLayer = "default-objects";
	public String defaultTileLayer = "tiles";
	public String collidableTile = "obstacle";

	@Override
	public void write(Json json) {
		json.writeValue("tileWidth", tileWidth);
		json.writeValue("tileHeight", tileHeight);
		json.writeValue("playerMaxHP", playerMaxHP);
		json.writeValue("enemyMaxHP", enemyMaxHP);
		json.writeValue("playerMaxEnergy", playerMaxEnergy);
		json.writeValue("enemyMaxEnergy", enemyMaxEnergy);
		json.writeValue("playerInitialEnergy", playerInitialEnergy);
		json.writeValue("enemyInitialEnergy", enemyInitialEnergy);
		json.writeValue("deckSize", deckSize);
		json.writeValue("handMaxSize", handMaxSize);
		json.writeValue("drawInterval", drawInterval);
		json.writeValue("energyIncrease", energyIncrease);
		json.writeValue("energyIncreaseInterval", energyIncreaseInterval);
		json.writeValue("language", language);

		json.writeValue("backgroundWidth", backgroundWidth);
		json.writeValue("backgroundHeight", backgroundHeight);
		json.writeValue("bottomElementY", bottomElementY);
		json.writeValue("deckX", deckX);
		json.writeValue("energyX", energyX);
		json.writeValue("firstCardX", firstCardX);
		json.writeValue("cardSpacing", cardSpacing);
		json.writeValue("lifeBarY", lifeBarY);
		json.writeValue("playerLifeX", playerLifeX);
		json.writeValue("enemyLifeX", enemyLifeX);
		json.writeValue("mapHeight", mapHeight);
		json.writeValue("mapWidth", mapWidth);

		json.writeValue("rpgTileSize", rpgTileSize);
		json.writeValue("defaultObjLayer", defaultObjLayer);
		json.writeValue("defaultTileLayer", defaultTileLayer);
		json.writeValue("collidableTile", collidableTile);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		tileWidth = json.readValue("tileWidth", Integer.class, jsonData);
		tileHeight = json.readValue("tileHeight", Integer.class, jsonData);
		playerMaxHP = json.readValue("playerMaxHP", Integer.class, jsonData);
		enemyMaxHP = json.readValue("enemyMaxHP", Integer.class, jsonData);
		playerMaxEnergy = json.readValue("playerMaxEnergy", Integer.class,
				jsonData);
		enemyMaxEnergy = json.readValue("enemyMaxEnergy", Integer.class,
				jsonData);
		playerInitialEnergy = json.readValue("playerInitialEnergy",
				Integer.class, jsonData);
		enemyInitialEnergy = json.readValue("enemyInitialEnergy",
				Integer.class, jsonData);
		deckSize = json.readValue("deckSize", Integer.class, jsonData);
		handMaxSize = json.readValue("handMaxSize", Integer.class, jsonData);
		drawInterval = json.readValue("drawInterval", Integer.class, jsonData);
		energyIncrease = json.readValue("energyIncrease", Integer.class,
				jsonData);
		language = json.readValue("language", String.class, jsonData);

		backgroundWidth = json.readValue("backgroundWidth", Integer.class,
				jsonData);
		backgroundHeight = json.readValue("backgroundHeight", Integer.class,
				jsonData);
		bottomElementY = json.readValue("bottomElementY", Integer.class,
				jsonData);
		deckX = json.readValue("deckX", Integer.class, jsonData);
		energyX = json.readValue("energyX", Integer.class, jsonData);
		firstCardX = json.readValue("firstCardX", Integer.class, jsonData);
		cardSpacing = json.readValue("cardSpacing", Integer.class, jsonData);
		lifeBarY = json.readValue("lifeBarY", Integer.class, jsonData);
		playerLifeX = json.readValue("playerLifeX", Integer.class, jsonData);
		enemyLifeX = json.readValue("enemyLifeX", Integer.class, jsonData);
		mapHeight = json.readValue("mapHeight", Integer.class, jsonData);
		mapWidth = json.readValue("mapWidth", Integer.class, jsonData);

		rpgTileSize = json.readValue("rpgTileSize", Integer.class, jsonData);
		defaultObjLayer = json.readValue("defaultObjLayer", String.class,
				jsonData);
		defaultTileLayer = json.readValue("defaultTileLayer", String.class,
				jsonData);
		collidableTile = json.readValue("collidableTile", String.class,
				jsonData);
	}

}
