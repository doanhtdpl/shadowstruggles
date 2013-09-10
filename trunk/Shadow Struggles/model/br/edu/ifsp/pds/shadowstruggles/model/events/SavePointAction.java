package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen.Mode;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Shows a save screen.
 */
public class SavePointAction extends EventAction {

	@Override
	public void act() {
		// TODO: Consertar (tela não está sendo exibida em automático, e trigger
		// por interact não localiza o evento).
		ShadowStruggles game = ShadowStruggles.getInstance();
		game.setScreenWithTransition(new SaveLoadScreen(game, game
				.getController(), Mode.SAVE, (BaseScreen) game.getScreen()));
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
	}

	@Override
	public void write(Json json) {
	}
}
