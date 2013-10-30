package br.edu.ifsp.pds.shadowstruggles.data.dao;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SerializationException;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

/**
 * This class manages the interpretation of a profile from the DataManager
 */

public class ProfileDAO {

	public static Profile getProfile(int id) {
		Profile profile = null;

		@SuppressWarnings("unchecked")
		Array<Profile> profiles = DataManager.getInstance().getObjectSet(
				Profile.class);
		if (!profiles.contains(new Profile(id), false))
			return null;

		for (Profile p : profiles) {
			if (p.getId() == id) {
				/**
				 * Retrieves the cards from their respective DAOs.
				 */
				Array<Item> itemsCopy = new Array<Item>();

				for (Item item : p.getUnlockedItems()) {
					if (item instanceof Fighter) {
						item = FighterDAO.getFighter(item.getName());
						((Card) item).setAction(FighterDAO
								.getFighter(item.getName()).getAction().copy());
					} else if (item instanceof Effect) {
						item = EffectDAO.getEffect(item.getName());
						((Card) item).setAction(EffectDAO
								.getEffect(item.getName()).getAction().copy());
					} else if (item instanceof Trap) {
						item = TrapDAO.getTrap(item.getName());
						((Card) item).setAction(TrapDAO.getTrap(item.getName())
								.getAction().copy());
					}
					itemsCopy.add(item);
				}
				p.setUnlockedItems(itemsCopy);
				profile = p;
			}
		}

		return profile;
	}

	public static Array<Profile> getProfiles() throws SerializationException {
		@SuppressWarnings("unchecked")
		Array<Profile> orderedProfiles = DataManager.getInstance()
				.getObjectSet(Profile.class);
		return orderedProfiles;
	}

	public static void createProfile(Profile profile) {
		DataManager.getInstance().writeProfile(profile);
	}

	/**
	 * Indicates whether or not there is at least one profile.
	 */
	@SuppressWarnings("unchecked")
	public static boolean profileExists() {
		Array<Profile> profiles = null;

		try {
			profiles = DataManager.getInstance().getObjectSet(Profile.class);
			return profiles.size > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Updates the profile on DataManager's record set for objects. Changes are
	 * not persistent.
	 */
	@SuppressWarnings("unchecked")
	public static void updateProfile(int id, Profile profile) {
		Profile oldProfile = getProfile(id);
		Array<Profile> allProfiles = DataManager.getInstance().getObjectSet(
				Profile.class);
		allProfiles.set(allProfiles.indexOf(oldProfile, true), profile);
	}
}
