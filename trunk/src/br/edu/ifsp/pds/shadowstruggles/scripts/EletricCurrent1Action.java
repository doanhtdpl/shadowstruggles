package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * EletricCurrent1 action. The first enemy that pass it, cannot move and take 10
 * of damage over 5 seconds.
 * 
 */

public class EletricCurrent1Action extends CardAction {

	private boolean finished;
	private boolean started;
	private float initialSpeed;
	private int count;
	private Fighter aflicted;

	public EletricCurrent1Action() {
		this.finished = false;
		this.started = false;
		this.count = 0;
		this.aflicted = null;
	}

	@Override
	public void doAction(BattlePlatform platform, Image img, float delta) {

		Trap2D f = (Trap2D) img;

		if (!started) {
			img.setX(img.getX() + 24);
			started = true;
		}
		if (aflicted != null) {
			if ((aflicted).getHealth() > 0) {

				if (finished) {
					((Fighter) aflicted).setSpeed(initialSpeed);
					f.getController().removeCard(f.getTrap());
				} else if (((int) f.getStateTime()) == count) {
					(aflicted).setHealth(((Fighter) aflicted).getHealth() - 2);
					count++;

				}

			} else {
				f.getController().removeCard(f.getTrap());
			}
		} else {
			for (Card c : platform.getMap().getTiles()
					.get(f.getTrap().getTile()).get(f.getTrap().getLane())) {
				if (c.getClass().equals(Fighter.class)
						&& c.getDirection() != f.getTrap().getDirection()) {
					if (((Fighter) c).getSpeed() > 0) {
						f.setVisible(true);
						initialSpeed = ((Fighter) c).getSpeed();
						((Fighter) c).setSpeed(0);
						aflicted = (Fighter) c;
					} else {
						if (finished) {
							((Fighter) c).setSpeed(initialSpeed);
							f.getController().removeCard(f.getTrap());
							break;
						} else if (((int) f.getStateTime()) == count) {
							((Fighter) c)
									.setHealth(((Fighter) c).getHealth() - 2);
							count++;

						}

					}

				}
			}
		}

	}

	@Override
	public void update(Image f1) {
		Trap2D trap = (Trap2D) f1;
		if (trap.isVisible()) {
			trap.setStateTime(Gdx.graphics.getDeltaTime() + trap.getStateTime());
			trap.setCurrentFrame(trap.getAnimation().getKeyFrame(
					trap.getStateTime(), true));
			if (trap.getStateTime() > 5f) {
				finished = true;
			}
		}
	}

	@Override
	public void write(Json json) {

	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		// this.id = json.readValue("id", Integer.class, jsonData);
	}

	@Override
	public CardAction copy() {
		return new EletricCurrent1Action();
	}
}
