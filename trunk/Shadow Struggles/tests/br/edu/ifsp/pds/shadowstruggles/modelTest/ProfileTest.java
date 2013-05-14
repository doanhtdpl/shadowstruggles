package br.edu.ifsp.pds.shadowstruggles.modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

public class ProfileTest {

	@Test
	public void testMoveMoney() {
		Profile tester = new Profile();
		tester.moveMoney(10000);
		assertEquals("Result:", 20000, tester.getMoney());
	}

	@Test
	public void testCompareTo() {
		Profile tester = new Profile();
		Profile tester2 = new Profile();
		int isEqual = tester.compareTo(tester2);
		assertEquals("Result:", 0, isEqual);
	}

}
