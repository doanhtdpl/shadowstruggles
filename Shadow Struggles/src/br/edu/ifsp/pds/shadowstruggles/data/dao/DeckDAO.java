package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.Trap;

import com.badlogic.gdx.utils.Array;

/**
 * This class manages the interpretation of a deck from the DataManager
 */
public class DeckDAO {

	public static Deck getDeck(String key) {
		Deck deck = new Deck();

		@SuppressWarnings("unchecked")
		Array<Deck> decks = DataManager.getInstance().getObjectSet(Deck.class);

		for (Deck d : decks) {
			if (d.getName().equals(key)) {
				/*
				 * This makes a deep copy of the deck so that similar decks
				 * don't share the same memory blocks.
				 */
				deck.getCards().addAll(d.getCards());
				deck.setDeckImage(d.getDeckImage());
				deck.setMaxCapacity(d.getMaxCapacity());
				deck.setMinCapacity(d.getMinCapacity());
				deck.setName(d.getName());
			}
		}

		Array<Card> cardsCopy = new Array<Card>();

		for (Card card : deck.getCards()) {

			if (card.getClass().equals(Fighter.class)) {
				card = FighterDAO.getFighter(card.getName());
				card.setAction(FighterDAO.getFighter(card.getName())
						.getAction().copy());
			} else if (card.getClass().equals(Effect.class)) {
				card = EffectDAO.getEffect(card.getName());
				card.setAction(EffectDAO.getEffect(card.getName())
						.getAction().copy());
			} else if (card.getClass().equals(Trap.class)) {
				card = TrapDAO.getTrap(card.getName());
				card.setAction(TrapDAO.getTrap(card.getName())
						.getAction().copy());
			}
			cardsCopy.add(card);
		}

		deck.setCards(cardsCopy);

		return deck;
	}

	@SuppressWarnings("unchecked")
	public static Array<Deck> getDecks() {
		return DataManager.getInstance().getObjectSet(Deck.class);
	}
}
