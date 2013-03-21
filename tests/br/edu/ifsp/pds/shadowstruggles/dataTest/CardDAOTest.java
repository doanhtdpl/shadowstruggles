package br.edu.ifsp.pds.shadowstruggles.dataTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.CardDAO;

import br.edu.ifsp.pds.shadowstruggles.model.Card;

public class CardDAOTest extends TestCase{

	@Test
	public void cardDaoTest() {
		CardDAO cardDao = new CardDAO();
		assertNotNull("Right", cardDao);
	}
	
	@Test
	public void getCardTest() {
		CardDAO cardDao = new CardDAO();
		if(cardDao == null){
			fail("Constructor not yet implemented");
		}
		Card card = new Card();
		
		Random rand = new Random();
		int num = rand.nextInt();
		String numS = Integer.toString(num);
		
		cardDao.setCard(numS, card);
		
		assertEquals(card, cardDao.getCard(numS));
	}
	
	@Test
	public void getCardsTest(){
		CardDAO cardDao = new CardDAO();
		if(cardDao == null){
			fail("Constructor not yet implemented");
		}
		Card card = new Card();
		
		Random rand = new Random();
		int num = rand.nextInt();
		String numS = Integer.toString(num);
		
		cardDao.setCard(numS, card);
		
		ArrayList<Card> cardAL = new ArrayList<Card>();
		cardAL.add(card);
		
		assertEquals(cardAL, cardDao.getCards());
	}
}
