package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.MenuText;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Modifies quantitative attributes such as maximum health and energy, either
 * adding/subtracting or replacing their values.
 */
public class AmountModifier extends Modifier {
	public static enum ModifiedAttribute {
		MAX_ENERGY, INITIAL_ENERGY, MAX_HEALTH, MAX_CARD_POINTS, STORY_POINTS, MONEY, DOUBLE_DRAW, LEVEL, EXPERIENCE, ENERGY_RECOVERY, DECK_CAPACITY
	};

	private float amount;
	private boolean replace;
	private ModifiedAttribute attribute;

	private boolean leveledUp;

	public AmountModifier() {
		this.amount = 0;
		this.attribute = null;
	}

	public AmountModifier(float amount, boolean replace,
			ModifiedAttribute attribute) {
		this.amount = amount;
		this.replace = replace;
		this.attribute = attribute;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.amount = json.readValue("amount", Float.class, jsonData);
		this.replace = json.readValue("replace", Boolean.class, jsonData);
		this.attribute = json.readValue("attribute", ModifiedAttribute.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("amount", this.amount);
		json.writeValue("replace", this.replace);
		json.writeValue("attribute", this.attribute);
	}

	@Override
	public void modify() {
		Profile profile = ShadowStruggles.getInstance().getProfile();

		if (attribute == ModifiedAttribute.MAX_ENERGY) {
			if (replace)
				profile.getPlayer().setMaxEnergy((int) amount);
			else
				profile.getPlayer().setMaxEnergy(
						profile.getPlayer().getMaxEnergy() + (int) amount);
		}

		if (attribute == ModifiedAttribute.INITIAL_ENERGY) {
			if (replace)
				profile.getPlayer().setInitialEnergy((int) amount);
			else
				profile.getPlayer().setInitialEnergy(
						profile.getPlayer().getInitialEnergy() + (int) amount);
		}

		if (attribute == ModifiedAttribute.MAX_HEALTH) {
			if (replace)
				profile.getPlayer().setMaxHealth((int) amount);
			else
				profile.getPlayer().setMaxHealth(
						profile.getPlayer().getMaxHealth() + (int) amount);
		}

		if (attribute == ModifiedAttribute.MAX_CARD_POINTS) {
			if (replace)
				profile.getPlayer().setMaxCardPoints((int) amount);
			else
				profile.getPlayer().setMaxCardPoints(
						profile.getPlayer().getMaxCardPoints() + (int) amount);
		}

		if (attribute == ModifiedAttribute.STORY_POINTS) {
			if (replace)
				profile.setStoryPoints((int) amount);
			else
				profile.setStoryPoints(profile.getStoryPoints() + (int) amount);
		}

		if (attribute == ModifiedAttribute.MONEY) {
			if (replace)
				profile.setMoney((int) amount);
			else
				profile.setMoney(profile.getMoney() + (int) amount);
		}

		if (attribute == ModifiedAttribute.DOUBLE_DRAW) {
			if (replace)
				profile.getPlayer().setDoubleDraw(amount);
			else
				profile.getPlayer().setDoubleDraw(
						profile.getPlayer().getDoubleDraw() + amount);
		}

		if (attribute == ModifiedAttribute.LEVEL) {
			if (replace)
				profile.setLevel((int) amount);
			else
				profile.setLevel(profile.getLevel() + (int) amount);
		}

		if (attribute == ModifiedAttribute.EXPERIENCE) {
			if (replace)
				leveledUp = profile.setExperience((int) amount);
			else
				leveledUp = profile.setExperience(profile.getExperience()
						+ (int) amount);
		}

		if (attribute == ModifiedAttribute.ENERGY_RECOVERY) {
			if (replace)
				profile.getPlayer().setEnergyRecovery((int) amount);
			else
				profile.getPlayer().setEnergyRecovery(
						profile.getPlayer().getEnergyRecovery() + (int) amount);
		}

		if (attribute == ModifiedAttribute.DECK_CAPACITY) {
			if (replace)
				profile.getPlayer().setDeckCapacity((int) amount);
			else
				profile.getPlayer().setDeckCapacity(
						profile.getPlayer().getDeckCapacity() + (int) amount);
		}
	}

	@Override
	public String getMessage() {
		MenuText text = MenuTextDAO.getMenuText();
		Profile profile = ShadowStruggles.getInstance().getProfile();

		if (attribute == ModifiedAttribute.MAX_ENERGY) {
			return text.newMaxEnergy.replace("%d",
					Integer.toString(profile.getPlayer().getMaxEnergy()));
		}

		if (attribute == ModifiedAttribute.INITIAL_ENERGY) {
			return text.newInitialEnergy.replace("%d",
					Integer.toString(profile.getPlayer().getInitialEnergy()));
		}

		if (attribute == ModifiedAttribute.MAX_HEALTH) {
			return text.newMaxHealth.replace("%d",
					Integer.toString(profile.getPlayer().getMaxHealth()));
		}

		if (attribute == ModifiedAttribute.MAX_CARD_POINTS) {
			return text.newMaxCardPoints.replace("%d",
					Integer.toString(profile.getPlayer().getMaxCardPoints()));
		}

		if (attribute == ModifiedAttribute.STORY_POINTS)
			return null;

		if (attribute == ModifiedAttribute.MONEY) {
			return text.newMoney.replace("%d",
					Integer.toString(profile.getMoney()));
		}

		if (attribute == ModifiedAttribute.DOUBLE_DRAW) {
			return text.newDoubleDraw.replace("%f",
					Float.toString(profile.getPlayer().getDoubleDraw() * 100));
		}

		if (attribute == ModifiedAttribute.LEVEL) {
			return text.newLevel.replace("%d",
					Integer.toString(profile.getLevel()));
		}

		if (attribute == ModifiedAttribute.EXPERIENCE) {
			String msg = text.newExperience.replace("%d",
					Integer.toString(profile.getExperience()));
			if (leveledUp)
				msg += " "
						+ text.newLevel.replace("%d",
								Integer.toString(profile.getLevel()));
			return msg;
		}

		if (attribute == ModifiedAttribute.ENERGY_RECOVERY) {
			return text.newEnergyRecovery.replace("%d",
					Integer.toString(profile.getPlayer().getEnergyRecovery()));
		}

		if (attribute == ModifiedAttribute.DECK_CAPACITY) {
			return text.newDeckCapacity.replace("%d",
					Integer.toString(profile.getPlayer().getDeckCapacity()));
		}

		return null;
	}
}
