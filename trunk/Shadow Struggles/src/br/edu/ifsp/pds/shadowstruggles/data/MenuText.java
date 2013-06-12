package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Class for storage of all text shown in the GUI, for purposes of i18n.
 */
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
	public String editDeck="";
	public String shop="";

	// VictoryScreen
	public String victory = "";
	public String continueButton = "";
	public String mainMenuButton = "";

	// InGameScreen
	public String returnToGame = "";
	public String exit = "";
	public String checkCards = "";

	// SettingsScreen
	public String volume = "";

	// defeatScreen
	public String defeat = "";
	public String retryButton = "";

	// FreePlayScreen
	public String practiceBattle = "";
	
	//EditDeckScreen
	public String newDeck = "";
	
	@Override
	public void write(Json json) {
		json.writeValue("continueGame", continueGame);
		json.writeValue("newGame", newGame);
		json.writeValue("returnToStart", returnToStart);
		json.writeValue("profile", profile);
		json.writeValue("terminate", terminate);
		json.writeValue("details", details);
		json.writeValue("campaign", campaign);
		json.writeValue("freePlay", freePlay);
		json.writeValue("configurations", configurations);
		json.writeValue("changeProfile", changeProfile);
		json.writeValue("editDeck", editDeck);
		json.writeValue("shop", shop);
		json.writeValue("victory", victory);
		json.writeValue("continueButton", continueButton);
		json.writeValue("mainMenuButton", mainMenuButton);
		json.writeValue("returnToGame", returnToGame);
		json.writeValue("exit", exit);
		json.writeValue("checkCards", checkCards);
		json.writeValue("volume:", volume);
		json.writeValue("retryButton", retryButton);
		json.writeValue("defeat", defeat);
		json.writeValue("practiceBattle", practiceBattle);
		json.writeValue("newDeck", newDeck);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		continueGame = json.readValue("continueGame", String.class, jsonData);
		newGame = json.readValue("newGame", String.class, jsonData);
		returnToStart = json.readValue("returnToStart", String.class, jsonData);
		profile = json.readValue("profile", String.class, jsonData);
		terminate = json.readValue("terminate", String.class, jsonData);
		details = json.readValue("details", String.class, jsonData);
		campaign = json.readValue("campaign", String.class, jsonData);
		freePlay = json.readValue("freePlay", String.class, jsonData);
		configurations = json.readValue("configurations", String.class,
				jsonData);
		changeProfile = json.readValue("changeProfile", String.class, jsonData);
		editDeck = json.readValue("editDeck", String.class, jsonData);
		shop = json.readValue("shop", String.class, jsonData);
		victory = json.readValue("victory", String.class, jsonData);
		continueButton = json.readValue("continueButton", String.class,
				jsonData);
		mainMenuButton = json.readValue("mainMenuButton", String.class,
				jsonData);
		returnToGame = json.readValue("returnToGame", String.class, jsonData);
		exit = json.readValue("exit", String.class, jsonData);
		checkCards = json.readValue("checkCards", String.class, jsonData);
		volume = json.readValue("volume", String.class, jsonData);
		defeat = json.readValue("defeat", String.class, jsonData);
		retryButton = json.readValue("retryButton", String.class, jsonData);
		practiceBattle = json.readValue("practiceBattle", String.class,
				jsonData);
		newDeck = json.readValue("newDeck", String.class,
				jsonData);
	}

}
