package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;

public class LoaderTest {
	@Test
	public void testLoading(ShadowStruggles game) {
		Loader loader = new Loader(game);
		Array<Asset> regions = new Array<Asset>();
		regions.add(new Asset("dr-001.png", "cards"));

		loader.setAssetsToLoad(regions, null, null, null);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		if (Gdx.files.local("tmp").exists()) {
			try {
				game.getAssets().get("tmp/tmp.atlas");
				System.out.println("testLoading: Success");
			} catch (Exception ex) {
				System.out.println("testLoading: Fail (asset not loaded)");
				ex.printStackTrace();
			}
		} else {
			System.out
					.println("testLoading: Fail (temporary folder not created)");
		}
	}

	@Test
	public void testGetRegion(ShadowStruggles game) {
		Loader loader = new Loader(game);
		Array<Asset> regions = new Array<Asset>();
		regions.add(new Asset("broomy.png", "card_attacking"));

		loader.setAssetsToLoad(regions, null, null, null);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		try {
			TextureRegion region = loader.getTextureRegion("broomy",
					"card_attacking");
			if (region != null)
				System.out.println("testGetRegion: Success");
			else
				System.out.println("testGetRegion: Fail (region not found)");
		} catch (Exception ex) {
			System.out.println("testGetRegion: Fail");
			ex.printStackTrace();
		}
	}

	@Test
	public void testDispose(ShadowStruggles game) {
		Loader loader = new Loader(game);
		Array<Asset> regions = new Array<Asset>();
		regions.add(new Asset("dr-001.png", "cards"));

		loader.setAssetsToLoad(regions, null, null, null);
		loader.loadAssets();
		System.out.println("Loading assets...");
		while (!game.getAssets().update()) {
		}

		loader.dispose();
		if (!Gdx.files.local("tmp").exists()) {
			System.out.println("testDispose: Success");
		} else {
			System.out.println("testDispose: Fail");
		}
	}

}
