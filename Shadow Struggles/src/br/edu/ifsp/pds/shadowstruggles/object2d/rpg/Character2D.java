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
	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;

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
	private Character charModel;
	private int size = 64;
	private int tileSize;
	private WalkDirection direction = WalkDirection.WALK_DOWN;
	private boolean walking;
	private float characterSpeed = 100.0f;
	private float initialPosition = 0;

	public Character2D(Character charModel, ShadowStruggles game) {
		super(game.getTextureRegion("char_down", "char"));
		this.charModel = charModel;
		this.game = game;
		this.setSize(size, size);
		this.setScale(1.0f, 1.0f);
		this.setPosition(0, 0);
		
		this.tileSize = SettingsDAO.getSettings().tileSize;
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

		// stateTime = 0;

		this.currentFrame = walkDownAnimation.getKeyFrame(0);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		// Atualizar frame da animação do personagem!
		stateTime += Gdx.graphics.getDeltaTime();
		float delta = Gdx.graphics.getDeltaTime();
		switch (direction) {
		case WALK_UP:
			if (walking) {
				// atualizar frame
				this.currentFrame = walkUpAnimation
						.getKeyFrame(stateTime, true);

				// continuar andando!
				walked += (delta * characterSpeed);
				// checar se já andou um tile!
				if (walked >= tileSize) {
					this.setY(initialPosition + tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setY(this.getY() + (delta * characterSpeed));
				}

			} else {
				this.currentFrame = walkUpAnimation.getKeyFrame(0, true);
			}
			break;
		case WALK_DOWN:
			if (walking) {
				// atualizar frame
				this.currentFrame = walkDownAnimation.getKeyFrame(stateTime,
						true);

				// continuar andando!
				walked += (delta * characterSpeed);
				// checar se já andou um tile!
				if (walked >= tileSize) {
					this.setY(initialPosition - tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setY(this.getY() - (delta * characterSpeed));
				}

			} else {
				this.currentFrame = walkDownAnimation.getKeyFrame(0, true);
			}
			break;
		case WALK_LEFT:
			if (walking) {
				// atualizar frame
				this.currentFrame = walkLeftAnimation.getKeyFrame(stateTime,
						true);

				// continuar andando!
				walked += (delta * characterSpeed);
				// checar se já andou um tile!
				if (walked >= tileSize) {
					this.setX(initialPosition - tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setX(this.getX() - (delta * characterSpeed));
				}

			} else {
				this.currentFrame = walkLeftAnimation.getKeyFrame(0, true);
			}
			break;
		case WALK_RIGHT:
			if (walking) {
				// atualizar frame
				this.currentFrame = walkRightAnimation.getKeyFrame(stateTime,
						true);

				// continuar andando!
				walked += (delta * characterSpeed);
				// checar se já andou um tile!
				if (walked >= tileSize) {
					this.setX(initialPosition + tileSize);
					walking = false;
					charModel.setReadyToWalk(true);
				} else {
					this.setX(this.getX() + (delta * characterSpeed));
				}

			} else {
				this.currentFrame = walkRightAnimation.getKeyFrame(0, true);
			}
			break;
		}

		// this.currentFrame = walkDownAnimation.getKeyFrame(0, true);

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
