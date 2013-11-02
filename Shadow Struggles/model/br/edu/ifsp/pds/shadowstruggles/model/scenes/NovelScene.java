package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.NovelScreen;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class NovelScene extends SceneItem {
	private String text;
	private String background;

	public NovelScene() {
		this.text = "";
		this.background = "";
	}

	@Override
	public void action() {
		ShadowStruggles game = ShadowStruggles.getInstance();
		NovelScreen nextScreen = new NovelScreen(game, this);
		game.setScreenWithTransition(new LoadingScreen(game, nextScreen));
	}

	public String getText() {
		return text;
	}

	public String getBackground() {
		return background;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.text = json.readValue("text", String.class, jsonData);
		this.background = json.readValue("background", String.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("text", this.text);
		json.writeValue("background", this.background);
	}
}
