package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class RpgPlatform {
	private TiledMap map;
	private Character character;
	public RpgPlatform(String stageName, Character character) {
		
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
