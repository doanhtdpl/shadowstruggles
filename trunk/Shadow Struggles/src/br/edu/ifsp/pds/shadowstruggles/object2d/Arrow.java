package br.edu.ifsp.pds.shadowstruggles.object2d;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * An Arrow object is an arrow pointing either to the right or left.
 */

public class Arrow extends FixedObject implements InputProcessor {

	public Arrow(int side, Skin skin) {
		super(skin.getRegion("right"), 480 + 440 * side);
		if (side != 1) {
			TextureRegion copy = new TextureRegion();
			copy.setRegion(skin.getRegion("right"));
			copy.flip(true, false);
			((TextureRegionDrawable) this.getDrawable()).setRegion(copy);
		}

		this.setY(340);
		this.setWidth(32);
		this.setHeight(32);

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
