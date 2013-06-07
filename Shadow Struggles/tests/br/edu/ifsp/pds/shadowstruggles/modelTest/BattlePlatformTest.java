package br.edu.ifsp.pds.shadowstruggles.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;

public class BattlePlatformTest {
	
	@Test
	public void testCardOnEnemyHand() {
		BattlePlatform platform = new BattlePlatform(new Deck(), new Deck(), new BattleMap(""), new DefaultRules());
		Card card = new Card();
		platform.addEnemyHandCard(card);
		assertTrue(platform.cardOnEnemyHand(card.getName()));
	}

	@Test
	public void testGetCardFromEnemy() {
		BattlePlatform platform = new BattlePlatform(new Deck(), new Deck(), new BattleMap(""), new DefaultRules());
		Card expected = new Card();
		platform.addEnemyHandCard(expected);
		Card actual = platform.getCardFromEnemy("");
		assertEquals(expected, actual);
	}
	
}
