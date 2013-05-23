package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

		this.insert(this.languages, Languages.class);
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
		Languages retrievedLanguages = null;

		// First, check language-independent files and resources. Also, get the
		// Languages file.

		boolean hasLanguages = false;
		while ((entry = zis.getNextEntry()) != null) {
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
		
		// Close InputStream and open another.
		zis.close();
		zis = new ZipInputStream(new FileInputStream(zip));

		// Then, look into each language's folder structure and check them.
		// TODO: Concluir método.
		
		zis.close();

		return check;
	}

	// TODO: Implementar método.
	public void insert(Object obj, Class<?> c) {

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
