package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class BattleControl extends SceneItem {
	public BattlePlatform nextBattle;

	public BattleControl() {
		this.nextBattle = null;
	}

	public BattleControl(BattlePlatform nextBattle) {
		this.nextBattle = nextBattle;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.nextBattle = json.readValue("nextBattle", BattlePlatform.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("nextBattle", this.nextBattle);
	}

	@Override
	public void action() {
		ShadowStruggles game = ShadowStruggles.getInstance();
		BattleScreen nextScreen = new BattleScreen(game, game.getProfile(),
				game.getController(), nextBattle, true);
		game.setScreenWithTransition(new LoadingScreen(game, nextScreen));
	}
}
