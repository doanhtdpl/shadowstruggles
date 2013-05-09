package br.edu.ifsp.pds.shadowstruggles.screens.utils;

import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Matrix4;

// Based on: http://blog.gemserk.com/2012/03/05/implementing-transitions-between-screens/.

public class FadeInTransitionEffect extends TransitionEffect {
	private static ImmediateModeRenderer renderer;
	private Color color;

	public FadeInTransitionEffect(float duration) {
		super(duration);
		this.color = new Color();
		this.alpha = 1;
	}

	public void update(float delta) {
		alpha -= (float) ((float) delta / (float) duration);
		this.duration -= delta;
		if (duration <= 0)
			finished = true;

	}

	@Override
	public void render(BaseScreen current, BaseScreen next, float delta) {
		next.render(delta);
		color.set(0f, 0f, 0f, getAlpha());
		// draw a quad over the screen using the color
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		fillRectangle(0, 0, current.getWidth(), current.getHeight(), new Color(
				0, 0, 0, getAlpha()));
		Gdx.gl10.glDisable(GL10.GL_BLEND);
	}

	public void fillRectangle(float x0, float y0, float x1, float y1,
			Color color) {
		ImmediateModeRenderer renderer = getRenderer();
		Matrix4 projectionMatrix = new Matrix4();
		projectionMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		renderer.begin(projectionMatrix, GL10.GL_TRIANGLES);
		{
			// first triangle
			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x0, y0, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x0, y1, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x1, y1, 0f);

			// second triangle
			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x1, y1, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x1, y0, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x0, y0, 0f);
		}
		renderer.end();
	}

	private static ImmediateModeRenderer getRenderer() {
		if (renderer == null)
			renderer = Gdx.graphics.isGL20Available() ? new ImmediateModeRenderer20(
					false, true, 0) : new ImmediateModeRenderer10();
		return renderer;
	}

}
