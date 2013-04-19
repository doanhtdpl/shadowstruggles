package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.Map;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.TutorialEnemy;
import br.edu.ifsp.pds.shadowstruggles.object2d.FixedImage;
import br.edu.ifsp.pds.shadowstruggles.object2d.FixedLabel;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class BTutorial extends BattleScreen{
	private FixedImage dialogBox;
	private FixedLabel dialogText;

	public BTutorial(ShadowStruggles game) {
		super(game, game.getProfile(), game.getController(),
				new BattlePlatform(DeckDAO.getDeck("Tutorial Deck",
						game.getManager()), DeckDAO.getDeck(
						"Practice Deck Enemy", game.getManager()), new Map(
						"cena1"), new DefaultRules(game.getManager()
						.getSettings()), new TutorialEnemy(game.getController())), game
						.getManager().getMenuText().practiceBattle, false);
		dialogBox = new FixedImage(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"), 150, this){@Override
				public void clicked() {
					// TODO Auto-generated method stub
					super.clicked();
				}};
		dialogBox= (FixedImage)  ScreenUtils.defineImage(dialogBox, 150, 370, 650, 200);
		dialogText= new FixedLabel("Seja bem vindo ao tutorial! Toque para prosseguir", 170, this);
		dialogText = (FixedLabel) ScreenUtils.defineLabel(dialogText, 170, 400, 600, 200);
		dialogText.setWrap(true);
		stage.addActor(dialogBox);
		stage.addActor(dialogText);
		fixedImages.add(dialogBox);
		
	}
	
	@Override
	public void moveFixedObjects() {
		
		super.moveFixedObjects();
		dialogText.move(stage, CAMERA_INITIAL_X);
	}
	
	

}
