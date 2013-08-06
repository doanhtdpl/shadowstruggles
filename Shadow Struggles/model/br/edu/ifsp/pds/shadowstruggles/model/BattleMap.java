package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * A BattleMap object is a three-dimensional Array containing the cards placed
 * on the field. A card is inside an Array of cards, which is inside a tile,
 * which is inside an Array of tiles. Thus, it is possible to have multiple
 * cards on each tile.
 */

public class BattleMap implements Serializable {
	public static final int HUMAN_PLAYER = 1;
	public static final int COMPUTER_PLAYER = -1;

	private int id;
	private String name;
	
	private Array<Array<Array<Card>>> tiles;

	public BattleMap() {
		this("");
	}

	public BattleMap(String string) {
		this(1, string);
	}
	
	public BattleMap(int id, String name) {
		this.name = name;
		this.tiles = new Array<Array<Array<Card>>>();
		for (int i = 0; i < 50; i++) {
			tiles.add(new Array<Array<Card>>());
			for (int j = 0; j < 4; j++) {
				tiles.get(i).add(new Array<Card>());
			}
		}

	}


	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("name", this.name);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
	}

	/***
	 * Verifies if the card is on the map. Lane is an optional attribute which
	 * informs whether a specific lane should be searched or all of them. All of
	 * the lanes are searched if lane = -1.
	 */

	public boolean cardOnMap(Card c, int lane, int player) {
		boolean cardOnMap = false;
		for (Array<Array<Card>> lanes : tiles) {
			for (Array<Card> tiles : lanes) {
				for (Card card : tiles) {
					if (card.getName().equals(c.getName())
							&& card.getDirection() == player)
						return true;
				}
			}
		}
		return cardOnMap;
	}

	/***
	 * Verifies if the enemy base is being attacked. The base is being attacked
	 * if it's inside the range of an enemy fighter.
	 */

	public int enemyBaseAttacked() {
		int lane = -1;
		for (Array<Array<Card>> lanes : tiles) {
			for (Array<Card> tiles : lanes) {
				for (Card card : tiles) {
					if (card.getClass().equals(Fighter.class))
						if (card.direction == 1)
							if (((Fighter) card).getTile()
									+ ((Fighter) card).getRange() > 35)
								lane = card.getLane();
				}
			}
		}
		return lane;
	}

	/***
	 * Verifies if the player base is being attacked. The base is being attacked
	 * if it's inside the range of an enemy fighter.
	 */

	public int playerBaseAttacked() {
		int lane = -1;
		for (Array<Array<Card>> lanes : tiles) {
			for (Array<Card> tiles : lanes) {
				for (Card card : tiles) {
					if (card.direction == -1)
						if (((Fighter) card).getTile()
								- ((Fighter) card).getRange() < 1)
							lane = card.getLane();
				}
			}
		}
		return lane;
	}

	/***
	 * Returns an Array of Card containing all of the cards belonging to the
	 * human player on the field.
	 */

	public Array<Card> getPlayerCards() {
		Array<Card> cards = new Array<Card>();
		for (Array<Array<Card>> lanes : tiles) {
			for (Array<Card> tiles : lanes) {
				for (Card card : tiles) {
					if (card.direction == 1)
						cards.add(card);
				}
			}
		}
		return cards;
	}

	/***
	 * Returns an Array of Card containing all of the cards belonging to the
	 * machine player on the field.
	 */

	public Array<Card> getEnemyCards() {
		Array<Card> cards = new Array<Card>();
		for (Array<Array<Card>> lanes : tiles) {
			for (Array<Card> tiles : lanes) {
				for (Card card : tiles) {
					if (card.direction == -1)
						cards.add(card);
				}
			}
		}
		return cards;
	}

	/**
	 * Returns the next available lane, from top to down. A lane is available if
	 * there is at least one free slot in it.
	 * 
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */

	public int nextAvailableLane(int player) {
		int[] lane = new int[4];
		Array<Card> cards;
		if (player == HUMAN_PLAYER)
			cards = getPlayerCards();
		else
			cards = getEnemyCards();
		for (Card card : cards)
			lane[(int) card.getMarkLane()] += 1;
		for (int i = 0; i < 4; i++) {
			if (lane[i] < 5)
				return i;
		}
		return -1;
	}

	/**
	 * Returns the next available tile in the specified lane, from the outside
	 * to into the base. A tile is available if it's not occupied by a card.
	 * 
	 * @param lane
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */

	public int nextAvailableTile(int lane, int player) {
		int start;
		float[] tile = new float[5];
		Array<Card> cards;
		if (player == HUMAN_PLAYER) {
			start = 0;
			cards = getPlayerCards();
		} else {
			start = 30;
			cards = getEnemyCards();
		}
		for (Card card : cards)
			if (card.getMarkLane() == lane)
				tile[(int) card.getMarkTile() - start] = 1;
		for (int i = 0; i <= 4; i++) {
			if (tile[i] != 1)
				return i + start;
		}
		return -1;
	}

	/**
	 * Returns the lane with the greatest number of enemy fighters.
	 * 
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */
	public int laneWithMoreEnemies(int player) {
		int lane = 0;
		Array<Card> enemies = new Array<Card>();
		OrderedMap<Integer, Integer> lanes = new OrderedMap<Integer, Integer>();

		if (player == COMPUTER_PLAYER)
			enemies = this.getPlayerCards();
		else
			enemies = this.getEnemyCards();

		for (Card fighter : enemies) {
			if (fighter.getClass() == Fighter.class) {
				if (lanes.containsKey(fighter.getLane()))
					lanes.put(fighter.getLane(),
							lanes.get(fighter.getLane()) + 1);
				else
					lanes.put(fighter.getLane(), 1);
			}
		}

		for (int l : lanes.keys()) {
			System.out.println(lanes.keys().toArray().size);
			if (lanes.get(l) > lanes.get(lane))
				lane = l;
		}

		return lane;
	}

	/**
	 * Returns the lane with the least number of allied fighters.
	 * 
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */
	public int laneWithLessAllies(int player) {
		int lane = 0;
		Array<Card> allies = new Array<Card>();
		OrderedMap<Integer, Integer> lanes = new OrderedMap<Integer, Integer>();

		if (player == COMPUTER_PLAYER)
			allies = this.getEnemyCards();
		else
			allies = this.getPlayerCards();

		for (Card fighter : allies) {
			if (fighter.getClass() == Fighter.class) {
				if (lanes.containsKey(fighter.getLane()))
					lanes.put(fighter.getLane(),
							lanes.get(fighter.getLane()) + 1);
				else
					lanes.put(fighter.getLane(), 1);
			}
		}

		for (int l : lanes.keys()) {
			if (lanes.get(l) < lanes.get(lane))
				lane = l;
		}

		return lane;
	}

	/**
	 * Returns the lane with the greatest number of enemy fighters invading the
	 * player's field.
	 * 
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */

	public int laneWithMoreEnemiesInvading(int player) {
		Integer[] lane = new Integer[4];
		Array<Card> cards;
		if (player == COMPUTER_PLAYER)
			cards = getPlayerCards();
		else
			cards = getEnemyCards();
		for (Card card : cards)
			lane[(int) ((Fighter) card).getLane()] += 1;
		Array<Integer> temp = new Array<Integer>(lane);
		temp.sort();
		return temp.get(temp.size - 1);
	}

	/**
	 * Returns the next available tile in the specified lane that is closest to
	 * the base. A tile is available if it's not occupied by a card.
	 * 
	 * @param lane
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */
	// TODO: Implementar método.
	public int tileClosestToBase(int lane, int player) {
		int tile = 0;

		return tile;
	}

	/**
	 * Returns the next available tile in the specified lane that is furthest
	 * from the base. A tile is available if it's not occupied by a card.
	 * 
	 * @param lane
	 * @param player
	 *            Indicates which field to look into: BattleMap.HUMAN_PLAYER or
	 *            BattleMap.ENEMY_PLAYER.
	 */
	// TODO: Implementar método.
	public int tileFurthestFromBase(int lane, int player) {
		int tile = 0;

		return tile;
	}

	public Array<Array<Array<Card>>> getTiles() {
		return tiles;
	}

	public void setTiles(Array<Array<Array<Card>>> tiles) {
		this.tiles = tiles;
	}

	public void addCard(Card card, int tile, int lane) {
		tiles.get(tile).get(lane).add(card);
		card.setMarkPosition(lane, tile);
		card.setTile(tile);
		card.setLane(lane);
	}

	public String getName() {
		return name;
	}

}
