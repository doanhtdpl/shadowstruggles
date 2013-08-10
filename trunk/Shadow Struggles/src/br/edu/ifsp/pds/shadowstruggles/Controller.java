package br.edu.ifsp.pds.shadowstruggles;

import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.object2d.BackCard;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.Fighter2D;
import br.edu.ifsp.pds.shadowstruggles.object2d.HandCard;
import br.edu.ifsp.pds.shadowstruggles.object2d.Trap2D;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.BattleScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.InGameMenu;
import br.edu.ifsp.pds.shadowstruggles.screens.MyStage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * Acts as a link between the visual elements and the logic ones. The visual
 * layer is a listener for the Controller, calling one of its methods when a
 * triggering event (such as input from the player) happens.
 */
public class Controller {
	private BaseScreen currentScreen;
	private BattlePlatform platform;
	private static Controller instance;

	public static Controller getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new Controller();
			return instance;
		}
	}

	private Controller() {

	}

	// ---------------------------------------------EVENT
	// HANDLE----------------------------------------------------

	public void mapClicked(float x, float y) {
		int lane = (int) ((y - 48) / 72);
		int tile = ((int) ((x - 96) / 48) / 2) * 2;

		if (platform.getSelectedCard() != null) {

			Card handCard = platform.getSelectedCard();
			int playerEnergy = platform.getRules().getPlayerEnergy();

			// Verifica energia suficiente
			if (playerEnergy - handCard.getEnergyCost() >= 0
					&& handCard.readyToSummom(platform)) {

				// Verifica se foi clicado no pentagrama
				if (x >= 96 && tile <= 9 && y >= 48 && lane <= 3) {
					// Verifica se clicou em uma backCard
					if (platform.getPlayerField().getTiles().get(tile / 2)
							.get(lane) == null) {
						hexagramClicked(lane, tile);
					} else
						backCardClicked(lane / 2, tile);
				} else {
					returnHandCard();
				}
			} else {
				returnHandCard();
			}

		}
	}

	public void backCardClicked(int lane, int tile) {
		Card handCard = platform.getSelectedCard();
		if (handCard.getClass().equals(Effect.class)
				&& ((Effect) handCard).isOnFighter()) {
			activateEffect(handCard, lane, tile);
			platform.setSelectedCard(null);
			((BattleScreen) currentScreen).changeHexagram(false);
		} else
			returnHandCard();
	}

	public void hexagramClicked(int lane, int tile) {
		BattleScreen battleScreen = (BattleScreen) currentScreen;
		Card handCard = platform.getSelectedCard();
		this.playerEnergyChanged(-handCard.getEnergyCost());
		battleScreen.changeHexagram(false);
		if (handCard.getClass().equals(Fighter.class)) {
			summonFighter(handCard, lane, tile);

		} else if (handCard.getClass().equals(Effect.class)
				&& !((Effect) handCard).isOnFighter()) {
			activateEffect(handCard, lane, tile);
		} else if (handCard.getClass().equals(Trap.class)) {
			putTrap(handCard, lane, tile);
		}
		platform.setSelectedCard(null);
		battleScreen.removeResumedCardInfo();
	}

	public void handCardClicked(Card handCard, boolean isSelected) {
		if (isSelected) {
			for (Card card : platform.getPlayerHandCards()) {
				if (card.getImage().getClass().equals(HandCard.class)) {
					((HandCard) (card.getImage())).resetBlink();
					if (!card.equals(handCard))
						((HandCard) (card.getImage())).setSelected(false);
				}
			}
			platform.setSelectedCard(handCard);
		} else {
			for (Card card : platform.getPlayerHandCards()) {
				if (card.getImage().getClass().equals(HandCard.class)) {
					((HandCard) (card.getImage())).resetBlink();
				}
			}
			platform.setSelectedCard(null);
		}
		((BattleScreen) currentScreen).changeHexagram(isSelected);
		try {
			((BattleScreen) currentScreen).showResumedCardInfo();
		} catch (Exception execaoNaoIdentificada) {
		}

	}

	public void deckClicked() {
		BattleScreen battleScreen = (BattleScreen) currentScreen;
		if (battleScreen.getTimeDelay() <= 0) {
			if (platform.getPlayerDeck().getCards().size > 0
					&& platform.getPlayerHandCards().size < 5) {
				Card card = platform.getPlayerDeck().draw();

				((BattleScreen) currentScreen).insertHandCard(card);
				platform.addPlayerHandCard(card);
				battleScreen
						.setTimeDelay((float) SettingsDAO.getSettings().drawInterval);
				battleScreen.getDeck().setReady(false);
				battleScreen.getDeck().stopBlink();
			}
		}
	}

	public void menuButtonClicked(ShadowStruggles game) {
		InGameMenu menu = InGameMenu.getInstance(game, game.getController(),
				null);
		menu.setBattleScreen((BattleScreen) game.getScreen());
		game.setScreenWithTransition(menu);
	}

	/***
	 * Updates the player life to the BattlePlatform and LifeBar objects.
	 * 
	 * @param amount
	 *            If negative, it means the player has taken the amount of
	 *            damage. If positive, it means the player was healed by the
	 *            specified amount.
	 */

	public void playerLifeChanged(int amount) {
		int newLife = this.platform.getRules().getPlayerHP() + amount;
		int maxLife = this.platform.getRules().getPlayerHPmax();
		this.platform.getRules().setPlayerHP(
				verifyValueChange(newLife, maxLife));
		try {

			((BattleScreen) this.currentScreen).getPlayerLife().setPercentage(
					this.platform.getRules().getPlayerHpPercent());
		} catch (Exception ex) {
		}

	}

	/***
	 * Updates the enemy life to the BattlePlatform and LifeBar objects.
	 * 
	 * @param amount
	 *            If negative, it means the enemy has taken the amount of
	 *            damage. If positive, it means the enemy was healed by the
	 *            specified amount.
	 */

	public void enemyLifeChanged(int amount) {
		int newLife = this.platform.getRules().getEnemyHP() + amount;
		int maxLife = this.platform.getRules().getEnemyHPmax();

		this.platform.getRules()
				.setEnemyHP(verifyValueChange(newLife, maxLife));
		((BattleScreen) this.currentScreen).getEnemyLife().setPercentage(
				this.platform.getRules().getEnemyHpPercent());
	}

	public void playerEnergyChanged(int amount) {
		int newEnergy = this.platform.getRules().getPlayerEnergy() + amount;
		int maxEnergy = this.platform.getRules().getPlayerEnergyMax();
		this.platform.getRules().setPlayerEnergy(
				verifyValueChange(newEnergy, maxEnergy));
		((BattleScreen) this.currentScreen).getEnergyBar().setPercentage(
				this.platform.getRules().getPlayerEnergyPercent());
	}

	public void enemyEnergyChanged(int amount) {
		int newEnergy = this.platform.getRules().getEnemyEnergy() + amount;
		int maxEnergy = this.platform.getRules().getPlayerEnergyMax();
		this.platform.getRules().setEnemyEnergy(
				verifyValueChange(newEnergy, maxEnergy));
	}

	public void tileChanged(Fighter card) {
		this.platform.getMap().getTiles().get(card.getTile())
				.get(card.getLane()).removeValue(card, true);
		this.platform.getMap().addCard(card,
				card.getTile() + card.getDirection(), (card.getLane()));
	}

	// ----------------------------------------------ACTION
	// METHODS---------------------------------------------------

	public void playCard(Card handCard, int lane, int tile) {
		handCard.setLane(lane);
		handCard.setTile(tile);
		((BattleScreen) currentScreen).getBackcards()
				.get((handCard.getTile() / 2) * 4 + handCard.getLane())
				.setVisible(true);
		handCard.setMarkPosition(lane, tile);
		platform.getPlayerField().getTiles().get(handCard.getTile() / 2)
				.set(handCard.getLane(), handCard);
		float initialX = 0;
		if ((handCard.getImage()).getClass().equals(HandCard.class))
			initialX = ((HandCard) handCard.getImage()).getInitialX();

		if (handCard.getClass().equals(Fighter.class)) {
			Fighter2D f2d = new Fighter2D((Fighter) handCard,
					currentScreen.getGame());
			f2d.create();
			addCardToMap(handCard, f2d, tile, lane);
		} else if (handCard.getClass().equals(Effect.class)) {
			Effect2D e2d = (Effect2D) new Effect2D((Effect) handCard,
					this.currentScreen.getGame());
			e2d.create();
			addCardToMap(handCard, e2d, tile, lane);
		} else if (handCard.getClass().equals(Trap.class)) {
			Trap2D t2d = new Trap2D((Trap) handCard, currentScreen.getGame());
			t2d.create();
			addCardToMap(handCard, t2d, tile, lane);
		}
		rearrangeCards(initialX);
	}

	public void addCardToMap(Card handCard, Image cardImage, int tile, int lane) {
		platform.getMap().addCard(handCard, tile, lane);
		currentScreen.addGameObject(cardImage);
		platform.getPlayerHandCards().removeValue(platform.getSelectedCard(),
				true);
		platform.setSelectedCard(null);

		handCard.getImage().remove();
		((MyStage) currentScreen.getStage()).getCurrentActors().removeValue(
				handCard.getImage(), true);
		handCard.setImage(cardImage);
		if (handCard.getClass().equals(Effect.class)) {
			Effect2D e2d = (Effect2D) cardImage;
			e2d.getEffect().action(platform, cardImage, 0);
		}
		currentScreen.getGame().getAudio().playSound("button_7");
	}

	private void activateEffect(Card handCard, int lane, int tile) {
		playCard(handCard, lane, tile);
	}

	public void summonFighter(Card handCard, int lane, int tile) {
		playCard(handCard, lane, tile);
	}

	private void putTrap(Card handCard, int lane, int tile) {
		playCard(handCard, lane, tile);
	}

	private void returnHandCard() {
		for (Actor actor : currentScreen.getStage().getActors())
			if (actor.getClass().equals(HandCard.class))
				if (((HandCard) actor).getImageX() != ((HandCard) actor)
						.getInitialX()
						|| ((HandCard) actor).getImageY() != SettingsDAO
								.getSettings().bottomElementY)
					((HandCard) actor).resetPosition();
	}

	private void rearrangeCards(float initialX) {
		for (Actor actor : currentScreen.getStage().getActors()) {
			if (actor.getClass().equals(HandCard.class)) {
				if (((HandCard) actor).getInitialX() > initialX) {
					actor.addAction(Actions.moveTo(actor.getX() - 130,
							actor.getY(), 0.1f));
					((HandCard) actor).setInitialX(((HandCard) actor)
							.getInitialX() - 130);
				}
			}
		}
	}

	public void updateTimer(float delta) {
		this.platform.getRules().update(delta);

		float time = this.platform.getRules().getTimer();
		((BattleScreen) this.currentScreen).getTimer().setTime(time);
	}

	public void removeCard(Card c) {
		this.platform.getMap().getTiles().get(c.getTile()).get(c.getLane())
				.removeValue(c, true);
		(c.getImage()).remove();
		((MyStage) currentScreen.getStage()).getCurrentActors().removeValue(
				c.getImage(), true);

		for (int i = 0; i < platform.getPlayerField().getTiles().size; i++) {
			for (int j = 0; j < platform.getPlayerField().getTiles().get(i).size; j++) {
				if (platform.getPlayerField().getTiles().get(i).get(j) != null)
					if (platform.getPlayerField().getTiles().get(i).get(j)
							.equals(c)) {
						platform.getPlayerField().getTiles().get(i)
								.set(j, null);
						((BattleScreen) currentScreen).getBackcards()
								.get(4 * i + j).setVisible(false);
					}
			}
		}

	}

	// -----------------------------------------------------------------CALC
	// METHODS---------------------------------------

	@SuppressWarnings("unused")
	private int nextAvailableSlot(int lane) {
		int next = 4;
		for (BackCard backCard : ((BattleScreen) currentScreen).getBackcards()) {
			if (backCard.isVisible()
					&& ((int) ((backCard.getImageY() - 48) / 72)) - 2 == lane) {
				next--;
			}
		}
		return next;
	}

	public int verifyValueChange(int newValue, int maxValue) {
		/*
		 * If new value is in normal range, apply changes without correction. If
		 * new value is less than zero, set new value to zero. If new value is
		 * greater than maximum value, set new value to maximum value.
		 */
		if (newValue >= 0 && newValue <= maxValue) {
			return newValue;
		} else if (newValue < 0) {
			return 0;
		} else if (newValue > maxValue) {
			return maxValue;
		}
		return 0;
	}

	// ----------------------------------------------------GETTERS/SETTERS---------------------------------------------------

	public Card getCardFromImage(Image cardImage) {

		return platform.getSelectedCard();
	}

	public BaseScreen getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentscreen(BaseScreen currentScreen) {
		this.currentScreen = currentScreen;
	}

	public BattlePlatform getPlatform() {
		return platform;
	}

	public void setPlatform(BattlePlatform platform) {
		this.platform = platform;
	}

}
