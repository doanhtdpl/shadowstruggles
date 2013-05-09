package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SerializationException;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;

/**
 * This class manages the interpretation of a profile from the DataManager
 */

public class ProfileDAO {

	public static Profile getProfile(int id, DataManager manager) {
		Profile profile = null;

		Array<Profile> profiles = manager.retrieveProfiles();
		for (Profile p : profiles) {
			if (p.getId() == id)
				profile = p;
		}

		return profile;
	}

	public static Array<Profile> getProfiles(DataManager manager)
			throws SerializationException {
		Array<Profile> orderedProfiles = manager.retrieveProfiles();
		orderedProfiles.sort();
		return orderedProfiles;
	}

	public static void createProfile(Profile profile, DataManager manager)
			throws SerializationException {
		manager.writeProfile(profile);
	}
}
