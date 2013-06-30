package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;

/***
 * The pause button.
 */

public class MenuButton extends FixedObject implements InputProcessor {

	private Controller controller;
	private boolean touched;
	private boolean justTouched;
	private ShadowStruggles game;

	public MenuButton(Controller c, final ShadowStruggles game) {
		super(game.getTextureRegion("pause", "game_ui_images"), 10);
		this.setY(560);
		this.game = game;
		this.controller = c;

	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int deltaCamX = (int) (controller.getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (x + deltaCamX >= this.getX()
				&& x + deltaCamX <= this.getX() + this.getWidth() * getScaleX()
				&& invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight() * getScaleY()) {
			touched = true;
			game.getAudio().playSound("button_4");
			if (!justTouched) {
				justTouched = true;
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int deltaCamX = (int) (controller.getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) controller
				.getCurrentScreen().getSettings().screenHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (touched) {
			if (x + deltaCamX >= this.getX()
					&& x + deltaCamX <= this.getX() + this.getWidth()
							* getScaleX() && invertY >= this.getY()
					&& invertY <= this.getY() + this.getHeight() * getScaleY()) {
				controller.menuButtonClicked(this.game);
			}
			touched = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {

		return false;
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
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
