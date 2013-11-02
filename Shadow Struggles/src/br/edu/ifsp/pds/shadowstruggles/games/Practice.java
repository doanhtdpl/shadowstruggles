package br.edu.ifsp.pds.shadowstruggles.games;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
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
				isInCampaign);
	}

	@Override
	public void initComponents() {
		if (!initialized) {
			super.initComponents();
			game.getAudio().stop();
			game.getAudio().setMusic("battle");
			initialized = true;
		}
	}

	public BaseScreen copy() {
		return new Practice(this.game, this.battlePlatform, this.isInCampaign);
	}

}
