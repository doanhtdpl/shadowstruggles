package br.edu.ifsp.pds.shadowstruggles.rpg;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.C4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.U4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.V4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.X4;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y1;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y2;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y3;
import static com.badlogic.gdx.graphics.g2d.SpriteBatch.Y4;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.events.EventInGame;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.object2d.rpg.Character2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * This class is a more robust substitute for {@link OrthogonalTiledMapRenderer}
 * , including a sprite batch previously created (instead of having to
 * instantiate its own) and rendering the objects of the current layer
 * (including the player character) as well;
 */
public class RpgRenderer extends BatchTiledMapRenderer {
	private float[] vertices = new float[20];
	private ShapeRenderer debugRenderer;

	private ShadowStruggles game;
	private RpgMap rpgMap;
	private Array<Character2D> sprites;
	private Character2D playerCharacter;
	private Character playerCharacterModel;

	public RpgRenderer(RpgMap rpgMap, float unitScale,
			SpriteBatch spriteBatch, ShadowStruggles game) {
		this(rpgMap, unitScale, spriteBatch, game, null);
	}

	public RpgRenderer(RpgMap rpgMap, float unitScale,
			SpriteBatch spriteBatch, ShadowStruggles game,
			Character playerCharacterModel) {
		super(rpgMap.getMap(), unitScale, spriteBatch);

		this.rpgMap = rpgMap;
		this.game = game;
		this.playerCharacterModel = playerCharacterModel;
		this.sprites = new Array<Character2D>();

		if (game.getMode() == RunMode.DEBUG)
			debugRenderer = new ShapeRenderer();
	}

