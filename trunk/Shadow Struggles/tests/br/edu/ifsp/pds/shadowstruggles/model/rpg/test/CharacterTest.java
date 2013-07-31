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

// TODO: Usar outro mapa de teste, sem obstáculos, para não obstruir testes.
public class CharacterTest {

	@Test
	public void WalkUpTest() {
		RpgMap map = new RpgMap(new TmxMapLoader(
				new InternalFileHandleResolver()).load("data/rpg_maps/map.tmx"));
		int initialTileX = MathUtils.random(map.getWidthInTiles() - 1);
		int initialTileY = MathUtils.random(1, map.getHeightInTiles() - 1);
		Character character = new Character(initialTileX, initialTileY, 2, 2,
				map);

		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				map);
		platform.getCharacter().walk(WalkDirection.WALK_UP, map);

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
		RpgMap map = new RpgMap(new TmxMapLoader(
				new InternalFileHandleResolver()).load("data/rpg_maps/map.tmx"));
		int initialTileX = MathUtils.random(map.getWidthInTiles() - 1);
		int initialTileY = MathUtils.random(map.getHeightInTiles() - 2);
		Character character = new Character(initialTileX, initialTileY, 2, 2,
				map);

		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				map);
		platform.getCharacter().walk(WalkDirection.WALK_DOWN, map);

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
		RpgMap map = new RpgMap(new TmxMapLoader(
				new InternalFileHandleResolver()).load("data/rpg_maps/map.tmx"));
		int initialTileX = MathUtils.random(1, map.getWidthInTiles() - 1);
		int initialTileY = MathUtils.random(map.getHeightInTiles() - 1);
		Character character = new Character(initialTileX, initialTileY, 2, 2,
				map);

		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				map);
		platform.getCharacter().walk(WalkDirection.WALK_LEFT, map);

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
		RpgMap map = new RpgMap(new TmxMapLoader(
				new InternalFileHandleResolver()).load("data/rpg_maps/map.tmx"));
		int initialTileX = MathUtils.random(map.getWidthInTiles() - 2);
		int initialTileY = MathUtils.random(map.getHeightInTiles() - 1);
		Character character = new Character(initialTileX, initialTileY, 2, 2,
				map);

		RpgPlatform platform = new RpgPlatform(new RpgController(), character,
				map);
		platform.getCharacter().walk(WalkDirection.WALK_RIGHT, map);

		boolean walkedRight = false;
		if (platform.getCharacter().getTileX() == initialTileX + 1)
			walkedRight = true;
		if (walkedRight)
			System.out.println("WalkRightTest: Success!");
		else
			System.out.println("WalkRightTest: Fail!");
	}

}
