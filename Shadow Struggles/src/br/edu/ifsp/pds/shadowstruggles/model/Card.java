package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/***
 * Template for the behavior of the cards on the field. This class only concerns
 * itself with the logic aspects of the card, not data.
 */

public class Card implements Serializable {

	private int energyCost;
	private String description;
	private int buyCost;
	private String name;
	private String nameVisualization;
	private Image image;
	private CardAction action;
	private BattlePlatform platform;
	private float markLane;
	private float markTile;
	protected int tile;

	private Array<String> preRequisites = new Array<String>();

	/***
	 * Specifies the card's orientation and which player it belongs to. 1 - The
	 * card moves to the right and belongs to the human player. -1 - The card
	 * moves to the left and belongs to the machine player.
	 */

	protected int direction;

	/***
	 * The lane is counted from bottom to top, starting from 0.
	 */
	protected int lane;

	public Card() {
		this.name = "";
		this.description = "";
	}

	public Card(BattlePlatform platform, int tile, int lane, String name,
			String nameVisualization, CardAction action, Image img) {
		this.name = name;
		this.nameVisualization = nameVisualization;
		this.platform = platform;
		this.lane = lane;
		this.tile = tile;
		this.action = action;
		this.image = img;
		this.description = "";
	}

	public Card(String name, String nameVisualization, int energyCost,
			String description, int buyCost, CardAction action) {
		this.name = name;
		this.nameVisualization = nameVisualization;
		this.energyCost = energyCost;
		this.description = description;
		this.buyCost = buyCost;
		this.action = action;
	}

	public void action(BattlePlatform platform, Image image, float delta) {
		this.action.doAction(platform, image, delta);
	}

	@Override
	public void write(Json json) {
		json.writeValue("name", this.name);
		json.writeValue("nameVisualization", this.nameVisualization);
		json.writeValue("energyCost", this.energyCost);
		json.writeValue("description", this.description);
		json.writeValue("buyCost", this.buyCost);
		json.writeValue("preRequisites", this.preRequisites);
		json.writeValue("Action", this.action);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.nameVisualization = json.readValue("nameVisualization",
				String.class, jsonData);
		this.energyCost = json.readValue("energyCost", Integer.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.buyCost = json.readValue("buyCost", Integer.class, jsonData);
		this.preRequisites = json.readValue("preRequisites", Array.class,
				jsonData);
		this.action = json.readValue("Action", CardAction.class, jsonData);
	}

	public String getName() {
		return name;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public String getDescription() {
		return description;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public CardAction getAction() {
		return action;
	}

	public void setAction(CardAction action) {
		this.action = action;
	}

	public BattlePlatform getPlatform() {
		return platform;
	}

	public void setPlatform(BattlePlatform platform) {
		this.platform = platform;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getLane() {
		return lane;
	}

	public int getTile() {
		return tile;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public void setTile(int tile) {
		this.tile = tile;
	}

	/**
	 * Verifies if a card is ready to summon based on its preRequisites
	 * 
	 * @param platform
	 *            Gathers the current data of the battle
	 * @return true: the card can be summoned right now false: the card can't be
	 *         summoned right now
	 */
	public boolean readyToSummom(BattlePlatform platform) {
		boolean bool = true;
		for (String card : this.preRequisites) {
			if (!platform.getMap().cardOnMap(
					new Card(card, card, 0, null, 0, null), -1,
					BattleMap.HUMAN_PLAYER)) {
				bool = false;
				break;
			}
		}
		return bool;

	}

	public String getNameVisualization() {
		return nameVisualization;
	}

	public void setNameVisualization(String nameVisualization) {
		this.nameVisualization = nameVisualization;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getMarkLane() {
		return markLane;
	}

	public void setMarkLane(float markLane) {
		this.markLane = markLane;
	}

	public void setMarkPosition(float lane, float tile) {
		this.markLane = lane;
		this.markTile = tile;
	}

	public void setPosition(int lane, int tile) {
		this.lane = lane;
		this.tile = tile;
	}

	public float getMarkTile() {
		return markTile;
	}

	public void act(float delta) {
		action.doAction(platform, this, delta);
	}

	public Array<String> getPreRequisites() {
		return preRequisites;
	}

	public void setPreRequisites(Array<String> preRequisites) {
		this.preRequisites = preRequisites;
	}
}
