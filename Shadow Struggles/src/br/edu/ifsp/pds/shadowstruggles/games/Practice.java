package br.edu.ifsp.pds.shadowstruggles.games;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.Map;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.PracticeEnemy;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

/**
 * A BattleScreen with a practice match.
 */

public class Practice extends BattleScreen {

	public Practice(ShadowStruggles game, boolean isInCampaign) {
		super(game, game.getProfile(), game.getController(),
				new BattlePlatform(DeckDAO.getDeck("Practice Deck Player",
						game.getManager()), DeckDAO.getDeck(
						"Practice Deck Enemy", game.getManager()), new Map(
						"cena1"), new DefaultRules(game.getManager()
						.getSettings()), new PracticeEnemy()), game
						.getManager().getMenuText().practiceBattle,
				isInCampaign);
		game.getAudio().stop();
		game.getAudio().setMusic("battle");
	}

	public BaseScreen copy() {
		return new Practice(this.game, this.isInCampaign);
	}

}
