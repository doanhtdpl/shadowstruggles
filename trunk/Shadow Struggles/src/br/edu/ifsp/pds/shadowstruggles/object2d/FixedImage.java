package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class FixedImage extends Image implements InputProcessor {
	private int initialX;
	private boolean draggable;
	private boolean touched;
	private boolean justTouched;
	private BaseScreen screen;

	public FixedImage(TextureRegion textureRegion, int initialX,
			BaseScreen screen) {
		super(textureRegion);
		this.initialX = initialX;
		this.screen = screen;
	}

/*	*//**
	 * Standard constructor. It assumes that the object will never be moved,
	 * even with user interaction.
	 * 
	 * @param initialX
	 *//*
	public FixedImage(int initialX) {
		this(initialX, false);
	}

	*//**
	 * Alternative constructor for objects which the player may interact with.
	 * 
	 * @param draggable
	 *            Indicates whether the object is draggable or not.
	 *//*
	public FixedImage(int initialX, boolean draggable) {
		this.initialX = initialX;
		this.draggable = draggable;
	}

	*//**
	 * Standard constructor with TextureRegion.
	 *//*
	public FixedImage(TextureRegion textureRegion, int initialX) {
		this(textureRegion, initialX, false);
	}

	*//**
	 * Alternative constructor with TextureRegion.
	 *//*
	public FixedImage(TextureRegion textureRegion, int initialX,
			boolean draggable) {
		super(textureRegion);
		this.initialX = initialX;
		this.setX(initialX);
		this.draggable = draggable;
	}*/

	/***
	 * This should be called whenever the camera is updated. The object's
	 * position is adjusted accordingly, unless the object is draggable.
	 * 
	 * @param st
	 *            The stage which contains the camera.
	 * @param cameraInitialX
	 *            The initial position of the camera on the screen.
	 */

	public void move(Stage st, int cameraInitialX) {
		if (!draggable)
			this.setX(this.initialX + st.getCamera().position.x
					- cameraInitialX);
	}

	public void clicked() {
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
	
	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}
}
