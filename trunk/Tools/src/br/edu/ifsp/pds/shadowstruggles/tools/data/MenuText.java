package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class MenuText implements Serializable {

	public String languageName;

	// Start Screen
	public String continueGame = "";
	public String newGame = "";

	// Select Profile
	public String returnToStart = "";
	public String profile = "";

	// Error Screen
	public String terminate = "";
	public String details = "";

	// MainScreen
	public String campaign = "";
	public String freePlay = "";
	public String configurations = "";
	public String changeProfile = "";
	public String editDeck = "";
	public String shop = "";

	// VictoryScreen
	public String victory = "";
	public String continueButton = "";
	public String mainMenuButton = "";

	// InGameScreen
	public String returnToGame = "";
	public String exit = "";
	public String checkCards = "";

	// ConfigurationScreen
	public String volume = "";

	// defeatScreen
	public String defeat = "";
	public String retryButton = "";

	// FreePlayScreen
	public String practiceBattle = "";

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
