package br.edu.ifsp.pds.shadowstruggles.modelTest;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.data.dao.ProfileDAO;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;

import com.badlogic.gdx.utils.Array;

public class ProfileTest {
	public static void testProfileReading(){
		Array<Profile> profiles = ProfileDAO.getProfiles();
		if(profiles.size>0){			
			for(Profile profile:profiles){
				System.out.println("Nome do profile: "+profile.getId());
				System.out.println("Level: ");
				System.out.println(profile.getLevel());
				
			}
		}else{
			System.out.println("Não existe nenhum profile gravado!");
		}
	}
	
	public static void testUpdateProfile(){
		Profile profile = ProfileDAO.getProfile(5);
		System.out.println("Profile 5 before:");
		System.out.println("ID: "+profile.getId());
		System.out.println("Level: "+profile.getLevel());
		Profile temp = new Profile(5);
		temp.setLevel(8000);
		ProfileDAO.updateProfile(5, temp);
		DataManager.getInstance().save();
		profile = ProfileDAO.getProfile(5);
		System.out.println("Profile 5 after:");
		System.out.println("ID: "+profile.getId());
		System.out.println("Level: "+profile.getLevel());
		
			
		
	}
}
