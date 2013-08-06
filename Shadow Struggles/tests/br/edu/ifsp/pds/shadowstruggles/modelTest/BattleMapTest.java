package br.edu.ifsp.pds.shadowstruggles.modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;

public class BattleMapTest {
	
	@Test
	public void testCardOnMap() {
		//método pode estar com erro
		Card card = new Card("Carta", "carta", 10, "carta", 10, null);
		Card card2 = new Card("Carta2", "carta2", 10, "carta2", 10, null);
		BattleMap map = new BattleMap("mapa");
		map.addCard(card, 20, 2);
		assertTrue(map.cardOnMap(card, 2, 1));
		assertFalse(map.cardOnMap(card2, 3, 1));
	}
	
	@Test
	public void testEnemyBaseAttacked() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnemyCards() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetPlayerCards() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testLaneWithLessAllies() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testLaneWithMoreEnemies() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testLaneWithMoreEnemiesInvading() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNextAvailable() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNextAvailableTile() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPlayerBaseAttacked() {
		fail("Not yet implemented");
	}
	
}
