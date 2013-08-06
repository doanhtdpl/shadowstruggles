package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.model.modifiers.Modifier;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * A BattlePlatform object storages all the information regarding a battle: the
 * players' decks, the map and the cards placed on it, the rules being applied
 * etc.
 */
public class BattlePlatform implements Serializable {
	// Persistent data.
	private int id;
	private Enemy enemy;
	private Array<Modifier> rewards;
	private BattleMap map;
	private DefaultRules rules;
	private boolean tutorial;

	// Input data (decks from the players).
	private Deck playerDeck;
	private Deck enemyDeck;

	// Real-time updated data for ongoing matches.
	private Array<Card> playerDestroyedCards;
	private Array<Card> enemyDestroyedCards;
	private Card selectedCard;
	private Array<Card> playerHandCards;
	private Array<Card> enemyHandCards;
	private Field playerField;
	private Field enemyField;

	public BattlePlatform() {
		this(new Deck(), new Deck(), new BattleMap(), new DefaultRules());
	}

	public BattlePlatform(Deck playerDeck, Deck enemyDeck, BattleMap map,
			DefaultRules rules) {
		this(playerDeck, enemyDeck, map, rules, null);
	}

	public BattlePlatform(Deck playerDeck, Deck enemyDeck, BattleMap map,
			DefaultRules rules, Enemy enemy) {
		this(playerDeck, enemyDeck, map, rules, enemy, 1, null, false);
	}

	public BattlePlatform(Deck playerDeck, Deck enemyDeck, BattleMap map,
			DefaultRules rules, Enemy enemy, int id, Array<Modifier> rewards,
			boolean tutorial) {
		this.enemy = enemy;
		this.map = map;
		this.rules = rules;
		this.id = id;
		this.rewards = rewards;
		this.tutorial = tutorial;

		this.playerDeck = playerDeck;
		this.enemyDeck = enemyDeck;

		this.playerHandCards = new Array<Card>();
		this.enemyHandCards = new Array<Card>();
		this.playerField = new Field();
		this.enemyField = new Field();
	}

	/**
	 * Checks for a card on the enemy hand.
	 */
	public boolean cardOnEnemyHand(String cardName) {
		boolean b = false;
		for (Card c : enemyHandCards) {
			if (c.getName().equals(cardName)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * Gets a card from the enemy hand.
	 */
	public Card getCardFromEnemy(String string) {
		Card c = null;
		for (Card card : enemyHandCards) {
			if (card.getName().equals(string)) {
				c = card;
				break;
			}
		}
		return c;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("enemy", this.enemy);
		json.writeValue("rewards", this.rewards);
		json.writeValue("map", this.map);
		json.writeValue("rules", this.rules);
		json.writeValue("tutorial", this.tutorial);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.enemy = json.readValue("enemy", Enemy.class, jsonData);
		this.rewards = json.readValue("rewards", Array.class, jsonData);
		this.map = json.readValue("map", BattleMap.class, jsonData);
		this.rules = json.readValue("rules", DefaultRules.class, jsonData);
		this.tutorial = json.readValue("tutorial", Boolean.class, jsonData);
	}

	public void addPlayerHandCard(Card card) {
		playerHandCards.add(card);
	}

	public void addEnemyHandCard(Card card) {
		enemyHandCards.add(card);
	}

	public Deck getPlayerDeck() {
		return playerDeck;
	}

	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
	}

	public Card getSelectedCard() {
		return selectedCard;
	}

	public void setPlayerDeck(Deck playerDeck) {
		this.playerDeck = playerDeck;
	}

	public Deck getEnemyDeck() {
		return enemyDeck;
	}

	public void setEnemyDeck(Deck enemyDeck) {
		this.enemyDeck = enemyDeck;
	}

	public BattleMap getMap() {
		return map;
	}

	public void setMap(BattleMap map) {
		this.map = map;
	}

	public DefaultRules getRules() {
		return this.rules;
	}

	public void setRules(DefaultRules rules) {
		this.rules = rules;
	}

	public Array<Card> getPlayerDestroyedCards() {
		return playerDestroyedCards;
	}

	public void setPlayerDestroyedCards(Array<Card> playerDestroyedCards) {
		this.playerDestroyedCards = playerDestroyedCards;
	}

	public Array<Card> getEnemyDestroyedCards() {
		return enemyDestroyedCards;
	}

	public void setEnemyDestroyedCards(Array<Card> enemyDestroyedCards) {
		this.enemyDestroyedCards = enemyDestroyedCards;
	}

	public Array<Card> getPlayerHandCards() {
		return playerHandCards;
	}

	public Array<Card> getEnemyHandCards() {
		return enemyHandCards;
	}

	public Field getPlayerField() {
		return playerField;
	}

	public void setPlayerField(Field playerField) {
		this.playerField = playerField;
	}

	public Field getEnemyField() {
		return enemyField;
	}

	public void setEnemyField(Field enemyField) {
		this.enemyField = enemyField;
	}

	public void setPlayerHandCards(Array<Card> playerHandCards) {
		this.playerHandCards = playerHandCards;
	}

	public void setEnemyHandCards(Array<Card> enemyHandCards) {
		this.enemyHandCards = enemyHandCards;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public int getId() {
		return this.id;
	}
}
