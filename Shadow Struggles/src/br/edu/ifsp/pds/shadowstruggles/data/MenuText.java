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

	@Override
	public void write(Json json) {
		// General use
		json.writeValue("returnToStart", returnToStart);
		
		// StartScreen
		json.writeValue("continueGame", continueGame);
		json.writeValue("newGame", newGame);
		
		// Select Profile
		json.writeValue("profile", profile);
		
		// ErrorScreen
		json.writeValue("terminate", terminate);
		json.writeValue("details", details);
		
		// MainScreen
		json.writeValue("campaign", campaign);
		json.writeValue("freePlay", freePlay);
		json.writeValue("editDeck", editDeck);
		json.writeValue("shop", shop);
		
		// VictoryScreen
		json.writeValue("victory", victory);
		json.writeValue("continueButton", continueButton);
		json.writeValue("mainMenuButton", mainMenuButton);
		
		// InGameMenu
		json.writeValue("returnToGame", returnToGame);
		json.writeValue("exit", exit);
		json.writeValue("checkCards", checkCards);
		
		// SettingsScreen
		json.writeValue("configurations", configurations);
		json.writeValue("volume", volume);
		json.writeValue("music", music);
		json.writeValue("on", on);
		json.writeValue("off", off);
		json.writeValue("languageSelection", languageSelection);
		
		// ShopScreen
		json.writeValue("buy", buy);
		json.writeValue("sell", sell);
		json.writeValue("packs", packs);
		json.writeValue("cards", cards);
		json.writeValue("extra", extra);
		
		// DefeatScreen
		json.writeValue("retryButton", retryButton);
		json.writeValue("defeat", defeat);
		
		// FreePlayScreen
		json.writeValue("practiceBattle", practiceBattle);
		
		// EditDeckScreen
		json.writeValue("newDeck", newDeck);
	}

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
		languageSelection = json.readValue("languageSelection", String.class, jsonData);
		
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
	}

}
