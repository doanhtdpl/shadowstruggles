package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class DataManager {

	private String currentFile;
	private Languages languages;
	private String currentLanguage;
	private ArrayList<String> modifiedFiles;

	/**
	 * Creates a new zip file in the specified path and establishes the initial
	 * directory structure.
	 */
	public void newZip(String path) throws IOException, ZipException {
		this.currentFile = path;
		this.currentLanguage = "en_us";
		this.languages = new Languages();
		languages.put("en_us", "English");
		this.modifiedFiles = new ArrayList<String>();

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path));

		for (Class<?> c : FileMap.classToFile.keySet()) {
			ZipEntry entry;

			if (c == Languages.class)
				entry = new ZipEntry(FileMap.classToFile.get(c));
			else
				entry = new ZipEntry(this.localizedPath(FileMap.classToFile
						.get(c)));

			zos.putNextEntry(entry);
		}
		for (String s : FileMap.resourcesToDirectory.keySet()) {
			ZipEntry entry = new ZipEntry(FileMap.resourcesToDirectory.get(s));
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
		zip.extractAll("data");

		return true;
	}

	/**
	 * Checks the zip file to see if its directory structure corresponds to the
	 * application's expected structure. For this, it must have a Languages file
	 * and all the required files.
	 */
	public static boolean checkZip(String zip) throws IOException {
		boolean check = true;
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
		ZipEntry entry;
		ArrayList<String> retrievedEntries = new ArrayList<String>();
		Languages retrievedLanguages = null;

		// First, get the Languages file and storage all entries as strings.

		boolean hasLanguages = false;
		while ((entry = zis.getNextEntry()) != null) {
			retrievedEntries.add(entry.getName());

			if (entry.getName() == FileMap.classToFile.get(Languages.class)) {
				hasLanguages = true;
				retrievedLanguages = MyJson.getJson().fromJson(Languages.class,
						new File(entry.getName()));
			}
		}

		if (!hasLanguages)
			return false;

		// Has any required folder been deleted?
		for (String value : FileMap.resourcesToDirectory.values()) {
			if (!retrievedEntries.contains(value))
				return false;
		}

		// Has any required file been deleted?
		for (Class<?> c : FileMap.classToFile.keySet()) {
			// Check languages-independent classes first.
			if (c == Languages.class) {
				if (!retrievedEntries.contains(FileMap.classToFile.get(c)))
					return false;
			} else {
				// This class is languages-dependent, therefore, it must be
				// localized first according to each retrieved languages.
				for (String lang : retrievedLanguages.languages.keySet()) {
					String localizedPath = lang + "/"
							+ FileMap.classToFile.get(c);
					if (!retrievedEntries.contains(localizedPath))
						return false;
				}
			}
		}

		zis.close();

		return check;
	}

	/**
	 * Inserts the object, overwriting the previous JSON class file.
	 */
	public <T> void insertObject(T obj, Class<?> c) throws IOException {
		if (FileMap.classToFile.containsKey(c)) {
			String path = "data/";

			if (c != Languages.class)
				path += localizedPath(FileMap.classToFile.get(c));
			else
				path += FileMap.classToFile.get(c);

			File file = new File(searchFile(path, null, c));
			MyJson.getJson().toJson(obj, file);
			this.modifiedFiles.add(path);

			// If the languages file has been modified, another directory may
			// have to be created.
			if (c == Languages.class) {
				Languages languages = (Languages) obj;
				File directory = new File("data");
				ArrayList<String> folders = new ArrayList<String>(
						Arrays.asList(directory.list()));

				for (String lang : languages.keySet()) {
					if (!folders.contains(lang)) {
						ZipOutputStream zos = new ZipOutputStream(
								new FileOutputStream(currentFile));

						for (Class<?> cl : FileMap.classToFile.keySet()) {
							ZipEntry entry = null;
							if (cl != Languages.class)
								entry = new ZipEntry(lang + "/"
										+ FileMap.classToFile.get(cl));
							zos.putNextEntry(entry);
						}
						
						modifiedFiles.add(lang);
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
			Path destinyPath = Paths.get("data/"
					+ FileMap.resourcesToDirectory.get(resourceType) + file);
			Files.copy(originPath, destinyPath,
					StandardCopyOption.REPLACE_EXISTING);
			this.modifiedFiles.add(destinyPath.toString());
		}
	}

	/**
	 * Retrieves an object from the respective class file of the object,
	 * according to a specified key (id). If the objects are stored as an array,
	 * this method requires an "id" field of type integer as the comparator for
	 * localizing the searched object.
	 */
	@SuppressWarnings("unchecked")
	public <T> T searchObject(int key, Class<?> c) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		T obj = null;

		if (FileMap.classToFile.containsKey(c)) {
			String path = "data/";

			if (c != Languages.class)
				path += localizedPath(FileMap.classToFile.get(c));
			else
				path += FileMap.classToFile.get(c);

			File file = new File(searchFile(path, null, c));
			Object retrieved = MyJson.getJson().fromJson(c, file);

			if (retrieved.getClass().isArray()) {
				ArrayList<T> list = (ArrayList<T>) retrieved;

				for (T tmp : list) {
					Field id = c.getField("id");
					if (id.getInt(tmp) == key)
						obj = tmp;
				}

			} else {
				obj = (T) retrieved;
			}

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
			if (c == Languages.class)
				file = "data/" + FileMap.classToFile.get(c);
			else
				file = "data/" + this.localizedPath(FileMap.classToFile.get(c));
		} else if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
			file = "data/" + FileMap.resourcesToDirectory.get(resourceType)
					+ name;
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
			String path = "data/";
			path += FileMap.classToFile.get(c);

			File file = new File(searchFile(path, null, c));
			list = MyJson.getJson().fromJson(ArrayList.class, file);

			// Object retrieved = MyJson.getJson().fromJson(ArrayList.class,
			// file);
			// if (retrieved != null) {
			// if (retrieved.getClass() == ArrayList.class) {
			// list = (ArrayList<T>) retrieved;
			// } else {
			// list.add((T) retrieved);
			// }
			// }

		}

		return list;
	}

	public ArrayList<String> searchAllFiles(String resourceType) {
		ArrayList<String> files = new ArrayList<String>();

		if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
			String path = "data/"
					+ FileMap.resourcesToDirectory.get(resourceType);
			File directory = new File(path);
			files = new ArrayList<String>(Arrays.asList(directory.list()));
		}

		return files;
	}

	// TODO: Corrigir erro causado ao utilizar o método searchAllObjects.
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

	// TODO: Corrigir erro causado ao utilizar o método searchAllObjects.
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

	public void deleteDirectory() throws IOException {
		Files.delete(Paths.get("data"));
	}

	// TODO: Corrigir método.
	public void saveToZip() throws ZipException {
		ZipFile zip = new ZipFile(currentFile);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		
		for(String path : modifiedFiles) {
			File f = new File(path);
			
			if(f.isDirectory())
				zip.addFolder(path, parameters);
			else {
				zip.addFile(f, parameters);
			}
		}
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

	private String localizedPath(String path) {
		return currentLanguage + "/" + path;
	}
}
