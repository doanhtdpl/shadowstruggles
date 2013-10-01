package br.edu.ifsp.pds.shadowstruggles.model.modifiers;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Marks items as unlocked for the current profile.
 */
public class UnlockModifier extends Modifier {
	private Array<Item> items;

	public UnlockModifier() {
		this.items = new Array<Item>();
	}

	public UnlockModifier(Array<Item> items) {
		this.items = items;
	}

	@Override
	public void modify() {
		for (Item item : items) {
			Profile profile = ShadowStruggles.getInstance().getProfile();
			if (!profile.getUnlockedItems().contains(item, false))
				profile.getUnlockedItems().add(item);
		}
	}

	@Override
	public String getMessage() {
		String message = MenuTextDAO.getMenuText().unlocked + " ";

		for (int i = 0; i < items.size; i++) {
			Item item = items.get(i);
			message += item.getLocalizedName();
			if (i < items.size - 2) // More than one item left.
				message += ", ";
			if (i == items.size - 2) // One item left.
				message += " and ";
			if (i == items.size - 1) // This is the final item.
				message += "!";
		}

		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.items = json.readValue("items", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("items", this.items);
	}
}
