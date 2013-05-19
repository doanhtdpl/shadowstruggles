package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.util.HashMap;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Pack;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Ending;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;

/**
 * Provides an easy access to the directory structure by mapping classes and
 * resources to their respective files and directories, without considering the
 * language.
 */
public class FileMap {

	public static HashMap<Class<?>, String> classToFile;
	public static HashMap<String, String> resourcesToDirectory;

	public static void initMap() {
		classToFile = new HashMap<Class<?>, String>();
		resourcesToDirectory = new HashMap<String, String>();
		
		resourcesToDirectory.put("sound_effects", "audio/sound_effects/");
		resourcesToDirectory.put("soundtrack", "audio/soundtrack/");
		
		classToFile.put(BattlePlatform.class, "files/battles.json");
		classToFile.put(Card.class, "files/cards.json");
		classToFile.put(Deck.class, "files/decks.json");
		classToFile.put(Ending.class, "files/endings.json");
		classToFile.put(Enemy.class, "files/enemies.json");
		classToFile.put(Event.class, "files/events.json");
		classToFile.put(Item.class, "files/items.json");
		classToFile.put(MenuText.class, "files/menu_text.json");
		classToFile.put(Pack.class, "files/packs.json");
		classToFile.put(Profile.class, "files/profiles.json");
		classToFile.put(Quest.class, "files/quests.json");
		classToFile.put(Scene.class, "files/scenes.json");
		classToFile.put(Shop.class, "files/shops.json");
		
		resourcesToDirectory.put("battle_maps", "images/battle_maps/");
		resourcesToDirectory.put("card_attacking", "images/card_attacking/");
		resourcesToDirectory.put("card_effects", "images/card_effects/");
		resourcesToDirectory.put("card_images", "images/card_images/");
		resourcesToDirectory.put("card_walking", "images/card_walking/");
		resourcesToDirectory.put("game_ui_images", "images/game_ui_images/");
		resourcesToDirectory.put("novel_images", "images/novel_images/");
		resourcesToDirectory.put("rpg_maps", "images/rpg_maps/");
		resourcesToDirectory.put("skin", "images/skin/");

		classToFile.put(Languages.class, "languages.json");
	}
}
