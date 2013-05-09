package br.edu.ifsp.pds.shadowstruggles.object2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * Abstract class inherited by all fixed objects on the screen, that is, the
 * objects which remain on the same horizontal position relative to the camera.
 * However, they may be, optionally, draggable by the player.
 */

public abstract class FixedObject extends Image {
	private int initialX;
	private boolean draggable;

	/**
	 * Standard constructor. It assumes that the object will never be moved,
	 * even with user interaction.
	 * 
	 * @param initialX
	 */
	public FixedObject(int initialX) {
		this(initialX, false);
	}

	/**
	 * Alternative constructor for objects which the player may interact with.
	 * 
	 * @param draggable
	 *            Indicates whether the object is draggable or not.
	 */
	public FixedObject(int initialX, boolean draggable) {
		this.initialX = initialX;
		this.draggable = draggable;
	}

	/**
	 * Standard constructor with TextureRegion.
	 */
	public FixedObject(TextureRegion textureRegion, int initialX) {
		this(textureRegion, initialX, false);
	}

	/**
	 * Alternative constructor with TextureRegion.
	 */
	public FixedObject(TextureRegion textureRegion, int initialX,
			boolean draggable) {
		super(textureRegion);
		this.initialX = initialX;
		this.setX(initialX);
		this.draggable = draggable;
	}

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
			this.setX( this.initialX + st.getCamera().position.x - cameraInitialX);
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}
}
