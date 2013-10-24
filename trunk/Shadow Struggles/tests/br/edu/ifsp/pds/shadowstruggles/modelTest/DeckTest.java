package br.edu.ifsp.pds.shadowstruggles.modelTest;

import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.data.dao.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Deck;

public class DeckTest {
	public static void testDeckRead(){
		Array<Deck> decks= DeckDAO.getDecks();
		if(decks.size>0){			
			for(Deck deck:decks){
				System.out.println("Nome do Deck: "+deck.getName());
				System.out.println("Cartas: ");
				for(Card card:deck.getCards()){
					System.out.println(card.getName());
				}
			}
		}else{
			System.out.println("Não existe nenhum deck gravado!");
		}
	}
	
	
	public static void testDraw(){
		Deck deck = DeckDAO.getDecks().first();
		for(int i =0;i<10;i++){
		System.out.println(i+" Quantity before draw: "+deck.getCards().size);
		Card card = deck.draw();
		System.out.println(i+" Drawed Card: "+card.getName());
		System.out.println(i+" Quantity after draw: "+deck.getCards().size);
		}
	}
}
