package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.data.Settings;

/***
 * Basic set of rules for any match, including the rules/logic for determining victory or defeat.
 * Matches with special conditions may extend this class.
 */
public class DefaultRules {

	public static final String ONGOING_GAME = "The game is still ongoing";
	public static final String PLAYER_VICTORY = "The player has won";
	public static final String ENEMY_VICTORY = "The enemy has won";

	private float timer = 15 * 60;
	private int playerHP;
	private int enemyHP;
	private int playerHPmax;
	private int enemyHPmax;
	private int playerRemainingCards;
	private int enemyRemainingCards;
	private int playerEnergy;
	private int enemyEnergy;
	private int playerEnergyMax;
	private int enemyEnergyMax;

	public DefaultRules() {
		this(new Settings());
	}
	
	public DefaultRules(Settings settings) {

		this.playerHPmax = settings.playerMaxHP;
		this.enemyHPmax = settings.enemyMaxHP;
		this.playerRemainingCards = settings.deckSize;
		this.enemyRemainingCards = settings.deckSize;
		this.playerEnergy = settings.playerInitialEnergy;
		this.enemyEnergy = settings.enemyInitialEnergy;
		this.playerEnergyMax = settings.playerMaxEnergy;
		this.enemyEnergyMax = settings.enemyMaxEnergy;

		this.playerHP = playerHPmax;
		this.enemyHP = enemyHPmax;
	}

	public DefaultRules(int playerHP, int enemyHP, int playerRemainingCards,
			int enemyRemainingCards, int playerEnergy, int enemyEnergy,
			Settings settings) {

		this.playerHPmax = playerHP;
		this.enemyHPmax = enemyHP;
		this.playerHP = playerHP;
		this.enemyHP = enemyHP;
		this.playerRemainingCards = playerRemainingCards;
		this.enemyRemainingCards = enemyRemainingCards;
		this.playerEnergy = playerEnergy;
		this.enemyEnergy = enemyEnergy;

		this.playerEnergyMax = settings.playerMaxEnergy;
		this.enemyEnergyMax = settings.enemyMaxEnergy;
	}

	/**
	 * This method should be called to update the timer whenever the interface
	 * is updated.
	 * 
	 * @param elapsedTime
	 *            The time elapsed since the start of the match.
	 */

	public void update(float elapsedTime) {
		float futureTime = this.timer - elapsedTime;
		if (futureTime < 0.0f)
			this.timer = 0.0f;
		else
			this.timer = futureTime;
	}

	/**
	 * Gets the game state according to the object's parameters. The loser
	 * of a match is the one whose life or cards are completely depleted first or
	 * the one whose life is lesser than the opponent's when the time is over.
	 * In case of a draw, the human player wins.
	 * 
	 * @return A string describing the state. Its possible values
	 * are specified by static parameters.
	 */

	public String gameState() {
		String state = DefaultRules.ONGOING_GAME;

		if (this.timer > 0) {
			if (this.playerHP <= 0 || this.playerRemainingCards <= 0)
				state = ENEMY_VICTORY;
			else if (this.enemyHP <= 0 || this.enemyRemainingCards <= 0)
				state = PLAYER_VICTORY;
		} else {
			if(this.playerHP < this.enemyHP)
				state = ENEMY_VICTORY;
			else if(this.playerHP > this.enemyHP)
				state = PLAYER_VICTORY;
			else
				state = ONGOING_GAME;
		}

		return state;
	}

	public int getPlayerHP() {
		return playerHP;
	}

	public int getPlayerHPmax() {
		return this.playerHPmax;
	}

	public float getPlayerHpPercent() {
		return (float) playerHP / (float) playerHPmax;
	}

	public void setPlayerHP(int playerHP) {
		this.playerHP = playerHP;
	}

	public int getEnemyHP() {
		return enemyHP;
	}

	public int getEnemyHPmax() {
		return this.enemyHPmax;
	}

	public float getEnemyHpPercent() {
		return (float) enemyHP / (float) enemyHPmax;
	}

	public void setEnemyHP(int enemyHP) {
		this.enemyHP = enemyHP;
	}

	public int getPlayerRemainingCards() {
		return playerRemainingCards;
	}

	public void setPlayerRemainingCards(int playerRemainingCards) {
		this.playerRemainingCards = playerRemainingCards;
	}

	public int getEnemyRemainingCards() {
		return enemyRemainingCards;
	}

	public void setEnemyRemainingCards(int enemyRemainingCards) {
		this.enemyRemainingCards = enemyRemainingCards;
	}

	public int getPlayerEnergy() {
		return playerEnergy;
	}

	public int getPlayerEnergyMax() {
		return playerEnergyMax;
	}

	public int getEnemyEnergyMax() {
		return enemyEnergyMax;
	}

	public void setPlayerEnergy(int playerEnergy) {
		this.playerEnergy = playerEnergy;
	}

	public int getEnemyEnergy() {
		return enemyEnergy;
	}

	public void setEnemyEnergy(int enemyEnergy) {
		this.enemyEnergy = enemyEnergy;
	}

	public float getPlayerEnergyPercent() {
		return (float) playerEnergy / (float) playerEnergyMax;
	}

	public float getEnemyEnergyPercent() {
		return (float) enemyEnergy / (float) enemyEnergyMax;
	}

	public float getTimer() {
		return this.timer;
	}

}
