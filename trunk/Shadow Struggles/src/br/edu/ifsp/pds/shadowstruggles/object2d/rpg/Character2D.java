package br.edu.ifsp.pds.shadowstruggles.object2d.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

	public Character2D(RpgController rpgController, ShadowStruggles game) {
		super(game.getAssets()
				.get("data/images/char/char.atlas", TextureAtlas.class)
				.findRegion("char_down"));
		this.game = game;
		this.setSize(64, 64);
		this.setPosition(30, 30);
		this.setScale(1.0f, 1.0f);
	}

	/**
	 * Changes the character position on map.
	 * 
	 * @param direction
	 *            The direction the character will move on the map.
	 */
	public void move(WalkDirection direction) {

	}

	public float getWalked() {
		return walked;
	}

	public void setWalked(float walked) {
		this.walked = walked;
	}

	@Override
	public void create() {
		walkSheet_left = game.getAssets()
				.get("data/images/char/char.atlas", TextureAtlas.class)
				.findRegion("char_left");
		walkSheet_up = game.getAssets()
				.get("data/images/char/char.atlas", TextureAtlas.class)
				.findRegion("char_up");
		walkSheet_right = game.getAssets()
				.get("data/images/char/char.atlas", TextureAtlas.class)
				.findRegion("char_right");
		walkSheet_down = game.getAssets()
				.get("data/images/char/char.atlas", TextureAtlas.class)
				.findRegion("char_down");

		TextureRegion[][] aux_left = walkSheet_left.split(64, 64);
		TextureRegion[][] aux_up = walkSheet_up.split(64, 64);
		TextureRegion[][] aux_right = walkSheet_right.split(64, 64);
		TextureRegion[][] aux_down = walkSheet_down.split(64, 64);

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

		// De onde puxarei a posição X mesmo?

		this.setX(10);
		this.setY(10);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
