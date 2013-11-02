package br.edu.ifsp.pds.shadowstruggles.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;

public class ControllerTest {

	@Test
	public void testBackCardClicked() {
		fail("Not yet implemented"); // TODO: Implementar teste.

	}

	@Test
	public void testHexagramClicked() {
		fail("Not yet implemented"); // TODO: Implementar teste.
	}

	@Test
	public void testHandCardClicked() {
		// fail("Not yet implemented"); //TODO: Implementar teste.
		Controller control = new Controller();
		Card card = new Card("Teste", "CartaTeste", 10, "Descrição", 0, null);
		BattlePlatform platform = new BattlePlatform(new Deck(), new Deck(),
				new BattleMap(""), new DefaultRules());
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		control.handCardClicked(card, true);
		assertEquals(card, platform.getSelectedCard());
	}

	@Test
	public void testDeckClicked() {
		fail("Not yet implemented"); // TODO: Implementar teste.
	}

	@Test
	public void testTileChanged() {
		fail("Not yet implemented"); // TODO: Implementar teste.

	}

	@Test
	public void testPlayCard() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descrição", 0,
				null);
		int laneTest = 2;
		int tileTest = 2;

		Controller control = new Controller();

		control.playCard(cartaTest, laneTest, tileTest);

	}

	@Test
	public void testAddCardToMap() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descrição", 1,
				null);
		int laneTest = 3;
		int tileTest = 3;
		Controller control = new Controller();
		Image cardImage = new Image();

		control.addCardToMap(cartaTest, cardImage, tileTest, laneTest);
		boolean cardOnMap = control.getPlatform().getMap()
				.cardOnMap(cartaTest, laneTest, 1);

		assertEquals(cardOnMap, true);

	}

	@Test
	public void testRemoveCard() {
		Controller control = new Controller();
		Card card = new Card("Teste", "CartaTeste", 10, "Descrição", 1, null);
		int laneTest = 2;
		int tileTest = 2;
		Image cardImage = new Image();

		control.addCardToMap(card, cardImage, tileTest, laneTest);
		control.removeCard(card);

		boolean cardOnMap = control.getPlatform().getMap()
				.cardOnMap(card, laneTest, 1);

		assertEquals(cardOnMap, false);

	}

	@Test
	public void testVerifyValueChange() {
		Controller controlTest = new Controller();
		int value1 = 200;
		int valueMax = 300;

		int result = controlTest.verifyValueChange(value1, valueMax);

		assertEquals(value1, result);

	}

}
