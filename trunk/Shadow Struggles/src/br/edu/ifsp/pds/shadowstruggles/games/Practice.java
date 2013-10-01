package br.edu.ifsp.pds.shadowstruggles.games;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

/**
 * A BattleScreen with a practice match.
 */

public class Practice extends BattleScreen {

	public Practice(ShadowStruggles game, BattlePlatform platform,
			boolean isInCampaign) {
		super(game, game.getProfile(), game.getController(), platform,
				MenuTextDAO.getMenuText().practiceBattle, isInCampaign);
	}

	@Override
	public void initComponents() {
		super.initComponents();
		game.getAudio().stop();
		game.getAudio().setMusic("battle");
	}

	public BaseScreen copy() {
		return new Practice(this.game, this.battlePlatform, this.isInCampaign);
	}

}
