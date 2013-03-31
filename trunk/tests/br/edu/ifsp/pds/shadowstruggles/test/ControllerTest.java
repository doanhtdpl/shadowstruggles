package br.edu.ifsp.pds.shadowstruggles.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.model.Card;

public class ControllerTest {

	@Test
	public void testMapClicked() {
		fail("Not yet implemented");
	}

	@Test
	public void testBackCardClicked() {
		fail("Not yet implemented");
	}

	@Test
	public void testPentagramClicked() {
		fail("Not yet implemented");
	}

	@Test
	public void testHandCardClicked() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeckClicked() {
		fail("Not yet implemented");
	}

	@Test
	public void testMenuButtonClicked() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayerLifeChanged() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnemyLifeChanged() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayerEnergyChanged() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnemyEnergyChanged() {
		fail("Not yet implemented");
	}

	@Test
	public void testTileChanged() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPlayCard() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descrição", 0, null);
		int laneTest = 10;
		int tileTest = 20;
		
		Controller control = new Controller();
		
		control.playCard(cartaTest, laneTest, tileTest);
		
		
	}
	
	@Test
	public void testAddCardToMap(){
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descricao", 1, null);
		int laneTest = 10;
		int tileTest = 20;
		Controller control = new Controller();
		Image cardImage = new Image();
		
		control.addCardToMap(cartaTest, cardImage, tileTest, laneTest);
	}

	@Test
	public void testUpdateTimer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyValueChange() {
		Controller controlTest = new Controller();
		int value1 = 200;
		int value2 = 400;
		int value3 = 0;
		int valueMax = 300;
		
		int result = controlTest.verifyValueChange(value1, valueMax);
		
		assertEquals(value1, result);
		
	}

}
