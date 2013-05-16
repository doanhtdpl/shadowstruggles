package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Player;

public class Enemy extends Player {
	public int id;
	public String name;
	public ArrayList<Sequence> strategy;

	public Enemy() {
		super();
		
		this.id = 1;
		this.name = "";
		this.strategy = new ArrayList<Sequence>();
	}

	public Enemy(Deck deck, int maxHealth, int maxEnergy, int initialEnergy,
			int maxCardPoints, int deckCapacity, int energyRecovery,
			float doubleDraw, int id, String name, ArrayList<Sequence> strategy) {
		super(deck, maxHealth, maxEnergy, initialEnergy, maxCardPoints,
				deckCapacity, energyRecovery, doubleDraw);

		this.id = id;
		this.name = name;
		this.strategy = strategy;
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
