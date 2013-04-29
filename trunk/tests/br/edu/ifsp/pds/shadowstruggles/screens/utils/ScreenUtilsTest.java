package br.edu.ifsp.pds.shadowstruggles.screens.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ScreenUtilsTest {

	@Test
	public void defineButtonTest() {
		Skin skin = new Skin(Gdx.files.internal("../data/files/skin.json"),
				new TextureAtlas(
						Gdx.files.internal("data/images/controls/skin.atlas")));
		TextButton button = new TextButton("texto", skin);
		final float x = MathUtils.random(0, 100), y = MathUtils.random(0, 100), width = MathUtils
				.random(0, 100), height = MathUtils.random(0, 100);
		button = ScreenUtils.defineButton(button, x, y, width, height, skin);
		float[] expected = { x, y, width, height };
		float[] actuals = { button.getX(), button.getY(), button.getWidth(),
				button.getHeight() };
		assertEquals(expected, actuals);
	}

	@Test
	public void definelabelTest() {
		Skin skin = new Skin(Gdx.files.internal("data/files/skin.json"),
				new TextureAtlas(
						Gdx.files.internal("data/images/controls/skin.atlas")));
		Label label = new Label("texto", skin);
		final float x = MathUtils.random(0, 100), y = MathUtils.random(0, 100), width = MathUtils
				.random(0, 100), height = MathUtils.random(0, 100);
		label = ScreenUtils.defineLabel(label, x, y, width, height);
		float[] expected = { x, y, width, height };
		float[] actuals = { label.getX(), label.getY(), label.getWidth(),
				label.getHeight() };
		assertEquals(expected, actuals);
	}

}
