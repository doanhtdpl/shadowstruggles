package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.MyStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


/***
 * A visual representation of the remaining time of the current match.
 */

public class Timer2D extends Label {

	private float time;
	private int initialX;

	public Timer2D(BaseScreen screen, int initialX) {
		super("",screen.getSkin());

		this.initialX = initialX;
		this.setX(initialX);

		BitmapFont font = screen.getFont();
		font.scale(1.0f);
		this.setStyle(new LabelStyle(font, Color.WHITE));
	}

	/**
	 * Updates the timer text.
	 * 
	 * @param time
	 *            The time elapsed since the beginning of the match, in seconds.
	 */
	public void setTime(float time) {
		this.time = time;

		int mins = (int) (time / 60);
		int secs = (int) (time % 60);

		String timeText = "";
		timeText += mins;
		timeText += ':';

		if (secs < 10)
			timeText += "0";

		timeText += secs;

		this.setText(timeText);
	}

	public float getTime(float time) {
		return this.time;
	}

	public void move(MyStage stage, int cameraInitialX) {
		this.setX(this.initialX + stage.getCamera().position.x - cameraInitialX);
	}

}
