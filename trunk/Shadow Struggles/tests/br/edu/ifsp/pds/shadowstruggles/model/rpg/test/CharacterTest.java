package br.edu.ifsp.pds.shadowstruggles.model.rpg.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;

import com.badlogic.gdx.math.MathUtils;

public class CharacterTest {

	@Test
	public void WalkUpTest() {		
		int initialTileX=MathUtils.random(50);
		int initialTileY=MathUtils.random(30);
		Character character = new Character(new Profile(), initialTileX, initialTileY);		
		RpgPlatform platform = new RpgPlatform(new RpgMap(), character);
		platform.getCharacter().walk(WalkDirection.WALK_UP);
		boolean walkedUp=false;
		if(platform.getCharacter().getTileY()==initialTileY+1 && 
				!platform.getMap().getTile(initialTileX, initialTileY+1).hasCharacter().equals(null))
			walkedUp=true;
		assertEquals(true, walkedUp);
	}

}
