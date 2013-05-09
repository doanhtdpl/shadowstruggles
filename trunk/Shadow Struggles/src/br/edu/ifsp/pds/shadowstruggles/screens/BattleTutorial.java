package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
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
	private static enum EventType {
		NONE, CARD_SELECTED, CARD_SUMMONED, PREPARE_EVENT
	};

	/**
	 * An event is a specific sort of input (e.g, select a card, summon a card)
	 * which must be given by the user in order to advance in the tutorial.
	 */
	private class TutorialEvent {
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

			if (this.eventType == EventType.NONE)
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

	private Array<String> text;
	private Array<TutorialEvent> events;
	private int currentIndex;
	private int currentEventIndex;

	private Array<Integer> image1X;
	private Array<Integer> image1Y;
	private Array<Boolean> imageVisible;

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
				.findRegion("indicator"), image1X.get(currentIndex), this);
		stage.addActor(image);

		nextDialog();
	}

	@Override
	public void doBeforeSet() {
		controller.getPlatform().setPlayerDeck(
				DeckDAO.getDeck("Tutorial Deck", game.getManager()));
	}

	private void loadData() {
		text = new Array<String>();
		events = new Array<TutorialEvent>();
		image1X = new Array<Integer>();
		image1Y = new Array<Integer>();
		imageVisible = new Array<Boolean>();

		// TODO incluir em arquivo json
		text.add("Hora de aprender a batalhar! (Toque para prosseguir com a aula)");
		events.add(new TutorialEvent());
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Este é o campo de batalha, onde a ação acontece! Percorra-o para reconhecer o território");
		events.add(new TutorialEvent());
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Seu objetivo é destruir a base inimiga, enquanto defende sua própria base");
		events.add(new TutorialEvent());
		image1X.add(25);
		image1Y.add(330);
		imageVisible.add(true);
		text.add("Fique sempre atento à vida da sua base. Perdeu a base, perdeu a batalha!");
		events.add(new TutorialEvent());
		image1X.add(10);
		image1Y.add(470);
		imageVisible.add(true);
		text.add("Vamos ao ataque! Aqui fica o seu deck, onde voce deverá sacar as cartas sempre que possível");
		events.add(new TutorialEvent());
		image1X.add(840);
		image1Y.add(25);
		imageVisible.add(true);
		text.add("Logo ao lado estão as cartas que você sacou. (Selecione a carta DR-002)");
		events.add(new TutorialEvent(this, EventType.CARD_SELECTED, "DR-002"));
		image1X.add(210);
		image1Y.add(35);
		imageVisible.add(true);
		text.add("Para invocá-la, mande-a em um dos circulos de transmutação");
		events.add(new TutorialEvent(this, EventType.CARD_SUMMONED, "DR-002"));
		image1X.add(270);
		image1Y.add(260);
		imageVisible.add(true);
		text.add("Cada carta terá um gasto de energia ao ser invocada. Sem energia, sem invocação!");
		events.add(new TutorialEvent());
		image1X.add(60);
		image1Y.add(15);
		imageVisible.add(true);
		text.add("Uma vez invocado, o lutador irá ao ataque da base inimiga");
		events.add(new TutorialEvent(this, EventType.PREPARE_EVENT, "DR-002"));
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Lembre-se de sacar uma carta do deck! ");
		events.add(new TutorialEvent());
		image1X.add(840);
		image1Y.add(25);
		imageVisible.add(true);
		text.add("Agora, vamos às cartas de efeito! (Invoque Mineralogia em um dos circulos)");
		events.add(new TutorialEvent(this, EventType.CARD_SUMMONED, "Mineralogy"));
		image1X.add(335);
		image1Y.add(30);
		imageVisible.add(true);
		text.add("Essa carta te permite utilizar o poder da terra. É um requisito para invocar algumas cartas.");
		events.add(new TutorialEvent(this, EventType.PREPARE_EVENT, "DR-002"));
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Agora você pode invocar a Rocha para te proteger! (Invoque a Rocha em um dos circulos)");
		events.add(new TutorialEvent(this, EventType.CARD_SUMMONED, "Rock"));
		image1X.add(600);
		image1Y.add(30);
		imageVisible.add(true);
		text.add("A rocha irá proteger o caminho temporariamente contra o ataque inimigo");
		events.add(new TutorialEvent());
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Que tal deixar um presentinho pro lutador inimigo? Armadilhas são um ótimo meio de surpreender o inimigo." );
		events.add(new TutorialEvent(this, EventType.PREPARE_EVENT, "DR-002"));
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Experimente invocar a armadilha indicada abaixo!");
		events.add(new TutorialEvent(this, EventType.CARD_SUMMONED,"Electric_Current_Level_One"));
		image1X.add(470);
		image1Y.add(30);
		imageVisible.add(true);
		text.add("A corrente elétrica será ativada assim que um lutador inimigo pisar sobre a carta");
		events.add(new TutorialEvent());
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);
		text.add("Você pode acessar o menu para verificar informações detalhadas das suas cartas");
		events.add(new TutorialEvent());
		image1X.add(-20);
		image1Y.add(520);
		imageVisible.add(true);
		text.add("Seja rápido, se o tempo acabar, vencerá aquele que estiver com a base com maior vida");
		events.add(new TutorialEvent());
		image1X.add(450);
		image1Y.add(540);
		imageVisible.add(true);
		text.add("Agora, destrua a base inimiga!");
		events.add(new TutorialEvent());
		image1X.add(10);
		image1Y.add(10);
		imageVisible.add(false);

	}

	private void boxClicked() {
		// Because the event index is updated before clicking the box, this
		// method actually requires the previous event to be completed, not the
		// current one; hence the currentEventIndex - 1.
		TutorialEvent event = events.get(currentEventIndex - 1);
		if (event.eventCompleted() && (event.getEventType() == EventType.NONE || event.getEventType() == EventType.PREPARE_EVENT))
			if (currentIndex < text.size)
				nextDialog();
	}

	private void nextDialog() {
		dialogText.setText(text.get(currentIndex));

		image.setX(image1X.get(currentIndex));
		image.setInitialX(image1X.get(currentIndex));
		image.setY(image1Y.get(currentIndex));
		if (imageVisible.get(currentIndex)
				&& !stage.getActors().contains(image, true)) {
			stage.addActor(image);
		}

		if (!imageVisible.get(currentIndex)
				&& stage.getActors().contains(image, false)) {
			stage.removeActor(image);
		}

		currentIndex++;
		currentEventIndex++;
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// Updates the box as soon as the user completes some kinds of events,
		// such as summoning cards.
		TutorialEvent event = events.get(currentEventIndex - 1);
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
