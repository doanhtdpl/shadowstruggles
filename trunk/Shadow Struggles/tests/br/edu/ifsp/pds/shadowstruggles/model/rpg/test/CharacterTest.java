package br.edu.ifsp.pds.shadowstruggles.model.rpg.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.Profile;
import br.edu.ifsp.pds.shadowstruggles.rpg.model.Character;
import br.edu.ifsp.pds.shadowstruggles.rpg.model.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.rpg.model.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.rpg.model.Character.WalkDirection;

import com.badlogic.gdx.math.MathUtils;

public class CharacterTest {

	@Test
	public void WalkUpTest() {		
		int initialTileX=MathUtils.random(49);
		int initialTileY=MathUtils.random(29);
		Character character = new Character(new Profile(), initialTileX, initialTileY);		
		RpgPlatform platform = new RpgPlatform(new RpgMap(50,30), character);
		platform.getCharacter().walk(WalkDirection.WALK_UP, platform.getMap());
		boolean walkedUp=false;
		if(platform.getCharacter().getTileY()==initialTileY+1 && 
				!platform.getMap().getTile(initialTileX, initialTileY+1).hasCharacter().equals(null))
			walkedUp=true;
		assertEquals(true, walkedUp);
	}
	@Test
	public void WalkDownTest() {		
		int initialTileX=MathUtils.random(49);
		int initialTileY=MathUtils.random(29);
		Character character = new Character(new Profile(), initialTileX, initialTileY);		
		RpgPlatform platform = new RpgPlatform(new RpgMap(50,30), character);
		platform.getCharacter().walk(WalkDirection.WALK_DOWN, platform.getMap());
		boolean walkedDown=false;
		if(platform.getCharacter().getTileY()==initialTileY-1 && 
				!platform.getMap().getTile(initialTileX, initialTileY-1).hasCharacter().equals(null))
			walkedDown=true;
		assertEquals(true, walkedDown);
	}
	@Test
	public void WalkLeftTest() {		
		int initialTileX=MathUtils.random(49);
		int initialTileY=MathUtils.random(29);
		Character character = new Character(new Profile(), initialTileX, initialTileY);		
		RpgPlatform platform = new RpgPlatform(new RpgMap(50,30), character);
		platform.getCharacter().walk(WalkDirection.WALK_LEFT, platform.getMap());
		boolean walkedLeft=false;
		if(platform.getCharacter().getTileX()==initialTileX-1 && 
				!platform.getMap().getTile(initialTileX-1, initialTileY).hasCharacter().equals(null))
			walkedLeft=true;
		assertEquals(true, walkedLeft);
	}
	@Test
	public void WalkRightTest() {		
		int initialTileX=MathUtils.random(49);
		int initialTileY=MathUtils.random(29);
		Character character = new Character(new Profile(), initialTileX, initialTileY);		
		RpgPlatform platform = new RpgPlatform(new RpgMap(50,30), character);
		platform.getCharacter().walk(WalkDirection.WALK_RIGHT, platform.getMap());
		boolean walkedRight=false;
		if(platform.getCharacter().getTileX()==initialTileX+1 && 
				!platform.getMap().getTile(initialTileX+1, initialTileY).hasCharacter().equals(null))
			walkedRight=true;
		assertEquals(true, walkedRight);
	}

}
