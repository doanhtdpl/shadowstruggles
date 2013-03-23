package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.model.enemies.Enemy;

import com.badlogic.gdx.utils.Array;

/**
 * A BattlePlatform object storages all the information regarding a battle: the
 * players' decks, the map and the cards placed on it, the rules being applied
 * etc.
 */
public class BattlePlatform {

	private Deck playerDeck;
	private Deck enemyDeck;
	private Map map;
	private DefaultRules rules;
	private Array<Card> playerDestroyedCards;
	private Array<Card> enemyDestroyedCards;
	private Card selectedCard;
	private Array<Card> playerHandCards;
	private Array<Card> enemyHandCards;
	private Field PlayerField;
	private Field EnemyField;
	private Enemy enemy;

	public BattlePlatform(Deck playerDeck, Deck enemyDeck, Map map,
			DefaultRules rules, Enemy enemy) {
		super();
		this.playerDeck = playerDeck;
		this.enemyDeck = enemyDeck;
		this.map = map;
		this.rules = rules;
		this.playerHandCards = new Array<Card>();
		this.enemyHandCards = new Array<Card>();
		this.PlayerField = new Field();
		this.EnemyField = new Field();
		this.enemy = enemy;
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
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
		return PlayerField;
	}

	public void setPlayerField(Field playerField) {
		PlayerField = playerField;
	}

	public Field getEnemyField() {
		return EnemyField;
	}

	public void setEnemyField(Field enemyField) {
		EnemyField = enemyField;
	}

	public void setPlayerHandCards(Array<Card> playerHandCards) {
		this.playerHandCards = playerHandCards;
	}

	public void setEnemyHandCards(Array<Card> enemyHandCards) {
		this.enemyHandCards = enemyHandCards;
	}

	/**
	 * Verifies if there is any card on enemy hand
	 * 
	 * @param card
	 *            Card object used to compare to enemyHandCards
	 * @return boolean true: There is a card on enemy hand false: there is no
	 *         card on enemy hand
	 */
	public boolean cardOnEnemyHand(Card card) {
		boolean b = false;
		for (Card c : enemyHandCards) {
			if (c.getName().equals(card.getName())) {
				b = true;
			}
		}
		return b;
	}

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

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

}
