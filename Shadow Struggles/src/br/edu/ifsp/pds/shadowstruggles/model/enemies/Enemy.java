package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.Trap;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * A representation of an enemy.
 */

public abstract class Enemy {

	protected float delay;

	public Enemy() {
		this.delay = 5;
	}

	public abstract void action(BattlePlatform platform, BattleScreen screen,
			float delta);

	/***
	 * Summons a card in the enemy field
	 */

	protected void summonCard(int tile, int lane, Card card,
			BattlePlatform platform, BattleScreen screen) {
		platform.getEnemyHandCards().removeValue(card, true);
		card.setLane(lane);
		card.setTile(tile);
		card.setDirection(-card.getDirection());
		Image cardImage = new Image();
		if (card.getClass().equals(Fighter.class)) {
			Fighter2D f2d = new Fighter2D((Fighter) card, screen.getGame());
			f2d.create();
			card.setMarkLane(lane);
			cardImage = f2d;
		} else if (card.getClass().equals(Effect.class)) {
			Effect2D e2d = new Effect2D((Effect) card, screen.getGame());
			e2d.create();
			cardImage = e2d;
		} else if (card.getClass().equals(Trap.class)) {
			Trap2D t2d = new Trap2D((Trap) card, screen.getGame());
			t2d.create();
			cardImage = t2d;
		}
		card.setImage(cardImage);
		platform.getMap().addCard(card, tile, lane);
		screen.addGameObject(cardImage);
	}
}
