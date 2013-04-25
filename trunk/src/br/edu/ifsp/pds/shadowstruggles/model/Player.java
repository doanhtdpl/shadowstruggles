package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.utils.Array;

public class Player {
	private final static int FIELD_SIDE_LEFT=0;
	private final static int FIELD_SIDE_RIGHT=1;
	
	private Deck deck;
	private Array<Card> handCards;
	private Array<Card> cardsInField;
	private Array<Card> destroyedCards;
	private int fieldSide;
	private int baseHp;
	private int maxBaseHp;
	private int energy;
	private int maxEnergy;	
	private Field field;
	
	
	
	public Player() {
		
	}
	
	public void baseHpChange(int amount){
		baseHp = verifyValueChange((baseHp+amount), maxBaseHp);	
	}
	
	public void energyChange(int amount) {						
		energy = verifyValueChange((energy+amount), maxEnergy);		
	}
	
	public int verifyValueChange(int newValue, int maxValue){	
		/*
		 * If new value is in normal range, apply changes without correction.
		 * If new value is less than zero, set new value to zero. If new
		 * value is greater than maximum value, set new value to maximum
		 * value. */		
		if (newValue >= 0
				&& newValue <= maxValue) {
			return newValue;
		} else if (newValue < 0) {
			return 0;
		} else if (newValue > maxValue) {
			return maxValue;
		}
		return 0;
	}
	
	public boolean cardOnHand(Card card){
		boolean b = false;
		for (Card c : handCards) {
			if (c.getName().equals(card.getName())) {
				b = true;
			}
		}
		return b;
	}
	
	public void summonCard(Battle battle, Card card, int tile, int lane) {
		card.setPosition(lane, tile);		
		card.setMarkPosition(lane, tile);
		field.getTiles().get(card.getTile() / 2)
				.set(card.getLane(), card);
		battle.getMap().addCard(card, tile, lane);
		handCards.removeValue(card,true);
		if (card.getClass().equals(Effect.class)) {
			//((Effect)card).action(?);
		}		
	}
	
	
	public Deck getDeck() {
		return deck;
	}
	public Array<Card> getCardsInField() {
		return cardsInField;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public void setHandCards(Array<Card> handCards) {
		this.handCards = handCards;
	}
	
	public Array<Card> getHandCards() {
		return handCards;
	}
}
