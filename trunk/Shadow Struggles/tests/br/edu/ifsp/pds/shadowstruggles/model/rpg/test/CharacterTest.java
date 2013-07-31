package br.edu.ifsp.pds.shadowstruggles.model.rpg.test;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgPlatform;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character.WalkDirection;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;

public class CharacterTest {

	@Test
	public void WalkUpTest() {
		int initialTileX = MathUtils.random(29);
		int initialTileY = MathUtils.random(19);
		Character character = new Character(initialTileX, initialTileY);
		character
				.setCurrentMap(new RpgMap(new TmxMapLoader(
						new InternalFileHandleResolver())
						.load("data/rpg_maps/map.tmx")));
		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				character.getCurrentMap());
		platform.getCharacter().walk(WalkDirection.WALK_UP,
				platform.getRpgMap());
		boolean walkedUp = false;
		if (platform.getCharacter().getTileY() == initialTileY - 1)
			walkedUp = true;
		if (walkedUp)
			System.out.println("WalkUpTest: Success!");
		else
			System.out.println("WalkUpTest: Fail!");
	}

	@Test
	public void WalkDownTest() {
		int initialTileX = MathUtils.random(29);
		int initialTileY = MathUtils.random(19);
		Character character = new Character(initialTileX, initialTileY);
		character
				.setCurrentMap(new RpgMap(new TmxMapLoader(
						new InternalFileHandleResolver())
						.load("data/rpg_maps/map.tmx")));
		RpgPlatform platform = new RpgPlatform(new RpgController(), character);
		platform.getCharacter().walk(WalkDirection.WALK_DOWN,
				platform.getRpgMap());
		boolean walkedDown = false;
		if (platform.getCharacter().getTileY() == initialTileY + 1)
			walkedDown = true;
		if (walkedDown)
			System.out.println("WalkDownTest: Success!");
		else
			System.out.println("WalkDownTest: Fail!");
	}

	@Test
	public void WalkLeftTest() {
		int initialTileX = MathUtils.random(29);
		int initialTileY = MathUtils.random(19);
		Character character = new Character(initialTileX, initialTileY);
		character
				.setCurrentMap(new RpgMap(new TmxMapLoader(
						new InternalFileHandleResolver())
						.load("data/rpg_maps/map.tmx")));
		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				character.getCurrentMap());
		platform.getCharacter().walk(WalkDirection.WALK_LEFT,
				platform.getRpgMap());
		boolean walkedLeft = false;
		if (platform.getCharacter().getTileX() == initialTileX - 1)
			walkedLeft = true;
		if (walkedLeft)
			System.out.println("WalkLeftTest: Success!");
		else
			System.out.println("WalkLeftTest: Fail!");
	}

	@Test
	public void WalkRightTest() {
		int initialTileX = MathUtils.random(29);
		int initialTileY = MathUtils.random(19);
		Character character = new Character(initialTileX, initialTileY);
		character
				.setCurrentMap(new RpgMap(new TmxMapLoader(
						new InternalFileHandleResolver())
						.load("data/rpg_maps/map.tmx")));
		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				character.getCurrentMap());
		platform.getCharacter().walk(WalkDirection.WALK_RIGHT,
				platform.getRpgMap());
		boolean walkedRight = false;
		if (platform.getCharacter().getTileX() == initialTileX + 1)
			walkedRight = true;
		if (walkedRight)
			System.out.println("WalkRightTest: Success!");
		else
			System.out.println("WalkRightTest: Fail!");
	}

}
