package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;


public class DefaultActionModel extends CardAction {

	@Override
	public void doAction(BattlePlatform platform, Card card, float delta) {
		Fighter fighter = (Fighter) card;
		fighter.setAttacking(false);
		if (fighter.getHealth() > 0) {
			attack(fighter, platform, delta);

			if (!fighter.isAttacking())
				attackBase(fighter, platform, delta);

			if (!fighter.isAttacking())
				fighter.move(delta);

		} else {
			// platform.destroyCard(fighter);
		}
	}

	private void attack(Fighter fighter, BattlePlatform platform, float delta) {
		for (int i = 0; i <= fighter.getRange()
				&& fighter.getTile() + fighter.getDirection() * i >= 0
				&& fighter.getTile() + fighter.getDirection() * i <= 35; i++) {
			if (platform.getMap().getTiles()
					.get(fighter.getTile() + fighter.getDirection() * i)
					.get(fighter.getLane()).size > 0) {
				for (Card card : platform.getMap().getTiles()
						.get(fighter.getTile() + fighter.getDirection() * i)
						.get(fighter.getLane())) {
					if (card.getClass().equals(Fighter.class)) {
						if (((Fighter) card).getDirection() != fighter
								.getDirection()) {
							if (fighter.getDelay() <= 0.0f) {
								((Fighter) card).setHealth(((Fighter) card)
										.getHealth() - fighter.getDamage());
								fighter.setDelay(fighter.getAttackDelay());
							} else
								fighter.setDelay(fighter.getDelay() - delta);
							fighter.setAttacking(true);
							break;
						}
					}
				}
			}
		}
	}

	private void attackBase(Fighter fighter, BattlePlatform platform,
			float delta) {
		if (fighter.getDirection() == 1) {
			if (fighter.getTile() >= 36 - fighter.getRange()) {
				if (fighter.getDelay() <= 0.0f) {
					fighter.setDelay(fighter.getAttackDelay());
				} else
					fighter.setDelay(fighter.getDelay() - delta);
				fighter.setAttacking(true);
			}
		} else {
			if (fighter.getTile() <= -1 + fighter.getRange()) {
				if (fighter.getDelay() <= 0.0f) {
					fighter.setDelay(fighter.getAttackDelay());
				} else
					fighter.setDelay(fighter.getDelay() - delta);

				fighter.setAttacking(true);
			}
		}
	}

	@Override
	public CardAction copy() {
		return new DefaultAction();
	}

}
