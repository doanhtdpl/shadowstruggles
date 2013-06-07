package br.edu.ifsp.pds.shadowstruggles.object2d.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * This is the visual representation of the RPG Character.
 * 
 */
public class Character2D extends Image implements ApplicationListener {

	private Animation walkAnimation;
	private TextureRegion stoppedFrame;
	private TextureRegion walkSheet;
	private Array<TextureRegion> walkFrames;
	private ShadowStruggles game;
	private float walked = 0;
	

	public float getWalked() {
		return walked;
	}

	public void setWalked(float walked) {
		this.walked = walked;
	}

	public Character2D(String characterName, ShadowStruggles game) {
		super(game.getAssets().get("data/images/char/char.atlas", TextureAtlas.class).findRegion("char_down"));
		this.setSize(64, 64);
		this.game = game;
		this.setPosition(30, 30);
		
		this.setScale(1.0f, 1.0f);

	}
	
	public void move(){
		
	}

	@Override
	public void create() {
		
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

}
