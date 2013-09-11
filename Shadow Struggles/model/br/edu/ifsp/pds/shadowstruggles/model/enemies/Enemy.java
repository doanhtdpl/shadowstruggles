package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.Action.Attribute;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Player;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/***
 * The representation of an enemy.
 */
public class Enemy extends Player {
	// Serializable fields.
	private int id;
	private String name;
	private Array<Sequence> strategy;

	// Non-serializable fields.
	protected float delay;

	public Enemy() {
		this.id = 1;
		this.name = "";
		this.strategy = new Array<Sequence>();
		this.delay = 5;
	}

	/**
	 * Tries performing an action, with a fixed delay between attempts. It
	 * evaluates the conditions for each Sequence in the Sequence Array and
	 * chooses the first which has its conditions satisfied.
	 */
	public void action(BattlePlatform platform, BattleScreen screen, float delta) {
		if (delay < 0) {
			for (Sequence sequence : strategy) {
				sequence.updateActions();
				if (sequence.evaluateConditions()) {
					performActions(sequence, platform, screen);
					break;
				}
			}
			delay = 10;
		} else {
			delay -= delta;
		}
	}

	/**
	 * Invokes a call to {@link Enemy#summonCard()} with the parameters
	 * specified by the actions of the Sequence object or draws a card with the
	 * {@link Enemy#draw()} method. It assumes that the conditions are already
	 * satisfied and that there are at least 3 Action objects specifying the
	 * tile, lane and card. Attribution to redundant attributes results in
	 * overwriting.
	 */
	private void performActions(Sequence sequence, BattlePlatform platform,
			BattleScreen screen) {
		int tile = 0, lane = 0;
		Card card = null;

		for (Action action : sequence.getActions()) {
			if (action.getType() == Attribute.TILE)
				tile = (Integer) action.getValue();
			if (action.getType() == Attribute.LANE)
				lane = (Integer) action.getValue();
			if (action.getType() == Attribute.CARD)
				card = (Card) action.getValue();
		}

		if (card != null && tile >= 0 && lane >= 0
				&& card.getEnergyCost() <= platform.getRules().getEnemyEnergy())
			summonCard(tile, lane, card, platform, screen);
		else
			draw(platform);
	}

	/***
	 * Summons a card on the enemy field. It assumes that the enemy can legally
	 * summon the specified card with his.
	 */
	public void summonCard(int tile, int lane, Card card,
			BattlePlatform platform, BattleScreen screen) {
		platform.getEnemyHandCards().removeValue(card, true);
		card.setLane(lane);
		card.setTile(tile);
		card.setDirection(-1);
		Image cardImage = new Image();

		if (card.getClass().equals(Fighter.class)) {
			Fighter2D f2d = new Fighter2D((Fighter) card, screen.getGame());
			f2d.create();
			card.setMarkLane(lane);
			cardImage = f2d;
		} else if (card.getClass().equals(Effect.class)) {
			Effect2D e2d = new Effect2D((Effect) card, screen.getGame());
			e2d.create();
			cardImage = e2d;
		} else if (card.getClass().equals(Trap.class)) {
			Trap2D t2d = new Trap2D((Trap) card, screen.getGame());
			t2d.create();
			cardImage = t2d;
		}

		card.setImage(cardImage);
		platform.getMap().addCard(card, tile, lane);
		screen.addGameObject(cardImage);
		platform.getRules().setEnemyEnergy(
				platform.getRules().getEnemyEnergy() - card.getEnergyCost());
	}

	/**
	 * Draws a card from the deck, if possible.
	 */
	// TODO: Considerar intervalo de tempo antes de poder sacar uma carta.
	public void draw(BattlePlatform platform) {
		if (platform.getEnemyDeck().getCards().size > 0
				&& platform.getEnemyHandCards().size < 5) {
			platform.getEnemyHandCards().add(platform.getEnemyDeck().draw());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.strategy = json.readValue("strategy", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		super.write(json);

		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
		json.writeValue("strategy", this.strategy);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Enemy)
			return ((Enemy) obj).getId() == this.id;
		return false;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setStrategy(Array<Sequence> strategy) {
		this.strategy = strategy;
	}

	public Array<Sequence> getStrategy() {
		return this.strategy;
	}
}
