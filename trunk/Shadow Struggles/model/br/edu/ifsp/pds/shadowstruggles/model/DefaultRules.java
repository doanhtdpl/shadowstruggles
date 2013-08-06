package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.data.Settings;

/***
 * Basic set of rules for any match, including the rules/logic for determining
 * victory or defeat. Matches with special conditions may extend this class.
 */
public class DefaultRules implements Serializable {

	public static final String ONGOING_GAME = "The game is still ongoing";
	public static final String PLAYER_VICTORY = "The player has won";
	public static final String ENEMY_VICTORY = "The enemy has won";

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

	private float timer = 15 * 60;

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

	@Override
	public void write(Json json) {
		json.writeValue("playerHP", this.playerHP);
		json.writeValue("enemyHP", this.enemyHP);
		json.writeValue("playerHPmax", this.playerHPmax);
		json.writeValue("enemyHPmax", this.enemyHPmax);
		json.writeValue("playerRemainingCards", this.playerRemainingCards);
		json.writeValue("enemyRemainingCards", this.enemyRemainingCards);
		json.writeValue("playerEnergy", this.playerEnergy);
		json.writeValue("enemyEnergy", this.enemyEnergy);
		json.writeValue("playerEnergyMax", this.playerEnergyMax);
		json.writeValue("enemyEnergyMax", this.enemyEnergyMax);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.playerHP = json.readValue("playerHP", Integer.class, jsonData);
		this.enemyHP = json.readValue("enemyHP", Integer.class, jsonData);
		this.playerHPmax = json.readValue("playerHPmax", Integer.class,
				jsonData);
		this.enemyHPmax = json.readValue("enemyHPmax", Integer.class, jsonData);
		this.playerRemainingCards = json.readValue("playerRemainingCards",
				Integer.class, jsonData);
		this.enemyRemainingCards = json.readValue("enemyRemainingCards",
				Integer.class, jsonData);
		this.playerEnergy = json.readValue("playerEnergy", Integer.class,
				jsonData);
		this.enemyEnergy = json.readValue("enemyEnergy", Integer.class,
				jsonData);
		this.playerEnergyMax = json.readValue("playerEnergyMax", Integer.class,
				jsonData);
		this.enemyEnergyMax = json.readValue("enemyEnergyMax", Integer.class,
				jsonData);
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
	 * Gets the game state according to the object's parameters. The loser of a
	 * match is the one whose life or cards are completely depleted first or the
	 * one whose life is lesser than the opponent's when the time is over. In
	 * case of a draw, the human player wins.
	 * 
	 * @return A string describing the state. Its possible values are specified
	 *         by static parameters.
	 */

	public String gameState() {
		String state = DefaultRules.ONGOING_GAME;

		if (this.timer > 0) {
			if (this.playerHP <= 0 || this.playerRemainingCards <= 0)
				state = ENEMY_VICTORY;
			else if (this.enemyHP <= 0 || this.enemyRemainingCards <= 0)
				state = PLAYER_VICTORY;
		} else {
			if (this.playerHP < this.enemyHP)
				state = ENEMY_VICTORY;
			else if (this.playerHP > this.enemyHP)
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
