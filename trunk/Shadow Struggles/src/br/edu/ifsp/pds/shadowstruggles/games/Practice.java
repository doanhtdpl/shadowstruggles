package br.edu.ifsp.pds.shadowstruggles.games;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.PracticeEnemy;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

/**
 * A BattleScreen with a practice match.
 */

public class Practice extends BattleScreen {

	public Practice(ShadowStruggles game, BattlePlatform platform, boolean isInCampaign) {
		super(
				game,
				game.getProfile(),
				game.getController(),
				new BattlePlatform(DeckDAO.getDeck("Practice Deck Player"),
						DeckDAO.getDeck("Practice Deck Enemy"), new BattleMap(
								"cena1"), new DefaultRules(
								SettingsDAO.getSettings()), new PracticeEnemy()),
				MenuTextDAO.getMenuText().practiceBattle, isInCampaign);
		game.getAudio().stop();
		game.getAudio().setMusic("battle");
	}

	public BaseScreen copy() {
		return new Practice(this.game, this.battlePlatform, this.isInCampaign);
	}

}