	/**
	 * Prepares the sprites so that they may be rendered.
	 */
	public void prepareCharacters() {
		if (playerCharacterModel != null) {
			playerCharacter = new Character2D("char", playerCharacterModel,
					game);
			playerCharacter.create();
		}

		Array<EventInGame> events = game.getProfile().getEvents(
				rpgMap.getMapName(), rpgMap.getObjectLayer());
		for (EventInGame event : events) {
			RpgMap eventMap = event.getCharacter().getCurrentMap();
			if (eventMap.getMap() == null
					|| !eventMap.getMapName().equals(rpgMap.getMapName())
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
		}
	}

	/**
	 * Returns the texture regions for the sprites to be shown on screen.
	 */
	public Array<Asset> textureRegionsToLoad() {
		Array<Asset> assets = new Array<Asset>();
		// Array for keeping track of sprites, making sure that there are no
		// duplicates.
		Array<String> previousSprites = new Array<String>();

		if (playerCharacterModel != null) {
			assets.add(new Asset("char.png", "sprites"));
			previousSprites.add("char");
		}

		for (Character2D char2d : sprites) {
			if (!previousSprites.contains(char2d.getName(), false)
					&& char2d.getName() != null) {
				assets.add(new Asset(char2d.getName() + ".png", "sprites"));
				previousSprites.add(char2d.getName());
			}
		}

		return assets;
	}

	public Character2D getPlayerCharacter() {
		return this.playerCharacter;
	}

	@Override
	public void render() {
		spriteBatch.begin();
		for (MapLayer layer : map.getLayers()) {
			if (layer.isVisible()) {
				if (layer instanceof TiledMapTileLayer) {
					renderTileLayer((TiledMapTileLayer) layer);
				}
			}
		}
		spriteBatch.end();
	}

	public void renderGameObjects() {
		spriteBatch.begin();
		for (Character2D char2d : sprites) {
			// Update visual direction, if necessary and possible.
			if (char2d.getCharModel().getDirection() != char2d.getDirection()
					&& char2d.getCharModel().getDirection() != null
					&& !char2d.isWalking()) {
				char2d.setDirection(char2d.getCharModel().getDirection());
			}

			// Render character.
			char2d.render();
			spriteBatch.draw(char2d.getCurrentFrame(), char2d.getX(),
					char2d.getY());
		}

		if (playerCharacterModel != null) {
			playerCharacter.render();
			spriteBatch.draw(playerCharacter.getCurrentFrame(),
					playerCharacter.getX(), playerCharacter.getY());
		}
		spriteBatch.end();
	}

	/**
	 * Renders debug lines for the objects' collision boxes.
	 */
	public void renderGameObjectsDebug() {
		debugRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.setColor(Color.RED);

		int tileSize = SettingsDAO.getSettings().rpgTileSize;

		for (Character2D char2d : sprites) {
			Character model = char2d.getCharModel();
			Rectangle rect = model.getMover().getRectangle();
			debugRenderer.rect(rect.x * tileSize, rect.y * tileSize, rect.width
					* tileSize, rect.height * tileSize);
		}

		if (playerCharacterModel != null) {
			Rectangle rect = playerCharacterModel.getMover().getRectangle();
			debugRenderer.rect(rect.x * tileSize, rect.y * tileSize, rect.width
					* tileSize, rect.height * tileSize);
		}

		debugRenderer.end();
	}

	/**
	 * Renders debug lines for the tiles' collision boxes.
	 */
	public void renderTilesDebug() {
		debugRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.setColor(Color.BLUE);
		for (MapLayer layer : map.getLayers()) {
			if (layer.isVisible()) {
				if (layer instanceof TiledMapTileLayer) {
					renderTileLayerDebug((TiledMapTileLayer) layer);
				}
			}
		}
		debugRenderer.end();
	}

	private void renderTileLayerDebug(TiledMapTileLayer layer) {
		float width = rpgMap.getWidthInTiles();
		float height = rpgMap.getHeightInTiles();
		int tileSize = SettingsDAO.getSettings().rpgTileSize;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				TiledMapTile tile = layer.getCell(i, j).getTile();
				if (tile.getProperties().containsKey(
						SettingsDAO.getSettings().collidableTile)) {
					Rectangle rect = new Rectangle(i * tileSize, j * tileSize,
							tileSize, tileSize);
					debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
				}
			}
		}
	}

	@Override
	public void renderObject(MapObject object) {
	}

	@Override
	public void renderTileLayer(TiledMapTileLayer layer) {
		final float color = Color.toFloatBits(1, 1, 1, layer.getOpacity());

		final int layerWidth = layer.getWidth();
		final int layerHeight = layer.getHeight();

		final float layerTileWidth = layer.getTileWidth() * unitScale;
		final float layerTileHeight = layer.getTileHeight() * unitScale;

		final int col1 = Math.max(0, (int) (viewBounds.x / layerTileWidth));
		final int col2 = Math.min(layerWidth, (int) ((viewBounds.x
				+ viewBounds.width + layerTileWidth) / layerTileWidth));

		final int row1 = Math.max(0, (int) (viewBounds.y / layerTileHeight));
		final int row2 = Math.min(layerHeight, (int) ((viewBounds.y
				+ viewBounds.height + layerTileHeight) / layerTileHeight));

		float y = row1 * layerTileHeight;
		float xStart = col1 * layerTileWidth;
		final float[] vertices = this.vertices;

		for (int row = row1; row < row2; row++) {
			float x = xStart;
			for (int col = col1; col < col2; col++) {
				final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
				if (cell == null) {
					x += layerTileWidth;
					continue;
				}
				final TiledMapTile tile = cell.getTile();
				if (tile != null) {
					if (tile instanceof AnimatedTiledMapTile)
						continue;

					final boolean flipX = cell.getFlipHorizontally();
					final boolean flipY = cell.getFlipVertically();
					final int rotations = cell.getRotation();

					TextureRegion region = tile.getTextureRegion();

					float x1 = x;
					float y1 = y;
					float x2 = x1 + region.getRegionWidth() * unitScale;
					float y2 = y1 + region.getRegionHeight() * unitScale;

					float u1 = region.getU();
					float v1 = region.getV2();
					float u2 = region.getU2();
					float v2 = region.getV();

					vertices[X1] = x1;
					vertices[Y1] = y1;
					vertices[C1] = color;
					vertices[U1] = u1;
					vertices[V1] = v1;

					vertices[X2] = x1;
					vertices[Y2] = y2;
					vertices[C2] = color;
					vertices[U2] = u1;
					vertices[V2] = v2;

					vertices[X3] = x2;
					vertices[Y3] = y2;
					vertices[C3] = color;
					vertices[U3] = u2;
					vertices[V3] = v2;

					vertices[X4] = x2;
					vertices[Y4] = y1;
					vertices[C4] = color;
					vertices[U4] = u2;
					vertices[V4] = v1;

					if (flipX) {
						float temp = vertices[U1];
						vertices[U1] = vertices[U3];
						vertices[U3] = temp;
						temp = vertices[U2];
						vertices[U2] = vertices[U4];
						vertices[U4] = temp;
					}
					if (flipY) {
						float temp = vertices[V1];
						vertices[V1] = vertices[V3];
						vertices[V3] = temp;
						temp = vertices[V2];
						vertices[V2] = vertices[V4];
						vertices[V4] = temp;
					}
					if (rotations != 0) {
						switch (rotations) {
						case Cell.ROTATE_90: {
							float tempV = vertices[V1];
							vertices[V1] = vertices[V2];
							vertices[V2] = vertices[V3];
							vertices[V3] = vertices[V4];
							vertices[V4] = tempV;

							float tempU = vertices[U1];
							vertices[U1] = vertices[U2];
							vertices[U2] = vertices[U3];
							vertices[U3] = vertices[U4];
							vertices[U4] = tempU;
							break;
						}
						case Cell.ROTATE_180: {
							float tempU = vertices[U1];
							vertices[U1] = vertices[U3];
							vertices[U3] = tempU;
							tempU = vertices[U2];
							vertices[U2] = vertices[U4];
							vertices[U4] = tempU;
							float tempV = vertices[V1];
							vertices[V1] = vertices[V3];
							vertices[V3] = tempV;
							tempV = vertices[V2];
							vertices[V2] = vertices[V4];
							vertices[V4] = tempV;
							break;
						}
						case Cell.ROTATE_270: {
							float tempV = vertices[V1];
							vertices[V1] = vertices[V4];
							vertices[V4] = vertices[V3];
							vertices[V3] = vertices[V2];
							vertices[V2] = tempV;

							float tempU = vertices[U1];
							vertices[U1] = vertices[U4];
							vertices[U4] = vertices[U3];
							vertices[U3] = vertices[U2];
							vertices[U2] = tempU;
							break;
						}
						}
					}
					spriteBatch.draw(region.getTexture(), vertices, 0, 20);
					x += layerTileWidth;
				}
			}
			y += layerTileHeight;
		}
	}
}
