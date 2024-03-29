package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Settings;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter.FighterSize;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

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
	private Array<TextureRegion> walkFrames;
	private TextureRegion attackSheet;
	private Array<TextureRegion> attackFrames;
	private boolean attacking = false;
	private ShadowStruggles game;
	private Fighter fighter;
	private float stateTime;

	public Fighter2D(Fighter fighter, ShadowStruggles game) {
		super(game.getTextureRegion(fighter.getName().toLowerCase(),
				"card_walking"));
		this.setSize(64, 64);
		this.fighter = fighter;

		this.game = game;
		this.settings = SettingsDAO.getSettings();

		if (fighter.getSize() == FighterSize.SMALL) {
			this.setScaleY(0.8f);
		} else if (fighter.getSize() == FighterSize.MEDIUM) {
			this.setScaleY(1.0f);
		} else if (fighter.getSize() == FighterSize.LARGE) {
			this.setScaleY(1.5f);
		}
	}

	@Override
	public void create() {
		walkSheet = game.getTextureRegion(fighter.getName().toLowerCase(),
				"card_walking");

		TextureRegion[][] tmp = walkSheet.split(64, 64);
		walkFrames = new Array<TextureRegion>();
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (fighter.getDirection() == -1)
					tmp[i][j].flip(true, false);
				walkFrames.add(tmp[i][j]);
			}
		}
		walkAnimation = new Animation(0.075f, walkFrames);

		stateTime = 0f;

		attackSheet = game.getTextureRegion(fighter.getName().toLowerCase(),
				"card_attacking");
		tmp = attackSheet.split(64, 64);
		attackFrames = new Array<TextureRegion>();

		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (fighter.getDirection() == -1)
					tmp[i][j].flip(true, false);
				attackFrames.add(tmp[i][j]);
			}
		}

		attackAnimation = new Animation(0.075f, attackFrames);
		this.setX(settings.tileHeight * 2 + (fighter.getTile())
				* settings.tileWidth);
		this.setY(settings.mapHeight - settings.backgroundHeight
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

	public Array<TextureRegion> getWalkFrames() {
		return walkFrames;
	}

	public void setWalkFrames(Array<TextureRegion> walkFrames) {
		this.walkFrames = walkFrames;
	}

	public TextureRegion getAttackSheet() {
		return attackSheet;
	}

	public void setAttackSheet(TextureRegion attackSheet) {
		this.attackSheet = attackSheet;
	}

	public Array<TextureRegion> getAttackFrames() {
		return attackFrames;
	}

	public void setAttackFrames(Array<TextureRegion> attackFrames) {
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