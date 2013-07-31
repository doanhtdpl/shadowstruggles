package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.ManagementStrategy;

public class LoaderTest {

	@Test
	public void testStaticTextureAtlasStrategy(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.STATIC_TEXTURE_ATLAS, null);
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
				ManagementStrategy.STATIC_TEXTURE_ATLAS, null);
		loader.loadAssets();

		try {
			TextureRegion region = loader.getTextureRegion("broomy",
					"card_attacking");
			if (region != null)
				System.out.println("testGetStaticRegion: Success");
			else
				System.out.println("testGetStaticRegion: Fail");
		} catch (Exception ex) {
			System.out.println("testGetStaticRegion: Fail");
			ex.printStackTrace();
		}
	}

	@Test
	public void testGetDynamicRegion(ShadowStruggles game) {
		// TODO: Implementar teste.
	}

	@Test
	public void testDynamicCreation() {
		// TODO: Implementar teste.
	}

	@Test
	public void testDynamicLoading() {
		// TODO: Implementar teste.
	}

	@Test
	public void testDynamicDispose() {
		// TODO: Implementar teste.
	}

}
