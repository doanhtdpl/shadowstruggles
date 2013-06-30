package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/***
 * Specifies how the cards look and behave on the player's hand.
 */

public class HandCard extends FixedObject implements InputProcessor {

	private static final float SCALE_X = 0.4f;
	private static final float SCALE_Y = 0.3f;

	private int type = 1;
	private boolean isSelected = false;
	private int side = 1;
	private String name;
	private Card card;
	private ShadowStruggles game;
	private boolean touched;
	private boolean justTouched;
	private boolean dragging;
	private float clickedX;
	private float clickedY;
	private TextureRegion illustration;

	public HandCard(ShadowStruggles game, String name, int initialX, Card card) {
		super(game.getTextureRegion(name.toLowerCase(), "cards"), initialX,
				true);
		this.setSize(360, 480);

		this.illustration = game.getTextureRegion(name.toLowerCase(), "cards");
		((TextureRegionDrawable) super.getDrawable()).setRegion(illustration);

		this.card = card;
		card.setImage(this);
		this.setScaleX(SCALE_X);
		this.setScaleY(SCALE_Y);
		this.name = name;
		this.game = game;
		this.touched = false;
		this.justTouched = false;
		this.dragging = false;
	}

	// ----------------------------------------CLASS
	// METHODS--------------------------------------------------------------

	public void move(Stage st, int cameraInitialX) {
		if (!dragging)
			this.setX(this.getInitialX() + st.getCamera().position.x
					- cameraInitialX);
	}

	public void startBlink() {
		this.addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.3f),
				Actions.fadeIn(0.3f))));
	}

	public void resetBlink() {
		this.clearActions();
		this.addAction(Actions.fadeIn(0.3f));
	}

	public void resetPosition() {
		this.setX(getInitialX()
				+ (int) (game.getController().getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X));
		this.setY(SettingsDAO.getSettings().bottomElementY);
		this.setScaleX(SCALE_X);
		this.setScaleY(SCALE_Y);
	}

	// ----------------------------------------GETTERS/SETTERS-------------------------------------------------------------

	public int getType() {
		return type;
	}

	public int getSide() {
		return side;
	}

	public String getName() {
		return name;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isDragging() {
		return this.dragging;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	// ---------------------------------------EVENT METHODS
	// ------------------------------------------------------------

	private int getInvertY(int y) {
		return (int) ((game.getController().getCurrentScreen().getHeight() - y) * (float) ((float) SettingsDAO
				.getSettings().mapHeight / (float) game.getController()
				.getCurrentScreen().getHeight()));
	}

	private int calcX(int x) {
		return (int) (x * (float) ((float) SettingsDAO.getSettings().mapWidth / (float) game
				.getController().getCurrentScreen().getWidth()));
	}

	private int getDeltaCamX() {
		return (int) (game.getController().getCurrentScreen().getCamera().position.x - BaseScreen.CAMERA_INITIAL_X);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int invertY = getInvertY(y);
		x = calcX(x);
		if (x + getDeltaCamX() >= this.getX()
				&& x + getDeltaCamX() <= this.getX() + this.getWidth()
						* this.getScaleX() && invertY >= this.getY()
				&& invertY <= this.getY() + this.getHeight() * this.getScaleY()) {
			touched = true;

			if (!justTouched) {
				justTouched = true;
				clickedX = x;
				clickedY = y;
			}

			game.getAudio().playSound("button_8");
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int invertY = getInvertY(y);
		x = calcX(x);

		if (touched) {
			if (!dragging) {
				if (x + getDeltaCamX() >= this.getX()
						&& x + getDeltaCamX() <= this.getX() + this.getWidth()
								* this.getScaleX()
						&& invertY >= this.getY()
						&& invertY <= this.getY() + this.getHeight()
								* this.getScaleY()) {
					if (!isSelected)
						isSelected = true;
					else
						isSelected = false;

					if (isSelected) {

						clicked();
						startBlink();
					} else {
						clicked();
					}

				}
			} else {
				unSelect();
			}

			touched = false;
			dragging = false;
		}

		justTouched = false;

		return false;
	}

	public void clicked() {
		game.getController().handCardClicked(getCard(), isSelected);
	}

	public void unSelect() {
		resetPosition();
		game.getController().getPlatform().setSelectedCard(null);
		((BattleScreen) (game.getController().getCurrentScreen()))
				.changeHexagram(false);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		int invertY = getInvertY(y);
		x = calcX(x);

		if (justTouched) {
			game.getController().handCardClicked(this.getCard(), true);
		}

		if (touched
				&& Math.sqrt(Math.pow(x - clickedX, 2)
						+ Math.pow(y - clickedY, 2)) > 50) {
			if (!dragging) {
				this.dragging = true;
				this.setScaleX((float) (this.getScaleX() / 2));
				this.setScaleY((float) (this.getScaleY() / 2));
				clickedX = -100;
				clickedY = -100;
			}
			this.setX(x - this.getWidth() * this.getScaleX() / 2
					+ getDeltaCamX());
			this.setY(invertY - this.getHeight() * this.getScaleY() / 2);

			return true;
		}
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
