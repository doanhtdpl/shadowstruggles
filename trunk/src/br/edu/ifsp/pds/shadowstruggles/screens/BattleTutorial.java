package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class BattleTutorial extends BattleScreen{
	private FixedImage dialogBox;
	private FixedLabel dialogText;
	private FixedImage image;
	private Array<String> text;	
	private Array<Integer> image1X;
	private Array<Integer> image1Y;
	private int currentIndex;
	private Array<Boolean> imageVisible;

	public BattleTutorial(ShadowStruggles game) {
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
					System.out.println("Clicou");
					boxCLicked();
				}};
		dialogBox= (FixedImage)  ScreenUtils.defineImage(dialogBox, 150, 370, 650, 200);
		dialogText= new FixedLabel("", 170, this);
		dialogText = (FixedLabel) ScreenUtils.defineLabel(dialogText, 170, 400, 600, 200);
		dialogText.setWrap(true);
		inputSources.removeProcessor(map2d);
		inputSources.addProcessor(dialogBox);
		
		stage.addActor(dialogBox);
		stage.addActor(dialogText);
		fixedImages.add(dialogBox);
		loadData();
		// TODO: Usar TextureRegion carregada pelo AssetsManager, sem instanciar novas Textures ou TextureRegion.
		image = new FixedImage(new TextureRegion(new Texture(Gdx.files.internal("data/images/objects/indicator.png"))), image1X.get(currentIndex), this);
		stage.addActor(image);
		nextDialog();
	}
	
	@Override
	public void doBeforeSet() {
		controller.getPlatform().setPlayerDeck(DeckDAO.getDeck("Tutorial Deck", game.getManager()));
	}
	
	private void loadData() {
		text = new Array<String>();
		image1X= new Array<Integer>();
		image1Y= new Array<Integer>();
		imageVisible= new Array<Boolean>();
		// TODO incluir em arquivo json
		text.add("Hora de aprender a batalhar! Toque para prosseguir com a aula");
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
        text.add("Este é o campo de batalha, onde a ação acontece! Percorra-o para reconhecer o território");
        image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
        text.add("Seu objetivo é destruir a base inimiga, enquanto defende sua própria base");
        image1X.add(25);
		image1Y.add(330);
		imageVisible.add(true);
        text.add("Fique sempre atento à vida da sua base. Perdeu a base, perdeu a batalha!");
        image1X.add(15);
		image1Y.add(490);
		imageVisible.add(true);
        text.add("Vamos ao ataque! Aqui fica o seu deck, onde voce deverá sacar as cartas sempre que possível");
        image1X.add(840);
		image1Y.add(25);
		imageVisible.add(true);
        text.add("Logo ao lado estão as cartas que você sacou. Selecione a carta DR-002");
        image1X.add(215);
		image1Y.add(40);
		imageVisible.add(true);
        text.add("Para invocá-la, mande-a em um dos circulos de transmutação");
        image1X.add(260);
		image1Y.add(320);
		imageVisible.add(true);
        text.add("Cada carta terá um gasto de energia ao ser invocada. Sem energia, sem invocação!");
        image1X.add(60);
		image1Y.add(15);
		imageVisible.add(true);
        text.add("Uma vez invocado, o lutador irá ao ataque da base inimiga");
        image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
        text.add("Lembre-se de sacar uma carta do deck! Agora, vamos às magias! Invoque Mineralogia em um dos circulos");
        image1X.add(470);
		image1Y.add(30);
		imageVisible.add(true);
        text.add("Essa carta te permite invocar o poder da terra! Invoque uma Rocha para te proteger!");
        image1X.add(600);
		image1Y.add(40);
		imageVisible.add(true);
        text.add("A rocha irá proteger o caminho temporariamente contra o ataque inimigo");
        image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
        text.add("Que tal deixar um presentinho pro lutador inimigo? Experimente invocar uma armadilha");
        image1X.add(730);
		image1Y.add(50);
		imageVisible.add(true);
        text.add("A corrente elétrica será ativada assim que um lutador inimigo pisar sobre a carta");
        image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
        text.add("Você pode acessar o menu para verificar informações detalhadas das suas cartas");
        image1X.add(0);
		image1Y.add(550);
		imageVisible.add(true);
        text.add("Seja rápido, se o tempo acabar, vencerá aquele que estiver com a base com maior vida");
        image1X.add(470);
		image1Y.add(570);
		imageVisible.add(true);
        text.add("Agora, destrua a base inimiga!");
        image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
        
        

	}
	
	private void boxCLicked()
	{
		if(currentIndex<text.size)
		nextDialog();
	}	
	
	private void nextDialog(){
		
		dialogText.setText(text.get(currentIndex));
		image.setX(image1X.get(currentIndex));
		image.setInitialX(image1X.get(currentIndex));		
		image.setY(image1Y.get(currentIndex));		
		if(imageVisible.get(currentIndex) && !stage.getActors().contains(image, true)){
			stage.addActor(image);
		}
		System.out.println(stage.getActors().contains(image, true));
		if (!imageVisible.get(currentIndex) && stage.getActors().contains(image, false)){			
			stage.removeActor(image);
		}
		currentIndex++;
	}
	@Override
	public void moveFixedObjects() {
		
		super.moveFixedObjects();
		dialogText.move(stage, CAMERA_INITIAL_X);
	}
	
	

}


