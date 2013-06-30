package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.Scene;
import br.edu.ifsp.pds.shadowstruggles.model.Trap;
import br.edu.ifsp.pds.shadowstruggles.model.TutorialDialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SerializationException;

/**
 * The main class for dealing with I/O operations, including i18n. It uses JSON
 * as its data notation.
 */

public class DataManager {

	private Json json;

	private FileHandle gameSettings;
	private FileHandle profiles;
	private FileHandle scenes;
	private FileHandle decks;
	private FileHandle fighters;
	private FileHandle effects;
	private FileHandle traps;
	private FileHandle menuTextFile;
	private FileHandle tutorialDialogs;

	private Array<Fighter> fighterList;
	private Array<Effect> effectList;
	private Array<Trap> trapList;
	private Array<Deck> decksList;
	private Array<Scene> scenesList;
	private Array<TutorialDialog> tutorialDialogsList;

	private Languages languages;
	private Settings settings;
	private MenuText menuText;

	public DataManager() {
		this("en_us");
	}

	/**
	 * The constructor instantiates the FileHandle objects and retrieves all
	 * informations from the files, so that most of the processing can happen
	 * here. There should be only one instance of DataManager for the entire
	 * application.
	 * 
	 * @param language
	 *            The language to write/recover the files from. The currently
	 *            supported languages are en_us and pt_br.
	 */
	public DataManager(String language) throws SerializationException {
		this.json = new Json();

		this.gameSettings = Gdx.files.internal("data/game_settings.json");
		this.profiles = Gdx.files.local("data/files/data_profiles.json");
		this.decks = Gdx.files.internal("data/files/decks.json");
		this.languages = json.fromJson(Languages.class,
				Gdx.files.internal("data/menu/languages.json"));

		this.createDecks();
		this.createSettings();

		this.changeLanguage(language);
	}

	public void changeLanguage(String language) {
		this.scenes = Gdx.files.internal("data/script/" + language + ".json");
		this.fighters = Gdx.files.internal("data/files/fighters_" + language
				+ ".json");
		this.effects = Gdx.files.internal("data/files/effects_" + language
				+ ".json");
		this.traps = Gdx.files.internal("data/files/traps_" + language
				+ ".json");
		this.menuTextFile = Gdx.files.internal("data/menu/text_" + language
				+ ".json");
		this.tutorialDialogs = Gdx.files.local("data/files/tutorial_"
				+ language + ".json");
		this.createFighters();
		this.createEffects();
		this.createTraps();
		this.createMenuText();
		this.createScenes();
		this.createTutorialDialogs();
	}

	/**
	 * Populates the Array of fighters in the game with data from file,
	 * attributing to each fighter its respective battleAction. This method
	 * should be invoked only once in the entire application.
	 */
	private void createFighters() {
		fighterList = new Array<Fighter>();
		Array<Fighter> retrievedFighters = retrieveFighters();

		for (Fighter fighter : retrievedFighters) {
			fighterList.add(fighter);
		}
	}

	/**
	 * Populates the Array of effects in the game with data from file,
	 * attributing to each effect its respective battleAction. This method
	 * should be invoked only once in the entire application.
	 */
	private void createEffects() {
		effectList = new Array<Effect>();
		Array<Effect> retrievedEffects = retrieveEffects();

		for (Effect effect : retrievedEffects) {
			effectList.add(effect);
		}
	}

	/**
	 * Populates the Array of traps in the game with data from file, attributing
	 * to each trap its respective battleAction. This method should be invoked
	 * only once in the entire application.
	 */
	private void createTraps() {
		trapList = new Array<Trap>();
		Array<Trap> retrievedTraps = retrieveTraps();
		for (Trap trap : retrievedTraps) {
			/*
			 * if (trap.getName().equals("Electric Current Level 1") ||
			 * trap.getName().equals("Corrente El�trica n�vel 1")) {
			 * 
			 * trap.setAction(new EletricCurrent1Action()); } else if
			 * (trap.getName().equals("Hacking")) { trap.setAction(new
			 * HackingAction()); }
			 */
			trapList.add(trap);
		}
	}

	private void createMenuText() throws SerializationException {
		if (this.menuTextFile.exists()) {
			menuText = json.fromJson(MenuText.class, menuTextFile);
		} else {
			menuText = new MenuText();
		}
	}

	/***
	 * Gets the settings for the game from a file and storages them in the
	 * variable settings.
	 * 
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	private void createSettings() throws SerializationException {

		if (this.gameSettings.exists()) {
			settings = json.fromJson(Settings.class, this.gameSettings);
		} else {
			settings = new Settings();
		}
	}

	/***
	 * Gets the decks from a file.
	 * 
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	private void createDecks() throws SerializationException {

		decksList = new Array<Deck>();

		if (!this.decks.exists()) {
			decksList.add(new Deck());
		} else {
			decksList.addAll(json.fromJson(Array.class, this.decks));
		}
	}

	/***
	 * Gets the scenes from a file.
	 * 
	 * @throws SerializationException
	 *             An error while reading an object.
	 */
	private void createScenes() throws SerializationException {
		scenesList = new Array<Scene>();

		if (!this.scenes.exists()) {
			scenesList.add(new Scene());
		} else {
			scenesList.addAll(json.fromJson(Array.class, this.scenes));
		}

	}

