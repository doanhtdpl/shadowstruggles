package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json.Serializable;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;

public class Card extends Item implements Serializable {
	public int energyCost;
	public CardAction action;
	public BattlePlatform platform;
	public String illustration;
	public ArrayList<String> preRequisites = new ArrayList<String>();

	public Card() {
		super();
		this.energyCost = 0;
		this.action = new DefaultAction();
		this.platform = new BattlePlatform();
		this.illustration = "";
		this.preRequisites = new ArrayList<String>();
	}
	
	public Card(String name) {
		this.name = name;
	}
}
