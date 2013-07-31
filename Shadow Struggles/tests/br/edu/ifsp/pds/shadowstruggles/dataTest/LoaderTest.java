package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.ManagementStrategy;

public class LoaderTest {

	@Test
	public void testStaticTextureAtlasStrategy(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.STATIC_TEXTURE_ATLAS);
		loader.loadAssets();

		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		try {
			game.getAssets().get("data/images/cards/cards.atlas",
					TextureAtlas.class);
			System.out.println("testStaticTextureAtlasStrategy: Success");
		} catch (Exception e) {
			System.out.println("testStaticTextureAtlasStrategy: Fail");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetStaticRegion(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.STATIC_TEXTURE_ATLAS);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		try {
			TextureRegion region = loader.getTextureRegion("broomy",
					"card_attacking");
			if (region != null)
				System.out.println("testGetStaticRegion: Success");
			else
				System.out
						.println("testGetStaticRegion: Fail (region not found)");
		} catch (Exception ex) {
			System.out.println("testGetStaticRegion: Fail");
			ex.printStackTrace();
		}
	}

	@Test
	public void testDynamicLoading(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.DYNAMIC_TEXTURE_ATLAS);
		Array<Asset> textures = new Array<Asset>();
		textures.add(new Asset("dr-001.png", "cards"));

		loader.setAssetsToLoad(textures, null, null);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		if (Gdx.files.local("tmp").exists()) {
			try {
				game.getAssets().get("tmp/tmp.atlas");
				System.out.println("testDynamicLoading: Success");
			} catch (Exception ex) {
				System.out
						.println("testDynamicLoading: Fail (asset not loaded)");
				ex.printStackTrace();
			}
		} else {
			System.out
					.println("testDynamicLoading: Fail (temporary folder not created)");
		}
	}

	@Test
	public void testGetDynamicRegion(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.DYNAMIC_TEXTURE_ATLAS);
		Array<Asset> textures = new Array<Asset>();
		textures.add(new Asset("broomy.png", "card_attacking"));

		loader.setAssetsToLoad(textures, null, null);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		try {
			TextureRegion region = loader.getTextureRegion("broomy",
					"card_attacking");
			if (region != null)
				System.out.println("testGetDynamicRegion: Success");
			else
				System.out
						.println("testGetDynamicRegion: Fail (region not found)");
		} catch (Exception ex) {
			System.out.println("testGetDynamicRegion: Fail");
			ex.printStackTrace();
		}
	}

	@Test
	public void testDynamicDispose(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.DYNAMIC_TEXTURE_ATLAS);
		Array<Asset> textures = new Array<Asset>();
		textures.add(new Asset("dr-001.png", "cards"));

		loader.setAssetsToLoad(textures, null, null);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		loader.dispose();
		if (!Gdx.files.local("tmp").exists()) {
			System.out.println("testDynamicDispose: Success");
		} else {
			System.out.println("testDynamicDispose: Fail");
		}
	}

}
