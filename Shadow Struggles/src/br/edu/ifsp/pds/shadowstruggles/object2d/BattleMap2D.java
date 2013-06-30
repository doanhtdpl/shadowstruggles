package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.data.Settings;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * A visual representation of the map.
 */

public class BattleMap2D extends Image implements InputProcessor {

	private Controller controller;
	private boolean touched;
	private boolean justTouched;
	private int touchX;

	public BattleMap2D(final Controller controller, TextureRegion tRegion) {
		super(tRegion);
		touched = false;
		justTouched = false;
		this.controller = controller;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) SettingsDAO
				.getSettings().mapHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (x >= 0 && x <= this.getWidth() && invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight()) {
			touched = true;

			if (!justTouched) {
				justTouched = true;
				touchX = x;
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) SettingsDAO
				.getSettings().mapHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (touched) {
			if (x >= 0 && x <= this.getWidth() && invertY >= this.getY()
					&& invertY <= this.getY() + this.getHeight()) {

				controller.mapClicked(x
						+ controller.getCurrentScreen().getCamera().position.x
						- 480, invertY - 160);
			}
			touched = false;
		} else

			controller.mapClicked(x
					+ controller.getCurrentScreen().getCamera().position.x
					- 480, invertY - 160);

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		int invertY = (int) ((controller.getCurrentScreen().getHeight() - y) * (float) ((float) SettingsDAO
				.getSettings().mapHeight / (float) controller
				.getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) controller
				.getCurrentScreen().getWidth()));
		if (touched) {
			if (x >= 0 && x <= this.getWidth() && invertY >= this.getY()
					&& invertY <= this.getY() + this.getHeight()) {

				Settings settings = SettingsDAO.getSettings();
				BaseScreen screen = controller.getCurrentScreen();
				if (screen.getCamera().position.x + touchX - x > BaseScreen.CAMERA_INITIAL_X
						&& screen.getCamera().position.x + touchX - x < settings.backgroundWidth
								- BaseScreen.CAMERA_INITIAL_X) {
					screen.getCamera().position.x += touchX - x;

					// Render the screen again to avoid blinking.
					moveFixedObjects();
					this.touchX = x;
				}
				if (screen.getCamera().position.x + touchX - x > settings.backgroundWidth
						- settings.mapWidth / 2) {
					screen.getCamera().position.x = settings.backgroundWidth
							- settings.mapWidth / 2;
				}
				if (screen.getCamera().position.x + touchX - x < BaseScreen.CAMERA_INITIAL_X) {
					screen.getCamera().position.x = BaseScreen.CAMERA_INITIAL_X;
				}
				return true;
			}
		}

		return false;
	}

	public void moveFixedObjects() {
		((BattleScreen) controller.getCurrentScreen()).moveFixedObjects();
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
