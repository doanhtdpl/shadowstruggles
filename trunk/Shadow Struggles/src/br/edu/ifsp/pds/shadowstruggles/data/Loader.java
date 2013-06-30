package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.CheckCardsScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SettingsScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.DefeatScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.EditDeckScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.FreePlayScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.InGameMenu;
import br.edu.ifsp.pds.shadowstruggles.screens.MainScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.StartScreen;

/**
 * Class for dealing with general memory management. It allows the use of
 * different management strategies for experimenting and flexibility.
 */

public class Loader {

	// TODO: Implementar estratégia DYNAMIC_TEXTURE_ATLAS
	public static enum ManagementStrategy {
		STATIC_TEXTURE_ATLAS, DYNAMIC_TEXTURE_ATLAS
	};

	private ShadowStruggles game;
	private ManagementStrategy strategy;
	private Array<String> assetsPaths;

	/**
	 * Constructor for the Loader class.
	 * 
	 * @param assetsPaths
	 *            The locations of the texture files. Can be null or empty if
	 *            the STATIC_TEXTURE_ATLAS strategy is being employed.
	 */

	public Loader(ShadowStruggles game, ManagementStrategy strategy,
			Array<String> assetsPaths) {
		this.game = game;
		this.strategy = strategy;
		this.assetsPaths = assetsPaths;
	}

	public ManagementStrategy getStrategy() {
		return this.strategy;
	}

	public synchronized void loadAssets() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			loadTextureAtlas();
			loadSound();
		}
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			createTextureAtlas();
			loadTextureAtlas();
			loadSound();
		}
	}

	/**
	 * Optional initialization method which forces the instantiation of screens
	 * so that the screen transitions are smoother and less memory is consumed
	 * upon going from one screen to another.
	 */
	public void instantiateScreens() {
		CheckCardsScreen.getInstance(game, game.getController(), null);
		SettingsScreen.getInstance(game, game.getController(), null);
		DefeatScreen.getInstance(game, game.getController(), "", null);
		EditDeckScreen.getInstance(game, game.getController(), null);
		FreePlayScreen.getInstance(game, game.getController());
		MainScreen.getInstance(game, game.getController());
		InGameMenu.getInstance(game, game.getController(), null);
		SaveLoadScreen.getInstance(game, game.getController(), "", false);
		StartScreen.getInstance(game, game.getController());
	}

	public void dispose() {
		game.getAssets().dispose();

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			//
		}
	}

	/**
	 * Retrieves a TextureRegion from the file system.
	 * 
	 * @param regionName
	 *            The name of the region.
	 * @param resourceType
	 *            The resource type (card, skin, map etc.). These names are
	 *            specified in the {@link FileMap} class.
	 */
	public TextureRegion getTextureRegion(String regionName, String resourceType) {
		TextureRegion region = null;

		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
				String path = FileMap.resourcesToDirectory.get(resourceType)
						+ resourceType + ".atlas";
				TextureAtlas atlas = game.getAssets().get(path,
						TextureAtlas.class);
				region = atlas.findRegion(regionName);
			}
		} else if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			//
		}

		return region;
	}

	private synchronized void loadTextureAtlas() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			game.getAssets().load("data/images/game_ui_images/game_ui_images.atlas",
					TextureAtlas.class);
			game.getAssets().load("data/images/char/char.atlas",
					TextureAtlas.class);
			game.getAssets().load("data/images/cards/cards.atlas",
					TextureAtlas.class);
			game.getAssets().load(
					"data/images/card_attacking/card_attacking.atlas",
					TextureAtlas.class);
			game.getAssets().load(
					"data/images/card_walking/card_walking.atlas",
					TextureAtlas.class);
			game.getAssets().load(
					"data/images/card_effects/card_effects.atlas",
					TextureAtlas.class);
			game.getAssets().load("data/images/game_ui_images/energy100.png",
					Texture.class);
			// game.getAssets().load("data/images/card_images/card_images.atlas",
			// TextureAtlas.class);
			game.getAssets().setLoader(TiledMap.class,
					new TmxMapLoader(new InternalFileHandleResolver()));
			game.getAssets().load("data/rpg_maps/map.tmx",
					TiledMap.class);
		}
	}

	private synchronized void loadSound() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			game.getAssets().load("data/audio/sound_effects/button_1.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_2.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_3.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_4.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_5.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_6.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_7.ogg",
					Sound.class);
			game.getAssets().load("data/audio/sound_effects/button_8.ogg",
					Sound.class);
			game.getAssets().load("data/audio/soundtrack/battle.ogg",
					Music.class);
			game.getAssets().load("data/audio/soundtrack/intro.ogg",
					Music.class);
		}
	}

	/**
	 * Dynamically creates an atlas for the textures which will be used. This
	 * method is not synchronized because the atlas must be created before the
	 * assets can be loaded; both cannot be done at the same time.
	 */

	private void createTextureAtlas() {
		//
	}
}
