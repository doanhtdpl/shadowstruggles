package br.edu.ifsp.pds.shadowstruggles.tools.model.items;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;

public class Pack extends Item {
	public ArrayList<Card> cards;
	
	public Pack() {
		super();
		
		this.cards = new ArrayList<Card>();
	}
	
	public Pack(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, ArrayList<Card> cards) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		
		this.cards = cards;
	}
}
