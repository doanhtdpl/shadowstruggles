package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import com.badlogic.gdx.math.MathUtils;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Map;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

public class PracticeEnemy extends Enemy {
	public PracticeEnemy() {
		super();
	}

	@Override
	public void action(BattlePlatform platform, BattleScreen screen, float delta) {
		int tile;
		int lane;
		if (delay < 0) {
			Card card = null;
			if (platform.getMap().enemyBaseAttacked() > -1) {
				lane = platform.getMap().enemyBaseAttacked();
				tile = 35;
				if (platform.getMap().cardOnMap(
						new Card("Mineralogy", null, 0, "", 0, null), -1,
						Map.COMPUTER_PLAYER)) {
					if (platform.cardOnEnemyHand(new Card("Rock", null, 0, "",
							0, null)))
						card = platform.getCardFromEnemy("Rock");
				} else if (platform.cardOnEnemyHand(new Card("Mineralogy",
						null, 0, "", 0, null))
						&& platform.getCardFromEnemy("Rock").getEnergyCost() > platform
								.getRules().getEnemyEnergy()) {
					card = platform.getCardFromEnemy("Mineralogy");
				} else if (platform.cardOnEnemyHand(new Card("DR-002", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("DR-002");
				else if (platform.cardOnEnemyHand(new Card("DR-003", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("DR-003");
				else if (platform.cardOnEnemyHand(new Card(
						"Eletric Current level 1", null, 0, "", 0, null)))
					card = platform.getCardFromEnemy("Eletric Current level 1");
				else if (platform.cardOnEnemyHand(new Card("Hacking", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("Hacking");
			} else {
				tile = 35 - MathUtils.random(5);
				lane = MathUtils.random(3);
				if (platform.cardOnEnemyHand(new Card("DR-002", null, 0, "", 0,
						null)))
					card = platform.getCardFromEnemy("DR-002");
				else if (platform.cardOnEnemyHand(new Card("DR-003", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("DR-003");
				else if (platform.cardOnEnemyHand(new Card(
						"Eletric Current level 1", null, 0, "", 0, null)))
					card = platform.getCardFromEnemy("Eletric Current level 1");
				else if (platform.cardOnEnemyHand(new Card("Hacking", null, 0,
						"", 0, null)))
					card = platform.getCardFromEnemy("Hacking");
			}

			if (card != null
					&& platform.getRules().getEnemyEnergy() > card
							.getEnergyCost()) {
				platform.getRules().setEnemyEnergy(
						platform.getRules().getEnemyEnergy()
								- card.getEnergyCost());
				summonCard(tile, lane, card, platform, screen);
			} else {
				if (platform.getEnemyDeck().getCards().size > 0) {
					platform.getEnemyHandCards().add(
							platform.getEnemyDeck().draw());
				}
			}
			delay = 10;
		} else {
			delay = delay - delta;
		}
	}

}
