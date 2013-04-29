package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/***
 * A visual representation of the player deck.
 */

public class Deck2D extends FixedObject implements InputProcessor {
	private static final float SCALE_X = 2;
	private static final float SCALE_Y = 2;
	private boolean ready;
	private boolean touched;
	private ShadowStruggles game;

	public Deck2D(ShadowStruggles game, int initialX) {
		super(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("deck"), initialX);

		this.setScaleX(SCALE_X);
		this.setScaleY(SCALE_Y);
		this.game = game;
		this.setReady(true);
	}

	public void startBlink() {
		this.addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.3f),
				Actions.fadeIn(0.3f))));
	}

	public void stopBlink() {
		this.clearActions();
		this.addAction(Actions.fadeIn(0.3f));
	}

	public void setReady(boolean ready) {
		this.ready = ready;

		if (ready) {
			this.setDrawable(new TextureRegionDrawable(game
					.getAssets()
					.get("data/images/objects/objects.atlas",
							TextureAtlas.class).findRegion("deck")));
			startBlink();
		}
		if (!ready) {
			this.setDrawable(new TextureRegionDrawable(game
					.getAssets()
					.get("data/images/objects/objects.atlas",
							TextureAtlas.class).findRegion("deckNotReady")));
		}
	}

	public boolean isReady() {
		return this.ready;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = (int) ((game.getController().getCurrentScreen()
				.getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game
				.getController().getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (game.getController().getCurrentScreen()
				.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (x + deltaCamX >= this.getX()
				&& x + deltaCamX <= this.getX() + this.getWidth()
						* this.getScaleX() && invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight() * this.getScaleY()) {
			touched = true;
			game.getAudio().playSound("button_4");
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = (int) ((game.getController().getCurrentScreen()
				.getHeight() - y) * (float) ((float) game.getController()
				.getCurrentScreen().getSettings().screenHeight / (float) game
				.getController().getCurrentScreen().getHeight()));
		x = (int) (x * (float) ((float) game.getController().getCurrentScreen()
				.getSettings().screenWidth / (float) game.getController()
				.getCurrentScreen().getWidth()));
		int deltaCamX = (int) (game.getController().getCurrentScreen()
				.getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);

		if (touched) {
			if (x + deltaCamX >= this.getX()
					&& x + deltaCamX <= this.getX() + this.getWidth()
							* this.getScaleX()
					&& invertY >= this.getY()
					&& invertY <= this.getY() + this.getHeight()
							* this.getScaleY()) {
				game.getController().deckClicked();
				return true;
			}
		}
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
