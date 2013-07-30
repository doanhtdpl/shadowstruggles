package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SerializationException;

/**
 * The main class for dealing with I/O operations, including i18n. It uses JSON
 * as its data notation.
 */

public class DataManager {

	private static DataManager instance;

	private String currentLanguage;
	@SuppressWarnings("rawtypes")
	private ObjectMap<Class<?>, Array> recordSet;

	public static DataManager getInstance() {
		if (instance == null)
			instance = new DataManager();

		return instance;
	}

	public static DataManager getInstance(String language) {
		if (instance != null)
			if (instance.getCurrentLanguage().equals(language))
				return instance;

		instance = new DataManager(language);
		return instance;
	}

	private DataManager() {
		this("en_us");
	}

	/**
	 * The constructor instantiates the FileHandle objects and retrieves all
	 * informations from the files, so that most of the processing can happen
	 * here.
	 * 
	 * @param language
	 *            The language to write/recover the files from.
	 */
	@SuppressWarnings("rawtypes")
	private DataManager(String language) {
		this.recordSet = new ObjectMap<Class<?>, Array>();
		this.changeLanguage(language);
	}

	public void changeLanguage(String language) {
		this.currentLanguage = language;
		this.retrieve();
	}

	/**
	 * Stores all data from JSON files into the record set.
	 */
	private void retrieve() {
		for (Class<?> c : FileMap.classToFile.keySet()) {
			Array<Object> objectList = new Array<Object>();
			String path = FileMap.classToFile.get(c);

			if (c != Languages.class && c != Settings.class)
				path = localizedPath(currentLanguage, path);

			FileHandle handle = Gdx.files.local(path);

			try {
				objectList.addAll(MyJson.getJson()
						.fromJson(Array.class, handle));
			} catch (SerializationException ex) {
				ex.printStackTrace(); // TODO: Logging.
			}

			this.recordSet.put(c, objectList);
		}
	}

	/**
	 * Creates a new profile.
	 */
	public void writeProfile(Profile profile) {
		// First, update the record set.
		@SuppressWarnings("unchecked")
		Array<Profile> currentProfiles = recordSet.get(Profile.class);
		currentProfiles.add(profile);
		recordSet.put(Profile.class, currentProfiles);

		// Then, rewrite the file.
		String path = localizedPath(currentLanguage,
				FileMap.classToFile.get(Profile.class));
		FileHandle handle = Gdx.files.local(path);

		try {
			MyJson.getJson().toJson(currentProfiles, handle);
		} catch (SerializationException ex) {
			ex.printStackTrace(); // TODO: Logging.
		}
	}

	/**
	 * Persists the changes made in profiles and events.
	 */
	public void save() {
		String profilesPath = localizedPath(currentLanguage,
				FileMap.classToFile.get(Profile.class));
		String eventsPath = localizedPath(currentLanguage,
				FileMap.classToFile.get(Event.class));
		FileHandle profilesHandle = Gdx.files.local(profilesPath);
		FileHandle eventsHandle = Gdx.files.local(eventsPath);

		try {
			MyJson.getJson().toJson(recordSet.get(Profile.class),
					profilesHandle);
			MyJson.getJson().toJson(recordSet.get(Event.class), eventsHandle);
		} catch (SerializationException ex) {
			ex.printStackTrace(); // TODO: Logging.
		}
	}

	/**
	 * Edits an entry in the Event set. All changes are local; in order to
	 * persist them, the profile must be saved.
	 */
	public void editEvent(int id, Event modifiedEvent) {
		@SuppressWarnings("unchecked")
		Array<Event> currentEvents = recordSet.get(Event.class);

		Event previousEvent = new Event();
		previousEvent.setId(id);
		int index = currentEvents.indexOf(previousEvent, false);

		currentEvents.set(index, modifiedEvent);
		recordSet.put(Event.class, currentEvents);
	}

	@SuppressWarnings("rawtypes")
	public Array getObjectSet(Class<?> c) {
		return this.recordSet.get(c);
	}

	public String getCurrentLanguage() {
		return this.currentLanguage;
	}

	private static String localizedPath(String currentLanguage, String path) {
		return path.replace("data/", "data/" + currentLanguage + "/");
	}

}
