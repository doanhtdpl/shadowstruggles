package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public abstract class CardAction implements Serializable{
	/***
	 * Execution of the card action.
	 */
	public void doAction(BattlePlatform platform, String img, float delta) { }
	public void doAction(BattlePlatform platform, int lane, int tile){}
	public void doAction(BattlePlatform platform, Card card, float delta){}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {}
	@Override
	public void write(Json arg0) {}
}
