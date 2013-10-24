package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;

public class RpgMenu extends BaseScreen{
	private Label stats;
	private Label inventory;
	private Label saveGame;
	private Label Settings;
	private Label decks;
	

	public RpgMenu(ShadowStruggles game) {
		super(game);
		initComponents();
	}
	
	@Override
	public void initComponents() {
		stats = new Label("stats",super.getSkin());
		stage.addActor(stats);
	}

}
