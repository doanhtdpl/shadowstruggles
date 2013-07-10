package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.edu.ifsp.pds.shadowstruggles.tools.Controller;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class DataManager {

	private String currentFile;
	private Languages languages;
	private Settings settings;
	private String currentLanguage;
	private Controller controller;

	/**
	 * Creates a new zip file in the specified path and establishes the initial
	 * directory structure.
	 */
	public void newZip(String path) throws IOException, ZipException {
		this.currentFile = path;
		this.settings = new Settings();
		this.currentLanguage = "en_us";
		this.languages = new Languages();
		languages.put("en_us", "English");

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path));

		for (Class<?> c : FileMap.classToFile.keySet()) {
			ZipEntry entry;

			if (c == Languages.class || c == Settings.class)
				entry = new ZipEntry(FileMap.classToFile.get(c));
			else
				entry = new ZipEntry(localizedPath(currentLanguage,
						FileMap.classToFile.get(c)));

			zos.putNextEntry(entry);
		}
		for (String s : FileMap.resourcesToDirectory.keySet()) {
			ZipEntry entry = new ZipEntry(FileMap.resourcesToDirectory.get(s));
			zos.putNextEntry(entry);
		}
		zos.close();

		this.openZip(currentFile, true);
		this.insertObject(this.languages, Languages.class);
		this.insertObject(this.settings, Settings.class);
	}

	/**
	 * If not firstTime, checks the directory structure of the zip file; else,
	 * simply extract the contents without verification.
	 */
	public boolean openZip(String path, boolean firstTime) throws IOException,
			ZipException {
		if (!firstTime && !checkZip(path)) {
			return false;
		}

		this.currentFile = path;
		net.lingala.zip4j.core.ZipFile zip = new net.lingala.zip4j.core.ZipFile(
				path);
		Path currentRelativePath = Paths.get("");
		zip.extractAll(currentRelativePath.toAbsolutePath().toString());

		return true;
	}

	/**
	 * Checks the zip file to see if its directory structure corresponds to the
	 * application's expected structure. For this, it must have a Languages file
	 * and all the required files.
	 */
	public static boolean checkZip(String zip) throws IOException, ZipException {
		boolean check = true;
		ArrayList<String> retrievedEntries = new ArrayList<String>();
		Languages retrievedLanguages = null;
		ZipFile zipFile = new ZipFile(zip);

		// First, get the Languages and Settings files and storage all entries
		// as strings.

		boolean hasLanguages = false;
		boolean hasSettings = false;
		List<?> fileHeaderList = zipFile.getFileHeaders();

		for (int i = 0; i < fileHeaderList.size(); i++) {
			FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
			String fileName = fileHeader.getFileName();
			retrievedEntries.add(fileName);

			if (fileName.equals(FileMap.classToFile.get(Languages.class))) {
				hasLanguages = true;
				retrievedLanguages = (Languages) MyJson.getJson()
						.fromJson(ArrayList.class, new File(fileName)).get(0);
			}

			if (fileName.equals(FileMap.classToFile.get(Settings.class)))
				hasSettings = true;
		}

		if (!hasLanguages || !hasSettings)
			return false;

		// Has any required folder been deleted?
		for (String value : FileMap.resourcesToDirectory.values()) {
			if (!retrievedEntries.contains(value)) {
				return false;
			}
		}

		// Has any required file been deleted?
		for (Class<?> c : FileMap.classToFile.keySet()) {
			// Check languages-independent classes first.
			if (c == Languages.class || c == Settings.class) {
				if (!retrievedEntries.contains(FileMap.classToFile.get(c))) {
					return false;
				}
			} else {
				// This class is languages-dependent, therefore, it must be
				// localized first according to each retrieved languages.
				for (String lang : retrievedLanguages.keySet()) {
					String localizedPath = localizedPath(lang,
							FileMap.classToFile.get(c));
					if (!retrievedEntries.contains(localizedPath)) {
						return false;
					}
				}
			}
		}

		return check;
	}

	/**
	 * Inserts the object or list of objects into its respective JSON class
	 * file. If a list of objects is passed as argument, the method overwrites
	 * the file content. The file is also overwritten if the object is a
	 * Languages instance. Regardless of the object type, the JSON output will
	 * always be a list.
	 */
	public <T> void insertObject(T obj, Class<?> c) throws IOException {
		if (FileMap.classToFile.containsKey(c)) {
			String path = "";

			if (c != Languages.class && c != Settings.class)
				path += localizedPath(currentLanguage,
						FileMap.classToFile.get(c));
			else
				path += FileMap.classToFile.get(c);

			File file = new File(searchFile(path, null, c));

			if (obj.getClass().isArray() || obj.getClass() == ArrayList.class) {
				MyJson.getJson().toJson(obj, file);
			} else if (obj.getClass() == Languages.class
					|| obj.getClass() == Settings.class) {
				ArrayList<T> currentObjects = new ArrayList<T>();
				currentObjects.add(obj);
				MyJson.getJson().toJson(currentObjects, file);
			} else {
				ArrayList<T> currentObjects = searchAllObjects(c);
				if (currentObjects == null)
					currentObjects = new ArrayList<T>();
				currentObjects.add(obj);
				MyJson.getJson().toJson(currentObjects, file);
			}

			if (c == Languages.class)
				createLanguagesDirectory();
		}
	}

	/**
	 * If there's any language in the languages field which doesn't have its
	 * directory structure yet, create it.
	 */
	private void createLanguagesDirectory() throws IOException {
		for (String language : this.languages.keySet()) {
			if (!Files.exists(Paths.get("data/" + language),
					LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectory(Paths.get("data/" + language));
				Files.createDirectory(Paths.get("data/" + language + "/files"));

				for (Class<?> c : FileMap.classToFile.keySet()) {
					if (c != Languages.class && c != Settings.class) {
						String localizedPath = localizedPath(language,
								FileMap.classToFile.get(c));
						Files.createFile(Paths.get(localizedPath));
					}
				}
			}
		}
	}

	/**
	 * Inserts a file into its respective directory, overwriting any existing
	 * files with the same name.
	 */
	public void insertFile(String file, String resourceType) throws IOException {
		if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
			Path originPath = Paths.get(file);
			Path destinyPath = Paths.get(FileMap.resourcesToDirectory
					.get(resourceType) + file);
			Files.copy(originPath, destinyPath,
					StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/**
	 * Retrieves an object from the respective class file of the object,
	 * according to a specified key (id).
	 */
	public <T> T searchObject(int key, Class<?> c) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		T obj = null;

		ArrayList<T> allObjects = searchAllObjects(c);
		for (T tmp : allObjects) {
			Field id = c.getField("id");
			if (id.getInt(tmp) == key)
				obj = tmp;
		}

		return obj;
	}

	/**
	 * Retrieves the single object in a JSON class file (such as the Languages
	 * object, which is unique).
	 */
	@SuppressWarnings("unchecked")
	public <T> T searchObject(Class<?> c) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		T obj = null;

		if (FileMap.classToFile.containsKey(c)) {
			String path = FileMap.classToFile.get(c);

			File file = new File(searchFile(path, null, c));
			obj = (T) MyJson.getJson().fromJson(c, file);
		}

		return obj;
	}

	/**
	 * Retrieves a file belonging to a specified resource category (or class).
	 */
	public String searchFile(String name, String resourceType, Class<?> c)
			throws IOException {
		String file = null;

		if (FileMap.classToFile.containsKey(c)) {
			if (c == Languages.class || c == Settings.class)
				file = FileMap.classToFile.get(c);
			else
				file = localizedPath(currentLanguage,
						FileMap.classToFile.get(c));
		} else if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
			file = FileMap.resourcesToDirectory.get(resourceType) + name;
		}

		if (file != null)
			if (!Files.exists(Paths.get(file), LinkOption.NOFOLLOW_LINKS))
				file = null;

		return file;
	}

	/**
	 * Returns an ArrayList containing all of the objects in a JSON class file.
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> searchAllObjects(Class<?> c) throws IOException {
		ArrayList<T> list = new ArrayList<T>();

		if (FileMap.classToFile.containsKey(c)) {
			String path = FileMap.classToFile.get(c);

			File file = new File(searchFile(path, null, c));
			list = MyJson.getJson().fromJson(ArrayList.class, file);
		}

		return list;
	}

	public ArrayList<String> searchAllFiles(String resourceType) {
		ArrayList<String> files = new ArrayList<String>();

		if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
			String path = FileMap.resourcesToDirectory.get(resourceType);
			File directory = new File(path);
			files = new ArrayList<String>(Arrays.asList(directory.list()));
		}

		return files;
	}

	public <T> void update(int key, Class<?> c, T editedObject)
			throws IOException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		ArrayList<T> objects = searchAllObjects(c);
		ArrayList<T> tmpObjects = new ArrayList<T>(objects);

		for (T obj : tmpObjects) {
			Field id = c.getField("id");
			if (key == id.getInt(obj)) {
				int previousIndex = objects.indexOf(obj);
				objects.remove(obj);
				objects.add(previousIndex, editedObject);

				break;
			}
		}

		insertObject(objects, c);
	}

	public <T> void deleteObject(int key, Class<?> c)
			throws NoSuchFieldException, SecurityException, IOException,
			IllegalArgumentException, IllegalAccessException {
		ArrayList<T> objects = searchAllObjects(c);
		ArrayList<T> tmpObjects = new ArrayList<T>(objects);

		for (T obj : tmpObjects) {
			Field id = c.getField("id");
			if (key == id.getInt(obj)) {
				objects.remove(obj);
				break;
			}
		}

		insertObject(objects, c);
	}

	public void deleteFile(String path) throws IOException {
		Files.delete(Paths.get(path));
	}

	public void deleteDirectory(String dir) throws IOException {
		File directory = new File(dir);
		File[] files = directory.listFiles();

		for (File f : files) {
			if (f.isDirectory() && f.list().length > 0) {
				deleteDirectory(f.getPath());
			} else
				Files.delete(Paths.get(f.getPath()));
		}

		Files.delete(Paths.get(dir));
	}

	public void saveToZip() throws ZipException, IOException {
		Files.delete(Paths.get(currentFile));
		ZipFile zip = new ZipFile(currentFile);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

		File f = new File("data");
		zip.addFolder(f, parameters);
	}

	/**
	 * Inserts a new entry in the languages field and updates the languages
	 * file.
	 */
	public void insertLanguage(String code, String lang) throws IOException {
		this.languages.put(code, lang);
		this.insertObject(this.languages, Languages.class);
	}

	/**
	 * Removes an entry in the languages field and updates the languages file.
	 */
	public void removeLanguage(String code) throws IOException {
		if (this.languages.containsKey(code)) {
			this.languages.remove(code);
			this.insertObject(this.languages, Languages.class);
		}
	}

	/**
	 * Updates an entry in the languages field and the languages file.
	 */
	public void updateLanguage(String code, String modifiedLang)
			throws IOException {
		if (this.languages.containsKey(code)) {
			this.languages.put(code, modifiedLang);
			this.insertObject(this.languages, Languages.class);
		}
	}

	/**
	 * Duplicates the localized data from one language to another, to make
	 * string localization easier. All data from the destiny language is
	 * deleted.
	 */
	public void copyData(String originLanguage, String destinyLanguage)
			throws IOException {
		if (languages.containsKey(originLanguage)
				&& languages.containsKey(destinyLanguage)) {
			for (Class<?> c : FileMap.classToFile.keySet()) {
				if (c != Languages.class && c != Settings.class) {
					Path source = Paths.get(localizedPath(originLanguage,
							FileMap.classToFile.get(c)));
					Path target = Paths.get(localizedPath(destinyLanguage,
							FileMap.classToFile.get(c)));
					Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public Languages getLanguages() {
		return languages;
	}

	public Settings getSettings() {
		return settings;
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String lang) {
		if (this.languages.containsKey(lang))
			this.currentLanguage = lang;
	}

	private static String localizedPath(String currentLanguage, String path) {
		return path.replace("data/", "data/" + currentLanguage + "/");
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
}
