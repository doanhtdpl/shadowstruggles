package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * The default behavior of a fighter card. It walks towards the opposing base
 * with the objective of destroying it, attacking any enemy fighters along the
 * way.
 */
public class DefaultAction extends CardAction {

	@Override
	public void doAction(BattlePlatform platform, Image image, float delta) {
		Fighter2D fighter = (Fighter2D) image;
		fighter.setAttacking(false);
		if (fighter.getFighter().getHealth() > 0) {
			attack(fighter, platform,delta);

			if (!fighter.isAttacking())
				attackBase(fighter, platform,delta);

			if (!fighter.isAttacking())
				move(fighter, platform, delta);

		} else {
			fighter.getController().removeCard(fighter.getFighter());
		}

	}

	/**
	 * Verifies if there's an enemy within the fighter's range and deals damage
	 * to it.
	 * 
	 * @param f
	 *            The fighter's visual representation.
	 * @param platform
	 *            The match's state variables.
	 */
	private void attack(Fighter2D fighter, BattlePlatform platform, float delta) {
		for (int i = 0; i <= fighter.getFighter().getRange()
				&& fighter.getFighter().getTile()
						+ fighter.getFighter().getDirection() * i >= 0
				&& fighter.getFighter().getTile()
						+ fighter.getFighter().getDirection() * i <= 35; i++) {
			if (platform
					.getMap()
					.getTiles()
					.get(fighter.getFighter().getTile()
							+ fighter.getFighter().getDirection() * i)
					.get(fighter.getFighter().getLane()).size > 0) {
				for (Card card : platform
						.getMap()
						.getTiles()
						.get(fighter.getFighter().getTile()
								+ fighter.getFighter().getDirection() * i)
						.get(fighter.getFighter().getLane())) {
					if (card.getClass().equals(Fighter.class)) {
						if (((Fighter) card).getDirection() != fighter
								.getFighter().getDirection()) {
							if (fighter.getFighter().getDelay() <= 0.0f) {
								((Fighter) card).setHealth(((Fighter) card)
										.getHealth()
										- fighter.getFighter().getDamage());
								fighter.getFighter().setDelay(
										fighter.getFighter().getAttackDelay());
							} else
								fighter.getFighter()
										.setDelay(
												fighter.getFighter().getDelay()
														- delta);

							fighter.setAttacking(true);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Moves the fighter towards the opposing base, if it's possible to do so.
	 * 
	 * @param f
	 *            The fighter's visual representation.
	 * @param platform
	 *            The match's state variables.
	 */

	private void move(Fighter2D fighter, BattlePlatform platform, float delta) {
		fighter.setX( fighter.getX() + fighter.getFighter().getSpeed()
				* fighter.getFighter().getDirection()*delta*60);
		if (fighter.getFighter().getDirection() == 1) {
			if ((int) ((fighter.getX() - 96) / 48) > fighter.getFighter().getTile()) {
				fighter.getController().tileChanged(fighter.getFighter());
				fighter.getFighter()
						.setTile(fighter.getFighter().getTile() + 1);
			}
		} else {
			if ((int) ((fighter.getX() - 48) / 48) < fighter.getFighter().getTile()) {
				fighter.getController().tileChanged(fighter.getFighter());
				fighter.getFighter()
						.setTile(fighter.getFighter().getTile() - 1);
			}

		}

	}

	/**
	 * Attempts to attack the opposing base.
	 * 
	 * @param f
	 *            The fighter's visual representation.
	 * @param platform
	 *            The match's state variables.
	 */

	private void attackBase(Fighter2D f, BattlePlatform platform, float delta) {
		if (f.getFighter().getDirection() == 1) {
			if (f.getFighter().getTile() >= 36 - f.getFighter().getRange()) {
				if (f.getFighter().getDelay() <= 0.0f) {
					f.getController().enemyLifeChanged(
							-f.getFighter().getDamage());
					f.getFighter().setDelay(f.getFighter().getAttackDelay());
				} else
					f.getFighter().setDelay(
							f.getFighter().getDelay()
									- delta);

				f.setAttacking(true);
			}
		} else {
			if (f.getFighter().getTile() <= -1 + f.getFighter().getRange()) {
				if (f.getFighter().getDelay() <= 0.0f) {
					f.getController().playerLifeChanged(
							-f.getFighter().getDamage());
					f.getFighter().setDelay(f.getFighter().getAttackDelay());
				} else
					f.getFighter().setDelay(
							f.getFighter().getDelay()
									- delta);

				f.setAttacking(true);
			}
		}
	}
	
	/**
	 * Display the animation of fighting of a fighter during a fight 
	 * 
	 * @param f1
	 * 			The fighter Drawable
	 */

	public void update(Image f1) {
		Fighter2D fighter = (Fighter2D) f1;
		if (fighter.isAttacking()) {
			fighter.setStateTime(Gdx.graphics.getDeltaTime()
					+ fighter.getStateTime());
			fighter.setCurrentFrame(fighter.getAttackAnimation().getKeyFrame(
					fighter.getStateTime(), true));
			

		} else {
			fighter.setStateTime(Gdx.graphics.getDeltaTime()
					+ fighter.getStateTime());
			fighter.setCurrentFrame(fighter.getWalkAnimation().getKeyFrame(
					fighter.getStateTime(), true));
			
		}

	}

	@Override
	public CardAction copy() {
		return new DefaultAction();
	}
	
	@Override
	public void write(Json json) {
		
	}
	

	@Override
	public void read(Json json, JsonValue jsonData) {
		//this.id = json.readValue("id", Integer.class, jsonData);
	}
}
