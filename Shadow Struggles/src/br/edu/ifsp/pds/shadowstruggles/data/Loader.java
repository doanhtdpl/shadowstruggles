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

/**
 * Class for dealing with general resources management.
 */

public class Loader {

	public static class Asset {
		public String assetName;
		public String assetType;

		public Asset(String assetName, String assetType) {
			this.assetName = assetName;
			this.assetType = assetType;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Asset))
				return false;
			Asset objAsset = (Asset) obj;
			return objAsset.assetName.equals(assetName)
					&& objAsset.assetType.equals(assetType);
		}
	}

	private ShadowStruggles game;
	private Array<Asset> textures;
	private Array<Asset> textureRegions;
	private Array<Asset> sounds;
	private Array<Asset> rpgMaps;

	public Loader(ShadowStruggles game) {
		this.game = game;

		this.game.getAssets().setLoader(TiledMap.class,
				new TmxMapLoader(new InternalFileHandleResolver()));
	}

	/**
	 * Sets the assets to load next. It must precede any call to loadAssets().
	 * 
	 * @param textureRegions
	 *            The TextureRegion objects to be loaded, which must be properly
	 *            mapped in the {@link FileMap} class (resourcesToDirectory) and
	 *            the names must contain the extension. It may be null.
	 * @param textures
	 *            The Texture objects to be loaded, which must be properly
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
			Array<Asset> textures, Array<Asset> sounds, Array<Asset> rpgMaps) {
		this.textureRegions = textureRegions;
		this.textures = textures;
		this.sounds = sounds;
		this.rpgMaps = rpgMaps;
	}

	/**
	 * Starts loading the assets. {@link Loader#setAssetsToLoad()} must be
	 * called first.
	 */
	public void loadAssets() {
		createTextureAtlas();

		loadTextureAtlas();
		loadTextures();
		loadSounds();
		loadMaps();
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

		if (this.textureRegions != null
				&& FileMap.resourcesToDirectory.containsKey(resourceType)) {
			TextureAtlas atlas = game.getAssets().get("tmp/tmp.atlas",
					TextureAtlas.class);
			region = atlas.findRegion(resourceType + "/" + regionName);
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

		if (this.textures != null
				&& FileMap.resourcesToDirectory.containsKey(resourceType)) {
			texture = game.getAssets().get(
					FileMap.resourcesToDirectory.get(resourceType)
							+ textureName + ".png", Texture.class);
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

		if (this.rpgMaps != null
				&& FileMap.resourcesToDirectory.containsKey(resourceType)) {
			map = game.getAssets().get(
					FileMap.resourcesToDirectory.get(resourceType) + mapName
							+ ".tmx", TiledMap.class);
		}

		return map;
	}

	// Disposal methods.

	public void dispose() {
		disposeAtlas();
		disposeTextures();
		disposeMaps();
		disposeSounds();
	}

	/**
	 * Unloads the Atlas with the texture regions from memory and deletes the
	 * temporary directory.
	 */
	public void disposeAtlas() {
		if (this.textureRegions != null
				&& game.getAssets().isLoaded("tmp/tmp.atlas",
						TextureAtlas.class)) {
			game.getAssets().unload("tmp/tmp.atlas");
			Gdx.files.local("tmp").deleteDirectory();
			this.textureRegions = null;
		}
	}

	/**
	 * Unloads the textures from memory.
	 */
	public void disposeTextures() {
		if (this.textures != null) {
			for (Asset asset : this.textures) {
				game.getAssets().unload(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName);
			}

			this.textures = null;
		}
	}

	/**
	 * Unloads the maps from memory.
	 */
	public void disposeMaps() {
		if (this.rpgMaps != null) {
			for (Asset asset : this.rpgMaps) {
				game.getAssets().unload(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName);
			}

			this.rpgMaps = null;
		}
	}

	/**
	 * Unloads the sounds from memory.
	 */
	public void disposeSounds() {
		if (this.sounds != null) {
			for (Asset asset : this.sounds) {
				game.getAssets().unload(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName);
			}

			this.sounds = null;
		}
	}

	/**
	 * Unloads the specified sounds from memory.
	 */
	public void unloadSounds(Array<Asset> soundsToLoad) {
		if (soundsToLoad != null) {
			for (Asset asset : soundsToLoad) {
				game.getAssets().unload(
						FileMap.resourcesToDirectory.get(asset.assetType)
								+ asset.assetName);
				this.sounds.removeValue(asset, false);
			}
		}
	}

	// Loading and other initialization methods.

	private void loadTextureAtlas() {
		if (this.textureRegions != null
				&& !game.getAssets().isLoaded("tmp/tmp.atlas",
						TextureAtlas.class))
			game.getAssets().load("tmp/tmp.atlas", TextureAtlas.class);
	}

	private void loadTextures() {
		if (this.textures != null) {
			for (Asset asset : this.textures) {
				String fileName = FileMap.resourcesToDirectory
						.get(asset.assetType) + asset.assetName;
				if (!game.getAssets().isLoaded(fileName, Texture.class))
					game.getAssets().load(fileName, Texture.class);
			}
		}
	}

	private void loadSounds() {
		if (this.sounds != null) {
			for (Asset asset : this.sounds) {
				Class<?> c = Sound.class;
				if (asset.assetType.equals("soundtrack"))
					c = Music.class;

				String fileName = FileMap.resourcesToDirectory
						.get(asset.assetType) + asset.assetName;
				if (!game.getAssets().isLoaded(fileName, c))
					game.getAssets().load(fileName, c);
			}
		}
	}

	private void loadMaps() {
		if (this.rpgMaps != null) {
			for (Asset asset : this.rpgMaps) {
				String fileName = FileMap.resourcesToDirectory
						.get(asset.assetType) + asset.assetName;
				if (!game.getAssets().isLoaded(fileName, TiledMap.class))
					game.getAssets().load(fileName, TiledMap.class);
			}
		}
	}

	/**
	 * Dynamically creates an atlas for the textureRegions which will be used.
	 */
	private void createTextureAtlas() {
		if (this.textureRegions != null) {
			if (Gdx.files.local("tmp").exists()) {
				if (Gdx.files.local("tmp/tmp.atlas").exists()
						&& game.getAssets().isLoaded("tmp/tmp.atlas"))
					game.getAssets().unload("tmp/tmp.atlas");
				Gdx.files.local("tmp").deleteDirectory();
			}

			// First, copy assets to tmp folder; put them in their
			// sub-directories according to their types to make searching
			// easier.
			for (Asset asset : this.textureRegions) {
				FileHandle originFile = Gdx.files
						.internal(FileMap.resourcesToDirectory
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
