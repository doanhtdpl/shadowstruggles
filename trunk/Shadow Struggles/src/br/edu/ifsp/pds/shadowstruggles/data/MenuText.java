package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Class for storage of all text shown in the GUI, for purposes of i18n.
 */
public class MenuText implements Serializable {
	// General use
	public String returnToStart = "";

	// StartScreen
	public String continueGame = "";
	public String newGame = "";

	// Select Profile
	public String profile = "";

	// Error Screen
	public String terminate = "";
	public String details = "";

	// MainScreen
	public String campaign = "";
	public String freePlay = "";
	public String editDeck = "";
	public String shop = "";

	// VictoryScreen
	public String victory = "";
	public String continueButton = "";
	public String mainMenuButton = "";

	// InGameMenu
	public String returnToGame = "";
	public String exit = "";
	public String checkCards = "";

	// SettingsScreen
	public String configurations = "";
	public String volume = "";
	public String music = "";
	public String on = "";
	public String off = "";
	public String languageSelection = "";

	// ShopScreen
	public String buy = "";
	public String sell = "";
	public String packs = "";
	public String cards = "";
	public String extra = "";

	// DefeatScreen
	public String defeat = "";
	public String retryButton = "";

	// FreePlayScreen
	public String practiceBattle = "";

	// EditDeckScreen
	public String newDeck = "";

	// Modifiers
	public String unlocked = "";
	public String newMaxEnergy = "";
	public String newInitialEnergy = "";
	public String newMaxHealth = "";
	public String newMaxCardPoints = "";
	public String newDoubleDraw = "";
	public String newMoney = "";
	public String newLevel = "";
	public String newExperience = "";
	public String newEnergyRecovery = "";
	public String newDeckCapacity = "";
	public String acquired = "";
	public String removedItem = "";
	public String addedQuest = "";
	public String questCompleted = "";
	
	// RPGMenu
	public String status = "";
	public String inventory = "";
	public String saveGame = "";
	public String settings = "";
	public String decks = "";
	
	// RPGStatus
	public String maxEnergy = "";
	public String energyRecovery = "";
	public String deckCapacity = "";
	public String maxCardPoints = "";
	public String maxHealth = "";
	public String doubleDraw = "";

	@Override
	public void read(Json json, JsonValue jsonData) {
		// General use
		returnToStart = json.readValue("returnToStart", String.class, jsonData);

		// StartScreen
		continueGame = json.readValue("continueGame", String.class, jsonData);
		newGame = json.readValue("newGame", String.class, jsonData);

		// Select Profile
		profile = json.readValue("profile", String.class, jsonData);

		// MainScreen
		campaign = json.readValue("campaign", String.class, jsonData);
		freePlay = json.readValue("freePlay", String.class, jsonData);
		editDeck = json.readValue("editDeck", String.class, jsonData);
		shop = json.readValue("shop", String.class, jsonData);

		// ErrorScreen
		terminate = json.readValue("terminate", String.class, jsonData);
		details = json.readValue("details", String.class, jsonData);

		// VictoryScreen
		victory = json.readValue("victory", String.class, jsonData);
		continueButton = json.readValue("continueButton", String.class,
				jsonData);
		mainMenuButton = json.readValue("mainMenuButton", String.class,
				jsonData);

		// InGameMenu
		returnToGame = json.readValue("returnToGame", String.class, jsonData);
		exit = json.readValue("exit", String.class, jsonData);
		checkCards = json.readValue("checkCards", String.class, jsonData);

		// SettingsScreen
		configurations = json.readValue("configurations", String.class,
				jsonData);
		volume = json.readValue("volume", String.class, jsonData);
		music = json.readValue("music", String.class, jsonData);
		on = json.readValue("on", String.class, jsonData);
		off = json.readValue("off", String.class, jsonData);
		languageSelection = json.readValue("languageSelection", String.class,
				jsonData);

		// ShopScreen
		buy = json.readValue("buy", String.class, jsonData);
		sell = json.readValue("sell", String.class, jsonData);
		packs = json.readValue("packs", String.class, jsonData);
		cards = json.readValue("cards", String.class, jsonData);
		extra = json.readValue("extra", String.class, jsonData);

		// DefeatScreen
		defeat = json.readValue("defeat", String.class, jsonData);
		retryButton = json.readValue("retryButton", String.class, jsonData);

		// FreePlayScreen
		practiceBattle = json.readValue("practiceBattle", String.class,
				jsonData);

		// EditDeckScreen
		newDeck = json.readValue("newDeck", String.class, jsonData);

		// Modifiers
		unlocked = json.readValue("unlocked", String.class, jsonData);
		newMaxEnergy = json.readValue("newMaxEnergy", String.class, jsonData);
		newInitialEnergy = json.readValue("newInitialEnergy", String.class,
				jsonData);
		newMaxHealth = json.readValue("newMaxHealth", String.class, jsonData);
		newMaxCardPoints = json.readValue("newMaxCardPoints", String.class,
				jsonData);
		newDoubleDraw = json.readValue("newDoubleDraw", String.class, jsonData);
		newMoney = json.readValue("newMoney", String.class, jsonData);
		newLevel = json.readValue("newLevel", String.class, jsonData);
		newExperience = json.readValue("newExperience", String.class, jsonData);
		newEnergyRecovery = json.readValue("newEnergyRecovery", String.class,
				jsonData);
		newDeckCapacity = json.readValue("newDeckCapacity", String.class,
				jsonData);
		acquired = json.readValue("acquired", String.class, jsonData);
		removedItem = json.readValue("removedItem", String.class, jsonData);
		addedQuest = json.readValue("addedQuest", String.class, jsonData);
		questCompleted = json.readValue("questCompleted", String.class,
				jsonData);
		
		// RPGMenu
		status = json.readValue("status", String.class, jsonData);
		inventory = json.readValue("inventory", String.class, jsonData);
		saveGame = json.readValue("saveGame", String.class, jsonData);
		settings = json.readValue("settings", String.class, jsonData);
		decks = json.readValue("decks", String.class, jsonData);
		
		// RPGStatus
		maxEnergy = json.readValue("maxEnergy", String.class, jsonData);
		energyRecovery = json.readValue("energyRecovery", String.class, jsonData);
		deckCapacity = json.readValue("deckCapacity", String.class, jsonData);
		maxCardPoints = json.readValue("maxCardPoints", String.class, jsonData);
		maxHealth = json.readValue("maxHealth", String.class, jsonData);
		doubleDraw = json.readValue("doubleDraw", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
	}
}
