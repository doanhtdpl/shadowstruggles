package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DataManager {

	private String currentFile;
	private Languages languages;
	private String currentLanguage;

	/**
	 * Creates a new zip file in the specified path and establishes the initial
	 * directory structure.
	 */
	public void newZip(String path) throws IOException {
		this.currentFile = path;
		this.currentLanguage = "en_us";
		this.languages = new Languages();
		languages.getLanguages().put("en_us", "English");

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

		this.insertObject(this.languages, Languages.class);
	}

	public boolean openZip(String path) throws IOException {
		if (checkZip(path)) {
			this.currentFile = path;
			return true;
		}

		return false;
	}

	/**
	 * Checks the zip file to see if its directory structure corresponds to the
	 * application's expected structure.
	 */
	public static boolean checkZip(String zip) throws IOException {
		boolean check = true;
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
		ZipEntry entry;
		ArrayList<String> retrievedEntries = new ArrayList<String>();
		Languages retrievedLanguages = null;

		// First, check language-independent files and resources. Also, get the
		// Languages file and storage all entries as strings.

		boolean hasLanguages = false;
		while ((entry = zis.getNextEntry()) != null) {
			retrievedEntries.add(entry.getName());

			if (entry.isDirectory()) {
				if (!FileMap.resourcesToDirectory
						.containsValue(entry.getName()))
					return false;
			} else {
				if (entry.getName() == FileMap.classToFile.get(Languages.class)) {
					hasLanguages = true;
					retrievedLanguages = JsonInitializer.getJson().fromJson(
							Languages.class, new File(entry.getName()));
				}
			}
		}

		if (!hasLanguages)
			return false;

		// Then, look into each language's folder structure and check them.
		for (String lang : retrievedLanguages.getLanguages().keySet()) {
			for (String s : retrievedEntries) {
				if (!FileMap.classToFile.containsValue(s.replace(lang, "")))
					return false;
			}
		}

		// Final check: has any required file been deleted?
		for (String value : FileMap.resourcesToDirectory.values()) {
			if (!retrievedEntries.contains(value))
				return false;
		}

		for (Class<?> c : FileMap.classToFile.keySet()) {
			if (c == Languages.class)
				if (!retrievedEntries.contains(FileMap.classToFile.get(c)))
					return false;
				else {
					// This class is language-dependent, therefore, it must be
					// localized first according to each retrieved language.
					for (String lang : retrievedLanguages.getLanguages()
							.keySet()) {
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

	// TODO: Implementar método.
	public <T> void insertObject(T obj, Class<?> c) throws IOException {
		if (FileMap.classToFile.containsKey(c)) {
		}
	}

	public <T> void insertFile(File file, String resourceType)
			throws IOException {
		if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					this.currentFile, false));

			FileInputStream fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(FileMap.resourcesToDirectory
					.get(resourceType) + file.getName()));

			int len;
			byte[] buf = new byte[1024];

			while ((len = fis.read(buf)) > 0)
				zos.write(buf, 0, len);

			fis.close();
			zos.closeEntry();
		}
	}

	// TODO: Implementar método.
	public Object search(Object key, Class<?> c) {
		Object obj = null;

		return obj;
	}

	// TODO: Implementar método.
	public <T> ArrayList<T> searchAll() {
		ArrayList<T> list = new ArrayList<T>();

		return list;
	}

	// TODO: Implementar método.
	public void update(Object key, Class<?> c, Object editedObject) {

	}

	// TODO: Implementar método.
	public void delete(Object key, Class<?> c) {

	}

	private String localizedPath(String path) {
		return currentLanguage + "/" + path;
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
}
