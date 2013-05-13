package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Hacking action. The first enemy that pass it, will change his side
 */


public class HackingAction extends CardAction {

	private boolean finished;
	private boolean activated;
	public HackingAction() {
		this.finished = false;
		this.activated = false;
	}

	@Override
	public void doAction(BattlePlatform platform, Image img,float delta) {

		Trap2D f = (Trap2D) img;
		if (finished) {
			f.getController().removeCard(f.getTrap());
		} else {
			if (!activated) {
				for (Card c : platform.getMap().getTiles()
						.get(f.getTrap().getTile()).get(f.getTrap().getLane())) {
					if (c.getClass().equals(Fighter.class)
							&& c.getDirection() != f.getTrap().getDirection()
							&& ((Fighter) c).getName().startsWith("DR-")) {
						((Fighter) c).setDirection(-((Fighter) c)
								.getDirection());
						((Fighter2D) (c.getImage())).create();
						activated = true;
						f.setVisible(true);
					}
				}
			}
		}

	}

	@Override
	public void update(Image f1) {
		Trap2D f = (Trap2D) f1;
		if (f.isVisible()) {
			f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());
			f.setCurrentFrame(f.getAnimation().getKeyFrame(f.getStateTime(),
					true));
			
			if (f.getStateTime() > 2f) {
				finished = true;
			}
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
		return new HackingAction();
	}

}
