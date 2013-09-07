package br.edu.ifsp.pds.shadowstruggles.tools.data;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

public class DataManagerTest {

	@Test
	public void testNewZip() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			assertTrue(Files.exists(Paths.get("data"),
					LinkOption.NOFOLLOW_LINKS));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Zip file not created or created with errors");
		}
	}

	@Test
	public void testCheckZipTrue() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			manager.saveToZip();
			assertTrue(DataManager.checkZip("test.zip"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testCheckZipFalse() {
		FileMap.initMap();

		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					"test.zip"));
			zos.putNextEntry(new ZipEntry("test.txt"));
			zos.close();
			assertFalse(DataManager.checkZip("test.zip"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testInsertSearchObject() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			BattleMap expected = new BattleMap(20, "Foo");
			manager.insertObject(expected, BattleMap.class);

			BattleMap actual = (BattleMap) manager.searchObject(expected.id,
					BattleMap.class);
			assertNotNull(actual);
			assertEquals(expected.id, actual.id);
		} catch (ClassCastException e) {
			e.printStackTrace();
			fail("Search has not returned the requested object type");
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testInsertSearchFile() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");

			FileOutputStream fos = new FileOutputStream("test.txt");
			fos.write("test".getBytes());
			fos.close();

			File file = new File("test.txt");
			manager.insertFile(file.getName(), "skin");

			File actual = new File(manager.searchFile(file.getName(), "skin",
					null));
			assertNotNull(actual);
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testSearchAll() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			BattlePlatform bp = new BattlePlatform();
			bp.id = new Random().nextInt();

			ArrayList<BattlePlatform> list = new ArrayList<BattlePlatform>();
			list.add(bp);

			manager.insertObject(list, BattlePlatform.class);

			list = manager.searchAllObjects(BattlePlatform.class);
			assertEquals(1, list.size());
			for (BattlePlatform b : list) {
				assertTrue(b.id == bp.id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testSearchAllFiles() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");

			FileOutputStream fos = new FileOutputStream("test.txt");
			fos.close();
			fos = new FileOutputStream("test.json");
			fos.close();
			File f1 = new File("test.txt");
			File f2 = new File("test.json");
			ArrayList<String> files = new ArrayList<String>(
					Arrays.asList(new String[] { f1.getName(), f2.getName() }));

			for (String s : files)
				manager.insertFile(s, "skin");

			files = manager.searchAllFiles("skin");
			assertEquals(2, files.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testUpdate() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			BattleMap original = new BattleMap();
			manager.insertObject(original, BattleMap.class);

			BattleMap modified = new BattleMap();
			String expected = "Foo";
			modified.name = expected;
			manager.update(original.id, BattleMap.class, modified);

			BattleMap retrieved = (BattleMap) manager.searchObject(original.id,
					BattleMap.class);
			assertNotNull(retrieved);
			assertEquals(expected, retrieved.name);
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testDeleteObject() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			BattlePlatform bp = new BattlePlatform();
			bp.id = new Random().nextInt();
			manager.insertObject(bp, BattlePlatform.class);
			ArrayList<BattlePlatform> previousList = manager
					.searchAllObjects(BattlePlatform.class);

			manager.deleteObject(bp.id, BattlePlatform.class);

			ArrayList<BattlePlatform> list = manager
					.searchAllObjects(BattlePlatform.class);
			assertTrue(list.size() == 0 && previousList.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testSaveToZip() {
		FileMap.initMap();
		DataManager manager = new DataManager();

		try {
			manager.newZip("test.zip");
			BattlePlatform bp = new BattlePlatform();
			manager.insertObject(bp, BattlePlatform.class);
			manager.saveToZip();
			manager.deleteDirectory("data");

			assertFalse(new File("data").exists());
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testCopyData() {
		FileMap.initMap();
		DataManager manager = new DataManager();

		try {
			manager.newZip("test.zip");

			BattlePlatform bp = new BattlePlatform();
			bp.id = new Random().nextInt();
			manager.insertObject(bp, BattlePlatform.class);

			manager.insertLanguage("pt_br", "Portugu�s");
			manager.copyData("en_us", "pt_br");
			manager.setCurrentLanguage("pt_br");
			int retrievedId = ((BattlePlatform) manager.searchObject(bp.id,
					BattlePlatform.class)).id;

			assertEquals(bp.id, retrievedId);
		} catch (Exception e) {
			e.printStackTrace();
			fail("");
		}
	}

}
