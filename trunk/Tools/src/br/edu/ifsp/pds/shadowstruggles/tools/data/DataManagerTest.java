package br.edu.ifsp.pds.shadowstruggles.tools.data;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

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
		fail("Not yet implemented");
	}

	@Test
	public void testSearchAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
