package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.Profile;

import com.badlogic.gdx.utils.Array;

public class ProfileDAOTest {

	@Test
	public void getProfilesTest() {
		Array<Profile> profiles = ProfileDAO.getProfiles(new DataManager());
		
		if(profiles.get(0).getId() == 1)
			System.out.println("getProfilesTest: Success");
		else
			System.out.println("getProfilesTest: Fail");
	}

}
