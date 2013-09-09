package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Shows a save screen.
 */
public class SavePointAction extends EventAction {

	@Override
	public void act() {
		ShadowStruggles.getInstance().setScreenWithTransition(
				SaveLoadScreen.getInstance());
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
	}

	@Override
	public void write(Json json) {
	}
}
