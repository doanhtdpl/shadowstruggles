package br.edu.ifsp.pds.shadowstruggles.modelTest;

import org.junit.Test;
import static org.junit.Assert.*;

import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.Map;

public class CardTest {

	@Test
	public void testReadyToSummom() {
		BattlePlatform platform = new BattlePlatform(new Deck(), new Deck(),
				new Map(""), new DefaultRules());
		Card preRequisite = new Card();
		preRequisite.setName("pre-requisite");
		preRequisite.setDirection(1);
		Card card = new Card();
		card.setPreRequisites(new Array<String>(new String[] { preRequisite
				.getName() }));
		platform.getMap().addCard(preRequisite, 2, 2);
		assertTrue(card.readyToSummom(platform));
	}

}
