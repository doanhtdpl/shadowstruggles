package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class RpgPlatform {
	private TiledMap map;
	private Character character;
	
	public RpgPlatform(RpgController controller, String stageName, Character character) {
		controller.setModel(this);
		this.map=new TmxMapLoader(new InternalFileHandleResolver()).load("data/images/maps/example/map.tmx");
		this.character=character;	
		
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	

}
