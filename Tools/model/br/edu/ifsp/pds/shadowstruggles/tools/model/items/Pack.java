package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;

public class Pack extends Item {
	public ArrayList<Card> cards;
	
	public Pack() {
		super();
		
		this.cards = new ArrayList<Card>();
	}
}
