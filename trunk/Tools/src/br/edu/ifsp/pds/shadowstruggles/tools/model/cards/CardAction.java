package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

import com.esotericsoftware.jsonbeans.Json.Serializable;

// TODO: Implementar classes filhas de CardAction.

public abstract class CardAction implements Serializable{
	/***
	 * Execution of the card action.
	 */
	public void doAction(BattlePlatform platform, String img, float delta) { }
	public void doAction(BattlePlatform platform, int lane, int tile){}
	public void doAction(BattlePlatform platform, Card card, float delta){}
	
	/***
	 * Updates the visual representation of the card.
	 */
	public void update(String f1){}
	public void update(Card card){}
	public abstract CardAction copy();
}
