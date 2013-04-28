package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.ManagementStrategy;

public class LoaderTest {

	@Test
	public void testStaticTextureAtlasStrategy(ShadowStruggles game) {
		Loader loader = new Loader(game,
				ManagementStrategy.STATIC_TEXTURE_ATLAS, null, null);
		loader.loadAssets();

		while (!game.getAssets().update()) {
			System.out.println("Progress: " + game.getAssets().getProgress()
					* 100 + "%");
		}

		try {
			game.getAssets().get("data/images/objects/objects.atlas",
					TextureAtlas.class);
			System.out.println("testStaticTextureAtlasStrategy: Success");
		} catch (Exception e) {
			System.out
					.println("testStaticTextureAtlasStrategy: Fail (asset not loaded)");
			e.printStackTrace();
		}
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
