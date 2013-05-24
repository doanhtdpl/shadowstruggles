package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.data.TutorialDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.TutorialDialog;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.TutorialEnemy;
import br.edu.ifsp.pds.shadowstruggles.object2d.FixedImage;
import br.edu.ifsp.pds.shadowstruggles.object2d.FixedLabel;
import br.edu.ifsp.pds.shadowstruggles.object2d.HandCard;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class BattleTutorial extends BattleScreen {

	/**
	 * Possible types of events. NONE - Touching the tutorial text box, without
	 * special requirements; CARD_SELECTED - Select a card; CARD_SUMMONED -
	 * Summon a card.
	 */
	public static enum EventType {
		NONE, CARD_SELECTED, CARD_SUMMONED, PREPARE_EVENT
	};

	/**
	 * An event is a specific sort of input (e.g, select a card, summon a card)
	 * which must be given by the user in order to advance in the tutorial.
	 */
	public class TutorialEvent {
		private BattleTutorial tutorial;
		private EventType eventType;

		/**
		 * A string used to designate the target card (to be summoned, for
		 * example), if applicable to the event.
		 */
		private String target;

		public TutorialEvent() {
			this(null, EventType.NONE, null);
		}

		public TutorialEvent(BattleTutorial tutorial, EventType eventType,
				String target) {
			this.tutorial = tutorial;
			this.eventType = eventType;
			this.target = target;
		}

		public boolean eventCompleted() {
			boolean completed = false;

			if (this.eventType == EventType.NONE || this.eventType ==null)
				completed = true;

			if (this.eventType == EventType.CARD_SELECTED) {
				Array<InputProcessor> handCards = tutorial.getInputSources()
						.getProcessors();
				for (InputProcessor handCard : handCards) {
					if (handCard.getClass().equals(HandCard.class)) {
						HandCard castedHandCard = (HandCard) handCard;
						if (castedHandCard.getCard().getName().equals(target)
								&& castedHandCard.isSelected()){
							completed = true;
							inputSources.removeProcessor(dialogBox);
							inputSources.addProcessor(map2d);
						}
						
					}
				}
			}
			if(this.eventType==EventType.PREPARE_EVENT){				
				inputSources.addProcessor(map2d);
				completed=true;
			}
			if (this.eventType == EventType.CARD_SUMMONED){
				Card card = new Card();
				card.setName(target);
				if(controller.getPlatform().getMap().cardOnMap(card, -1, 1)){
					completed=true;
					inputSources.removeProcessor(map2d);
					if(!inputSources.getProcessors().contains(dialogBox, true))
					inputSources.addProcessor(dialogBox);
				}
			}
			
			return completed;
		}

		public EventType getEventType() {
			return this.eventType;
		}
	}

	private FixedImage dialogBox;
	private FixedLabel dialogText;
	private FixedImage image;

	
	private Array<TutorialEvent> events;
	private int currentIndex;
	private int currentEventIndex;
	
	private Array<TutorialDialog> dialogs;

	public BattleTutorial(ShadowStruggles game) {
		super(game, game.getProfile(), game.getController(),
				new BattlePlatform(DeckDAO.getDeck("Tutorial Deck",
						game.getManager()), DeckDAO.getDeck(
						"Practice Deck Enemy", game.getManager()), new BattleMap(
						"cena1"), new DefaultRules(game.getManager()
						.getSettings()), new TutorialEnemy()), game
						.getManager().getMenuText().practiceBattle, false);

		dialogBox = new FixedImage(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"), 150, this) {
			@Override
			public void clicked() {
				boxClicked();
			}
		};
		dialogBox = (FixedImage) ScreenUtils.defineImage(dialogBox, 150, 370,
				650, 200);
		dialogText = new FixedLabel("", 170, this);
		dialogText = (FixedLabel) ScreenUtils.defineLabel(dialogText, 170, 400,
				600, 200);
		dialogText.setWrap(true);

		inputSources.removeProcessor(map2d);
		inputSources.addProcessor(dialogBox);

		stage.addActor(dialogBox);
		stage.addActor(dialogText);
		fixedImages.add(dialogBox);

		loadData();

		image = new FixedImage(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("indicator"), dialogs.get(currentIndex).getIndicatorX(), this);
		stage.addActor(image);

		nextDialog();
	}

	@Override
	public void doBeforeSet() {
		controller.getPlatform().setPlayerDeck(
				DeckDAO.getDeck("Tutorial Deck", game.getManager()));
	}

	private void loadData() {
		dialogs= TutorialDAO.getDialogs(game.getManager());
		events = new Array<TutorialEvent>();
		for(TutorialDialog dialog : dialogs){
			events.add(new TutorialEvent(this, dialog.getEventType(), dialog.getEventTarget()));
		}
		
		
		
		

		/*// TODO incluir em arquivo json
		TutorialDialog temp = new TutorialDialog();
		temp.setText("Hora de aprender a batalhar! (Toque para prosseguir com a aula)");
		temp.setIndicatorX(10);
		temp.setIndicatorY(10);
		temp.setIndicatorVisible(false);		
		dialogs.add(temp);
		//events.add(new TutorialEvent());		
		//TutorialDAO.writeDialog(temp, game.getManager());
		temp = new TutorialDialog();
		temp.setText("Este é o campo de batalha, onde a ação acontece! Percorra-o para reconhecer o território");
		temp.setIndicatorX(10);
		temp.setIndicatorY(10);
		temp.setIndicatorVisible(false);
		dialogs.add(temp);		
		//events.add(new TutorialEvent());		
		//TutorialDAO.writeDialog(temp, game.getManager());
		temp = new TutorialDialog();
		temp.setText("Seu objetivo é destruir a base inimiga, enquanto defende sua própria base");
		temp.setIndicatorX(25);
		temp.setIndicatorY(330);
		temp.setIndicatorVisible(true);
		dialogs.add(temp);		
		//events.add(new TutorialEvent());				
		TutorialDAO.writeDialog(temp, game.getManager());
		temp = new TutorialDialog();
		temp.setText("Fique sempre atento à vida da sua base. Perdeu a base, perdeu a batalha!");
		temp.setIndicatorX(10);
		temp.setIndicatorY(470);
		temp.setIndicatorVisible(true);
		dialogs.add(temp);		
		//events.add(new TutorialEvent());		
		//TutorialDAO.writeDialog(temp, game.getManager());
		temp = new TutorialDialog();
		temp.setText("Vamos ao ataque! Aqui fica o seu deck, onde voce deverá sacar as cartas sempre que possível");
		temp.setIndicatorX(840);
		temp.setIndicatorY(25);
		temp.setIndicatorVisible(true);
		dialogs.add(temp);	
		//events.add(new TutorialEvent());		
		//TutorialDAO.writeDialog(temp, game.getManager());
		temp = new TutorialDialog();
		temp.setText("Logo ao lado estão as cartas que você sacou. (Selecione a carta DR-002)");
		temp.setIndicatorX(210);
		temp.setIndicatorY(35);
		temp.setIndicatorVisible(true);
		temp.setEventType(EventType.CARD_SELECTED);
		temp.setEventTarget("DR-002");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.CARD_SELECTED, "DR-002"));		
		//TutorialDAO.writeDialog(temp, game.getManager());
		temp = new TutorialDialog();
		temp.setText("Para invocá-la, mande-a em um dos circulos de transmutação");
		temp.setIndicatorX(270);
		temp.setIndicatorY(260);
		temp.setIndicatorVisible(true);
		temp.setEventType(EventType.CARD_SUMMONED);
		temp.setEventTarget("DR-002");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.CARD_SUMMONED, "DR-002"));
	//	TutorialDAO.writeDialog(temp, game.getManager());
		
		temp = new TutorialDialog();
		temp.setText("Cada carta terá um gasto de energia ao ser invocada. Sem energia, sem invocação!");
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(true);
		dialogs.add(temp);	
		//events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Uma vez invocado, o lutador irá ao ataque da base inimiga");
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(false);
		temp.setEventType(EventType.PREPARE_EVENT);
		temp.setEventTarget("DR-002");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.PREPARE_EVENT, "DR-002"));
	//	TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Lembre-se de sacar uma carta do deck! ");
		temp.setIndicatorX(840);
		temp.setIndicatorY(25);
		temp.setIndicatorVisible(true);
		dialogs.add(temp);	
		//events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Agora, vamos às cartas de efeito! (Invoque Mineralogia em um dos circulos)");
		temp.setIndicatorX(335);
		temp.setIndicatorY(30);
		temp.setIndicatorVisible(true);
		temp.setEventType(EventType.CARD_SUMMONED);
		temp.setEventTarget("Mineralogy");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.CARD_SUMMONED, "Mineralogy"));
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Essa carta te permite utilizar o poder da terra. É um requisito para invocar algumas cartas.");
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(false);
		temp.setEventType(EventType.PREPARE_EVENT);
		temp.setEventTarget("DR-002");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.PREPARE_EVENT, "DR-002"));
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Agora você pode invocar a Rocha para te proteger! (Invoque a Rocha em um dos circulos)");
		temp.setIndicatorX(600);
		temp.setIndicatorY(30);
		temp.setIndicatorVisible(true);
		temp.setEventType(EventType.CARD_SUMMONED);
		temp.setEventTarget("Rock");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.CARD_SUMMONED, "Rock"));
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("A rocha irá proteger o caminho temporariamente contra o ataque inimigo");
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(false);
		dialogs.add(temp);	
	///	events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Que tal deixar um presentinho pro lutador inimigo? Armadilhas são um ótimo meio de surpreender o inimigo." );
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(false);
		temp.setEventType(EventType.PREPARE_EVENT);
		temp.setEventTarget("DR-002");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.PREPARE_EVENT, "DR-002"));
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Experimente invocar a armadilha indicada abaixo!");
		temp.setIndicatorX(470);
		temp.setIndicatorY(30);
		temp.setIndicatorVisible(true);
		temp.setEventType(EventType.CARD_SUMMONED);
		temp.setEventTarget("Electric_Current_Level_One");
		dialogs.add(temp);	
		//events.add(new TutorialEvent(this, EventType.CARD_SUMMONED,"Electric_Current_Level_One"));
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("A corrente elétrica será ativada assim que um lutador inimigo pisar sobre a carta");
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(false);
		dialogs.add(temp);	
		//events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Você pode acessar o menu para verificar informações detalhadas das suas cartas");
		temp.setIndicatorX(-20);
		temp.setIndicatorY(520);
		temp.setIndicatorVisible(true);
		dialogs.add(temp);	
		//events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Seja rápido, se o tempo acabar, vencerá aquele que estiver com a base com maior vida");
		temp.setIndicatorX(450);
		temp.setIndicatorY(540);
		temp.setIndicatorVisible(true);
		//dialogs.add(temp);	
		//events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
		
		temp.setText("Agora, destrua a base inimiga!");
		temp.setIndicatorX(60);
		temp.setIndicatorY(15);
		temp.setIndicatorVisible(false);
		//dialogs.add(temp);	
		//events.add(new TutorialEvent());
		//TutorialDAO.writeDialog(temp, game.getManager());
*/	}

	private void boxClicked() {
		// Because the event index is updated before clicking the box, this
		// method actually requires the previous event to be completed, not the
		// current one; hence the currentEventIndex - 1.
		TutorialEvent event = events.get(currentEventIndex - 1);
		if (event.eventCompleted() && (event.getEventType() == EventType.NONE || event.getEventType() == EventType.PREPARE_EVENT|| event.getEventType() == null))
			if (currentIndex < dialogs.size)
				nextDialog();
	}

	private void nextDialog() {
		dialogText.setText(dialogs.get(currentIndex).getText());

		image.setX(dialogs.get(currentIndex).getIndicatorX());
		image.setInitialX(dialogs.get(currentIndex).getIndicatorX());
		image.setY(dialogs.get(currentIndex).getIndicatorY());
		if (dialogs.get(currentIndex).isIndicatorVisible()
				&& !stage.getActors().contains(image, true)) {
			stage.addActor(image);
		}

		if (!dialogs.get(currentIndex).isIndicatorVisible()
				&& stage.getActors().contains(image, false)) {
			stage.removeActor(image);
		}

		currentIndex++;
		currentEventIndex++;
		System.out.println(currentIndex);
		System.out.println(dialogs.size);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// Updates the box as soon as the user completes some kinds of events,
		// such as summoning cards.
		TutorialEvent event = events.get(currentIndex-1);
		if (event.getEventType() == EventType.CARD_SELECTED
				|| event.getEventType() == EventType.CARD_SUMMONED) {
			if (event.eventCompleted())
				this.nextDialog();
		}
	}

	@Override
	public void moveFixedObjects() {
		super.moveFixedObjects();
		dialogText.move(stage, CAMERA_INITIAL_X);
	}

}