	private void createTutorialDialogs() throws SerializationException {
		tutorialDialogsList = new Array<TutorialDialog>();
		if (!this.tutorialDialogs.exists()) {
			tutorialDialogsList.add(new TutorialDialog());
		} else {
			tutorialDialogsList.addAll(json.fromJson(Array.class,
					this.tutorialDialogs));
		}
	}

	/***
	 * Gets the profiles from a file. If the file doesn't exist, it will create
	 * one. Since profiles is the only dynamic data, that is, it is possible to
	 * modify and create profiles throughout the game, this method may be called
	 * multiple times.
	 * 
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public Array<Profile> retrieveProfiles() throws SerializationException {
		Array<Profile> profilesList = new Array<Profile>();

		if (this.profiles.exists()) {
			profilesList.addAll((json.fromJson(Array.class, profiles)));
		} else {
			profilesList.add(new Profile());
			json.toJson(profilesList, this.profiles);
		}

		return profilesList;
	}

	public Array<TutorialDialog> retrieveTutorial()
			throws SerializationException {
		Array<TutorialDialog> dialogs = new Array<TutorialDialog>();

		if (this.tutorialDialogs.exists()) {
			dialogs.addAll((json.fromJson(Array.class, tutorialDialogs)));
		} else {

			json.toJson(dialogs, this.profiles);
		}

		return dialogs;
	}

	/**
	 * Saves a profile to a file ("data/data_profiles.json").
	 * 
	 * @param profile
	 *            The profile to be saved.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public void writeProfile(Profile newProfile) throws SerializationException {
		Array<Profile> allProfiles;
		try {
			allProfiles = this.retrieveProfiles();
		} catch (Exception ew) {
			allProfiles = new Array<Profile>();
		}
		for (Profile profile : allProfiles) {
			if (profile.getId() == newProfile.getId())
				allProfiles.removeValue(profile, true);
		}
		allProfiles.add(newProfile);
		json.toJson(allProfiles, this.profiles);
	}

	public void writeTutorialDialog(TutorialDialog dialog) {
		Array<TutorialDialog> dialogs;
		try {
			dialogs = this.retrieveTutorial();
		} catch (Exception ew) {
			dialogs = new Array<TutorialDialog>();
		}

		dialogs.add(dialog);
		json.toJson(dialogs, this.tutorialDialogs);
	}

	public Array<Fighter> getFighterList() {
		return fighterList;
	}

	public Array<Effect> getEffectList() {
		return effectList;
	}

	public Array<Trap> getTrapList() {
		return trapList;
	}

	public MenuText getMenuText() {
		return menuText;
	}

	public Settings getSettings() {
		return settings;
	}

	public Array<Deck> getDecksList() {
		return decksList;
	}

	public Array<Scene> getScenesList() {
		return scenesList;
	}

	public ObjectMap<String, String> getLanguages() {
		return languages.getLanguages();
	}

	public boolean profileExists() {
		return this.profiles.exists();
	}

	public Array<TutorialDialog> getTutorialDialogsList() {
		return tutorialDialogsList;
	}

	/**
	 * Gets all fighters from a file. This should only be invoked once, and by
	 * the object itself; other objects should use the
	 * {@link DataManager#getFighterList()} method instead.
	 * 
	 * @return An Array<Fighter> object.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 * @see DataManager#getFighterList().
	 */
	private Array<Fighter> retrieveFighters() throws SerializationException {
		Array<Fighter> retrievedFighters = new Array<Fighter>();

		if (this.fighters.exists()) {
			retrievedFighters.addAll(json.fromJson(Array.class, this.fighters));
		}

		return retrievedFighters;
	}

	/**
	 * Gets all effects from a file. This should only be invoked once, and by
	 * the object itself; other objects should use the
	 * {@link DataManager#getEffectList()} method instead.
	 * 
	 * @return An Array<Effect> object.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 * @see DataManager#getEffectList().
	 */
	public Array<Effect> retrieveEffects() throws SerializationException {
		Array<Effect> retrievedEffects = new Array<Effect>();

		if (this.effects.exists()) {
			retrievedEffects.addAll(json.fromJson(Array.class, this.effects));
		}
		return retrievedEffects;
	}

	/**
	 * Gets all traps from a file. This should only be invoked once, and by the
	 * object itself; other objects should use the
	 * {@link DataManager#getTrapList()} method instead.
	 * 
	 * @return An Array<Trap> object.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 * @see DataManager#getTrapList().
	 */
	private Array<Trap> retrieveTraps() throws SerializationException {
		Array<Trap> retrievedTraps = new Array<Trap>();

		if (this.traps.exists()) {
			retrievedTraps.addAll(json.fromJson(Array.class, this.traps));
		}

		return retrievedTraps;
	}

}
