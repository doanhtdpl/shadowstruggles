package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SceneScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * A TransitionControl object is an arrow pointing either to the right or left,
 * moving the camera to the specified direction in a {@link BattleScreen}. It
 * has been replaced by the A-D and arrow keys and scrolling on the
 * {@link BattleMap2D} due to usability decisions and currently is being used for
 * advancing the story in a {@link SceneScreen}.
 */

public class TransitionControl extends FixedObject implements InputProcessor {

	public TransitionControl(int side, Skin skin) {
		super(skin.getRegion("right"), 480 + 440 * side);
//		if (side != 1)
//			((TextureRegionDrawable) this.getDrawable()).getRegion().flip(true,
//					false);
		
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
