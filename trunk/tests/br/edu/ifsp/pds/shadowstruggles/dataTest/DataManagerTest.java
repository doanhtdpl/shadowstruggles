package br.edu.ifsp.pds.shadowstruggles.dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;

public class DataManagerTest {

	@Test
	public void dataManagerTest() {
		DataManager dataManager = new DataManager();
		assertNotNull("Right", dataManager);
	}
	
	@Test
	public void dataManagerLanguageTest(){
		DataManager dataManager = new DataManager("pt-br");
		fail("test not yet implemented");
		//assertEquals(expected, actual) -- não sei como fazer o teste
	}
	
	

}
