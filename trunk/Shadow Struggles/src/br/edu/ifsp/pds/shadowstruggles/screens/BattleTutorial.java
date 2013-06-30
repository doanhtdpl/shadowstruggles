package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.DeckDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.data.dao.TutorialDAO;
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

			if (this.eventType == EventType.NONE || this.eventType == null)
				completed = true;

			if (this.eventType == EventType.CARD_SELECTED) {
				Array<InputProcessor> handCards = tutorial.getInputSources()
						.getProcessors();
				for (InputProcessor handCard : handCards) {
					if (handCard.getClass().equals(HandCard.class)) {
						HandCard castedHandCard = (HandCard) handCard;
						if (castedHandCard.getCard().getName().equals(target)
								&& castedHandCard.isSelected()) {
							completed = true;
							inputSources.removeProcessor(dialogBox);
							inputSources.addProcessor(map2d);
						}

					}
				}
			}
			if (this.eventType == EventType.PREPARE_EVENT) {
				inputSources.addProcessor(map2d);
				completed = true;
			}
			if (this.eventType == EventType.CARD_SUMMONED) {
				Card card = new Card();
				card.setName(target);
				if (controller.getPlatform().getMap().cardOnMap(card, -1, 1)) {
					completed = true;
					inputSources.removeProcessor(map2d);
					if (!inputSources.getProcessors().contains(dialogBox, true))
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
		super(
				game,
				game.getProfile(),
				game.getController(),
				new BattlePlatform(DeckDAO.getDeck("Tutorial Deck"),
						DeckDAO.getDeck("Practice Deck Enemy"), new BattleMap(
								"cena1"), new DefaultRules(
								SettingsDAO.getSettings()), new TutorialEnemy()),
				MenuTextDAO.getMenuText().practiceBattle, false);

		dialogBox = new FixedImage(game.getTextureRegion("box",
				"game_ui_images"), 150, this) {
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

		image = new FixedImage(game.getTextureRegion("indicator",
				"game_ui_images"), dialogs.get(currentIndex).getIndicatorX(),
				this);
		stage.addActor(image);

		nextDialog();
	}

	@Override
	public void doBeforeSet() {
		controller.getPlatform()
				.setPlayerDeck(DeckDAO.getDeck("Tutorial Deck"));
	}

	private void loadData() {
		dialogs = TutorialDAO.getDialogs();
		events = new Array<TutorialEvent>();
		for (TutorialDialog dialog : dialogs) {
			events.add(new TutorialEvent(this, dialog.getEventType(), dialog
					.getEventTarget()));
		}
	}

	private void boxClicked() {
		// Because the event index is updated before clicking the box, this
		// method actually requires the previous event to be completed, not the
		// current one; hence the currentEventIndex - 1.
		TutorialEvent event = events.get(currentEventIndex - 1);
		if (event.eventCompleted()
				&& (event.getEventType() == EventType.NONE
						|| event.getEventType() == EventType.PREPARE_EVENT || event
						.getEventType() == null))
			if (currentIndex < dialogs.size) {

				nextDialog();
			}
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

	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// Updates the box as soon as the user completes some kinds of events,
		// such as summoning cards.
		TutorialEvent event = events.get(currentIndex - 1);
		if (event.getEventType() == EventType.CARD_SELECTED
				|| event.getEventType() == EventType.CARD_SUMMONED) {
			if (event.eventCompleted()) {

				this.nextDialog();
			}
		}
	}

	@Override
	public void moveFixedObjects() {
		super.moveFixedObjects();
		dialogText.move(stage, CAMERA_INITIAL_X);
	}

}
