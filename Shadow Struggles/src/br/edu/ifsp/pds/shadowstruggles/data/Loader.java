package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.CheckCardsScreen;
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

	public static class Asset {
		public String assetName;
		public String assetType;

		public Asset(String assetName, String assetType) {
			this.assetName = assetName;
			this.assetType = assetType;
		}
	}

	public static enum ManagementStrategy {
		STATIC_TEXTURE_ATLAS, DYNAMIC_TEXTURE_ATLAS
	};

	private ShadowStruggles game;
	private ManagementStrategy strategy;
	private Array<Asset> textures;
	private Array<Asset> textureRegions;
	private Array<Asset> sounds;
	private Array<Asset> rpgMaps;

	public Loader(ShadowStruggles game, ManagementStrategy strategy) {
		this.game = game;
		this.strategy = strategy;

		this.game.getAssets().setLoader(TiledMap.class,
				new TmxMapLoader(new InternalFileHandleResolver()));
	}

	public ManagementStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Sets the assets to load next. This method is only used by the dynamic
	 * strategy, and it (or the individual set methods) must precede any call to
	 * loadAssets().
	 * 
	 * @param textureRegions
	 *            The TextureRegion objects to be loaded, which must be properly
	 *            mapped in the {@link FileMap} class (resourcesToDirectory) and
	 *            the names must contain the extension. It may be null.
	 * @param sounds
	 *            The Sound and Music objects to be loaded, which must be
	 *            properly mapped in the {@link FileMap} class
	 *            (resourcesToDirectory) and the names must contain the
	 *            extension. It may be null.
	 * @param rpgMaps
	 *            The TiledMap objects to be loaded, which must be properly
	 *            mapped in the {@link FileMap} class (resourcesToDirectory) and
	 *            the names must contain the extension. It may be null.
	 */
	public void setAssetsToLoad(Array<Asset> textureRegions,
			Array<Asset> sounds, Array<Asset> rpgMaps) {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			this.textureRegions = textureRegions;
			this.sounds = sounds;
			this.rpgMaps = rpgMaps;
		}
	}

	/**
	 * Sets the TextureRegion objects to load next (available on dynamic
	 * strategy).
	 * 
	 * @param textureRegions
	 *            The TextureRegion objects to be loaded, which must be properly
	 *            mapped in the {@link FileMap} class (resourcesToDirectory) and
	 *            the names must contain the extension. It may be null.
	 */
	public void setTextureRegions(Array<Asset> textureRegions) {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			this.textureRegions = textureRegions;
		}
	}

	/**
	 * Sets the Texture objects to load next (available on dynamic strategy).
	 * 
	 * @param textures
	 *            The Texture objects to be loaded, which must be properly
	 *            mapped in the {@link FileMap} class (resourcesToDirectory) and
	 *            the names must contain the extension. It may be null.
	 */
	public void setTextures(Array<Asset> textures) {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			this.textures = textures;
		}
	}

	/**
	 * Loads new sounds in a dynamic context, without unloading or reloading the
	 * currently loaded ones.
	 * 
	 * @param sounds
	 *            The Sound and Music objects to be loaded, which must be
	 *            properly mapped in the {@link FileMap} class
	 *            (resourcesToDirectory) and the names must contain the
	 *            extension. It may be null.
	 */
	public void addSounds(Array<Asset> sounds) {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.sounds == null)
				this.sounds = new Array<Asset>();

			for (Asset asset : sounds) {
				this.sounds.addAll(sounds);
				Class<?> c = Sound.class;
				if (asset.assetType.equals("soundtrack"))
					c = Music.class;

				game.getAssets().load(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName, c);
			}
		}
	}

	/**
	 * Starts loading the assets. If using the dynamic strategy,
	 * {@link Loader#setAssetsToLoad()} must be called first.
	 */
	public void loadAssets() {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS)
			createTextureAtlas();

		loadTextureAtlas();
		loadTextures();
		loadSound();
		loadMaps();
	}

	/**
	 * Starts loading the Texture and TextureRegion objects; it's only
	 * applicable to dynamic contexts.
	 */
	public void loadGraphics() {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			createTextureAtlas();
			loadTextureAtlas();
			loadTextures();
		}

	}

	/**
	 * Optional initialization method which forces the instantiation of screens
	 * so that the screen transitions are smoother and less memory is consumed
	 * upon going from one screen to another.
	 */
	public void instantiateScreens() {
		CheckCardsScreen.getInstance(game, game.getController(), null);
		DefeatScreen.getInstance(game, game.getController(), "", null);
		EditDeckScreen.getInstance(game, game.getController(), null);
		FreePlayScreen.getInstance(game, game.getController());
		MainScreen.getInstance(game, game.getController());
		InGameMenu.getInstance(game, game.getController(), null);
		SaveLoadScreen.getInstance(game, game.getController(), "", false);
		StartScreen.getInstance(game, game.getController());
	}

	// Retrieval methods.

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
		}

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.textureRegions != null
					&& FileMap.resourcesToDirectory.containsKey(resourceType)) {
				TextureAtlas atlas = game.getAssets().get("tmp/tmp.atlas",
						TextureAtlas.class);
				region = atlas.findRegion(resourceType + "/" + regionName);
			}
		}

		return region;
	}

	/**
	 * Retrieves a Texture from the file system.
	 * 
	 * @param textureName
	 *            The texture's short name (without extensions)
	 * @param resourceType
	 *            The resource type. These names are specified in the
	 *            {@link FileMap} class.
	 */
	public Texture getTexture(String textureName, String resourceType) {
		Texture texture = null;

		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
				texture = game.getAssets().get(
						FileMap.resourcesToDirectory.get(resourceType)
								+ textureName + ".png", Texture.class);
			}
		}

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.textures != null
					&& FileMap.resourcesToDirectory.containsKey(resourceType)) {
				texture = game.getAssets().get(
						FileMap.resourcesToDirectory.get(resourceType)
								+ textureName + ".png", Texture.class);
			}
		}

		return texture;
	}

	/**
	 * Retrieves a Tiled map (.tmx) from the system.
	 * 
	 * @param mapName
	 *            The map's short name (without extensions)
	 * @param resourceType
	 *            The resource type. These names are specified in the
	 *            {@link FileMap} class.
	 */
	public TiledMap getTiledMap(String mapName, String resourceType) {
		TiledMap map = null;

		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			if (FileMap.resourcesToDirectory.containsKey(resourceType)) {
				map = game.getAssets().get(
						FileMap.resourcesToDirectory.get(resourceType)
								+ mapName + ".tmx", TiledMap.class);
			}
		}

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.rpgMaps != null
					&& FileMap.resourcesToDirectory.containsKey(resourceType)) {
				map = game.getAssets().get(
						FileMap.resourcesToDirectory.get(resourceType)
								+ mapName + ".tmx", TiledMap.class);
			}
		}

		return map;
	}

	// Disposal methods.

	public void dispose() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS)
			game.getAssets().dispose();

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			disposeTextures();
			disposeMaps();
			disposeSounds();
		}
	}

	/**
	 * Unloads the textureRegions from memory and deletes the temporary
	 * directory. It's only usable in the dynamic strategy.
	 */
	public void disposeTextures() {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS
				&& this.textureRegions != null) {
			game.getAssets().unload("tmp/tmp.atlas");
			Gdx.files.local("tmp").deleteDirectory();
			this.textureRegions = null;
		}
	}

	/**
	 * Unloads the maps from memory. It's only usable in the dynamic strategy.
	 */
	public void disposeMaps() {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS
				&& this.rpgMaps != null) {
			for (Asset asset : this.rpgMaps) {
				game.getAssets().unload(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName);
			}

			this.rpgMaps = null;
		}
	}

	/**
	 * Unloads the sounds from memory. It's only usable in the dynamic strategy.
	 */
	public void disposeSounds() {
		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS
				&& this.sounds != null) {
			for (Asset asset : this.sounds) {
				game.getAssets().unload(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName);
			}

			this.sounds = null;
		}
	}

	// Loading and other initialization methods.

	private void loadTextureAtlas() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			game.getAssets().load(
					"data/images/game_ui_images/game_ui_images.atlas",
					TextureAtlas.class);
			game.getAssets().load("data/images/sprites/sprites.atlas",
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
		}

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.textureRegions != null)
				game.getAssets().load("tmp/tmp.atlas", TextureAtlas.class);
		}
	}

	private void loadTextures() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			game.getAssets().load("data/images/battle_maps/cena1.png",
					Texture.class);
		}

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.textures != null) {
				for (Asset asset : this.textures) {
					game.getAssets().load(
							FileMap.resourcesToDirectory.get(asset.assetType)
									+ asset.assetName, Texture.class);
				}
			}
		}
	}

	private void loadSound() {
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

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.sounds != null) {
				for (Asset asset : this.sounds) {
					Class<?> c = Sound.class;
					if (asset.assetType.equals("soundtrack"))
						c = Music.class;

					game.getAssets().load(
							FileMap.resourcesToDirectory.get(asset.assetType)
									+ asset.assetName, c);
				}
			}
		}
	}

	private void loadMaps() {
		if (this.strategy == ManagementStrategy.STATIC_TEXTURE_ATLAS) {
			game.getAssets().load("data/rpg_maps/map.tmx", TiledMap.class);
		}

		if (this.strategy == ManagementStrategy.DYNAMIC_TEXTURE_ATLAS) {
			if (this.rpgMaps != null) {
				for (Asset asset : this.rpgMaps) {
					game.getAssets().load(
							FileMap.resourcesToDirectory.get(asset.assetType)
									+ asset.assetName, TiledMap.class);
				}
			}
		}
	}

	/**
	 * Dynamically creates an atlas for the textureRegions which will be used.
	 */

	private void createTextureAtlas() {
		if (this.textureRegions != null) {
			if (Gdx.files.local("tmp").exists()) {
				if (Gdx.files.local("tmp/tmp.atlas").exists())
					game.getAssets().unload("tmp/tmp.atlas");
				Gdx.files.local("tmp").deleteDirectory();
			}

			// First, copy assets to tmp folder; put them in their
			// sub-directories according to their types to make searching
			// easier.
			for (Asset asset : this.textureRegions) {
				FileHandle originFile = Gdx.files
						.local(FileMap.resourcesToDirectory
								.get(asset.assetType) + asset.assetName);
				FileHandle destiny = Gdx.files.local("tmp/" + asset.assetType);
				destiny.mkdirs();
				originFile.copyTo(destiny);
			}

			// Next, pack them all into a single TextureAtlas.
			TexturePacker2.process("tmp", "tmp", "tmp");
		}
	}
}
