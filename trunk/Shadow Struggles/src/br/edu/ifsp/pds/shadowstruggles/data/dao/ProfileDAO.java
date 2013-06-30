package br.edu.ifsp.pds.shadowstruggles.data.dao;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SerializationException;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;

/**
 * This class manages the interpretation of a profile from the DataManager
 */

public class ProfileDAO {

	public static Profile getProfile(int id) {
		Profile profile = null;

		@SuppressWarnings("unchecked")
		Array<Profile> profiles = DataManager.getInstance().getObjectSet(
				Profile.class);
		for (Profile p : profiles) {
			if (p.getId() == id)
				profile = p;
		}

		return profile;
	}

	public static Array<Profile> getProfiles() throws SerializationException {
		@SuppressWarnings("unchecked")
		Array<Profile> orderedProfiles = DataManager.getInstance()
				.getObjectSet(Profile.class);
		orderedProfiles.sort();
		return orderedProfiles;
	}

	public static void createProfile(Profile profile)
			throws SerializationException {
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
}
