package br.edu.ifsp.pds.shadowstruggles.data;

import java.util.HashMap;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.TutorialDialog;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.model.items.Pack;
import br.edu.ifsp.pds.shadowstruggles.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Ending;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.Scene;

/**
 * Provides an easy access to the directory structure by mapping classes and
 * resources to their respective files and directories, without considering the
 * languages.
 */
public class FileMap {

	public static HashMap<Class<?>, String> classToFile;
	public static HashMap<String, String> resourcesToDirectory;

	public static void initMap() {
		classToFile = new HashMap<Class<?>, String>();
		resourcesToDirectory = new HashMap<String, String>();
		
		resourcesToDirectory.put("sound_effects", "data/audio/sound_effects/");
		resourcesToDirectory.put("soundtrack", "data/audio/soundtrack/");
		
		classToFile.put(BattlePlatform.class, "data/files/battles.json");
		classToFile.put(Card.class, "data/files/cards.json");
		classToFile.put(Deck.class, "data/files/decks.json");
		classToFile.put(Ending.class, "data/files/endings.json");
		classToFile.put(Enemy.class, "data/files/enemies.json");
		classToFile.put(Event.class, "data/files/events.json");
		classToFile.put(Item.class, "data/files/items.json");
		classToFile.put(MenuText.class, "data/files/menu_text.json");
		classToFile.put(Pack.class, "data/files/packs.json");
		classToFile.put(Profile.class, "data/files/profiles.json");
		classToFile.put(Quest.class, "data/files/quests.json");
		classToFile.put(Scene.class, "data/files/scenes.json");
		classToFile.put(Shop.class, "data/files/shops.json");
		classToFile.put(TutorialDialog.class, "data/files/tutorial.json");
		
		resourcesToDirectory.put("battle_maps", "data/images/battle_maps/");
		resourcesToDirectory.put("char", "data/images/char/");
		resourcesToDirectory.put("card_attacking", "data/images/card_attacking/");
		resourcesToDirectory.put("card_effects", "data/images/card_effects/");
		resourcesToDirectory.put("card_images", "data/images/card_images/");
		resourcesToDirectory.put("card_walking", "data/images/card_walking/");
		resourcesToDirectory.put("cards", "data/images/cards/");
		resourcesToDirectory.put("game_ui_images", "data/images/game_ui_images/");
		resourcesToDirectory.put("novel_images", "data/images/novel_images/");
		resourcesToDirectory.put("rpg_maps", "data/images/rpg_maps/");
		resourcesToDirectory.put("skin", "data/images/skin/");

		classToFile.put(Languages.class, "data/languages.json");
	}
}
