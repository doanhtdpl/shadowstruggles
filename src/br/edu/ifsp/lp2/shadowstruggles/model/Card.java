package br.edu.ifsp.lp2.shadowstruggles.model;

import br.edu.ifsp.lp2.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/***
 * Template for the behavior of the cards on the field. This class only concerns
 * itself with the logic aspects of the card, not data.
 */

public class Card implements Serializable {

	public int energyCost;
	public String description;
	public int buyCost;
	public String name;
	public Image image;
	public CardAction action;
	public BattlePlatform platform;
	public float markLane;
	public float markTile;
	public int tile;

	public int count = 0;
	
	public Array<String> preRequisites = new Array<String>();

	/***
	 * Specifies the card's orientation and which player it belongs to. 1 - The
	 * card moves to the right and belongs to the human player. -1 - The
	 * card moves to the left and belongs to the machine player.
	 */

	protected int direction;
	
	/***
	 * The lane is counted from bottom to top, starting from 0.
	 */
	public int lane;

	public Card() {
		this.name = "";
		this.description = "";
	}

	public Card(BattlePlatform platform, int tile, int lane, String name,
			CardAction action, Image img) {
		this.name = name;
		this.platform = platform;
		this.lane = lane;
		this.tile = tile;
		this.action = action;
		this.image = img;
		this.description = "";
	}

	public Card(String name, int energyCost, String description, int buyCost,
			CardAction action) {
		this.name = name;
		this.energyCost = energyCost;
		this.description = description;
		this.buyCost = buyCost;
		this.action = action;
	}

	public void action(BattlePlatform platform, Image image) {
		this.action.doAction(platform, image);
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(int buyCost) {
		this.buyCost = buyCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return this.count;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

	public int getTile() {
		return tile;
	}

	public void setTile(int tile) {
		this.tile = tile;
	}

	@Override
	public void write(Json json) {
		// json.setOutputType(Gdx.files.internal("data/fighters_en_us.json"));
		json.writeValue("name", this.name);
		json.writeValue("energyCost", this.energyCost);
		json.writeValue("description", this.description);
		json.writeValue("buyCost", this.buyCost);
		json.writeValue("preRequisites", this.preRequisites);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.name = json.readValue("name", String.class, jsonData);
		this.energyCost = json.readValue("energyCost", Integer.class, jsonData);
		this.description = json
				.readValue("description", String.class, jsonData);
		this.buyCost = json.readValue("buyCost", Integer.class, jsonData);
		this.preRequisites = json.readValue("preRequisites", Array.class, jsonData);
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


	public boolean readyToSummom(BattlePlatform platform){
		boolean bool = true;
		for( String card: this.preRequisites){
			if(!platform.getMap().cardOnMap(new Card(card,0,null,0,null), -1)){
				bool =false;
				break;
			}
		}
		return bool;
		
	}
}