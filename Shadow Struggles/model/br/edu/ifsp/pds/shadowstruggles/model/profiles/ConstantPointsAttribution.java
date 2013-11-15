package br.edu.ifsp.pds.shadowstruggles.model.profiles;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * For this implementation, the correspondence between a distribution point and
 * the amount to increase in attributes is always the same.
 */
public class ConstantPointsAttribution implements AttributePointsFormula {
	private ObjectMap<PlayerField, Float> constantAmountsMap;

	public ConstantPointsAttribution() {
		this.constantAmountsMap = new ObjectMap<AttributePointsFormula.PlayerField, Float>();
		constantAmountsMap.put(PlayerField.DECK_CAPACITY, 1f);
		constantAmountsMap.put(PlayerField.DOUBLE_DRAW, 0.05f);
		constantAmountsMap.put(PlayerField.ENERGY_RECOVERY, 5f);
		constantAmountsMap.put(PlayerField.MAX_CARD_POINTS, 5f);
		constantAmountsMap.put(PlayerField.MAX_ENERGY, 5f);
		constantAmountsMap.put(PlayerField.MAX_HEALTH, 5f);
	}

	public ConstantPointsAttribution(
			ObjectMap<PlayerField, Float> constantAmountsMap) {
		this.constantAmountsMap = constantAmountsMap;
	}

	@Override
	public void attributePoint(Profile profile, PlayerField field) {
		float increase = 0f;
		
		if (field == PlayerField.DECK_CAPACITY) {
			increase = constantAmountsMap.get(PlayerField.DECK_CAPACITY);
			profile.getPlayer().setDeckCapacity(
					(int) (profile.getPlayer().getDeckCapacity() + increase));
		} else if (field == PlayerField.DOUBLE_DRAW) {
			increase = constantAmountsMap.get(PlayerField.DOUBLE_DRAW);
			profile.getPlayer().setDoubleDraw(
					profile.getPlayer().getDoubleDraw() + increase);
		} else if (field == PlayerField.ENERGY_RECOVERY) {
			increase = constantAmountsMap.get(PlayerField.ENERGY_RECOVERY);
			profile.getPlayer().setEnergyRecovery(
					(int) (profile.getPlayer().getEnergyRecovery() + increase));
		} else if (field == PlayerField.MAX_CARD_POINTS) {
			increase = constantAmountsMap.get(PlayerField.MAX_CARD_POINTS);
			profile.getPlayer().setMaxCardPoints(
					(int) (profile.getPlayer().getMaxCardPoints() + increase));
		} else if (field == PlayerField.MAX_ENERGY) {
			increase = constantAmountsMap.get(PlayerField.MAX_ENERGY);
			profile.getPlayer().setMaxEnergy(
					(int) (profile.getPlayer().getMaxEnergy() + increase));
		} else if (field == PlayerField.MAX_HEALTH) {
			increase = constantAmountsMap.get(PlayerField.MAX_HEALTH);
			profile.getPlayer().setMaxHealth(
					(int) (profile.getPlayer().getMaxHealth() + increase));
		}
	}

}
