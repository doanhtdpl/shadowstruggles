package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/***
 * A visual representation of the player/enemy current life.
 */

public class LifeBar extends FixedObject {

	/***
	 * The percentage of the remaining life.
	 */
	private float percentage = 1.0f;

	/***
	 * The relative path of the image used for the life bar. It depends on the
	 * specified player.
	 */

	private Label life;

	public LifeBar(int initialX, ShadowStruggles game) {
		super(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("life100"), initialX);
		this.setScaleX(0.8f);
		this.setScaleY( 0.8f);

	}

	public void update(int currentLife, int maxLife) {
		percentage = (float) ((float) currentLife / (float) maxLife);
		this.setScaleX( 0.8f * percentage);
	}

	public void drawLife(int currentLife, int maxLife, Skin skin) {
		String lifeString = String.valueOf(currentLife) + "/"
				+ String.valueOf(maxLife);
		this.life = new Label(lifeString, skin);
		this.life.setX(this.getX() + 20);
		this.life.setY(this.getY());
		this.life.setStyle(new LabelStyle(skin
				.getFont("basic-font"), Color.WHITE));
		this.getStage().addActor(life);
	}

	public void drawLife(int currentLife, int maxLife) {
		String lifeString = String.valueOf(currentLife) + "/"
				+ String.valueOf(maxLife);
		this.life.setText(lifeString);
	}

	@Override
	public void move(Stage st, int cameraInitialX) {
		super.move(st, cameraInitialX);
		this.life.setX( this.getX() + 20);
		this.life.setY(this.getY());
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public float getPercentage() {
		return percentage;
	}

}
