package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Settings;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/***
 * A representation of an fighter on the field.
 */
public class Fighter2D extends Image implements ApplicationListener {

	public static final float SCALE_X = 72f / 64f;
	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;

	private Settings settings;

	private Animation walkAnimation;
	private Animation attackAnimation;
	private TextureRegion currentFrame;
	private TextureRegion walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion attackSheet;
	private TextureRegion[] attackFrames;
	private boolean attacking = false;
	private ShadowStruggles game;
	private Fighter fighter;
	private float stateTime;

	public Fighter2D(Fighter fighter, ShadowStruggles game) {
		super(game.getAssets()
				.get("data/images/card_walking/card_walking.atlas", TextureAtlas.class)
				.findRegion(fighter.getName().toLowerCase()));
		this.fighter = fighter;

		this.game = game;
		this.settings = game.getManager().getSettings();

		if (fighter.getSize().equals(Fighter.SIZE_SMALL)) {
			this.setScaleY( 0.8f);
		} else if (fighter.getSize().equals(Fighter.SIZE_MEDIUM)) {
			this.setScaleY(1.0f);
		} else if (fighter.getSize().equals(Fighter.SIZE_LARGE)) {
			this.setScaleY(1.5f);
		}
	}

	@Override
	public void create() {
		walkSheet = game.getAssets()
				.get("data/images/card_walking/card_walking.atlas", TextureAtlas.class)
				.findRegion(fighter.getName().toLowerCase());

		TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth()
				/ FRAME_COLS, walkSheet.getRegionHeight() / FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (fighter.getDirection() == -1)
					tmp[i][j].flip(true, false);
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.075f, walkFrames);
		
		stateTime = 0f;
		
		attackSheet = game.getAssets()
				.get("data/images/card_attacking/card_attacking.atlas", TextureAtlas.class)
				.findRegion(fighter.getName().toLowerCase());
		tmp = attackSheet.split(attackSheet.getRegionWidth() / FRAME_COLS,
				attackSheet.getRegionHeight() / FRAME_ROWS);
		attackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		index = 0;
		
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (fighter.getDirection() == -1)
					tmp[i][j].flip(true, false);
				attackFrames[index++] = tmp[i][j];
			}
		}
		
		attackAnimation = new Animation(0.075f, attackFrames);
		this.setX(settings.tileHeight * 2 + (fighter.getTile())
				* settings.tileWidth);
		this.setY( settings.screenHeight - settings.backgroundHeight
				+ settings.tileHeight + (this.fighter.getLane())
				* settings.tileHeight * 3 / 2);
	}

	@Override
	public void resize(int width, int height) {
	}

	public void update(float delta) {
		fighter.action(game.getController().getPlatform(), this, delta);
	}

	public Controller getController() {
		return game.getController();
	}

	public void setController(Controller controller) {
		this.game.setController(controller);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Animation getWalkAnimation() {
		return walkAnimation;
	}

	public void setWalkAnimation(Animation walkAnimation) {
		this.walkAnimation = walkAnimation;
	}

	public Animation getAttackAnimation() {
		return attackAnimation;
	}

	public void setAttackAnimation(Animation attackAnimation) {
		this.attackAnimation = attackAnimation;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
		
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
		((TextureRegionDrawable) this.getDrawable()).setRegion(currentFrame);
	}

	public TextureRegion getWalkSheet() {
		return walkSheet;
	}

	public void setWalkSheet(TextureRegion walkSheet) {
		this.walkSheet = walkSheet;
	}

	public TextureRegion[] getWalkFrames() {
		return walkFrames;
	}

	public void setWalkFrames(TextureRegion[] walkFrames) {
		this.walkFrames = walkFrames;
	}

	public TextureRegion getAttackSheet() {
		return attackSheet;
	}

	public void setAttackSheet(TextureRegion attackSheet) {
		this.attackSheet = attackSheet;
	}

	public TextureRegion[] getAttackFrames() {
		return attackFrames;
	}

	public void setAttackFrames(TextureRegion[] attackFrames) {
		this.attackFrames = attackFrames;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	

	public static int getFrameCols() {
		return FRAME_COLS;
	}

	public static int getFrameRows() {
		return FRAME_ROWS;
	}

	@Override
	public void render() {
		this.getFighter().getAction().update(this);
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

}