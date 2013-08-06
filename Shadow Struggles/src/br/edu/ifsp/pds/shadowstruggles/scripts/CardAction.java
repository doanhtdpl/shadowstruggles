package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Class for implementing the actions/effects for each card.
 */
public abstract class CardAction  implements Serializable{

	/***
	 * Execution of the card action.
	 */
	public void doAction(BattlePlatform platform, Image img, float delta) { }
	public void doAction(BattlePlatform platform, int lane, int tile){}
	public void doAction(BattlePlatform platform, Card card, float delta){}
	
	/***
	 * Updates the visual representation of the card.
	 */
	public void update(Image f1){}
	public void update(Card card){}
	public abstract CardAction copy();
	
	@Override
	public void write(Json json) {
		
	}
	

	@Override
	public void read(Json json, JsonValue jsonData) {
	}
}
