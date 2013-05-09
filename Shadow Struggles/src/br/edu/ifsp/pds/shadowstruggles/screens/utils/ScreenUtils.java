package br.edu.ifsp.pds.shadowstruggles.screens.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Useful methods for building the screens.
 */
public class ScreenUtils {

	/**
	 * Builds a TextButton according to the specified parameters, encapsulating
	 * its initialization.
	 */
	public static TextButton defineButton(TextButton actor, float x, float y,
			float width, float height, Skin skin) {
		actor.setX(x);
		actor.setY(y);
		actor.setWidth(width);
		actor.setHeight(height);
		
		actor.getLabel().setStyle(new LabelStyle(skin.getFont("andalus-font"), Color.BLACK));
		
		return actor;
	}
	/**
	 * Builds a Label according to the specified parameters, encapsulating
	 * its initialization.
	 */
	public static Label defineLabel(Label actor, float x, float y,
			float width, float height){
		actor.setX(x);
		actor.setY(y);
		actor.setWidth(width);
		actor.setHeight(height);
		return actor;
	}
	
	public static Image defineImage(Image actor, float x, float y, float width, float height){
		actor.setX(x);
		actor.setY(y);
		actor.setWidth(width);
		actor.setHeight(height);
		return actor;
	}
	
	public static Image defineImage(Image actor, float x, float y, float width, float height, float scaleX, float scaleY){
		actor.setX(x);
		actor.setY(y);
		actor.setWidth(width);
		actor.setHeight(height);
		actor.setScale(scaleX, scaleY);
		return actor;
	}

}
