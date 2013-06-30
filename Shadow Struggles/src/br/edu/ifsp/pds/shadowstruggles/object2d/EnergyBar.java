package br.edu.ifsp.pds.shadowstruggles.object2d;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/***
 * A visual representation of the player energy.
 */
public class EnergyBar extends FixedObject {

	private float percentage;
	private Label energy;
	private ShadowStruggles game;

	public EnergyBar(int initialX, ShadowStruggles game) {
		super(game.getTextureRegion("energy100", "game_ui_images"), initialX);
		this.percentage = 0.45f;
		this.game = game;
	}

	/**
	 * Changes the image according to the percentage.
	 */
	public void update() {
		this.energy.setX(this.getX() + 30);
		float h1 = percentage * 128;
		this.setHeight(h1);
		int h2 = (int) h1;

		// TODO: Não instanciar novas texturas; obter TextureRegion.
		this.setDrawable(new TextureRegionDrawable(new TextureRegion(game
				.getAssets().get("data/images/game_ui_images/energy100.png",
						Texture.class), 0, 128 - h2, 128, h2)));
	}

	public void initEnergy(int currentEnergy, int maxEnergy, Skin skin) {
		String energyString = String.valueOf(currentEnergy) + "/"
				+ String.valueOf(maxEnergy);
		this.energy = new Label(energyString, skin);
		this.energy.setX(this.getX() + 30);
		this.energy.setY(this.getY() + 50);
		this.getStage().addActor(energy);
	}

	public void drawEnergy(int currentEnergy, int maxEnergy) {
		String lifeString = String.valueOf(currentEnergy) + "/"
				+ String.valueOf(maxEnergy);
		this.energy.setText(lifeString);
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

}
