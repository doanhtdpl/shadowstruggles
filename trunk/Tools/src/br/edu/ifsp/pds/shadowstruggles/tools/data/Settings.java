package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

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
