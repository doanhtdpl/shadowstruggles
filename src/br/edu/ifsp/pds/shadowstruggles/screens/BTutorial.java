package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

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
	private FixedImage image;
	private Array<String> text;	
	private Array<Integer> image1X;
	private Array<Integer> image1Y;
	private int currentIndex;

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
					boxCLicked();
				}};
		dialogBox= (FixedImage)  ScreenUtils.defineImage(dialogBox, 150, 370, 650, 200);
		dialogText= new FixedLabel("", 170, this);
		dialogText = (FixedLabel) ScreenUtils.defineLabel(dialogText, 170, 400, 600, 200);
		dialogText.setWrap(true);
		stage.addActor(dialogBox);
		stage.addActor(dialogText);
		fixedImages.add(dialogBox);
		loadData();
		image = new FixedImage(null, image1X.get(currentIndex), this);
	}
	
	private void loadData() {
		text = new Array<String>();
		// TODO incluir em arquivo json
		text.add("Hora de aprender a batalhar! Toque para prosseguir com a aula");
        text.add("Este é o campo de batalha, onde a ação acontece! Percorra-o para reconhecer o território");
        text.add("Seu objetivo é destruir a base inimiga, enquanto defende sua própria base");
        text.add("Fique sempre atento à vida da sua base. Perdeu a base, perdeu a batalha!");
        text.add("Vamos ao ataque! Aqui fica o seu deck, onde voce deverá sacar as cartas sempre que possível");
        text.add("Logo ao lado estão as cartas que você sacou. Selecione a carta DR-002");
        text.add("Para invocá-la, mande-a em um dos circulos de transmutação");
        text.add("Cada carta terá um gasto de energia ao ser invocada. Sem energia, sem invocação!");
        text.add("Uma vez invocado, o lutador irá ao ataque da base inimiga");
        text.add("Lembre-se de sacar uma carta do deck! Agora, vamos às magias! Invoque Mineralogia em um dos circulos");
        text.add("Essa carta te permite invocar o poder da terra! Invoque uma Rocha para te proteger!");
        text.add("A rocha irá proteger o caminho temporariamente contra o ataque inimigo");
        text.add("Que tal deixar um presentinho pro lutador inimigo? Experimente invocar uma armadilha");
        text.add("A corrente elétrica será ativada assim que um lutador inimigo pisar sobre a carta");
        text.add("Você pode acessar o menu para verificar informações detalhadas das suas cartas");
        text.add("Seja rápido, se o tempo acabar, vencerá aquele que estiver com a base com maior vida");
        text.add("Agora, destrua a base inimiga! Invoque suas cartas com sabedoria. Acredite no coração das cartas");

	}
	
	private void boxCLicked()
	{
		
	}	
	
	private void nextDialog(){
		currentIndex++;
		dialogText.setText(text.get(currentIndex));
		image.setInitialX(image1X.get(currentIndex));
		image.setY(image1Y.get(currentIndex));
	}
	@Override
	public void moveFixedObjects() {
		
		super.moveFixedObjects();
		dialogText.move(stage, CAMERA_INITIAL_X);
	}
	
	

}


