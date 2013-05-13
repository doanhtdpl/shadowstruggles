package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Recovery action. Recover 30% of max life of all allied 'DR' units
 * 
 * 
 */


public class RecoveryAction extends CardAction {

	private boolean finished;
	private boolean used;
	public RecoveryAction() {
		this.used=false;
	}

	@Override
	public void doAction(BattlePlatform platform, int lane, int tile) {

	}

	@Override
	public void doAction(BattlePlatform platform, Image img,float delta) {
		Effect2D e = (Effect2D) img;
		if (!finished) {
			if (!used) {
				for (Array<Array<Card>> lanes : platform.getMap().getTiles()) {
					for (Array<Card> tiles : lanes) {
						for (Card card : tiles) {
							if (card.getClass().equals(Fighter.class)
									&& (((Fighter) card).getName().startsWith(
											"DR-") )) {
								if (card.getDirection() == e.getEffect()
										.getDirection())
									fighterRecovery((Fighter) card);
							}
						}
					}
				}
				used = true;

			}
		} else {
			e.getController().removeCard(e.getEffect());
		}
	}

	/**
	 * Recover 30% of max life of the specific fighter
	 * 
	 * 
	 * @param fighter
	 *            The fighter that will be healed
	 * 
	 */
	
	private void fighterRecovery(Fighter fighter) {

		fighter.setHealth(fighter.getHealth() + (fighter.getMaxHealth() / 3));
		if (fighter.getHealth() > fighter.getMaxHealth())
			fighter.setHealth(fighter.getMaxHealth());

	}

	@Override
	public void update(Image f1) {
		Effect2D f = (Effect2D) f1;
		f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
		f.setCurrentFrame(f.getAnimation().getKeyFrame(f.getStateTime(), true));
		
		if (f.getStateTime() > 2f) {
			finished = true;
		}

	}
	
	@Override
	public void write(Json json) {
		
	}
	

	@Override
	public void read(Json json, JsonValue jsonData) {
		//this.id = json.readValue("id", Integer.class, jsonData);
	}

	@Override
	public CardAction copy() {
		return new RecoveryAction();
	}
}
