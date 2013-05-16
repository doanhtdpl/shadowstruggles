package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
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

	public Card(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInMainShop, boolean consumable, int energyCost,
			CardAction action, BattlePlatform platform, String illustration,
			ArrayList<String> preRequisites) {
		super(id, name, description, buyCost, sellCost, sellable, icon, availableInMainShop, consumable);
		this.energyCost = energyCost;
		this.action = action;
		this.platform = platform;
		this.illustration = illustration;
		this.preRequisites = preRequisites;
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
		super.read(arg0, arg1);
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
		super.write(arg0);
	}

}
