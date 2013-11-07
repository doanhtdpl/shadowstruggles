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
	private String currentLanguage;
	private Controller controller;

	/**
	 * Creates a new zip file in the specified path and establishes the initial
	 * directory structure.
	 */
	public void newZip(String path) throws IOException, ZipException {
		this.currentFile = path;
		this.currentLanguage = "en_us";
		this.languages = new Languages();
		languages.put("en_us", "English");

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path));

		for (Class<?> c : FileMap.classToFile.keySet()) {
			String entryPath = localizedPath(currentLanguage,
					FileMap.classToFile.get(c));
			ZipEntry entry = new ZipEntry(entryPath);
			zos.putNextEntry(entry);
		}
		for (Class<?> c : FileMap.globalClassToFile.keySet()) {
			String entryPath = FileMap.globalClassToFile.get(c);
			ZipEntry entry = new ZipEntry(entryPath);
			zos.putNextEntry(entry);
		}
		for (String s : FileMap.resourcesToDirectory.keySet()) {
			String entryPath = FileMap.resourcesToDirectory.get(s);
			ZipEntry entry = new ZipEntry(entryPath);
			zos.putNextEntry(entry);
		}

		zos.close();

		this.openZip(currentFile, true);
		this.insertObject(this.languages, Languages.class);
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
		this.currentLanguage="en_us";		
		File file = new File(searchFile(FileMap.globalClassToFile.get(Languages.class), null,Languages.class ));
		ArrayList temp = MyJson.getJson().fromJson(ArrayList.class,file);
		if(temp!=null && (Languages)temp.get(0)!=null)
		this.languages=(Languages)MyJson.getJson().fromJson(ArrayList.class,file).get(0);
		else{
			this.languages.put("en_us", "English");
			insertObject(languages, Languages.class);
		}
		return true;
	}

	/**
	 * Checks the zip file to see if its directory structure corresponds to the
	 * application's expected structure. For this, it must have all the required
	 * files.
	 */
	public static boolean checkZip(String zip) throws IOException, ZipException {
		boolean check = true;
		ArrayList<String> retrievedEntries = new ArrayList<String>();
		Languages retrievedLanguages = null;
		ZipFile zipFile = new ZipFile(zip);

		List<?> fileHeaderList = zipFile.getFileHeaders();

		// Storage all entries and retrieve the languages.
		for (int i = 0; i < fileHeaderList.size(); i++) {
			FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
			String fileName = fileHeader.getFileName();
			retrievedEntries.add(fileName);

			if (fileName.equals(FileMap.globalClassToFile.get(Languages.class))) {
				// Extract the file to access its contents.
				Path currentRelativePath = Paths.get("");
				zipFile.extractFile(fileHeader, currentRelativePath
						.toAbsolutePath().toString());

				// Retrieve Languages object.
				retrievedLanguages = (Languages) MyJson.getJson()
						.fromJson(ArrayList.class, new File(fileName)).get(0);

				// Delete temporary file.
				deleteFile(fileName);
			}
		}

		if (retrievedLanguages == null)
			return false;

		// Has any required folder been deleted?
		for (String value : FileMap.resourcesToDirectory.values()) {
			if (!retrievedEntries.contains(value)) {
				return false;
			}
		}

		// Has any required file been deleted?

		// Check languages-independent classes.
		for (Class<?> c : FileMap.globalClassToFile.keySet()) {
			if (!retrievedEntries.contains(FileMap.globalClassToFile.get(c))) {
				return false;
			}
		}

		// Check language-dependent classes.
		for (Class<?> c : FileMap.classToFile.keySet()) {
			// Localize the file name according to each retrieved languages.
			for (String lang : retrievedLanguages.keySet()) {
				String localizedPath = localizedPath(lang,
						FileMap.classToFile.get(c));
				if (!retrievedEntries.contains(localizedPath)) {
					return false;
				}
			}
		}

		return check;
	}

	/**
	 * Inserts the object or list of objects into its respective JSON class
	 * file. If a list of objects is passed as argument, the method overwrites
	 * the file content. The file is also overwritten if the object has a single
	 * instance on a file (e.g., Languages). Regardless of the object type, the
	 * JSON output will always be a list.
	 */
	public <T> void insertObject(T obj, Class<?> c) throws IOException {
		String path = "";

		if (FileMap.classToFile.containsKey(c))
			path = FileMap.classToFile.get(c);

		if (FileMap.globalClassToFile.containsKey(c)) {
			path = FileMap.globalClassToFile.get(c);
		}
		
		File file = new File(searchFile(path, null, c));

		if (obj.getClass().isArray() || obj.getClass() == ArrayList.class) {
			MyJson.getJson().toJson(obj, file);
		} else if (c == Languages.class || c == Settings.class
				|| c == MenuText.class) {
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
					String localizedPath = localizedPath(language,
							FileMap.classToFile.get(c));
					Files.createFile(Paths.get(localizedPath));
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
		String path = "";

		if (FileMap.classToFile.containsKey(c))
			path = FileMap.classToFile.get(c);
		else if (FileMap.globalClassToFile.containsKey(c))
			path = FileMap.globalClassToFile.get(c);

		File file = new File(searchFile(path, null, c));
		obj = (T) MyJson.getJson().fromJson(c, file);

		return obj;
	}

	/**
	 * Retrieves a file belonging to a specified resource category (or class).
	 */
	public String searchFile(String name, String resourceType, Class<?> c)
			throws IOException {
		String file = null;
		
		if (FileMap.globalClassToFile.containsKey(c))
			file = FileMap.globalClassToFile.get(c);
		else if (FileMap.classToFile.containsKey(c))
			file = localizedPath(currentLanguage, FileMap.classToFile.get(c));
		else if (FileMap.resourcesToDirectory.containsKey(resourceType))
			file = FileMap.resourcesToDirectory.get(resourceType) + name;
		System.out.println("searchFile: "+file);
		if (file != null)
			if (!Files.exists(Paths.get(file), LinkOption.NOFOLLOW_LINKS)) {				
				file = null;
			}

		return file;
	}

	/**
	 * Returns an ArrayList containing all of the objects in a JSON class file.
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> searchAllObjects(Class<?> c) throws IOException {
		ArrayList<T> list = new ArrayList<T>();
		String path = "";

		if (FileMap.globalClassToFile.containsKey(c))
			path = FileMap.globalClassToFile.get(c);
		if (FileMap.classToFile.containsKey(c))
			path = FileMap.classToFile.get(c);
		
		if (searchFile(path, null, c) != null) {
			File file = new File(searchFile(path, null, c));
			System.out.println("?????????");
			list = MyJson.getJson().fromJson(ArrayList.class, file);
			System.out.println("?????????2222222");
			System.out.println("SearchAllObjects: searchFile searching: "+file);
			return list;
		} else
			System.out.println("SearchAllObjects: searchFile retornando null");
			return null;
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

	public static void deleteFile(String path) throws IOException {
		Files.delete(Paths.get(path));
	}

	public static void deleteDirectory(String dir) throws IOException {
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
					Files.copy(source, target,
							StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}

	private static String localizedPath(String currentLanguage, String path) {
		return path.replace("data/", "data/" + currentLanguage + "/");
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public Languages getLanguages() {
		return languages;
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(String lang) {
		if(languages==null){
			languages= new Languages();
			languages.put("en_us", "en_us");
		}		
		if (this.languages.containsKey(lang))
			this.currentLanguage = lang;		
	}

	public Controller getController() {
		return this.controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}
