package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FixedLabel extends Label implements InputProcessor{
	private int initialX;
	private boolean draggable;
	private boolean touched;
	private boolean justTouched;
	private BaseScreen screen;

	public FixedLabel(CharSequence text, int initialX, BaseScreen screen) {
		super(text, screen.getSkin());
		this.screen=screen;
		this.initialX=initialX;
	}
	
	public void move(Stage st, int cameraInitialX) {
		if (!draggable)
			this.setX(this.initialX + st.getCamera().position.x
					- cameraInitialX);
	}
	
	public void clicked() {
	};

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
		int deltaCamX = (int) (screen.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);
		int invertY = (int) ((screen.getHeight() - screenY) * (float) ((float) screen
				.getSettings().screenHeight / (float) screen.getHeight()));
		screenX = (int) (screenX * (float) ((float) screen.getSettings().screenWidth / (float) screen
				.getWidth()));

		if (screenX + deltaCamX >= this.getX()
				&& screenX + deltaCamX <= this.getX() + this.getWidth() * getScaleX()
				&& invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight() * getScaleY()) {
			touched = true;			
			if (!justTouched) {
				justTouched = true;
			}			
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int deltaCamX = (int) (screen.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);
		int invertY = (int) ((screen.getHeight() - screenY) * (float) ((float) screen.getSettings().screenHeight / (float) screen.getHeight()));
		screenX = (int) (screenX * (float) ((float) screen
				.getSettings().screenWidth / (float) screen.getWidth()));

		if (touched) {
			if (screenX + deltaCamX >= this.getX()
					&& screenX + deltaCamX <= this.getX() + this.getWidth()
							* getScaleX() && invertY >= this.getY()
					&& invertY <= this.getY() + this.getHeight() * getScaleY()) {
				clicked();
			}
			touched = false;
		}

		justTouched = false;

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
