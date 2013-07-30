package br.edu.ifsp.pds.shadowstruggles.object2d.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

/**
 * This is the visual representation of the RPG Character.
 */
public class Character2D extends Image implements ApplicationListener {
	private static int FRAME_COLS = 3;
	private static int FRAME_ROWS = 4;
	private static float FRAME_DURATION = 0.11f;
	private static float CHARACTER_SPEED = 100.0f;

	private Character charModel;
	private TextureRegion currentFrame;
	private WalkDirection direction = WalkDirection.WALK_UP;
	private ShadowStruggles game;

	private Animation walkLeftAnimation;
	private Animation walkUpAnimation;
	private Animation walkRightAnimation;
	private Animation walkDownAnimation;

	private float walked = 0;
	private float stateTime;
	private float initialPosition = 0;
	private boolean walking;

	public Character2D(Character charModel, ShadowStruggles game) {
		super(game.getTextureRegion("char", "sprites"));
		this.charModel = charModel;
		this.game = game;

		int tileSize = SettingsDAO.getSettings().tileSize;

		this.setSize(charModel.getWidthInTiles() * tileSize,
				charModel.getHeightInTiles() * tileSize);
		this.setScale(1.0f, 1.0f);
		this.setPosition(charModel.getTileX() * tileSize, (charModel
				.getCurrentMap().getHeightInTiles() - charModel.getTileY() - 1)
				* tileSize);
	}

	/**
	 * Changes the character position on map.
	 * 
	 * @param direction
	 *            The direction the character will move on the map.
	 */
	public void move(WalkDirection direction) {
		if (!walking) {
			this.direction = direction;
			this.walking = true;
			this.walked = 0;

			// The model can't move during the walking animation.
			charModel.setReadyToWalk(false);

			switch (direction) {
			case WALK_UP:
				initialPosition = this.getY();
				this.setY(this.getY() + 1);
				break;
			case WALK_DOWN:
				initialPosition = this.getY();
				this.setY(this.getY() - 1);
				break;
			case WALK_LEFT:
				initialPosition = this.getX();
				this.setX(this.getX() - 1);
				break;
			case WALK_RIGHT:
				initialPosition = this.getX();
				this.setX(this.getX() + 1);
				break;
			}
		}
	}

	public float getWalked() {
		return walked;
	}

	public void setWalked(float walked) {
		this.walked = walked;
	}

	@Override
	public void create() {
		TextureRegion walksheet = game.getTextureRegion("char", "sprites");

		TextureRegion[][] aux = walksheet.split((int) getWidth(),
				(int) getHeight());
		TextureRegion[] aux_down = aux[0];
		TextureRegion[] aux_left = aux[1];
		TextureRegion[] aux_right = aux[2];
		TextureRegion[] aux_up = aux[3];

		Array<TextureRegion> walkDownFrames = new Array<TextureRegion>();
		Array<TextureRegion> walkLeftFrames = new Array<TextureRegion>();
		Array<TextureRegion> walkRightFrames = new Array<TextureRegion>();
		Array<TextureRegion> walkUpFrames = new Array<TextureRegion>();

		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 0)
					walkDownFrames.add(aux_down[j]);
				if (i == 1)
					walkLeftFrames.add(aux_left[j]);
				if (i == 2)
					walkRightFrames.add(aux_right[j]);
				if (i == 3)
					walkUpFrames.add(aux_up[j]);
			}
		}

		walkDownAnimation = new Animation(FRAME_DURATION, walkDownFrames);
		walkLeftAnimation = new Animation(FRAME_DURATION, walkLeftFrames);
		walkRightAnimation = new Animation(FRAME_DURATION, walkRightFrames);
		walkUpAnimation = new Animation(FRAME_DURATION, walkUpFrames);

		this.currentFrame = walkUpAnimation.getKeyFrame(FRAME_DURATION);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		float delta = Gdx.graphics.getDeltaTime();
		int tileSize = SettingsDAO.getSettings().tileSize;
		
		switch (direction) {
		case WALK_UP:
			if (walking) {
				// Update frame
				this.currentFrame = walkUpAnimation
						.getKeyFrame(stateTime, true);

				// Keep walking!
				walked += (delta * CHARACTER_SPEED);
				// Check if already moved past one tile!
				if (walked >= tileSize) {
					this.setY(initialPosition + tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setY(this.getY() + (delta * CHARACTER_SPEED));
				}

			} else {
				this.currentFrame = walkUpAnimation.getKeyFrame(FRAME_DURATION,
						true);
			}
			break;
		case WALK_DOWN:
			if (walking) {
				// Update frame
				this.currentFrame = walkDownAnimation.getKeyFrame(stateTime,
						true);

				// Keep walking!
				walked += (delta * CHARACTER_SPEED);
				// Check if already moved past one tile!
				if (walked >= tileSize) {
					this.setY(initialPosition - tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setY(this.getY() - (delta * CHARACTER_SPEED));
				}

			} else {
				this.currentFrame = walkDownAnimation.getKeyFrame(
						FRAME_DURATION, true);
			}
			break;
		case WALK_LEFT:
			if (walking) {
				// Update frame
				this.currentFrame = walkLeftAnimation.getKeyFrame(stateTime,
						true);

				// Keep walking!
				walked += (delta * CHARACTER_SPEED);
				// Check if already moved past one tile!
				if (walked >= tileSize) {
					this.setX(initialPosition - tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setX(this.getX() - (delta * CHARACTER_SPEED));
				}

			} else {
				this.currentFrame = walkLeftAnimation.getKeyFrame(
						FRAME_DURATION, true);
			}
			break;
		case WALK_RIGHT:
			if (walking) {
				// Update frame
				this.currentFrame = walkRightAnimation.getKeyFrame(stateTime,
						true);

				// Keep walking!
				walked += (delta * CHARACTER_SPEED);
				// Check if already moved past one tile!
				if (walked >= tileSize) {
					this.setX(initialPosition + tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setX(this.getX() + (delta * CHARACTER_SPEED));
				}

			} else {
				this.currentFrame = walkRightAnimation.getKeyFrame(
						FRAME_DURATION, true);
			}
			break;
		}

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

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public boolean isWalking() {
		return this.walking;
	}

	public void setDirection(WalkDirection direction) {
		this.direction = direction;
	}

	public WalkDirection getDirection() {
		return this.direction;
	}
}
