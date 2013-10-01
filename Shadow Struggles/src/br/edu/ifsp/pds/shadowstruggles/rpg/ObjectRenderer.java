package br.edu.ifsp.pds.shadowstruggles.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventInGame;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * Renders the objects of the map's current object layer according to the images
 * specified in the Event data files. It also renders the player character, if
 * it is present on the map.
 */
public class ObjectRenderer {
	private SpriteBatch batch;
	private RpgMap rpgMap;
	private Stage stage;
	private ShadowStruggles game;

	private Array<Character2D> sprites;
	private Character2D playerCharacter;
	private Character playerCharacterModel;

	public ObjectRenderer(SpriteBatch batch, RpgMap rpgMap, Stage stage,
			ShadowStruggles game) {
		this(batch, rpgMap, stage, game, null);
	}

	public ObjectRenderer(SpriteBatch batch, RpgMap rpgMap, Stage stage,
			ShadowStruggles game, Character playerCharacterModel) {
		this.batch = batch;
		this.rpgMap = rpgMap;
		this.stage = stage;
		this.game = game;

		this.sprites = new Array<Character2D>();
		this.playerCharacterModel = playerCharacterModel;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public RpgMap getRpgMap() {
		return rpgMap;
	}

	public void setRpgMap(RpgMap rpgMap) {
		this.rpgMap = rpgMap;
	}

	public void render() {
		batch.begin();
		for (Character2D char2d : sprites) {
			// Update visual direction, if necessary and possible.
			if (char2d.getCharModel().getDirection() != char2d.getDirection()
					&& char2d.getCharModel().getDirection() != null
					&& !char2d.isWalking()) {
				char2d.setDirection(char2d.getCharModel().getDirection());
			}

			// Render character.
			char2d.render();
			batch.draw(char2d.getCurrentFrame(), char2d.getX(), char2d.getY());
		}

		if (playerCharacterModel != null) {
			playerCharacter.render();
			batch.draw(playerCharacter.getCurrentFrame(),
					playerCharacter.getX(), playerCharacter.getY());
		}
		batch.end();
	}

	/**
	 * Prepares the sprites so that they may be rendered.
	 */
	public void prepareCharacters() {
		if (playerCharacterModel != null) {
			playerCharacter = new Character2D("char", playerCharacterModel,
					game);
			playerCharacter.create();
			stage.addActor(playerCharacter);
		}

		Array<EventInGame> events = game.getProfile().getEvents(
				rpgMap.getMapName(), rpgMap.getObjectLayer());
		for (EventInGame event : events) {
			RpgMap eventMap = event.getCharacter().getCurrentMap();
			if (!eventMap.getMapName().equals(rpgMap.getMapName())
					|| !eventMap.getObjectLayer().equals(
							rpgMap.getObjectLayer())) {
				// If the event character's RpgMap attribute doesn't match the
				// current map, update it.
				event.getCharacter().setCurrentMap(new RpgMap(rpgMap));
			}

			Character2D char2d = new Character2D(event.getSprite(),
					event.getCharacter(), game);
			char2d.create();
			sprites.add(char2d);
			stage.addActor(char2d);
		}
	}

	/**
	 * Returns the textures for the sprites to be shown on screen.
	 */
	public Array<Asset> texturesToLoad() {
		Array<Asset> assets = new Array<Asset>();
		// Array for keeping track of sprites, making sure that there are no
		// duplicates.
		Array<String> previousSprites = new Array<String>();

		if (playerCharacter != null) {
			assets.add(new Asset(playerCharacter.getName() + ".png", "sprites"));
			previousSprites.add(playerCharacter.getName());
		}

		for (Character2D char2d : sprites) {
			if (!previousSprites.contains(char2d.getName(), false)) {
				assets.add(new Asset(char2d.getName() + ".png", "sprites"));
				previousSprites.add(char2d.getName());
			}
		}

		return assets;
	}

	public Character2D getPlayerCharacter() {
		return this.playerCharacter;
	}
}
