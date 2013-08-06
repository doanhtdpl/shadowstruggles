package br.edu.ifsp.pds.shadowstruggles.modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.scripts.DefaultAction;

public class MapTest {

	@Test
	public void testCardOnMap() {
		BattleMap map = new BattleMap("teste");
		Card card = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card.setDirection(1);
		map.addCard(card, 4, 1);
		assertEquals("Result:", true, map.cardOnMap(card, -1, 1));
	}

	@Test
	public void testEnemyBaseAttacked() {
		BattleMap map = new BattleMap("teste");
		Fighter fighter = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter.setDirection(1);
		map.addCard(fighter, 35, 1);
		assertEquals("Result:", 1, map.enemyBaseAttacked());
	}

	@Test
	public void testPlayerBaseAttacked() {
		BattleMap map = new BattleMap("teste");
		Fighter fighter = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter.setDirection(-1);
		map.addCard(fighter, 1, 1);
		assertEquals("Result:", 1, map.playerBaseAttacked());
	}

	@Test
	public void testNextAvailableLane() {
		BattleMap map = new BattleMap("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 1, 0);
		Card card2 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card2.setDirection(1);
		map.addCard(card2, 2, 0);
		Card card3 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card3.setDirection(1);
		map.addCard(card3, 3, 0);
		Card card4 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card4.setDirection(1);
		map.addCard(card4, 4, 0);
		Card card5 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card5.setDirection(1);
		map.addCard(card5, 5, 0);
		assertEquals("Result:", 1, map.nextAvailableLane(1));
	}

	@Test
	public void testNextAvailableTile() {
		BattleMap map = new BattleMap("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 0, 0);
		Card card2 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card2.setDirection(1);
		map.addCard(card2, 1, 0);
		Card card3 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card3.setDirection(1);
		map.addCard(card3, 2, 0);
		Card card4 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card4.setDirection(1);
		map.addCard(card4, 3, 0);
		assertEquals("Result:", 4, map.nextAvailableTile(0, 1));
	}

	@Test
	public void testLaneWithMoreEnemies() {
		BattleMap map = new BattleMap("teste");
		Fighter fighter1 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter1.setDirection(-1);
		map.addCard(fighter1, 1, 2);
		Fighter fighter2 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter2.setDirection(-1);
		map.addCard(fighter2, 1, 2);
		Fighter fighter3 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter3.setDirection(-1);
		map.addCard(fighter3, 1, 2);
		Fighter fighter4 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter4.setDirection(-1);
		map.addCard(fighter4, 1, 2);
		assertEquals("Result:", 2, map.laneWithMoreEnemies(1));
	}

	@Test
	public void testLaneWithLessAllies() {
		BattleMap map = new BattleMap("teste");
		Fighter fighter1 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter1.setDirection(1);
		map.addCard(fighter1, 0, 0);
		Fighter fighter2 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter2.setDirection(1);
		map.addCard(fighter2, 1, 1);
		Fighter fighter3 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter3.setDirection(1);
		map.addCard(fighter3, 3, 3);
		assertEquals("Result:", 2, map.laneWithLessAllies(1));
	}

	@Test
	public void testLaneWithMoreEnemiesInvading() {
		BattleMap map = new BattleMap("teste");
		Fighter fighter1 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter1.setDirection(1);
		map.addCard(fighter1, 1, 2);
		Fighter fighter2 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter2.setDirection(1);
		map.addCard(fighter2, 1, 2);
		Fighter fighter3 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter3.setDirection(1);
		map.addCard(fighter3, 1, 2);
		Fighter fighter4 = new Fighter("DR002", "DR002", 15, "", 14,
				new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter4.setDirection(1);
		map.addCard(fighter4, 1, 2);
		assertEquals("Result:", 2, map.laneWithMoreEnemiesInvading(1));
	}

	@Test
	public void testTileClosestToBase() {
		BattleMap map = new BattleMap("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 0, 0);
		Card card2 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card2.setDirection(1);
		map.addCard(card2, 1, 0);
		Card card3 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card3.setDirection(1);
		map.addCard(card3, 2, 0);
		assertEquals("Result:", 3, map.tileClosestToBase(0, 1));
	}

	@Test
	public void testTileFurthestFromBase() {
		BattleMap map = new BattleMap("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 3, 0);
		Card card2 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card2.setDirection(1);
		map.addCard(card2, 4, 0);
		assertEquals("Result:", 2, map.tileFurthestFromBase(0, 1));
	}

	@Test
	public void testAddCard() {
		BattleMap map = new BattleMap("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100,
				new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 0, 0);
		Array<Card> cards = map.getPlayerCards();
		assertEquals("Result:", "DR-002", cards.get(0).getName());
	}

}
