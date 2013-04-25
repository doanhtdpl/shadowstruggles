package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;

public class DataManagerTest {

	@Test
	public void dataManagerTest() {
		try {
			DataManager dataManager = new DataManager();
			System.out.println("dataManagerTest: Success");
		} catch (Exception e) {
			System.out.println("dataManagerTest: Fail");
			System.out.println(e);
		}
	}

}
