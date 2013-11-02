package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventInGame;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;

public class TeleportControl extends SceneItem {
	private int destinyX, destinyY;
	private String destinyMap;
	private String destinyLayer;
	/**
	 * If the target event is null, it moves the player instead.
	 */
	private Event event;

	public TeleportControl() {
		this.destinyX = 0;
		this.destinyY = 0;
		this.destinyMap = "";
		this.destinyLayer = "";
		this.event = null;
	}

	@Override
	public void action() {
		ShadowStruggles game = ShadowStruggles.getInstance();

		if (event != null) {
			EventInGame eventInGame = game.getProfile().getEvent(event.getId());
			eventInGame.setMap(destinyMap);
			eventInGame.setLayer(destinyLayer);
			eventInGame.getCharacter().setPosition(destinyX, destinyY);
		} else {
			Character playerChar = game.getProfile().getCharacter();
			RpgMap newMap = new RpgMap(destinyMap, destinyLayer,
					SettingsDAO.getSettings().defaultTileLayer);
			playerChar.setPosition(destinyX, destinyY);
			playerChar.setCurrentMap(newMap);
		}

		if (this.update()) {
			RpgScreen currentScreen = (RpgScreen) game.getScreen();
			Controller controller = currentScreen.getController();
			RpgController rpgController = currentScreen.getRpgController();

			RpgScreen nextScreen = new RpgScreen(game, controller,
					rpgController);
			game.setScreenWithTransition(new LoadingScreen(game, nextScreen));
		}
	}

	/**
	 * Returns whether or not it's necessary to update the screen after the
	 * WarpAction has been applied. The screen is updated if 1) The event was
	 * previously on the same map and layer as the player's character (even if
	 * it remains on the same layer/map after the action); 2) The event will now
	 * be on the same map and layer as the player's character; or 3) The
	 * WarpAction will affect the player's character.
	 */
	private boolean update() {
		if (event == null)
			return true;

		EventInGame eventInGame = ShadowStruggles.getInstance().getProfile()
				.getEvent(event.getId());
		Character playerChar = ShadowStruggles.getInstance().getProfile()
				.getCharacter();

		String playerLayer = playerChar.getCurrentMap().getObjectLayer();
		String playerMap = playerChar.getCurrentMap().getMapName();
		return ((playerMap.equals(eventInGame.getMap()) && playerLayer
				.equals(eventInGame.getLayer())) || (playerMap
				.equals(destinyMap) && playerLayer.equals(destinyLayer)));
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.destinyX = json.readValue("destinyX", Integer.class, jsonData);
		this.destinyY = json.readValue("destinyY", Integer.class, jsonData);
		this.destinyMap = json.readValue("destinyMap", String.class, jsonData);
		this.destinyLayer = json.readValue("destinyLayer", String.class,
				jsonData);
		this.event = json.readValue("event", Event.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("destinyX", this.destinyX);
		json.writeValue("destinyY", this.destinyY);
		json.writeValue("destinyMap", this.destinyMap);
		json.writeValue("destinyLayer", this.destinyLayer);
		json.writeValue("event", this.event);
	}
}
