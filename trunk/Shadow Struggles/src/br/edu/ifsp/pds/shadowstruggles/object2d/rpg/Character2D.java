package br.edu.ifsp.pds.shadowstruggles.object2d.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

/**
 * This is the visual representation of the RPG Character.
 */
public class Character2D extends Image{

	private Animation walkAnimation;
	private TextureRegion stoppedFrame;
	private TextureRegion walkSheet;
	private Array<TextureRegion> walkFrames;
	private float walked = 0;
	

	public Character2D(ShadowStruggles game) {
		super(game.getAssets().get("data/images/char/char.atlas", TextureAtlas.class).findRegion("char_down"));
		this.setSize(64, 64);
		this.setPosition(30, 30);		
		this.setScale(1.0f, 1.0f);		
	}
	
	/**
	 * Changes the character position on map. 
	 * @param direction
	 * 		The direction the character will move on the map.
	 */
	public void move(WalkDirection direction){
		
	}
	
	
	public float getWalked() {
		return walked;
	}

	public void setWalked(float walked) {
		this.walked = walked;
	}

}
