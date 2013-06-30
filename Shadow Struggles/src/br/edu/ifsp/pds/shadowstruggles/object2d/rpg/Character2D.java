package br.edu.ifsp.pds.shadowstruggles.object2d.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

/**
 * This is the visual representation of the RPG Character.
 */
public class Character2D extends Image implements ApplicationListener {
	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;

	private TextureRegion stoppedFrame;

	private Animation walkLeftAnimation;
	private Animation walkUpAnimation;
	private Animation walkRightAnimation;
	private Animation walkDownAnimation;

	private Array<TextureRegion> walkLeftFrames;
	private Array<TextureRegion> walkUpFrames;
	private Array<TextureRegion> walkRightFrames;
	private Array<TextureRegion> walkDownFrames;

	private TextureRegion walkSheet_left;
	private TextureRegion walkSheet_right;
	private TextureRegion walkSheet_up;
	private TextureRegion walkSheet_down;

	private float walked = 0;
	private float stateTime;
	private ShadowStruggles game;
	private TextureRegion currentFrame;
	private RpgController rpgController;
	private int size = 64;

	public Character2D(RpgController rpgController, ShadowStruggles game) {
		super(game.getTextureRegion("char_down", "char"));
		this.game = game;
		this.setSize(size, size);
		this.setScale(1.0f, 1.0f);
		this.rpgController = rpgController;
		this.setPosition(0, 0);
	}

	/**
	 * Changes the character position on map.
	 * 
	 * @param direction
	 *            The direction the character will move on the map.
	 */
	public void move(WalkDirection direction) {
		rpgController.moveCharacter(direction);
	}

	public float getWalked() {
		return walked;
	}

	public void setWalked(float walked) {
		this.walked = walked;
	}

	@Override
	public void create() {
		walkSheet_left = game.getTextureRegion("char_left", "char");
		walkSheet_up = game.getTextureRegion("char_up", "char");
		walkSheet_right = game.getTextureRegion("char_right", "char");
		walkSheet_down = game.getTextureRegion("char_down", "char");

		TextureRegion[][] aux_left = walkSheet_left.split(size, size);
		TextureRegion[][] aux_up = walkSheet_up.split(size, size);
		TextureRegion[][] aux_right = walkSheet_right.split(size, size);
		TextureRegion[][] aux_down = walkSheet_down.split(size, size);

		walkLeftFrames = new Array<TextureRegion>();
		walkUpFrames = new Array<TextureRegion>();
		walkRightFrames = new Array<TextureRegion>();
		walkDownFrames = new Array<TextureRegion>();

		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkLeftFrames.add(aux_left[i][j]);
				walkUpFrames.add(aux_up[i][j]);
				walkRightFrames.add(aux_right[i][j]);
				walkDownFrames.add(aux_down[i][j]);
			}
		}

		walkLeftAnimation = new Animation(0.033f, walkLeftFrames);
		walkUpAnimation = new Animation(0.033f, walkUpFrames);
		walkRightAnimation = new Animation(0.033f, walkRightFrames);
		walkDownAnimation = new Animation(0.033f, walkDownFrames);

		stateTime = 0;
		
		this.currentFrame = walkDownAnimation.getKeyFrame(0);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {

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
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
		
	}

}
