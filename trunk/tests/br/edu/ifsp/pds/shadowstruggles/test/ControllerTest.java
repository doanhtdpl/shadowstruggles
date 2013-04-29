package br.edu.ifsp.pds.shadowstruggles.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.model.Card;

public class ControllerTest {

	@Test
	public void testBackCardClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testHexagramClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testHandCardClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testDeckClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testTileChanged() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testPlayCard() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descrição", 0,
				null);
		int laneTest = 10;
		int tileTest = 20;

		Controller control = Controller.getInstance();

		control.playCard(cartaTest, laneTest, tileTest);

	}

	@Test
	public void testAddCardToMap() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descricao", 1,
				null);
		int laneTest = 10;
		int tileTest = 20;
		Controller control = Controller.getInstance();
		Image cardImage = new Image();

		control.addCardToMap(cartaTest, cardImage, tileTest, laneTest);
	}

	@Test
	public void testRemoveCard() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testVerifyValueChange() {
		Controller controlTest = Controller.getInstance();
		int value1 = 200;
		int valueMax = 300;

		int result = controlTest.verifyValueChange(value1, valueMax);

		assertEquals(value1, result);

	}

}
