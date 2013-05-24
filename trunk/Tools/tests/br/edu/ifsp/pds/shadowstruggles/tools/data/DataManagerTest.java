package br.edu.ifsp.pds.shadowstruggles.tools.data;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

public class DataManagerTest {

	@Test
	public void testNewZip() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
		} catch (IOException e) {
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
			assertTrue(DataManager.checkZip("test.zip"));
		} catch (IOException e) {
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
		} catch (IOException e) {
			e.printStackTrace();
			fail("");
		}
	}

	@Test
	public void testInsertAndSearch() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			BattlePlatform expected = new BattlePlatform();
			manager.insertObject(expected, BattlePlatform.class);

			BattlePlatform actual = (BattlePlatform) manager.search(
					expected.id, BattlePlatform.class);
			assertNotNull(actual);
			assertTrue(expected.id == actual.id
					&& expected.map.equals(actual.map));
		} catch (IOException e) {
			e.printStackTrace();
			fail("");
		} catch (ClassCastException e) {
			e.printStackTrace();
			fail("search has not returned the requested object type");
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
			manager.insertObject(bp, BattlePlatform.class);

			ArrayList<BattlePlatform> list = manager.searchAll();
			assertEquals(1, list.size());
			for (BattlePlatform b : list) {
				assertTrue(b.id == bp.id && b.map.equals(b.map));
			}
		} catch (IOException e) {
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
			BattlePlatform original = new BattlePlatform();
			manager.insertObject(original, BattlePlatform.class);

			BattlePlatform modified = new BattlePlatform();
			String expected = "Foo";
			modified.map = expected;
			manager.update(1, BattlePlatform.class, modified);

			BattlePlatform retrieved = (BattlePlatform) manager.search(1,
					BattlePlatform.class);
			assertNotNull(retrieved);
			assertEquals(expected, retrieved.map);
		} catch (IOException e) {
			e.printStackTrace();
			fail("");
		} catch (ClassCastException e) {
			e.printStackTrace();
			fail("search has not returned the requested object type");
		}
	}

	@Test
	public void testDelete() {
		FileMap.initMap();
		DataManager manager = new DataManager();
		try {
			manager.newZip("test.zip");
			BattlePlatform bp = new BattlePlatform();
			bp.id = new Random().nextInt();
			manager.insertObject(bp, BattlePlatform.class);
			ArrayList<BattlePlatform> previousList = manager.searchAll();
			
			manager.delete(bp.id, BattlePlatform.class);

			ArrayList<BattlePlatform> list = manager.searchAll();
			assertTrue(list.size() == 0 && previousList.size() > 0);
		} catch (IOException e) {
			e.printStackTrace();
			fail("");
		}
	}

}
