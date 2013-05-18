package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

public class BattleControl extends SceneItem {
	public BattlePlatform nextBattle;
	
	public BattleControl() {
		this.nextBattle = new BattlePlatform();
	}
	
	public BattleControl(BattlePlatform nextBattle) {
		this.nextBattle = nextBattle;
	}
}
