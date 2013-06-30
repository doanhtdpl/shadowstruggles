package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Settings;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/***
 * A representation of an effect on the field.
 */
public class Effect2D extends Image {

	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;

	private float stateTime;
	private Animation animation;
	private TextureRegion currentFrame;
	private TextureRegion animationSheet;
	private TextureRegion[] animationFrames;
	private ShadowStruggles game;
	private Effect effect;
	private Settings settings;

	public Effect2D(Effect effect, ShadowStruggles game) {
		super(game.getTextureRegion(effect.getName().toLowerCase(),
				"card_effects"));
		this.setSize(64, 64);
		this.effect = effect;
		this.game = game;
		this.settings = game.getManager().getSettings();
	}

	public void create() {
		animationSheet = game.getTextureRegion(effect.getName().toLowerCase(),
				"card_effects");

		TextureRegion[][] tmp = animationSheet.split(64, 64);
		animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (effect.getDirection() == -1)
					tmp[i][j].flip(true, false);
				animationFrames[index] = tmp[i][j];

				index++;
			}
		}

		stateTime = 0f;
		this.setX(settings.tileHeight * 2 + (effect.getTile())
				* settings.tileWidth);
		this.setY(settings.screenHeight - settings.backgroundHeight
				+ settings.tileHeight + (this.effect.getLane())
				* settings.tileHeight * 3 / 2);
		animation = new Animation(0.075f, animationFrames);
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
		((TextureRegionDrawable) this.getDrawable()).setRegion(currentFrame);

	}

	public TextureRegion getAnimationSheet() {
		return animationSheet;
	}

	public void setAnimationSheet(TextureRegion animationSheet) {
		this.animationSheet = animationSheet;
	}

	public TextureRegion[] getAnimationFrames() {
		return animationFrames;
	}

	public void setAnimationFrames(TextureRegion[] animationFrames) {
		this.animationFrames = animationFrames;
	}

	public Controller getController() {
		return game.getController();
	}

	public void setController(Controller controller) {
		this.game.setController(controller);
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public void render() {
		this.getEffect().getAction().update(this);
	}

	public void update(float delta) {
		effect.action(game.getController().getPlatform(), this, delta);
	}
}
